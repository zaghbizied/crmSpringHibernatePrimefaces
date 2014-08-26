-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Mar 26 Août 2014 à 21:21
-- Version du serveur: 5.5.24-log
-- Version de PHP: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `crm`
--

-- --------------------------------------------------------

--
-- Structure de la table `achat`
--

CREATE TABLE IF NOT EXISTS `achat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `prixUnit` float NOT NULL,
  `quantite` int(11) NOT NULL,
  `vendu` int(11) NOT NULL,
  `opAchat` bigint(20) DEFAULT NULL,
  `produit` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5857B3992CA4A30` (`produit`),
  KEY `FK5857B391EDBA2AE` (`opAchat`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Contenu de la table `achat`
--

INSERT INTO `achat` (`id`, `prixUnit`, `quantite`, `vendu`, `opAchat`, `produit`) VALUES
(1, 5, 10000, 3000, 1, 1),
(2, 10, 10000, 0, 1, 2),
(3, 1, 10000, 0, 1, 3),
(4, 5, 10000, 0, 1, 4),
(5, 10, 10000, 0, 1, 5),
(6, 1, 10000, 0, 1, 6),
(7, 4.95, 5000, 5000, 2, 1),
(8, 9.95, 5000, 1000, 2, 2),
(9, 0.95, 5000, 1000, 2, 3),
(10, 4.95, 5000, 1000, 2, 4),
(11, 9.95, 5000, 1000, 2, 5),
(12, 0.95, 5000, 1000, 2, 6),
(13, 5, 10000, 0, 3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `app_user`
--

CREATE TABLE IF NOT EXISTS `app_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_expired` bit(1) NOT NULL,
  `account_locked` bit(1) NOT NULL,
  `address` varchar(150) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `postal_code` varchar(15) DEFAULT NULL,
  `province` varchar(100) DEFAULT NULL,
  `credentials_expired` bit(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `account_enabled` bit(1) DEFAULT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `password_hint` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `app_user`
--

INSERT INTO `app_user` (`id`, `account_expired`, `account_locked`, `address`, `city`, `country`, `postal_code`, `province`, `credentials_expired`, `email`, `account_enabled`, `first_name`, `last_name`, `password`, `password_hint`, `phone_number`, `username`, `version`, `website`) VALUES
(-3, b'0', b'0', '', 'Denver', 'US', '80210', 'CO', b'0', 'two_roles_user@appfuse.org', b'1', 'Two Roles', 'User', '$2a$10$bH/ssqW8OhkTlIso9/yakubYODUOmh.6m5HEJvcBq3t3VdBh7ebqO', 'Not a female kitty.', '', 'two_roles_user', 1, 'http://raibledesigns.com'),
(-2, b'0', b'0', '', 'Denver', 'US', '80210', 'CO', b'0', 'matt@raibledesigns.com', b'1', 'Matt', 'Raible', '$2a$10$bH/ssqW8OhkTlIso9/yakubYODUOmh.6m5HEJvcBq3t3VdBh7ebqO', 'Not a female kitty.', '', 'admin', 1, 'http://raibledesigns.com'),
(-1, b'0', b'0', '', 'Denver', 'US', '80210', 'CO', b'0', 'matt_raible@yahoo.com', b'1', 'Tomcat', 'User', '$2a$10$CnQVJ9bsWBjMpeSKrrdDEeuIptZxXrwtI6CZ/OgtNxhIgpKxXeT9y', 'A male kitty.', '', 'user', 1, 'http://tomcat.apache.org');

-- --------------------------------------------------------

--
-- Structure de la table `caisse`
--

CREATE TABLE IF NOT EXISTS `caisse` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dateCloture` datetime NOT NULL,
  `montant` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `caisse`
--

INSERT INTO `caisse` (`id`, `dateCloture`, `montant`) VALUES
(1, '2014-08-24 00:00:00', 0);

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adresse` varchar(255) NOT NULL,
  `email` varchar(40) DEFAULT NULL,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `ville` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `client`
--

INSERT INTO `client` (`id`, `adresse`, `email`, `nom`, `prenom`, `ville`) VALUES
(1, 'dqslkdqs', 'qjsdkj@dqsd.co', 'cli1', 'cli1', 'qkdskjh');

-- --------------------------------------------------------

--
-- Structure de la table `fournisseur`
--

CREATE TABLE IF NOT EXISTS `fournisseur` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adresse` varchar(255) NOT NULL,
  `email` varchar(40) DEFAULT NULL,
  `nom` varchar(40) NOT NULL,
  `numTel` int(11) NOT NULL,
  `ville` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `fournisseur`
--

INSERT INTO `fournisseur` (`id`, `adresse`, `email`, `nom`, `numTel`, `ville`) VALUES
(1, 'qdkjhdqs', 'qsdk@dqs.co', 'fou1', 76546536, 'qsdh'),
(2, 'dqkhdskj', 'qsdkjqs@dqsd.co', 'fou2', 76675354, 'qkjdhskjhd');

-- --------------------------------------------------------

--
-- Structure de la table `invoice`
--

CREATE TABLE IF NOT EXISTS `invoice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dateFact` date NOT NULL,
  `url` varchar(100) NOT NULL,
  `client` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK74D6432D7496E4F8` (`client`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `numtel`
--

CREATE TABLE IF NOT EXISTS `numtel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `numTel` int(11) NOT NULL,
  `client` bigint(20) DEFAULT NULL,
  `operateur` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC258A6B54CF3FDA0` (`operateur`),
  KEY `FKC258A6B57496E4F8` (`client`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `numtel`
--

INSERT INTO `numtel` (`id`, `numTel`, `client`, `operateur`) VALUES
(1, 22345667, 1, 1),
(2, 54321456, 1, 2);

-- --------------------------------------------------------

--
-- Structure de la table `opachat`
--

CREATE TABLE IF NOT EXISTS `opachat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dateAchat` datetime NOT NULL,
  `montant` float NOT NULL,
  `montantPaye` float NOT NULL,
  `fournisseur` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB2B501D843316520` (`fournisseur`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `opachat`
--

INSERT INTO `opachat` (`id`, `dateAchat`, `montant`, `montantPaye`, `fournisseur`) VALUES
(1, '2014-08-25 22:17:09', 320000, 320000, 1),
(2, '2014-08-25 22:17:40', 158500, 58500, 2),
(3, '2014-08-26 17:28:16', 50000, 0, 1);

-- --------------------------------------------------------

--
-- Structure de la table `operateur`
--

CREATE TABLE IF NOT EXISTS `operateur` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nom` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `operateur`
--

INSERT INTO `operateur` (`id`, `nom`) VALUES
(1, 'Tunisiana'),
(2, 'Orange'),
(3, 'Tunisie Télécom');

-- --------------------------------------------------------

--
-- Structure de la table `opvente`
--

CREATE TABLE IF NOT EXISTS `opvente` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dateVente` datetime NOT NULL,
  `montant` float NOT NULL,
  `montantPaye` float NOT NULL,
  `client` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB3DDF0EF7496E4F8` (`client`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `opvente`
--

INSERT INTO `opvente` (`id`, `dateVente`, `montant`, `montantPaye`, `client`) VALUES
(1, '2014-08-25 22:20:49', 32500, 32500, 1),
(2, '2014-08-26 17:07:40', 5100, 5100, 1),
(3, '2014-08-26 17:15:35', 25500, 25500, 1),
(4, '2014-08-26 17:18:23', 5100, 0, 1);

-- --------------------------------------------------------

--
-- Structure de la table `paiement`
--

CREATE TABLE IF NOT EXISTS `paiement` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avance` float DEFAULT NULL,
  `datePaiement` datetime NOT NULL,
  `montant` float NOT NULL,
  `client` bigint(20) DEFAULT NULL,
  `typePaiement` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK36AF49AB33CC4DCC` (`typePaiement`),
  KEY `FK36AF49AB7496E4F8` (`client`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `paiement`
--

INSERT INTO `paiement` (`id`, `avance`, `datePaiement`, `montant`, `client`, `typePaiement`) VALUES
(1, 0, '2014-08-26 17:06:29', 10000, 1, 1),
(2, 0, '2014-08-26 17:06:42', 10000, 1, 2),
(5, 0, '2014-08-26 17:18:25', 43100, 1, 3);

-- --------------------------------------------------------

--
-- Structure de la table `paiementfournisseur`
--

CREATE TABLE IF NOT EXISTS `paiementfournisseur` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avance` float DEFAULT NULL,
  `datePaiement` datetime NOT NULL,
  `montant` float NOT NULL,
  `fournisseur` bigint(20) DEFAULT NULL,
  `typePaiement` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKFE54DFF643316520` (`fournisseur`),
  KEY `FKFE54DFF633CC4DCC` (`typePaiement`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `paiementfournisseur`
--

INSERT INTO `paiementfournisseur` (`id`, `avance`, `datePaiement`, `montant`, `fournisseur`, `typePaiement`) VALUES
(1, 0, '2014-08-26 17:19:55', 20000, 1, 1),
(2, 0, '2014-08-26 17:20:18', 58500, 2, 2),
(3, 0, '2014-08-26 17:28:17', 100000, 1, 1),
(4, 0, '2014-08-26 17:28:17', 100000, 1, 2),
(5, 0, '2014-08-26 17:28:18', 100000, 1, 3);

-- --------------------------------------------------------

--
-- Structure de la table `prelevementcaisse`
--

CREATE TABLE IF NOT EXISTS `prelevementcaisse` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `datePrelevement` datetime NOT NULL,
  `montant` float NOT NULL,
  `motif` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `prixclient`
--

CREATE TABLE IF NOT EXISTS `prixclient` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `prixUnit` float NOT NULL,
  `client` bigint(20) DEFAULT NULL,
  `produit` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC659F9DC92CA4A30` (`produit`),
  KEY `FKC659F9DC7496E4F8` (`client`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `prixclient`
--

INSERT INTO `prixclient` (`id`, `prixUnit`, `client`, `produit`) VALUES
(1, 5.1, 1, 1),
(2, 10.1, 1, 2),
(3, 1.05, 1, 3),
(4, 5.1, 1, 4),
(5, 10.1, 1, 5),
(6, 1.05, 1, 6);

-- --------------------------------------------------------

--
-- Structure de la table `prixfournisseur`
--

CREATE TABLE IF NOT EXISTS `prixfournisseur` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `prixUnit` float NOT NULL,
  `fournisseur` bigint(20) DEFAULT NULL,
  `produit` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7057C73043316520` (`fournisseur`),
  KEY `FK7057C73092CA4A30` (`produit`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Contenu de la table `prixfournisseur`
--

INSERT INTO `prixfournisseur` (`id`, `prixUnit`, `fournisseur`, `produit`) VALUES
(1, 5, 1, 1),
(2, 10, 1, 2),
(3, 1, 1, 3),
(4, 5, 1, 4),
(5, 10, 1, 5),
(6, 1, 1, 6),
(7, 4.95, 2, 1),
(8, 9.95, 2, 2),
(9, 0.95, 2, 3),
(10, 4.95, 2, 4),
(11, 9.95, 2, 5),
(12, 0.95, 2, 6);

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE IF NOT EXISTS `produit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(100) NOT NULL,
  `recharge` bit(1) NOT NULL,
  `operateur` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKED8DCDA94CF3FDA0` (`operateur`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `produit`
--

INSERT INTO `produit` (`id`, `description`, `recharge`, `operateur`) VALUES
(1, 'Carte tunisiana 5d', b'0', 1),
(2, 'Carte tunisiana 10d', b'0', 1),
(3, 'Light tunisiana 1d', b'1', 1),
(4, 'Carte orange 5d', b'0', 2),
(5, 'Carte orange 10d', b'0', 2),
(6, 'Light orange 1d', b'1', 2);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(64) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `role`
--

INSERT INTO `role` (`id`, `description`, `name`) VALUES
(-2, 'Default role for all Users', 'ROLE_USER'),
(-1, 'Administrator role (can edit Users)', 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Structure de la table `typepaiement`
--

CREATE TABLE IF NOT EXISTS `typepaiement` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `typepaiement`
--

INSERT INTO `typepaiement` (`id`, `type`) VALUES
(1, 'espèces'),
(2, 'CP'),
(3, 'CB');

-- --------------------------------------------------------

--
-- Structure de la table `user_role`
--

CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK143BF46A923C03DC` (`role_id`),
  KEY `FK143BF46A3766C7BC` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(-3, -2),
(-1, -2),
(-3, -1),
(-2, -1);

-- --------------------------------------------------------

--
-- Structure de la table `vente`
--

CREATE TABLE IF NOT EXISTS `vente` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `prixAchat` float NOT NULL,
  `prixUnit` float NOT NULL,
  `quantite` int(11) NOT NULL,
  `validated` bit(1) NOT NULL,
  `numTel` bigint(20) DEFAULT NULL,
  `opVente` bigint(20) DEFAULT NULL,
  `produit` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6AE6A5092CA4A30` (`produit`),
  KEY `FK6AE6A509B22C2EC` (`numTel`),
  KEY `FK6AE6A50212D80DC` (`opVente`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Contenu de la table `vente`
--

INSERT INTO `vente` (`id`, `prixAchat`, `prixUnit`, `quantite`, `validated`, `numTel`, `opVente`, `produit`) VALUES
(1, 4950, 5.1, 1000, b'0', NULL, 1, 1),
(2, 9950, 10.1, 1000, b'0', NULL, 1, 2),
(3, 950, 1.05, 1000, b'0', 1, 1, 3),
(4, 4950, 5.1, 1000, b'0', NULL, 1, 4),
(5, 9950, 10.1, 1000, b'0', NULL, 1, 5),
(6, 950, 1.05, 1000, b'0', 2, 1, 6),
(7, 4950, 5.1, 1000, b'0', NULL, 2, 1),
(8, 24850, 5.1, 5000, b'0', NULL, 3, 1),
(9, 5000, 5.1, 1000, b'0', NULL, 4, 1);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `achat`
--
ALTER TABLE `achat`
  ADD CONSTRAINT `FK5857B391EDBA2AE` FOREIGN KEY (`opAchat`) REFERENCES `opachat` (`id`),
  ADD CONSTRAINT `FK5857B3992CA4A30` FOREIGN KEY (`produit`) REFERENCES `produit` (`id`);

--
-- Contraintes pour la table `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `FK74D6432D7496E4F8` FOREIGN KEY (`client`) REFERENCES `client` (`id`);

--
-- Contraintes pour la table `numtel`
--
ALTER TABLE `numtel`
  ADD CONSTRAINT `FKC258A6B57496E4F8` FOREIGN KEY (`client`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `FKC258A6B54CF3FDA0` FOREIGN KEY (`operateur`) REFERENCES `operateur` (`id`);

--
-- Contraintes pour la table `opachat`
--
ALTER TABLE `opachat`
  ADD CONSTRAINT `FKB2B501D843316520` FOREIGN KEY (`fournisseur`) REFERENCES `fournisseur` (`id`);

--
-- Contraintes pour la table `opvente`
--
ALTER TABLE `opvente`
  ADD CONSTRAINT `FKB3DDF0EF7496E4F8` FOREIGN KEY (`client`) REFERENCES `client` (`id`);

--
-- Contraintes pour la table `paiement`
--
ALTER TABLE `paiement`
  ADD CONSTRAINT `FK36AF49AB7496E4F8` FOREIGN KEY (`client`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `FK36AF49AB33CC4DCC` FOREIGN KEY (`typePaiement`) REFERENCES `typepaiement` (`id`);

--
-- Contraintes pour la table `paiementfournisseur`
--
ALTER TABLE `paiementfournisseur`
  ADD CONSTRAINT `FKFE54DFF633CC4DCC` FOREIGN KEY (`typePaiement`) REFERENCES `typepaiement` (`id`),
  ADD CONSTRAINT `FKFE54DFF643316520` FOREIGN KEY (`fournisseur`) REFERENCES `fournisseur` (`id`);

--
-- Contraintes pour la table `prixclient`
--
ALTER TABLE `prixclient`
  ADD CONSTRAINT `FKC659F9DC7496E4F8` FOREIGN KEY (`client`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `FKC659F9DC92CA4A30` FOREIGN KEY (`produit`) REFERENCES `produit` (`id`);

--
-- Contraintes pour la table `prixfournisseur`
--
ALTER TABLE `prixfournisseur`
  ADD CONSTRAINT `FK7057C73092CA4A30` FOREIGN KEY (`produit`) REFERENCES `produit` (`id`),
  ADD CONSTRAINT `FK7057C73043316520` FOREIGN KEY (`fournisseur`) REFERENCES `fournisseur` (`id`);

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `FKED8DCDA94CF3FDA0` FOREIGN KEY (`operateur`) REFERENCES `operateur` (`id`);

--
-- Contraintes pour la table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK143BF46A3766C7BC` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`id`),
  ADD CONSTRAINT `FK143BF46A923C03DC` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

--
-- Contraintes pour la table `vente`
--
ALTER TABLE `vente`
  ADD CONSTRAINT `FK6AE6A50212D80DC` FOREIGN KEY (`opVente`) REFERENCES `opvente` (`id`),
  ADD CONSTRAINT `FK6AE6A5092CA4A30` FOREIGN KEY (`produit`) REFERENCES `produit` (`id`),
  ADD CONSTRAINT `FK6AE6A509B22C2EC` FOREIGN KEY (`numTel`) REFERENCES `numtel` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
