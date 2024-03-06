/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  jerem
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
   duree INT NOT NULL,
   creationDate DATE,
   idGenre INT NOT NULL,
   idArtiste INT NOT NULL,
   PRIMARY KEY(idMusique),
   FOREIGN KEY(idGenre) REFERENCES Genre(idGenre),
   FOREIGN KEY(idArtiste) REFERENCES Artistes(idArtiste)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE Utilisateur (
   idUser INT AUTO_INCREMENT,
   nom VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   PRIMARY KEY(idUser),
   UNIQUE(email),
   UNIQUE(nom)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE Playlist (
   PlaylistID INT AUTO_INCREMENT,
   Nom VARCHAR(255) NOT NULL,
   dateCreation DATE,
   idUser INT,
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
