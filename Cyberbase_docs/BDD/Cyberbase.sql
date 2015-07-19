------------------------------------------------------------
--        Script Postgre 
------------------------------------------------------------



------------------------------------------------------------
-- Table: usager
------------------------------------------------------------
CREATE TABLE public.usager(
	id_usager             SERIAL NOT NULL ,
	tech_id               VARCHAR (25) NOT NULL UNIQUE,
	civilite_usager       VARCHAR (20)  ,
	nom_usager            VARCHAR (40) NOT NULL CHECK (nom_usager <> ' ') ,
	prenom_usager         VARCHAR (40) NOT NULL CHECK (prenom_usager <> ' ')  ,
	date_naissance_usager DATE   ,
	code_postal_usager    INT   ,
	email_usager          VARCHAR (50)  ,
	accompagnement        BOOL   ,
	site_inscription_id   INT   ,
	id_quartier           INT   ,
	id_csp                INT   ,
	id_formation          INT   ,
	CONSTRAINT prk_constraint_usager PRIMARY KEY (id_usager)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: site
------------------------------------------------------------
CREATE TABLE public.site(
	id_site          SERIAL NOT NULL ,
	nom_site         VARCHAR (25)  ,
	adresse_site     VARCHAR (50)  ,
	ville_site       VARCHAR (25)  ,
	code_postal_site INT   ,
	CONSTRAINT prk_constraint_site PRIMARY KEY (id_site)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: quartier
------------------------------------------------------------
CREATE TABLE public.quartier(
	id_quartier  SERIAL NOT NULL ,
	nom_quartier VARCHAR (25)  ,
	CONSTRAINT prk_constraint_quartier PRIMARY KEY (id_quartier)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: professionnel
------------------------------------------------------------
CREATE TABLE public.professionnel(
	id_professionnel     SERIAL NOT NULL ,
	tech_id              VARCHAR (25) NOT NULL UNIQUE,
	nom_professionnel    VARCHAR (50) NOT NULL CHECK (nom_professionnel <> ' ') ,
	prenom_professionnel VARCHAR (25) NOT NULL CHECK (prenom_professionnel <> ' ') ,
	site_reference       VARCHAR (25)  ,
	password             VARCHAR (255)  ,
	admin                BOOL   ,
	id_structure         INT   ,
	CONSTRAINT prk_constraint_professionnel PRIMARY KEY (id_professionnel)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: salle
------------------------------------------------------------
CREATE TABLE public.salle(
	id_salle  SERIAL NOT NULL ,
	nom_salle VARCHAR (2000)   ,
	id_site   INT   ,
	CONSTRAINT prk_constraint_salle PRIMARY KEY (id_salle)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: poste
------------------------------------------------------------
CREATE TABLE public.poste(
	id_poste      SERIAL NOT NULL ,
	nom_poste     VARCHAR (25)  ,
	disponibilite BOOL   ,
	id_salle      INT   ,
	CONSTRAINT prk_constraint_poste PRIMARY KEY (id_poste)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: requete_favorite
------------------------------------------------------------
CREATE TABLE public.requete_favorite(
	id_requete       SERIAL NOT NULL ,
	nom_requete      VARCHAR (25)  ,
	contenu_requete  VARCHAR (2000)   ,
	id_professionnel INT   ,
	CONSTRAINT prk_constraint_requete_favorite PRIMARY KEY (id_requete)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: demarche
------------------------------------------------------------
CREATE TABLE public.demarche(
	id_demarche  SERIAL NOT NULL ,
	nom_demarche VARCHAR (25)  ,
	CONSTRAINT prk_constraint_demarche PRIMARY KEY (id_demarche)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: csp
------------------------------------------------------------
CREATE TABLE public.csp(
	id_csp      SERIAL NOT NULL ,
	libelle_csp VARCHAR (40)  ,
	CONSTRAINT prk_constraint_csp PRIMARY KEY (id_csp)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: niveau_formation
------------------------------------------------------------
CREATE TABLE public.niveau_formation(
	id_formation     SERIAL NOT NULL ,
	niveau_formation VARCHAR (25)  ,
	CONSTRAINT prk_constraint_niveau_formation PRIMARY KEY (id_formation)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: affectation
------------------------------------------------------------
CREATE TABLE public.affectation(
	id_affectation         SERIAL NOT NULL ,
	date_debut_affectation DATE   ,
	date_fin_affectation   DATE   ,
	id_professionnel       INT   ,
	id_usager              INT   ,
	id_poste               INT   ,
	id_demarche            INT   ,
	CONSTRAINT prk_constraint_affectation PRIMARY KEY (id_affectation)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: exclusion
------------------------------------------------------------
CREATE TABLE public.exclusion(
	id_exclusion     SERIAL NOT NULL ,
	statut_exclusion VARCHAR (25)  ,
	date_debut       DATE   ,
	date_fin         DATE   ,
	id_usager        INT   ,
	CONSTRAINT prk_constraint_exclusion PRIMARY KEY (id_exclusion)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: structure_appartenance
------------------------------------------------------------
CREATE TABLE public.structure_appartence(
	id_structure  SERIAL NOT NULL ,
	nom_structure VARCHAR (25)  ,
	CONSTRAINT prk_constraint_structure_appartence PRIMARY KEY (id_structure)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: travailler
------------------------------------------------------------
CREATE TABLE public.travailler(
	id_site          INT  NOT NULL ,
	id_professionnel INT  NOT NULL ,
	CONSTRAINT prk_constraint_travailler PRIMARY KEY (id_site,id_professionnel)
)WITHOUT OIDS;



ALTER TABLE public.usager ADD CONSTRAINT FK_usager_id_quartier FOREIGN KEY (id_quartier) REFERENCES public.quartier(id_quartier);
ALTER TABLE public.usager ADD CONSTRAINT FK_usager_id_csp FOREIGN KEY (id_csp) REFERENCES public.csp(id_csp);
ALTER TABLE public.usager ADD CONSTRAINT FK_usager_id_formation FOREIGN KEY (id_formation) REFERENCES public.niveau_formation(id_formation);
ALTER TABLE public.professionnel ADD CONSTRAINT FK_professionnel_id_structure FOREIGN KEY (id_structure) REFERENCES public.structure_appartence(id_structure);
ALTER TABLE public.salle ADD CONSTRAINT FK_salle_id_site FOREIGN KEY (id_site) REFERENCES public.site(id_site);
ALTER TABLE public.poste ADD CONSTRAINT FK_poste_id_salle FOREIGN KEY (id_salle) REFERENCES public.salle(id_salle);
ALTER TABLE public.requete_favorite ADD CONSTRAINT FK_requete_favorite_id_professionnel FOREIGN KEY (id_professionnel) REFERENCES public.professionnel(id_professionnel);
ALTER TABLE public.affectation ADD CONSTRAINT FK_affectation_id_professionnel FOREIGN KEY (id_professionnel) REFERENCES public.professionnel(id_professionnel);
ALTER TABLE public.affectation ADD CONSTRAINT FK_affectation_id_usager FOREIGN KEY (id_usager) REFERENCES public.usager(id_usager);
ALTER TABLE public.affectation ADD CONSTRAINT FK_affectation_id_poste FOREIGN KEY (id_poste) REFERENCES public.poste(id_poste);
ALTER TABLE public.affectation ADD CONSTRAINT FK_affectation_id_demarche FOREIGN KEY (id_demarche) REFERENCES public.demarche(id_demarche);
ALTER TABLE public.exclusion ADD CONSTRAINT FK_exclusion_id_usager FOREIGN KEY (id_usager) REFERENCES public.usager(id_usager);
ALTER TABLE public.travailler ADD CONSTRAINT FK_travailler_id_site FOREIGN KEY (id_site) REFERENCES public.site(id_site);
ALTER TABLE public.travailler ADD CONSTRAINT FK_travailler_id_professionnel FOREIGN KEY (id_professionnel) REFERENCES public.professionnel(id_professionnel);

CREATE SEQUENCE professional_id;
CREATE SEQUENCE user_id;
alter table professionnel alter column tech_id set default 'PRO'||trim(to_char(nextval('professional_id'::regclass),'000000'));
alter table usager alter column tech_id set default 'USR'||trim(to_char(nextval('professional_id'::regclass),'000000'));
