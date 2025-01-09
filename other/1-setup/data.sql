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