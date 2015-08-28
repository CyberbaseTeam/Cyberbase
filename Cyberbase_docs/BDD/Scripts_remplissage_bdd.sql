
------------------------------------------------------------
-- Table: csp (7 entrées)
------------------------------------------------------------


INSERT INTO csp (libelle_csp) VALUES ('chomage'), ('en formation'), ('cadre'), ('ouvrier'), ('etudiant'), ('alternant'), ('intermittant');

------------------------------------------------------------
-- Table: quartier (7 entrées)
------------------------------------------------------------

INSERT INTO quartier (nom_quartier) VALUES ('nantes centre'), ('vertou'), ('saint herblain'), ('orvault'), ('saint sebastien'), ('malakoff'), ('bouguenais');


------------------------------------------------------------
-- Table: niveau_formation (7 entrées)
------------------------------------------------------------
INSERT INTO niveau_formation (nom_formation) VALUES ('bep'), ('bac'), ('bac pro'), ('bts'), ('licence'), ('master'), ('doctorat');


------------------------------------------------------------
-- Table: site (20 entrées)
------------------------------------------------------------

INSERT INTO "site" (nom_site,adresse_site,ville_site,code_postal_site) VALUES 
('Chaumont-Gistoux','Appartement 481-5305 In, Rd.','Poucet',44765),
('Picinisco','7529 Turpis. Rue','Ancaster Town',44722),
('Bedollo','CP 408, 961 Elit Rue','Kielce',44706),
('Neerglabbeek','CP 151, 5813 Dui. Chemin','Chandrapur',44213),
('Liernu','Appartement 825-3597 Mi Route','Moignelee',44327),
('Brunn am Gebirge','9476 Nunc Route','Langenburg',44698),
('North Las Vegas','151-7123 Vulputate, Avenue','Cellara',44484),
('Rosires','Appartement 584-1464 Elit Ave','Coevorden',44203),
('Crieff','1500 Nunc Chemin','Wha Ti',44323),
('Glendon','Appartement 560-9546 Ornare Ave','Sanluri',44773);

INSERT INTO "site" (nom_site,adresse_site,ville_site,code_postal_site) VALUES 
('Llangefni','Appartement 913-5107 Consectetuer, Impasse','Birori',44428),
('Medicine Hat','350-6585 Ullamcorper Ave','Moliterno',44852),
('Sint-Gillis','CP 780, 9040 Hendrerit. Av.','Saint-Quentin',44279),
('Whitchurch-Stouffville','4292 Dolor Rd.','Jaipur',44095),
('Garaguso','177-8118 Ante Rd.','Baden-Baden',44671),
('Kalisz','155-4862 Sit Impasse','Naihati',44553),
('Kanpur','167-7667 Odio. Avenue','Legal',44741),
('Neuville','3357 Non, Rue','Kasur',44005),
('Neudörfl','CP 403, 4209 Blandit Rd.','Acciano',44171),
('Lutsel K''e','397-5185 Integer Route','Gateshead',44870);


------------------------------------------------------------
-- Table: usager (30 entrées)
------------------------------------------------------------



INSERT INTO "usager" (nom_usager,prenom_usager,date_naissance_usager,code_postal_usager,email_usager,accompagnement, id_site_inscription, date_inscription, id_quartier,id_csp,id_formation,ville_usager,adresse_usager) VALUES 
('Neal','Gavin','18/07/2016',44879,'nonummy.ultricies.ornare@laciniavitaesodales.org','true', 4,'19/09/2014', 2,1,7,'Huntingdon','CP 810, 5718 Mauris. Ave'),('Nixon','Sierra','22/03/2015',44395,'et.lacinia.vitae@amet.com','true',14,'19/09/2014',5,3,1,'Le Cannet','Appartement 383-1740 Odio. Ave'),
('Carlson','Grady','09/06/2015',44871,'dapibus@mi.net','true',14,'19/09/2015',4,1,3,'Worcester','9587 Nulla Av.'),
('Marsh','Audra','22/08/2014',44601,'Sed.id.risus@Sed.org','true',6,'19/08/2016',4,4,4,'Peterhead','1903 Mus. Avenue'),
('Cooper','Orla','19/05/2015',44493,'sed.pede.Cum@amifringilla.org','true',14,'02/09/2015',3,2,1,'Montignoso','233-8495 Mus. Chemin'),('Anderson','Whitney','03/09/2015',44066,'pretium@ipsumdolorsit.org','true',7,'20/09/2015',4,1,3,'Weesp','Appartement 232-8493 Molestie Impasse'),('Williamson','Ross','20/07/2016',44124,'at.iaculis.quis@etnetus.edu','true',13,'19/09/2015',2,1,5,'Bolton','859-5129 Erat Impasse'),('Pena','Devin','24/03/2015',44377,'ipsum.dolor@tristiquealiquet.net','true',19,'17/09/2015',2,4,7,'Louisville','CP 422, 2824 Nunc Chemin'),('Hines','Hiram','27/07/2016',44337,'luctus@liberoMorbi.co.uk','true',9,'19/05/2015',4,4,2,'Cartago','544-3283 Orci Chemin'),
('Booker','Idona','26/04/2015',44077,'semper.Nam@Namligulaelit.co.uk','true',12,'19/04/2015',1,4,2,'Genk','Appartement 785-4109 Mauris Rd.');
INSERT INTO "usager" (nom_usager,prenom_usager,date_naissance_usager,code_postal_usager,email_usager,accompagnement, id_site_inscription, date_inscription, id_quartier,id_csp,id_formation,ville_usager,adresse_usager)  VALUES ('Kline','Marsden','13/01/2015',44673,'ad.litora@velit.net','false',18,'19/10/2013',5,2,4,'Racine','CP 318, 3236 Ut Avenue'),
('Spears','Hilary','01/11/2015',44190,'Praesent@euarcu.com','false',3,'19/12/2013',4,4,7,'Temploux','875-6558 Sed Av.'),
('Fulton','Tate','24/11/2015',44639,'posuere@sitametconsectetuer.co.uk','false',10,'19/11/2013',1,2,1,'Cerignola','CP 641, 5008 Duis Rd.'),('Pittman','Jaime','24/10/2014',44005,'eu@tincidunttempus.edu','false',15,'19/02/2013',5,1,2,'Springfield','2879 Vehicula. Avenue'),
('Hunter','Xenos','23/03/2016',44608,'In@gravida.edu','false',18,'19/05/2014',1,2,3,'Palma de Mallorca','808-7071 Duis Ave'),
('Maddox','Melvin','25/03/2016',44623,'vitae@dignissimlacusAliquam.ca','false',17,'30/09/2013',5,2,7,'San Luca','5717 Pharetra Ave'),
('Ashley','Richard','09/05/2016',44675,'luctus.sit@faucibusorci.co.uk','false',6,'10/10/2013',3,4,3,'Lowell','CP 639, 6588 Sed, Rd.'),('Burt','Quincy','11/04/2015',44788,'fringilla.euismod.enim@egetvarius.net','false',2,'11/09/2013',3,4,6,'Villers-la-Loue','158-3835 Laoreet Ave'),('Hopper','Suki','26/07/2015',44870,'dui.Cras@enimdiamvel.com','false',5,'19/11/2013',4,1,4,'Bayeux','5739 Aliquet Av.'),
('Hunt','Kennedy','14/02/2016',44790,'lacinia.orci@aliquetPhasellus.net','false',9,'19/01/2013',4,3,3,'Francavilla in Sinni','CP 269, 1917 Felis Chemin');
INSERT INTO "usager" (nom_usager,prenom_usager,date_naissance_usager,code_postal_usager,email_usager,accompagnement, id_site_inscription, date_inscription, id_quartier,id_csp,id_formation,ville_usager,adresse_usager) VALUES ('Chan','Basil','01/06/2016',44447,'Donec.est@Quisqueimperdieterat.edu','false',3,'19/01/2014',3,1,1,'North Las Vegas','Appartement 423-9424 Id, Route'),('Rosales','Portia','24/04/2015',44488,'Proin@maurissapien.net','false',10,'19/12/2014',4,3,4,'Okene','2446 Enim Chemin'),
('Hardin','Ingrid','29/08/2015',44663,'ante@et.ca','false',10,'19/09/2013',3,1,3,'Norwich','9912 Quisque Chemin'),
('Mcleod','Wilma','11/04/2015',44243,'Phasellus.elit@Proin.ca','false',13,'23/12/2015',4,1,2,'Lompret','Appartement 826-4527 Mi. Chemin'),
('Becker','Christopher','28/05/2015',44752,'orci@eleifend.org','false',2,'10/11/2013',3,4,5,'Edremit','6412 Odio. Ave'),('Banks','Adrienne','09/03/2015',44824,'dictum.Proin.eget@accumsaninterdumlibero.com','false',4,'05/05/2013',5,1,5,'Kilwinning','Appartement 386-3892 Sed Avenue'),('Atkinson','Arsenio','26/05/2015',44702,'Nam@euenim.edu','false',14,'08/03/2013',1,3,5,'San Sebastiano al Vesuvio','Appartement 987-8172 Enim. Chemin'),('Johns','Aurora','05/02/2016',44535,'Aliquam@lacusvestibulumlorem.org','false',19,'19/08/2015',5,3,7,'Hondelange','362-5289 Porttitor Impasse'),('Stephens','Barclay','15/09/2014',44593,'sit.amet@ornareFusce.edu','false',10,'19/12/2014',1,2,5,'Marbais','6276 Parturient Avenue'),('Rollins','Valentine','23/07/2016',44103,'magna.Suspendisse.tristique@orci.co.uk','false',4,'06/10/2015',5,2,2,'Oordegem','CP 998, 9548 Et Impasse');



