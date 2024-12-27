-- for views, triggers, functions
CREATE OR REPLACE VIEW Stock_Produit AS
SELECT 
    pr.Id_Production as Id_Production, 
    pr.date_production as date_production,
    SUM(pr.quantite) - COALESCE(SUM(vd.quantite), 0) AS quantite,
    pr.Id_Produit as Id_Produit
FROM 
    Production pr
LEFT JOIN 
    Vente_details vd 
ON 
    pr.Id_Production = vd.Id_Production
GROUP BY 
    vd.Id_Production;

CREATE OR REPLACE VIEW Stock_Ingredient as SELECT
    Pr.Id_Ingredient_Entree as Id_Ingredient_Entree,
    SUM(Pr.quantite) - COALESCE(SUM(Ent.quantite), 0) AS quantite,
    Pr.date_entree as date_entree,
    Pr.prix_unitaire as prix_unitaire,
    Pr.Id_Fournisseur as Id_Fournisseur,
    Pr.Id_Ingredient as Id_Ingredient,
    FROM Ingredient_Entree Ent
    LEFT JOIN 
    Production_Details Pr
    on Ent.Id_Ingredient_Entree = Pr.Id_Ingredient_Entree
    GROUP BY
    Pr.Id_Ingredient_Entree;
