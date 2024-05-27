/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Jérémy Hoarau
 * Created: 6 mars 2024
 */

DROP DATABASE IF EXISTS `lecteurmusique_db`;
CREATE DATABASE `lecteurmusique_db`;
USE `lecteurmusique_db`;

CREATE TABLE Artistes (
   idArtiste INT AUTO_INCREMENT,
   Nom VARCHAR(255) NOT NULL,
   PRIMARY KEY(idArtiste)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE Genre (
   idGenre INT AUTO_INCREMENT,
   Nom VARCHAR(150) NOT NULL,
   PRIMARY KEY(idGenre)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE musique (
   idMusique INT AUTO_INCREMENT,
   nom VARCHAR(255) NOT NULL,
   lien VARCHAR(255) NOT NULL,
   dateCreation DATE,
   idGenre INT NOT NULL,
   idArtiste INT NOT NULL,
   PRIMARY KEY(idMusique),
   FOREIGN KEY(idGenre) REFERENCES Genre(idGenre),
   FOREIGN KEY(idArtiste) REFERENCES Artistes(idArtiste)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE Utilisateur (
   idUser INT AUTO_INCREMENT,
   nom VARCHAR(255) NOT NULL,
   prenom VARCHAR(255) NOT NULL,
   pseudo VARCHAR(100) NOT NULL,
   email VARCHAR(255) NOT NULL,
   motDePasse VARCHAR(255) NOT NULL,
   PRIMARY KEY(idUser),
   UNIQUE(pseudo),
   UNIQUE(email)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE Playlist (
   idPlaylist INT AUTO_INCREMENT,
   Nom VARCHAR(255) NOT NULL,
   dateCreation DATE DEFAULT NOW(),
   idUser INT,
   privee BOOL NOT NULL,   
   PRIMARY KEY(idPlaylist),
   FOREIGN KEY(idUser) REFERENCES Utilisateur(idUser)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE Copyright(
   idCopyright INT,
   lienNCS VARCHAR(255) NOT NULL,
   lienYTB VARCHAR(255) NOT NULL,
   idMusique INT,
   PRIMARY KEY(idCopyright),
   UNIQUE(idMusique),
   FOREIGN KEY(idMusique) REFERENCES musique(idMusique)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE Playlist_Chanson (
   idMusique INT,
   idPlaylist INT,
   PRIMARY KEY(idMusique, idPlaylist),
   FOREIGN KEY(idMusique) REFERENCES musique(idMusique),
   FOREIGN KEY(idPlaylist) REFERENCES Playlist(idPlaylist)
) ENGINE=InnoDB CHARSET=utf8;

INSERT INTO `genre` (`Genre`) VALUES
    ('Alternative Dance'),
    ('Alternative Pop'),
    ('Ambient'),
    ('Anti-Pop'),
    ('Bass'),
    ('Bass House'),
    ('Brazilian Phonk'),
    ('Breakbeat'),
    ('Chill'),
    ('Chill Pop'),
    ('Dance Pop'),
    ('Deep House'),
    ('Disco House'),
    ('Drum & Bass'),
    ('Drumstep'),
    ('Dubstep'),
    ('EDM'),
    ('Electronic'),
    ('Electronic Pop'),
    ('Electronic Rock'),
    ('Future Bass'),
    ('Future House'),
    ('Future Trap'),
    ('Garage'),
    ('Glitch Hop'),
    ('Hardstyle'),
    ('House'),
    ('Indie Dance'),
    ('Jersey Club'),
    ('Jump-Up'),
    ('Liquid Dnb'),
    ('Lofi Hip-Hop'),
    ('Melodic Dubstep'),
    ('Melodic House'),
    ('Midtempo Bass'),
    ('Neurofunk'),
    ('Phonk'),
    ('Pop'),
    ('Tech House'),
    ('Techno'),
    ('Trap'),
    ('UKG');

INSERT INTO `Artistes` (nom) VALUES
    ("SUMU"),
    ("Dosi & Aisake"),
    ("Intouch"),
    ("yanvince"),
    ("Diamond Eyes"),
    ("M.I.M.E, Shiah Maisel, Clarx"),
    ("Zeli"),
    ("Christopher Damas"), -- 8
    ("Crushed Candy"),
    ("Emin Nilsen"),
--     ("DYNAMIS, HXDES");

INSERT INTO `musique` (nom, lien, dateCreation, idGenre, idArtiste) VALUES
    ("Apart", "sumu_-_apart.mp3", "2024-02-27", 32, 1),
    ("Cruising", "Dosi_&_Aisake_-_Cruising.mp3", "2024-03-26", 32, 2),
    ("Heart My Heart", "intouch_-_Heart_My_Heart.mp3", "2023-12-12", 18, 3),
    ("Triple no", "yanvince_-_triple_no.mp3", "2023-12-14", 18, 4),
    ("Flutter", "Diamond_Eyes_-_Flutter.mp3", "2018-10-17", 5, 5),
    ("Pull Me In", "Clarx_&_Shiah_Maisel_-_Pull_Me_In_(feat._M.I.M.E).mp3", "2024-1-19", 1, 6),
    ("Only The Fallen", "Zeli_-_Only_The_Fallen.mp3", "2024-05-07", 40, 7),
    ("KHARMA", "Christopher_Damas_-_KHARMA.mp3", "2024-05-27", 41, 8),
    ("Oh My Gawd", "Crushed_Candy_-_Oh_My_Gawd.mp3", "2024-02-06", 41, 9),
    ("BANE", "Emin_Nilsen_-_BANE.mp3", "2024-05-16", 7, 10),
--     ("VIDA NOVA", "Emin_Nilsen_-_VIDA_NOVA.mp3", "2024-04-25", 7, 10),
--     ("Kapoeira Phonk", "HXDES_&_DYNAMIS_-_Kapoeira Phonk.mp3", "2024-04-26", 7, 11);

/* 
Rajouter une table entre musique et genre permettre de régler le probleme si une musique a plusieur genre et idem pour les musiques qui ont plusieurs artistes
*/

INSERT INTO `Copyright` (idCopyright, lienNCS, lienYTB, idMusique) VALUES
    (1, "http://ncs.io/apart", "http://ncs.lnk.to/apartAT/youtube", 1),
    (2, "http://ncs.io/Cruising", "http://ncs.lnk.to/CruisingAT/youtube", 2),
    (3, "http://ncs.io/heartmyheart", "http://ncs.io/heartmyheartAT/youtube", 3),
    (4, "http://ncs.io/tripleno", "http://ncs.io/tripleno", 4),
    (5, "http://ncs.io/Flutter", "http://youtu.be/lEHM9HZf0IA", 5),
    (6, "http://ncs.io/PullMeIn", "http://ncs.lnk.to/PullMeInAT/youtube", 6),
    (7, "http://ncs.io/OnlyTheFallen", "http://ncs.lnk.to/OnlyTheFallenAT/youtube", 7),
    (8, "http://ncs.io/KHARMA", "http://ncs.lnk.to/KHARMAAT/youtube", 8),
    (9, "http://ncs.io/CC_OMG", "http://ncs.lnk.to/CC_OMGAT/youtube", 9),
    (10, "http://ncs.io/BANE", "http://ncs.lnk.to/BANEAT/youtube", 10),
--     (11, "http://ncs.io/VIDANOVA", "http://ncs.lnk.to/VIDANOVAAT/youtube", 11),
--     (12, "http://ncs.io/KapoeiraPhonk", "http://ncs.lnk.to/KapoeiraPhonkAT/youtube", 12);

CREATE TABLE nombreEcoute(
   idEcoute INT AUTO_INCREMENT,
   idMusique INT,
   dateAjout DATETIME,
   PRIMARY KEY(idEcoute),
   FOREIGN KEY(idMusique) REFERENCES musique(idMusique)
) ENGINE=InnoDB CHARSET=utf8;

CREATE VIEW v_nombreEcoute AS
    SELECT m.*, COUNT(ne.idEcoute) AS Nombre_Ecoute FROM musique m
    LEFT JOIN nombreEcoute ne ON m.idMusique = ne.idMusique;

SELECT * FROM v_nombreEcoute
ORDER BY DESC
LIMIT 50;

    -- GROUP BY m.idMusique

-- Song: HXDES, DYNAMIS - Kapoeira Phonk [NCS Release]
-- Music provided by NoCopyrightSounds
-- Free Download/Stream: http://ncs.io/KapoeiraPhonk
-- Watch: http://ncs.lnk.to/KapoeiraPhonkAT/youtube

-- Song: Emin Nilsen - VIDA NOVA [NCS Release]
-- Music provided by NoCopyrightSounds
-- Free Download/Stream: http://ncs.io/VIDANOVA
-- Watch: http://ncs.lnk.to/VIDANOVAAT/youtube

-------

-- Song: Emin Nilsen - BANE [NCS Release]
-- Music provided by NoCopyrightSounds
-- Free Download/Stream: http://ncs.io/BANE
-- Watch: http://ncs.lnk.to/BANEAT/youtube

-- Song: Crushed Candy - Oh My Gawd [NCS Release]
-- Music provided by NoCopyrightSounds
-- Free Download/Stream: http://ncs.io/CC_OMG
-- Watch: http://ncs.lnk.to/CC_OMGAT/youtube

-- Song: Christopher Damas - KHARMA [Arcade Release]
-- Music provided by NoCopyrightSounds
-- Free Download/Stream: http://ncs.io/KHARMA
-- Watch: http://ncs.lnk.to/KHARMAAT/youtube

-- Song: Zeli - Only The Fallen [NCS Release]
-- Music provided by NoCopyrightSounds
-- Free Download/Stream: http://ncs.io/OnlyTheFallen
-- Watch: http://ncs.lnk.to/OnlyTheFallenAT/youtube

-- Song: Clarx & Shiah Maisel - Pull Me In (feat. M.I.M.E) [NCS Release]
-- Music provided by NoCopyrightSounds
-- Free Download/Stream: http://ncs.io/PullMeIn
-- Watch: http://ncs.lnk.to/PullMeInAT/youtube

-- Song: Diamond Eyes - Flutter [NCS Release]
-- Music provided by NoCopyrightSounds
-- Free Download/Stream: http://ncs.io/Flutter
-- Watch: http://youtu.be/lEHM9HZf0IA

-- Song: yanvince - triple no [NCS Release]
-- Music provided by NoCopyrightSounds
-- Free Download/Stream: http://ncs.io/tripleno
-- Watch:

-- Song: intouch - Heart My Heart [NCS Release]
-- Music provided by NoCopyrightSounds
-- Free Download/Stream: http://ncs.io/heartmyheart
-- Watch: http://ncs.io/heartmyheartAT/youtube
-- Song: Dosi & Aisake - Cruising [NCS Release]

-- Music provided by NoCopyrightSounds
-- Free Download/Stream: http://ncs.io/Cruising
-- Watch: http://ncs.lnk.to/CruisingAT/youtube

-- Song: sumu - apart [NCS Release]
-- Music provided by NoCopyrightSounds
-- Free Download/Stream: http://ncs.io/apart
-- Watch: http://ncs.lnk.to/apartAT/youtube