------------------------------------------------------------
-- Table: demarche (5 entrées)
------------------------------------------------------------

INSERT INTO demarche (nom_demarche) VALUES ('cv'), ('lettre de motivation'), ('technique entretien'), ('recherche annonces'), ('administratif');


------------------------------------------------------------
-- Table: exclusion (15 entrées)
------------------------------------------------------------


INSERT INTO "exclusion" (statut_exclusion, date_debut,date_fin,id_usager) VALUES 
('temporaire','23/03/2016','31/10/2015',4),
('temporaire','25/08/2016','10/02/2015',11),
('définitive','04/07/2015',NULL, 12),
('temporaire','27/03/2015','03/01/2015',13),
('temporaire','05/08/2016','27/06/2016',10),
('temporaire','06/05/2015','13/11/2015',17),
('temporaire','25/04/2015','18/05/2016',5),
('définitive','24/01/2015',NULL,20),
('temporaire','15/07/2016','16/06/2016',19),
('temporaire','16/12/2015','06/05/2015',13),
('temporaire','08/05/2015','17/04/2015',25),
('temporaire','30/04/2016','11/01/2016',30),
('temporaire','09/09/2015','07/10/2015',4),
('définitive','16/04/2016',NULL,7),
('temporaire','27/08/2015','28/06/2015',11);


------------------------------------------------------------
-- Table: salle (32 entrées)
------------------------------------------------------------
INSERT INTO salle (nom_salle, id_site) VALUES 
('salle 1', 4),
('salle 2', 4),
('salle 3', 4),
('salle 4', 4),
('salle 1', 1),
('salle 2', 1),
('salle 3', 1),
('salle 4', 1),
('salle 1', 2),
('salle 2', 2),
('salle 3', 2),
('salle 4', 2),
('salle 1', 3),
('salle 2', 3),
('salle 3', 3),
('salle 4', 3),
('salle 1', 5),
('salle 2', 5),
('salle 3', 5),
('salle 4', 5),
('salle 1', 6),
('salle 2', 6),
('salle 3', 6),
('salle 4', 6),
('salle 1', 7),
('salle 2', 7),
('salle 3', 7),
('salle 4', 7),
('salle 1', 8),
('salle 2', 8),
('salle 3', 8),
('salle 4', 8);

------------------------------------------------------------
-- Table: poste (20 entrées)
------------------------------------------------------------
INSERT INTO poste (nom_poste, disponibilite, id_salle) VALUES
('poste 1', true, 1),
('poste 2', true, 1),
('poste 3', true, 1),
('poste 1', false, 2),
('poste 2', true, 2),
('poste 3', true, 2),
('poste 1', true, 3),
('poste 2', false, 3),
('poste 3', true, 3),
('poste 1', true, 4),
('poste 2', false, 4),
('poste 3', false, 4),
('poste 4', true, 4),
('poste 5', false, 4),
('poste 6', true, 4),
('poste 1', true, 5),
('poste 2', true, 5),
('poste 1', true, 6),
('poste 2', true, 6),
('poste 3', false, 6);



