-- for views, triggers, functions
CREATE
OR REPLACE VIEW Stock_Produit AS
SELECT
	pr.Id_Production as Id_Production,
	pr.date_production as date_production,
	pr.quantite - COALESCE(SUM(vd.quantite), 0) AS quantite,
	pr.Id_Produit_Format as Id_Produit_Format
FROM
	Production pr
	LEFT JOIN Vente_Facture_Details vd ON pr.Id_Production = vd.Id_Production
where
	vd.Id_Vente_Facture IN (
		SELECT
			vf.Id_Vente_Facture
		FROM
			Vente_Facture vf
			JOIN Vente v ON vf.Id_Vente = v.Id_Vente
		WHERE
			v.etat > 1
	)
GROUP BY
	pr.Id_Production
HAVING
	pr.quantite - COALESCE(SUM(vd.quantite), 0) > 0;

CREATE
OR REPLACE VIEW Stock_Ingredient as
SELECT
	Ent.Id_Ingredient_Entree as Id_Ingredient_Entree,
	Ent.quantite - COALESCE(SUM(Pr.quantite), 0) AS quantite,
	Ent.date_entree as date_entree,
	Ent.prix_unitaire as prix_unitaire,
	Ent.Id_Fournisseur as Id_Fournisseur,
	Ent.Id_Ingredient as Id_Ingredient
FROM
	Ingredient_Entree Ent
	LEFT JOIN Production_Details Pr on Ent.Id_Ingredient_Entree = Pr.Id_Ingredient_Entree
GROUP BY
	Ent.Id_Ingredient_Entree
HAVING
	Ent.quantite - COALESCE(SUM(Pr.quantite), 0) > 1;

-- Trigger for ingredient_entree to fetch prix_unitaire from ingredient_fournisseur
CREATE OR REPLACE FUNCTION update_prix_unitaire_ingredient_entree()
RETURNS TRIGGER AS $$
BEGIN
    SELECT prix_unitaire 
    INTO NEW.prix_unitaire
    FROM Ingredients_Fournisseurs
    WHERE Id_Fournisseur = NEW.Id_Fournisseur AND Id_Ingredient = NEW.Id_Ingredient;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_update_prix_unitaire_ingredient_entree
BEFORE INSERT OR UPDATE ON Ingredient_Entree
FOR EACH ROW
EXECUTE FUNCTION update_prix_unitaire_ingredient_entree();


-- Trigger for produit_format to calculate prix_unitaire
CREATE OR REPLACE FUNCTION update_prix_unitaire_produit_format()
RETURNS TRIGGER AS $$
BEGIN
    SELECT mult_prix 
    INTO NEW.prix_unitaire
    FROM Format
    WHERE Id_Format = NEW.Id_Format;

    NEW.prix_unitaire = NEW.prix_unitaire * 
                        (SELECT prix_unitaire FROM Produit WHERE Id_Produit = NEW.Id_Produit);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_update_prix_unitaire_produit_format
BEFORE INSERT OR UPDATE ON Produit_Format
FOR EACH ROW
EXECUTE FUNCTION update_prix_unitaire_produit_format();


-- Trigger for vente_facture to calculate prix_unitaire and montant
CREATE OR REPLACE FUNCTION update_vente_facture_values()
RETURNS TRIGGER AS $$
BEGIN
    SELECT prix_unitaire 
    INTO NEW.prix_unitaire
    FROM Produit_Format
    WHERE Id_Produit_Format = NEW.Id_Produit_Format;

    NEW.montant = NEW.quantite * NEW.prix_unitaire;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_update_vente_facture_values
BEFORE INSERT OR UPDATE ON Vente_Facture
FOR EACH ROW
EXECUTE FUNCTION update_vente_facture_values();


-- Trigger to update vente.montant based on vente_facture.montant
CREATE OR REPLACE FUNCTION update_vente_montant()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE Vente
    SET montant = (SELECT SUM(montant) FROM Vente_Facture WHERE Id_Vente = NEW.Id_Vente)
    WHERE Id_Vente = NEW.Id_Vente;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_update_vente_montant
AFTER INSERT OR UPDATE ON Vente_Facture
FOR EACH ROW
EXECUTE FUNCTION update_vente_montant();

CREATE OR REPLACE FUNCTION calculate_commission_vendeur()
RETURNS TRIGGER AS $$
BEGIN
    -- Calcul de la commission : 5% du montant
    NEW.commission_vendeur := NEW.montant * 0.05;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trg_calculate_commission
BEFORE INSERT OR UPDATE ON Vente
FOR EACH ROW
EXECUTE FUNCTION calculate_commission_vendeur();

create or replace view v_vendeur as
select * from employe where id_type_employe = 'VENDEUR';
