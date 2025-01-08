-- Fournisseurs
INSERT INTO Fournisseur (nom, contact) VALUES
('Fournisseur A', 'contactA@example.com'),
('Fournisseur B', 'contactB@example.com');

-- Clients
INSERT INTO Client (nom, adresse) VALUES
('Client A', 'Adresse A'),
('Client B', 'Adresse B');

-- Unités
INSERT INTO Unite (Id_Unite, val) VALUES
('KG', 'Kilogramme'),
('L', 'Litre'),
('PIECE', 'Pièce');

-- Formats
INSERT INTO Format (nom, mult_prix, mult_recette) VALUES
('Petit', 0.5, 0.5),
('Moyen', 1.0, 1.0),
('Grand', 1.5, 1.5);

-- Produits
INSERT INTO Produit (nom, description, prix_unitaire, Id_Unite) VALUES
('Pain', 'Pain de blé', 2.00, 'PIECE'),
('Baguette', 'Baguette croustillante', 1.00, 'PIECE');

-- Ingrédients
INSERT INTO Ingredient (nom, Id_Unite) VALUES
('Farine', 'KG'),
('Eau', 'L'),
('Levure', 'KG');

-- Produits_Recettes
INSERT INTO Produits_Recettes (Id_Ingredient, Id_Produit, quantite, instruction) VALUES
(1, 1, 1.0, 'Mélanger farine pour pain'),
(2, 1, 0.5, 'Ajouter eau pour pain'),
(3, 1, 0.1, 'Ajouter levure pour pain'),
(1, 2, 0.8, 'Mélanger farine pour baguette'),
(2, 2, 0.3, 'Ajouter eau pour baguette'),
(3, 2, 0.05, 'Ajouter levure pour baguette');

-- Ingredients_Fournisseurs
INSERT INTO Ingredients_Fournisseurs (Id_Fournisseur, Id_Ingredient, prix_unitaire) VALUES
(1, 1, 0.5),
(1, 2, 0.2),
(2, 3, 0.8);

-- Produit_Format
INSERT INTO Produit_Format (prix_unitaire, Id_Produit, Id_Format) VALUES
(2.00, 1, 1),
(4.00, 1, 2),
(6.00, 1, 3),
(1.00, 2, 1),
(2.00, 2, 2),
(3.00, 2, 3);

-- Production
INSERT INTO Production (date_production, quantite, Id_Produit_Format) VALUES
('2025-01-01 08:00:00', 100, 1),
('2025-01-02 10:00:00', 200, 2);

-- Ingredient_Entree
INSERT INTO Ingredient_Entree (quantite, date_entree, prix_unitaire, Id_Fournisseur, Id_Ingredient) VALUES
(500, '2025-01-01 07:00:00', 0.5, 1, 1),
(200, '2025-01-01 07:00:00', 0.2, 1, 2),
(50, '2025-01-01 07:00:00', 0.8, 2, 3);

-- Production_Details
INSERT INTO Production_Details (quantite, Id_Ingredient_Entree, Id_Production) VALUES
(100, 1, 1),
(50, 2, 1),
(10, 3, 1),
(200, 1, 2),
(100, 2, 2),
(20, 3, 2);


-- Ajouter l'ingrédient "Beurre"
INSERT INTO Ingredient (nom, Id_Unite) VALUES
('Beurre', 'KG'); -- Id_Ingredient généré automatiquement

-- Ajouter un prix unitaire pour "Beurre" fourni par le fournisseur 1
INSERT INTO Ingredients_Fournisseurs (Id_Fournisseur, Id_Ingredient, prix_unitaire) VALUES
(1, 4, 3.0); -- Assumant que l'Id_Ingredient pour "Beurre" est 4

-- Ajouter une entrée d'ingrédient pour "Beurre"
INSERT INTO Ingredient_Entree (quantite, date_entree, prix_unitaire, Id_Fournisseur, Id_Ingredient) VALUES
(50, '2025-01-01 07:30:00', 3.0, 1, 4); -- Quantité de 50 kg de beurre

-- Modifier la recette pour inclure du beurre dans "Pain"
INSERT INTO Produits_Recettes (Id_Ingredient, Id_Produit, quantite, instruction) VALUES
(4, 1, 0.2, 'Ajouter du beurre pour pain');

-- Ajouter du beurre dans une production existante
INSERT INTO Production_Details (quantite, Id_Ingredient_Entree, Id_Production) VALUES
(10, 4, 1); -- Utilisation de 10 kg de beurre dans la production 1