------------------------------------------------------------
-- Table: structure_appartenance (3 entrées)
------------------------------------------------------------
INSERT INTO structure_appartenance(nom_structure) VALUES
('pôle emploi'), ('maison de l''emploi'), ('mission locale');


------------------------------------------------------------
-- Table: professionnel (45 entrées)
------------------------------------------------------------
INSERT INTO "professionnel" (nom_professionnel,prenom_professionnel,site_reference, password, admin, id_structure) VALUES 
('Morton','Clinton',4,'imie', true, 2),
('Neal','Dai',19, 'imie', false, 2),
('Page','Hunter',12, 'imie',false, 1),
('Dawson','Dara',19, 'imie',false, 1),
('Roach','Unity',8, 'imie',false, 1),
('Alvarez','Hayden',5, 'imie',false, 3),
('Molina','Andrew',3, 'imie',false, 2),
('Skinner','Wesley',5,'imie', false,3),
('Richardson','Jescie',18,'imie', false,1),
('Burgess','Camille',3, 'imie', false,1),
('Huff','Xanthus',8, 'imie',false,3),
('Munoz','Eleanor',6, 'imie', false,1),
('Hull','Ulric',17,'imie', false,1),
('Richards','Rosalyn',20,'imie', false,2),
('Bradford','Nash',13,'imie', false,2);

INSERT INTO "professionnel" (nom_professionnel,prenom_professionnel,site_reference, password, admin, id_structure) VALUES 
('Smith','Driscoll',9, 'imie', false,2),
('Bowen','Jaime',10, 'imie', false,3),
('Rosario','Upton',6, 'imie', false,1),
('Hawkins','Ava',7, 'imie', false,3),
('Spencer','Alice',18, 'imie', false,2),
('England','Rae',3, 'imie', false,3),
('Franks','Clare',16, 'imie', false,2),
('Mendoza','Reece',14, 'imie', false,1),
('Andrews','Lane',12, 'imie', false,2),
('Shepherd','Asher',1, 'imie', false,1),
('Kerr','Roth',8, 'imie', false,1),
('Simpson','Xantha',4, 'imie', false,3),
('Collins','Aristotle',19, 'imie', false,2),
('Pierce','Laurel',15, 'imie', false,1),
('Mcgee','Herrod',2, 'imie', false,3);
INSERT INTO "professionnel" (nom_professionnel,prenom_professionnel,site_reference, password, admin, id_structure) VALUES 
('Silva','Timothy',4, 'imie', false,3),
('Fuller','Ahmed',19, 'imie', false,2),
('Schneider','Kirk',11, 'imie', false,2),
('Mosley','Daniel',7, 'imie', false,3),
('Kramer','Azalia',14, 'imie', false,1),
('Hendrix','Keane',11,'imie', false,1),
('Haynes','Jenna',20,'imie', false,2),
('Mccall','Nolan',5,'imie', false,3),
('Barnes','Deacon',20,'imie', false,1),
('Craft','Leila',7,'imie', false,3),
('Clayton','Colorado',17,'imie', false,2),
('William','Devin',6,'imie', false,3),
('Santos','Victoria',7,'imie', false,2),
('Fitzpatrick','Leandra',16,'imie', false,3),
('Camacho','Emery',17,'imie', false,3);


------------------------------------------------------------
-- Table: travailler (30 entrées)
------------------------------------------------------------
INSERT INTO "travailler" (id_site, id_professionnel) VALUES 
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), 
(2, 8), (2, 9), (2, 10), (2, 11), (1, 12), (1, 13), (1, 14),
(3, 15), (3, 16), (3, 17), (4, 18), (5, 19), (5, 20), (6, 21),
(6, 22), (6, 23), (7, 24), (7, 25), (7, 26), (8, 27), (8, 28),
(8, 29), (8,30);








