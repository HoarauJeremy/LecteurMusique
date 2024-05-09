/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Jérémy Hoarau
 * Created: 6 mars 2024
 */

DROP DATABASE IF EXISTS `LecteurMusique`;
CREATE DATABASE `LecteurMusique`;
USE `LecteurMusique`;

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

INSERT INTO `Artiste` (idArtiste, nom) VALUES
    (),
    ();

INSERT INTO `musique` (idMusique, nom, lien, dateCreation, idGenre, idArtiste) VALUES
    (),
    ();

INSERT INTO `Copyright` (idCopyright, lienNCS, lienYTB, idMusique) VALUES
    (),
    ();

-- Song: intouch - Heart My Heart [NCS Release]
-- Music provided by NoCopyrightSounds
-- Free Download/Stream: http://ncs.io/heartmyheart
-- Watch: http://ncs.io/heartmyheartAT/youtube

-- Song: Clarx & Shiah Maisel - Pull Me In (feat. M.I.M.E) [NCS Release]
-- Music provided by NoCopyrightSounds
-- Free Download/Stream: http://ncs.io/PullMeIn
-- Watch: http://ncs.lnk.to/PullMeInAT/youtube

-- Song: Diamond Eyes - Flutter [NCS Release]
-- Music provided by NoCopyrightSounds
-- Free Download/Stream: http://ncs.io/Flutter
-- Watch: http://youtu.be/lEHM9HZf0IA

-- Song: Dosi & Aisake - Cruising [NCS Release]
-- Music provided by NoCopyrightSounds
-- Free Download/Stream: http://ncs.io/Cruising
-- Watch: http://ncs.lnk.to/CruisingAT/youtube

-- Song: sumu - apart [NCS Release]
-- Music provided by NoCopyrightSounds
-- Free Download/Stream: http://ncs.io/apart
-- Watch: http://ncs.lnk.to/apartAT/youtube

-- Song: yanvince - triple no [NCS Release]
-- Music provided by NoCopyrightSounds
-- Free Download/Stream: http://ncs.io/tripleno
-- Watch:

