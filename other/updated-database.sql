\c postgres;

drop database if exists boulangerie;
create database boulangerie;

\c boulangerie;

CREATE TABLE Fournisseur(
   Id_Fournisseur SERIAL,
   nom VARCHAR(40) ,
   contact VARCHAR(30) ,
   etat BOOLEAN default true,
   PRIMARY KEY(Id_Fournisseur)
);

CREATE TABLE Client(
   Id_Client SERIAL,
   nom VARCHAR(50)  NOT NULL,
   adresse VARCHAR(50) ,
   PRIMARY KEY(Id_Client)
);

CREATE TABLE Unite(
   Id_Unite VARCHAR(5) ,
   val VARCHAR(30)  NOT NULL,
   PRIMARY KEY(Id_Unite)
);

CREATE TABLE Vente(
   Id_Vente SERIAL,
   date_vente TIMESTAMP NOT NULL,
   montant NUMERIC(22,2)   NOT NULL,
   date_livree TIMESTAMP,
   adresse_livraison VARCHAR(50) ,
   etat INTEGER NOT NULL,
   Id_Client INTEGER NOT NULL,
   PRIMARY KEY(Id_Vente),
   FOREIGN KEY(Id_Client) REFERENCES Client(Id_Client)
);

CREATE TABLE Format(
   Id_Format SERIAL,
   nom VARCHAR(50)  NOT NULL,
   mult_prix NUMERIC(15,2)   NOT NULL,
   mult_recette NUMERIC(15,2)  ,
   PRIMARY KEY(Id_Format)
);

CREATE TABLE Produit_Categorie(
   Id_Produit_Categorie SERIAL,
   nom VARCHAR(50)  NOT NULL,
   description VARCHAR(50) ,
   PRIMARY KEY(Id_Produit_Categorie)
);

CREATE TABLE Produit(
   Id_Produit SERIAL,
   nom VARCHAR(30)  NOT NULL,
   description VARCHAR(50) ,
   prix_unitaire NUMERIC(15,2)  ,
   Id_Produit_Categorie INTEGER NOT NULL,
   Id_Unite VARCHAR(5)  NOT NULL,
   PRIMARY KEY(Id_Produit),
   FOREIGN KEY(Id_Produit_Categorie) REFERENCES Produit_Categorie(Id_Produit_Categorie),
   FOREIGN KEY(Id_Unite) REFERENCES Unite(Id_Unite)
);

CREATE TABLE Ingredient(
   Id_Ingredient SERIAL,
   nom VARCHAR(50) ,
   Id_Unite VARCHAR(5)  NOT NULL,
   PRIMARY KEY(Id_Ingredient),
   FOREIGN KEY(Id_Unite) REFERENCES Unite(Id_Unite)
);

CREATE TABLE Produits_Recettes(
   Id_Ingredient INTEGER,
   Id_Produit INTEGER,
   quantite NUMERIC(15,2)   NOT NULL,
   instruction VARCHAR(100) ,
   PRIMARY KEY(Id_Ingredient, Id_Produit),
   FOREIGN KEY(Id_Ingredient) REFERENCES Ingredient(Id_Ingredient),
   FOREIGN KEY(Id_Produit) REFERENCES Produit(Id_Produit)
);

CREATE TABLE Ingredients_Fournisseurs(
   Id_Fournisseur INTEGER,
   Id_Ingredient INTEGER,
   prix_unitaire NUMERIC(15,2)  ,
   PRIMARY KEY(Id_Fournisseur, Id_Ingredient),
   FOREIGN KEY(Id_Fournisseur) REFERENCES Fournisseur(Id_Fournisseur),
   FOREIGN KEY(Id_Ingredient) REFERENCES Ingredient(Id_Ingredient)
);

CREATE TABLE Produit_Format(
   Id_Produit_Format SERIAL,
   nom VARCHAR(50) ,
   prix_unitaire NUMERIC(15,2)   NOT NULL,
   Id_Produit INTEGER NOT NULL,
   Id_Format INTEGER NOT NULL,
   PRIMARY KEY(Id_Produit_Format),
   FOREIGN KEY(Id_Produit) REFERENCES Produit(Id_Produit),
   FOREIGN KEY(Id_Format) REFERENCES Format(Id_Format)
);

CREATE TABLE Produit_Format_Recette(
   Id_Ingredient INTEGER,
   Id_Produit_Format INTEGER,
   quantte NUMERIC(15,2)   NOT NULL,
   instruction VARCHAR(100) ,
   PRIMARY KEY(Id_Ingredient, Id_Produit_Format),
   FOREIGN KEY(Id_Ingredient) REFERENCES Ingredient(Id_Ingredient),
   FOREIGN KEY(Id_Produit_Format) REFERENCES Produit_Format(Id_Produit_Format)
);

CREATE TABLE Production(
   Id_Production SERIAL,
   date_production TIMESTAMP NOT NULL,
   quantite INTEGER NOT NULL,
   Id_Produit_Format INTEGER NOT NULL,
   PRIMARY KEY(Id_Production),
   FOREIGN KEY(Id_Produit_Format) REFERENCES Produit_Format(Id_Produit_Format)
);

CREATE TABLE Ingredient_Entree(
   Id_Ingredient_Entree SERIAL,
   quantite NUMERIC(15,2)   NOT NULL,
   date_entree TIMESTAMP NOT NULL,
   prix_unitaire NUMERIC(15,2)  ,
   Id_Fournisseur INTEGER NOT NULL,
   Id_Ingredient INTEGER NOT NULL,
   PRIMARY KEY(Id_Ingredient_Entree),
   FOREIGN KEY(Id_Fournisseur, Id_Ingredient) REFERENCES Ingredients_Fournisseurs(Id_Fournisseur, Id_Ingredient)
);

CREATE TABLE Production_Details(
   Id_Production_Details SERIAL,
   quantite NUMERIC(20,2)   NOT NULL,
   Id_Ingredient_Entree INTEGER NOT NULL,
   Id_Production INTEGER NOT NULL,
   PRIMARY KEY(Id_Production_Details),
   FOREIGN KEY(Id_Ingredient_Entree) REFERENCES Ingredient_Entree(Id_Ingredient_Entree),
   FOREIGN KEY(Id_Production) REFERENCES Production(Id_Production)
);

CREATE TABLE Vente_Facture(
   Id_Vente_Facture SERIAL,
   quantite NUMERIC(20,2)   NOT NULL,
   prix_unitaire NUMERIC(15,2)   NOT NULL,
   montant NUMERIC(20,2)   NOT NULL,
   Id_Produit_Format INTEGER NOT NULL,
   Id_Vente INTEGER NOT NULL,
   PRIMARY KEY(Id_Vente_Facture),
   FOREIGN KEY(Id_Produit_Format) REFERENCES Produit_Format(Id_Produit_Format),
   FOREIGN KEY(Id_Vente) REFERENCES Vente(Id_Vente)
);

CREATE TABLE Vente_Facture_Details(
   Id_Vente_Facture_Details SERIAL,
   quantite INTEGER NOT NULL,
   Id_Production INTEGER NOT NULL,
   Id_Vente_Facture INTEGER NOT NULL,
   PRIMARY KEY(Id_Vente_Facture_Details),
   FOREIGN KEY(Id_Production) REFERENCES Production(Id_Production),
   FOREIGN KEY(Id_Vente_Facture) REFERENCES Vente_Facture(Id_Vente_Facture)
);

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
	Ent.quantite - COALESCE(SUM(Pr.quantite), 0) > 0;

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
INSERT INTO Produit_Format (prix_unitaire,nom, Id_Produit, Id_Format) VALUES
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
