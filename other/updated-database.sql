\c postgres;

drop database if exists boulangerie;
create database boulangerie;

\c boulangerie;

CREATE TABLE Fournisseur(
   Id_Fournisseur SERIAL,
   nom VARCHAR(40) ,
   contact VARCHAR(30) ,
   PRIMARY KEY(Id_Fournisseur)
);

CREATE TABLE Produit_Categorie(
   Id_Produit_Categorie SERIAL,
   nom VARCHAR(20)  NOT NULL,
   description VARCHAR(50) ,
   PRIMARY KEY(Id_Produit_Categorie)
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

CREATE TABLE Produit(
   Id_Produit SERIAL,
   nom VARCHAR(30)  NOT NULL,
   description VARCHAR(50) ,
   prix_unitaire NUMERIC(10,2)   NOT NULL,
   Id_Unite VARCHAR(5)  NOT NULL,
   Id_Produit_Categorie INTEGER NOT NULL,
   PRIMARY KEY(Id_Produit),
   FOREIGN KEY(Id_Unite) REFERENCES Unite(Id_Unite),
   FOREIGN KEY(Id_Produit_Categorie) REFERENCES Produit_Categorie(Id_Produit_Categorie)
);

CREATE TABLE Production(
   Id_Production SERIAL,
   date_production TIMESTAMP NOT NULL,
   quantite INTEGER NOT NULL,
   Id_Produit INTEGER NOT NULL,
   PRIMARY KEY(Id_Production),
   FOREIGN KEY(Id_Produit) REFERENCES Produit(Id_Produit)
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

CREATE TABLE Vente(
   Id_Vente SERIAL,
   date_vente TIMESTAMP NOT NULL,
   date_livree TIMESTAMP,
   adresse_livraison VARCHAR(50) ,
   Id_Client INTEGER,
   PRIMARY KEY(Id_Vente),
   FOREIGN KEY(Id_Client) REFERENCES Client(Id_Client)
);

CREATE TABLE Vente_details(
   Id_Details_Commande SERIAL,
   quantite INTEGER NOT NULL,
   prix_unitaire NUMERIC(10,2)   NOT NULL,
   Id_Production INTEGER NOT NULL,
   Id_Vente INTEGER NOT NULL,
   PRIMARY KEY(Id_Details_Commande),
   FOREIGN KEY(Id_Production) REFERENCES Production(Id_Production),
   FOREIGN KEY(Id_Vente) REFERENCES Vente(Id_Vente)
);

CREATE TABLE Ingredients_Fournisseurs(
   Id_Fournisseur INTEGER,
   Id_Ingredient INTEGER,
   prix_unitaire NUMERIC(15,2)  ,
   PRIMARY KEY(Id_Fournisseur, Id_Ingredient),
   FOREIGN KEY(Id_Fournisseur) REFERENCES Fournisseur(Id_Fournisseur),
   FOREIGN KEY(Id_Ingredient) REFERENCES Ingredient(Id_Ingredient)
);

CREATE TABLE Production_Details(
   Id_Production_Details SERIAL,
   quantite NUMERIC(20,2)   NOT NULL,
   Id_Ingredient INTEGER NOT NULL,
   Id_Produit INTEGER NOT NULL,
   Id_Production INTEGER NOT NULL,
   PRIMARY KEY(Id_Production_Details),
   FOREIGN KEY(Id_Ingredient, Id_Produit) REFERENCES Produits_Recettes(Id_Ingredient, Id_Produit),
   FOREIGN KEY(Id_Production) REFERENCES Production(Id_Production)
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

