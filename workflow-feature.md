Afin de développer au mieux l'application, et d'avoir le meilleur arbre d'historique possible, nous allons utiliser un workflown organisé en branches.

# Le workflow feature branch
Le principe est simple : une issue = une branche.

Ci-dessous, l'arbre que nous devrions avoir : 
![](http://nicoespeon.com/assets/img/git/github-flow-branching-model.jpg)

## Le principe
- Tout ce qui est dans master peut être déployé en production : ça compile, ça fonctionne.
- Créer des branches nommées ainsi : `milestone/noIssue-recapissue`
- Pousser sur `origin` régulièrement : Permet de communiquer sur votre avancement
- Ouvrir des Pulls Requests.
- Fusionner uniquement après une revue de code.

Je vais détailler ces différents étapes, et concrètement de quelle manière nous allons appliquer ces règles.

## Allez, zou, nouvelle branche !
Vous avez choisis une issue ? Vous pouvez commencer à la développer ? Première chose, s'affecter l'issue ! 
Super important pour éviter que vous soyiez deux à la développer en même temps, et pour qu'on ai une idée de notre avance/retard dans le sprint.

Ensuite…
```
$ git checkout -b milestone/noIssue-recapIssue 
```
Cette commande va créer la nouvelle branche, et vous y faire basculer automatiquement dessus. 

À noter qu'elle est strictement identique à 
```
$ git branch milestone/noIssue-recapIssue 
$ git checkout milestone/noIssue-recapIssue 
```

Si vous souhaitez afficher les branches disponibles en locales, et sur quelle branche vous êtes situés, vous pouvez faire un 
```
$ git branch
```
## Et on commit, on commit
Pour rappel… Faites le **maximum* de commits possibles ! Plus on a de commits, plus on a de points de restaurations, moins on perdra de boulot en cas de problème. Grossomodo, vous devriez faire un commit par petite modification. 

Par exemple, vous codez l'inscription d'un membre ? Un commit pour l'ihm, un commit pour le modèle, un commit pour le contrôleur

Référencez le numéro de l'issue dans votre commit, ça permet de lier facilement les commits avec les issues.

## Poussez, Poussez !
```
git push origin milestone/noIssue-recapIssue
```
Pousser sur `origin` régulièrement, c'est important, c'est chouette, ça ne mange pas de pain, et c'est rapide. 
Pour plusieurs raison
- Déjà, ça fait une sauvegarde… Jamais à l’abri d'un problème
- Vous communiquez sur votre avancement : on sait ce que vous faites, que vous bossez (ahah), et si on veux jeter un coup d'œil, on peut
- Si vous avez plusieurs pc, vous pouvez bosser des différents sans trop de problème en faisant ces pushs, aucun risques de problèmes vous êtes le seul sur cette branche.

## C'est terminé ? On ouvre une Pull Requests !
Tout d'abord, assurez d'avoir bien poussé la dernière version du code via un `git push origin milestone/noIssue-recapIssue`. 

Ensuite, on va sur Github, et on créé une PR via l'outil adéquat, on demande à fusionner votre branche dans master. 
Un message clair détaillant celle-ci. 

## Je veux encore bosser moi ! 
Si vous avez envie de faire beaucoup de boulot, votre dernière fonctionnalité n'aura pas eu le temps d'être relue et intégré… Mais pas de soucis, y'a toujours du boulot ne nécessitant pas ce que vous venez de faire.

Pour recommencer à bosser, on retourne sur master.
```
git checkout master 
```
Vous êtes donc sur la branche stable, pour recommencer à bosser… Il suffit de repartir au début de ce document, et ça repart ! 

## Intégration continue ? 
Le système des pull requests va être utile à plusieurs choses. Tout d'abord, ça va forcer la relecture de code comme dit précédemment. Déjà, ça peut permettre de voir des coquilles où des trucs pas beau. Et de vérifier que les besoins de la user story soient bien réglés. 

Ensuite, ça permet de facilement intégrer le boulot dans une branche master, qui comme dit plusieurs fois, sera systématiquement **stable**.

Une issue sera fermée au moment de l'intégration =)

## Au boulot !
BOn, ben maintenant, vous êtes fin prêt, on peut bosser ! 

Enjoy guys, and have fun ! :)


