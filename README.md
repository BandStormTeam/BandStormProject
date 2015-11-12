# BandStormProject
[![](https://travis-ci.org/BandStormTeam/BandStormProject.svg?branch=master)](https://travis-ci.org/BandStormTeam/BandStormProject) [![Coverage Status](https://coveralls.io/repos/BandStormTeam/BandStormProject/badge.svg?branch=master&service=github)](https://coveralls.io/github/BandStormTeam/BandStormProject?branch=master)
[![Average time to resolve an issue](http://isitmaintained.com/badge/resolution/BandStormTeam/BandStormProject.svg)](http://isitmaintained.com/project/BandStormTeam/BandStormProject "Average time to resolve an issue") [![Percentage of issues still open](http://isitmaintained.com/badge/open/BandStormTeam/BandStormProject.svg)](http://isitmaintained.com/project/BandStormTeam/BandStormProject "Percentage of issues still open")

Bandstorm est un réseau social pour des amateurs de musique. Celui-ci a pour but de mettre en relations des utilisateurs par le biais de communautés. Ces communautés sont constitués en fonction de préférences musicales. 

Les utilisateurs peuvent proposer aux membres de leurs communautés des liens vers des  morceaux de musique, des évènements ou des concerts.
Chaque utilisateur possède un compte personnalisé. Il peut suivre des membres, des groupes et des communautés. Il a une barre de recherche à sa disposition.
Un utilisateur peut “lighter” les statuts (évènements, messages...)  des personnes qu’il suit.

## Membres
Les collaborateurs sont les suivants : 
- BIRONNEAU Julian — [@johnSilver7](https://github.com/johnSilver7)
- MAGRAS Steve — [@smagras](https://github.com/smagras)
- ROLETTO Dylan — [@vlaks](https://github.com/vlaks)
- de ROQUEMAUREL Antoine — [@aroquemaurel](https://github.com/aroquemaurel/)
- ZACCARIA Zyat — [@SirAbel](https://github.com/SirAbel)



## Environnement de développement
|           Environment           |                Version                |
|              ---                |                  ---                  |
| Development Framework           | Grails 2.3.11                         |
| IDE                             | IntelliJ 14.1.1                       |
| Test framework                  | Spock                                 |
| SGBDR                           | H2 (dev), PostgreSQL (prod)           |
| Test coverage                   | Plugin coverage 2.0.3-3 (Cobertura)   |
| Intégration continue            | Travis CI                             |
| Version Controle System         | Git                                   |
| Shared Git repository           | Github                                |


## Nouveaux développeurs
Si vous souhaitez contribuez au projet, jetez un oeil au [workflow feature](workflow-feature.md)

## Déploiement
La branche master de l'application est déployée de façon continue et automatique, à l'aide de Heroku.
Le site est accessible à l'adresse suivante : https://m2dl-bandstorm.herokuapp.com/
