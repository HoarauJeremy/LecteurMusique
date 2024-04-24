/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Jérémy Hoarau
 * Created: 6 mars 2024
 */

DROP DATABASE IF EXISTS `Rezeed`;
CREATE DATABASE `Rezeed`;
USE `Rezeed`;

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
   --duree INT NOT NULL,
   --creationDate DATE,
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
   password VARCHAR(255) NOT NULL,
   PRIMARY KEY(idUser),
   UNIQUE(pseudo),
   UNIQUE(email)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE Playlist (
   PlaylistID INT AUTO_INCREMENT,
   Nom VARCHAR(255) NOT NULL,
   dateCreation DATE DEFAULT NOW(),
   idUser INT,
   privee BOOL NOT NULL,   
   PRIMARY KEY(PlaylistID),
   FOREIGN KEY(idUser) REFERENCES Utilisateur(idUser)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE Playlist_Chanson (
   idMusique INT,
   PlaylistID INT,
   PRIMARY KEY(idMusique, PlaylistID),
   FOREIGN KEY(idMusique) REFERENCES musique(idMusique),
   FOREIGN KEY(PlaylistID) REFERENCES Playlist(PlaylistID)
) ENGINE=InnoDB CHARSET=utf8;

INSERT INTO `genre` (`idGenre`, `Genre`) VALUES
(1,	'Acoustic'),
(2,	'Ambient'),
(3,	'Avant-garde'),
(4,	'Bachata'),
(5,	'Baroque'),
(6,	'Big band'),
(7,	'Bluegrass'),
(8,	'Blues'),
(9,	'Bossa nova'),
(10,	'Breakbeat'),
(11,	'Celtic'),
(12,	'Chamber'),
(13,	'Chanson'),
(14,	'Chiptune'),
(15,	'Christian'),
(16,	'Classical'),
(17,	'Country'),
(18,	'Cumbia'),
(19,	'Dance'),
(20,	'Death metal'),
(21,	'Disco'),
(22,	'Doo-wop'),
(23,	'Drum and bass'),
(24,	'Dub'),
(25,	'Easy listening'),
(26,	'EDM (Electronic Dance Music)'),
(27,	'Electric blues'),
(28,	'Electronic'),
(29,	'Experimental'),
(30,	'Flamenco'),
(31,	'Folk'),
(32,	'Funk'),
(33,	'Gospel'),
(34,	'Grime'),
(35,	'Grunge'),
(36,	'Hard rock'),
(37,	'Hardcore'),
(38,	'Heavy metal'),
(39,	'Hip-hop'),
(40,	'House'),
(41,	'Indie'),
(42,	'Industrial'),
(43,	'Jazz'),
(44,	'J-pop'),
(45,	'K-pop'),
(46,	'Latin'),
(47,	'Mambo'),
(48,	'Mariachi'),
(49,	'Merengue'),
(50,	'Metal'),
(51,	'Motown'),
(52,	'New age'),
(53,	'New wave'),
(54,	'Opera'),
(55,	'Orchestral'),
(56,	'Pop'),
(57,	'Post-punk'),
(58,	'Psychedelic'),
(59,	'Punk'),
(60,	'R&B (Rhythm and Blues)'),
(61,	'Ragtime'),
(62,	'Rap'),
(63,	'Reggae'),
(64,	'Reggaeton'),
(65,	'Renaissance'),
(66,	'Rock'),
(67,	'Rock and roll'),
(68,	'Salsa'),
(69,	'Samba'),
(70,	'Shoegaze'),
(71,	'Singer-songwriter'),
(72,	'Ska'),
(73,	'Soul'),
(74,	'Soundtrack'),
(75,	'Southern rock'),
(76,	'Space rock'),
(77,	'Surf'),
(78,	'Swing'),
(79,	'Symphonic metal'),
(80,	'Synth-pop'),
(81,	'Tango'),
(82,	'Techno'),
(83,	'Trance'),
(84,	'Trip-hop'),
(85,	'Tropical'),
(86,	'Vallenato'),
(87,	'Video game music'),
(88,	'Vocal jazz'),
(89,	'World'),
(90,	'Zydeco');