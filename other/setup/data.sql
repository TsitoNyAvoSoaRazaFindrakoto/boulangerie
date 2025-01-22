INSERT INTO Unite (Id_Unite, val) VALUES
('KG', 'Kilogramme'),
('G', 'Gramme'),
('L', 'Litre'),
('ML', 'Millilitre'),
('PC', 'Piece');

INSERT INTO Produit_Categorie (nom, description) VALUES
('Pains', 'Produits de boulangerie classiques tels que baguettes, pains complets, etc.'),
('Patisseries', 'Gateaux, tartes, et autres creations sucrees.'),
('Viennoiseries', 'Croissants, brioches, pains au chocolat, et autres produits feuilletes.');

INSERT INTO Ingredient (nom, Id_Unite) VALUES
('Farine de ble', 'KG'),
('Sucre', 'KG'),
('Sel', 'KG'),
('Levure boulangere', 'KG'),
('Beurre', 'KG'),
('Lait', 'L'),
('Oeufs', 'PC'),
('Chocolat', 'KG'),
('Vanille', 'KG'),
('Creme', 'L'),
('Pistache', 'KG'),
('Amandes', 'KG'),
('Noisettes', 'KG'),
('Citron', 'PC'),
('Raisin sec', 'KG'),
('Cannelle', 'KG'),
('Poudre d amandes', 'KG'),
('Sucre glace', 'KG'),
('Miel', 'KG'),
('Confiture', 'KG');

INSERT INTO Ingredient (nom, Id_Unite) VALUES ('Eau', 'L');
INSERT INTO Ingredient (nom, Id_Unite) VALUES ('Farine complete', 'KG');

INSERT INTO Fournisseur (nom, contact) VALUES
('Fournisseur Boulangerie Pierre', 'contact@boulangeriepierre.com'),
('Les Delices de la Patisserie', 'contact@delicespatisserie.fr'),
('Nature et Saveur', 'contact@naturetsaveur.com'),
('Boulangerie Artisanale du Nord', 'contact@artisanalenord.fr'),
('Fournitures Gourmandes', 'contact@fournituresgourmandes.fr'),
('JIRAMA', 'contact@JIRAMA.mg');

INSERT INTO Ingredients_Fournisseurs (Id_Fournisseur, Id_Ingredient, prix_unitaire) VALUES
(1, 1, 0.50),
(2, 2, 1.20),
(3, 3, 0.10),
(4, 4, 2.00),
(5, 5, 1.80),
(1, 6, 0.80),
(2, 7, 0.30),
(3, 8, 5.00),
(4, 9, 3.00),
(5, 10, 2.50),
(1, 11, 4.00),
(2, 12, 3.50),
(3, 13, 3.00),
(4, 14, 0.60),
(5, 15, 0.90),
(1, 16, 1.50),
(2, 17, 2.20),
(3, 18, 1.00),
(4, 19, 1.50),
(5, 20, 2.30),
(6, 21, 0.30),
(1, 22, 2.30);

INSERT INTO Produit (nom, description, prix_unitaire, Id_Produit_Categorie, Id_Unite) VALUES
('Baguette', 'Pain classique de forme longue', 1.50, 1, 'PC'),
('Pain Complet', 'Pain fait avec de la farine complete', 2.00, 1, 'PC'),
('Pain de Mie', 'Pain doux et moelleux', 2.20, 1, 'PC'),
('Croissant', 'Viennoiserie feuilletée au beurre', 1.80, 3, 'PC'),
('Pain de Campagne', 'Pain au levain rustique', 2.50, 1, 'PC'),
('Brioche', 'Pain sucre a base de beurre et d oeufs', 2.70, 3, 'PC'),
('Pain de Miel', 'Pain doux parfume au miel', 2.10, 1, 'PC'),
('Pains au Lait', 'Petits pains sucres et moelleux', 1.50, 3, 'PC'),
('Madeleine', 'Petite patisserie moelleuse', 1.20, 2, 'PC'),
('Pain Viennois', 'Pain sucre et leger, legerement dore', 2.30, 1, 'PC');

INSERT INTO Produits_Recettes (Id_Ingredient, Id_Produit, quantite, instruction) VALUES
((SELECT Id_Ingredient FROM Ingredient WHERE nom = 'Farine de ble'), (SELECT Id_Produit FROM Produit WHERE nom = 'Baguette'), 0.5, 'Melanger avec de l eau et petrir jusqu a obtenir une pate homogene'),
((SELECT Id_Ingredient FROM Ingredient WHERE nom = 'Eau'), (SELECT Id_Produit FROM Produit WHERE nom = 'Baguette'), 0.3, 'Ajouter progressivement en petrissant la pate'),
((SELECT Id_Ingredient FROM Ingredient WHERE nom = 'Sel'), (SELECT Id_Produit FROM Produit WHERE nom = 'Baguette'), 0.01, 'Ajouter pour assaisonner la pate'),
((SELECT Id_Ingredient FROM Ingredient WHERE nom = 'Levure boulangere'), (SELECT Id_Produit FROM Produit WHERE nom = 'Baguette'), 0.02, 'Melanger a la farine avant d ajouter l eau');

INSERT INTO Produits_Recettes (Id_Ingredient, Id_Produit, quantite, instruction) VALUES
((SELECT Id_Ingredient FROM Ingredient WHERE nom = 'Farine complete'), (SELECT Id_Produit FROM Produit WHERE nom = 'Pain Complet'), 0.5, 'Melanger avec de l eau et petrir jusqu a obtenir une pate homogene'),
((SELECT Id_Ingredient FROM Ingredient WHERE nom = 'Eau'), (SELECT Id_Produit FROM Produit WHERE nom = 'Pain Complet'), 0.5, 'Ajouter progressivement en petrissant la pate'),
((SELECT Id_Ingredient FROM Ingredient WHERE nom = 'Sel'), (SELECT Id_Produit FROM Produit WHERE nom = 'Pain Complet'), 0.01, 'Ajouter pour assaisonner la pate'),
((SELECT Id_Ingredient FROM Ingredient WHERE nom = 'Levure boulangere'), (SELECT Id_Produit FROM Produit WHERE nom = 'Pain Complet'), 0.02, 'Melanger a la farine avant d ajouter l eau');

INSERT INTO Produits_Recettes (Id_Ingredient, Id_Produit, quantite, instruction) VALUES
((SELECT Id_Ingredient FROM Ingredient WHERE nom = 'Farine de ble'), (SELECT Id_Produit FROM Produit WHERE nom = 'Pain de Mie'), 0.5, 'Melanger avec les liquides pour former une pate homogene'),
((SELECT Id_Ingredient FROM Ingredient WHERE nom = 'Eau'), (SELECT Id_Produit FROM Produit WHERE nom = 'Pain de Mie'), 0.2, 'Ajouter progressivement en petrissant'),
((SELECT Id_Ingredient FROM Ingredient WHERE nom = 'Lait'), (SELECT Id_Produit FROM Produit WHERE nom = 'Pain de Mie'), 0.1, 'Incorporer pour adoucir la pate'),
((SELECT Id_Ingredient FROM Ingredient WHERE nom = 'Sel'), (SELECT Id_Produit FROM Produit WHERE nom = 'Pain de Mie'), 0.01, 'Ajouter pour assaisonner'),
((SELECT Id_Ingredient FROM Ingredient WHERE nom = 'Sucre'), (SELECT Id_Produit FROM Produit WHERE nom = 'Pain de Mie'), 0.02, 'Incorporer pour un goût legerement sucre'),
((SELECT Id_Ingredient FROM Ingredient WHERE nom = 'Beurre'), (SELECT Id_Produit FROM Produit WHERE nom = 'Pain de Mie'), 0.05, 'Incorporer en dernier pour une pate souple'),
((SELECT Id_Ingredient FROM Ingredient WHERE nom = 'Levure boulangere'), (SELECT Id_Produit FROM Produit WHERE nom = 'Pain de Mie'), 0.02, 'Melanger a la farine avant d ajouter les liquides');

INSERT INTO Produits_Recettes (Id_Ingredient, Id_Produit, quantite, instruction) VALUES
((SELECT Id_Ingredient FROM Ingredient WHERE nom = 'Farine de ble'), (SELECT Id_Produit FROM Produit WHERE nom = 'Croissant'), 0.5, 'Melanger avec les autres ingredients secs'),
((SELECT Id_Ingredient FROM Ingredient WHERE nom = 'Beurre'), (SELECT Id_Produit FROM Produit WHERE nom = 'Croissant'), 0.3, 'Incorporer en couches pour le feuilletage'),
((SELECT Id_Ingredient FROM Ingredient WHERE nom = 'Eau'), (SELECT Id_Produit FROM Produit WHERE nom = 'Croissant'), 0.2, 'Ajouter pour former la pate'),
((SELECT Id_Ingredient FROM Ingredient WHERE nom = 'Lait'), (SELECT Id_Produit FROM Produit WHERE nom = 'Croissant'), 0.1, 'Ajouter pour rendre la pate plus moelleuse'),
((SELECT Id_Ingredient FROM Ingredient WHERE nom = 'Sel'), (SELECT Id_Produit FROM Produit WHERE nom = 'Croissant'), 0.01, 'Incorporer pour assaisonner'),
((SELECT Id_Ingredient FROM Ingredient WHERE nom = 'Sucre'), (SELECT Id_Produit FROM Produit WHERE nom = 'Croissant'), 0.05, 'Ajouter pour une touche sucree'),
((SELECT Id_Ingredient FROM Ingredient WHERE nom = 'Levure boulangere'), (SELECT Id_Produit FROM Produit WHERE nom = 'Croissant'), 0.02, 'Incorporer a la farine pour la levee');

INSERT INTO ingredient_entree (Id_Fournisseur, Id_Ingredient, quantite, date_entree)
SELECT 
    Id_Fournisseur, 
    Id_Ingredient, 
    1000 AS quantite, -- Quantité élevée par défaut
    CURRENT_DATE AS date_entree
FROM 
    Ingredients_Fournisseurs;

INSERT INTO Format (nom, mult_prix, mult_recette)
VALUES 
('Nature', 1.00, 1.00),
('Nature Petit', 0.80, 0.80),
('Nature Grand', 1.20, 1.20),
('Enrobage', 1.30, 1.10),
('Farci', 1.40, 1.15);

DO $$ 
DECLARE 
    prod RECORD;
    id_nature INT;
BEGIN
    -- Récupérer l'ID du format "Nature"
    SELECT Id_Format INTO id_nature FROM Format WHERE nom = 'Nature';

    -- Vérifier si le format "Nature" existe
    IF id_nature IS NULL THEN
        RAISE EXCEPTION 'Le format "Nature" n''existe pas.';
    END IF;

    -- Créer une entrée dans Produit_Format pour chaque produit existant
    FOR prod IN SELECT Id_Produit, nom FROM Produit LOOP
        INSERT INTO Produit_Format (Id_Produit, Id_Format, nom)
        VALUES (prod.Id_Produit, id_nature, prod.nom);
    END LOOP;
END $$;
