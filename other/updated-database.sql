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

INSERT INTO
	Fournisseur (nom, contact, etat)
VALUES
	('Fournisseur A', '0123456789', true),
	('Fournisseur B', '0987654321', true),
	('Fournisseur C', '0567894321', false);

INSERT INTO
	Client (nom, adresse)
VALUES
	('Client Alpha', '123 Rue Principale'),
	('Client Beta', '456 Boulevard Sud'),
	('Client Gamma', '789 Avenue Centrale');

INSERT INTO
	Unite (Id_Unite, val)
VALUES
	('kg', 'Kilogramme'),
	('ltr', 'Litre'),
	('pcs', 'Pièces');

INSERT INTO
	Unite (Id_Unite, val)
VALUES
	('kg', 'Kilogramme'),
	('ltr', 'Litre'),
	('pcs', 'Pièces');

INSERT INTO
	Format (nom, mult_prix, mult_recette)
VALUES
	('Chocolat', 1.2, 1.1),
	('Nature', 1.0, 1.0),
	('Vanille', 1.3, 1.2);

INSERT INTO
	Produit_Categorie (nom, description)
VALUES
	(
		'Viennoiserie',
		'Produits de boulangerie feuilletée'
	),
	('Pâtisserie', 'Produits de pâtisserie sucrée'),
	('Pain', 'Produits de boulangerie classiques');

INSERT INTO
	Produit (
		nom,
		description,
		prix_unitaire,
		Id_Produit_Categorie,
		Id_Unite
	)
VALUES
	('Croissant', 'Croissant au beurre', 1.5, 1, 'pcs'),
	(
		'Pain au Chocolat',
		'Viennoiserie avec chocolat',
		1.8,
		1,
		'pcs'
	),
	(
		'Baguette',
		'Pain français classique',
		0.9,
		3,
		'pcs'
	);

INSERT INTO
	Ingredient (nom, Id_Unite)
VALUES
	('Farine', 'kg'),
	('Beurre', 'kg'),
	('Chocolat', 'kg'),
	('Sucre', 'kg');

INSERT INTO
	Ingredients_Fournisseurs (Id_Fournisseur, Id_Ingredient, prix_unitaire)
VALUES
	(1, 1, 0.5),
	(1, 2, 3.0),
	(2, 3, 5.0),
	(3, 4, 1.2);

INSERT INTO
	Produit_Format (nom, prix_unitaire, Id_Produit, Id_Format)
VALUES
	('Croissant Chocolat', 1.8, 1, 1),
	('Croissant Nature', 1.5, 1, 2),
	('Pain au Chocolat Nature', 1.8, 2, 2),
	('Pain au Chocolat Chocolat', 2.0, 2, 1);

INSERT INTO
	Vente (
		date_vente,
		montant,
		date_livree,
		adresse_livraison,
		etat,
		Id_Client
	)
VALUES
	(
		'2025-01-01 10:00:00',
		10.0,
		'2025-01-02 10:00:00',
		'123 Rue Principale',
		1,
		1
	),
	(
		'2025-01-05 12:00:00',
		20.0,
		'2025-01-06 12:00:00',
		'456 Boulevard Sud',
		1,
		2
	);

INSERT INTO
	Vente_Facture (
		quantite,
		prix_unitaire,
		montant,
		Id_Produit_Format,
		Id_Vente
	)
VALUES
	(5, 1.8, 9.0, 1, 1),
	(6, 1.5, 9.0, 2, 1);

INSERT INTO
	Ingredient_Entree (
		quantite,
		date_entree,
		prix_unitaire,
		Id_Fournisseur,
		Id_Ingredient
	)
VALUES
	(10, '2025-01-01', 0.5, 1, 1),
	(5, '2025-01-02', 3.0, 1, 2),
	(7, '2025-01-03', 5.0, 2, 3);

