-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 30 avr. 2024 à 22:57
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `projetpoo3`
--

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

CREATE TABLE `article` (
  `article_id` int(11) NOT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `auteurs` text NOT NULL,
  `descriptionArticle` varchar(200) NOT NULL,
  `chemin_pdf` varchar(255) DEFAULT NULL,
  `statut` enum('NON_ATTRIBUE','EN_EVALUATION','ACCEPTE','REJETE') DEFAULT NULL,
  `conference_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `article`
--

INSERT INTO `article` (`article_id`, `titre`, `auteurs`, `descriptionArticle`, `chemin_pdf`, `statut`, `conference_id`) VALUES
(1, 'tiiiitre', '', '', 'c:/user/path', 'NON_ATTRIBUE', 1);

-- --------------------------------------------------------

--
-- Structure de la table `comiteorganisateur`
--

CREATE TABLE `comiteorganisateur` (
  `comite_organisateur_id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `institution` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `comitescientifique`
--

CREATE TABLE `comitescientifique` (
  `comite_scientifique_id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `institution` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `comitescientifique`
--

INSERT INTO `comitescientifique` (`comite_scientifique_id`, `nom`, `email`, `institution`) VALUES
(1, 'mohamed', 'mohamed21@gmail.com', 'isgt'),
(2, 'halim', 'halim01@gmail.com', 'isgtt');

-- --------------------------------------------------------

--
-- Structure de la table `conference`
--

CREATE TABLE `conference` (
  `conference_id` int(11) NOT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `date_debut` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  `lieu` varchar(255) DEFAULT NULL,
  `comite_organisateur_id` int(11) DEFAULT NULL,
  `comite_scientifique_id` int(11) DEFAULT NULL,
  `frais_inscription` decimal(10,2) DEFAULT NULL,
  `date_limite_soumission` date DEFAULT NULL,
  `date_limite_inscription` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `conference`
--

INSERT INTO `conference` (`conference_id`, `titre`, `date_debut`, `date_fin`, `lieu`, `comite_organisateur_id`, `comite_scientifique_id`, `frais_inscription`, `date_limite_soumission`, `date_limite_inscription`) VALUES
(1, 'jobfair', '2024-04-30', '2024-05-02', 'bardo', NULL, NULL, NULL, '2024-05-02', '2024-04-30');

-- --------------------------------------------------------

--
-- Structure de la table `conferencierinvite`
--

CREATE TABLE `conferencierinvite` (
  `conferencier_invite_id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `institution` varchar(255) DEFAULT NULL,
  `pays` varchar(255) DEFAULT NULL,
  `titre_presentation` varchar(255) DEFAULT NULL,
  `conference_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `conferencierinvite`
--

INSERT INTO `conferencierinvite` (`conferencier_invite_id`, `nom`, `email`, `institution`, `pays`, `titre_presentation`, `conference_id`) VALUES
(1, 'azertyu', 'zertyu', 'zertyu', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `participant`
--

CREATE TABLE `participant` (
  `participant_id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `institution` varchar(255) DEFAULT NULL,
  `mode_paiement` enum('CHEQUE','VIREMENT_BANCAIRE','BON_COMMANDE','ESPECE') DEFAULT NULL,
  `conference_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`article_id`),
  ADD KEY `conference_id` (`conference_id`);

--
-- Index pour la table `comiteorganisateur`
--
ALTER TABLE `comiteorganisateur`
  ADD PRIMARY KEY (`comite_organisateur_id`);

--
-- Index pour la table `comitescientifique`
--
ALTER TABLE `comitescientifique`
  ADD PRIMARY KEY (`comite_scientifique_id`);

--
-- Index pour la table `conference`
--
ALTER TABLE `conference`
  ADD PRIMARY KEY (`conference_id`),
  ADD KEY `comite_organisateur_id` (`comite_organisateur_id`),
  ADD KEY `comite_scientifique_id` (`comite_scientifique_id`);

--
-- Index pour la table `conferencierinvite`
--
ALTER TABLE `conferencierinvite`
  ADD PRIMARY KEY (`conferencier_invite_id`),
  ADD KEY `conference_id` (`conference_id`);

--
-- Index pour la table `participant`
--
ALTER TABLE `participant`
  ADD PRIMARY KEY (`participant_id`),
  ADD KEY `conference_id` (`conference_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `article`
--
ALTER TABLE `article`
  MODIFY `article_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `comiteorganisateur`
--
ALTER TABLE `comiteorganisateur`
  MODIFY `comite_organisateur_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `comitescientifique`
--
ALTER TABLE `comitescientifique`
  MODIFY `comite_scientifique_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `conference`
--
ALTER TABLE `conference`
  MODIFY `conference_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `conferencierinvite`
--
ALTER TABLE `conferencierinvite`
  MODIFY `conferencier_invite_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `participant`
--
ALTER TABLE `participant`
  MODIFY `participant_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `article`
--
ALTER TABLE `article`
  ADD CONSTRAINT `article_ibfk_1` FOREIGN KEY (`conference_id`) REFERENCES `conference` (`conference_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `conference`
--
ALTER TABLE `conference`
  ADD CONSTRAINT `conference_ibfk_1` FOREIGN KEY (`comite_scientifique_id`) REFERENCES `comitescientifique` (`comite_scientifique_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `conference_ibfk_2` FOREIGN KEY (`comite_organisateur_id`) REFERENCES `comiteorganisateur` (`comite_organisateur_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `conferencierinvite`
--
ALTER TABLE `conferencierinvite`
  ADD CONSTRAINT `conferencierinvite_ibfk_1` FOREIGN KEY (`conference_id`) REFERENCES `conference` (`conference_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `participant`
--
ALTER TABLE `participant`
  ADD CONSTRAINT `participant_ibfk_1` FOREIGN KEY (`conference_id`) REFERENCES `conference` (`conference_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
