
DROP TABLE IF EXISTS `lottos`;
CREATE TABLE `lottos` (
  `INDEX` int(11) NOT NULL AUTO_INCREMENT,
  `FIRST` int(5) DEFAULT NULL,
  `SECOND` int(5) DEFAULT NULL,
  `THIRD` int(5) DEFAULT NULL,
  `FOURTH` int(5) DEFAULT NULL,
  `FIFTH` int(5) DEFAULT NULL,
  `SIXTH` int(5) DEFAULT NULL,
  PRIMARY KEY (`INDEX`)
);

DROP TABLE IF EXISTS `rank`;
CREATE TABLE `rank` (
  `INDEX` int(11) NOT NULL AUTO_INCREMENT,
  `FIRST` int(3) DEFAULT NULL,
  `SECOND` int(3) DEFAULT NULL,
  `THIRD` int(3) DEFAULT NULL,
  `FOURTH` int(3) DEFAULT NULL,
  `FIFTH` int(3) DEFAULT NULL,
  PRIMARY KEY (`INDEX`)
);