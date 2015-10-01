Un petit tuto rapidement pour Git.
C'est vraiment les commandes de base, pour le reste, hop hop hop, on cherche dans la doc :) (vous pouvez toujours me poser des questions ^_^)

## Configuration
Première chose… Super importante : 
``` Sh
$ git config --global user.name "Prénom NOM"
$ git config --global user.email "adresse@serveur.com"
```

Votre adresse mail *doit* être la même que celle de Github ;)

C'est ça qui fera le lien entre vos commits et votre utilisateur.

## Gestion des commits
### Rajouter un fichier ou un répertoire au dépôt
Le dépôt dispose d'un index des fichiers qu'il contient. Pour rajouter un fichier ou un répertoire, il suffit de faire ceci :

``` Sh
$ git add path
```
Si le chemin est un répertoire, le répertoire lui-même ainsi que tout son contenu (récursivement) est alors rajouté dans l'index.
Vous ne pouvez pas commiter un fichier s'il n'est pas dans l'index.

### Valider un ou plusieurs fichiers / répertoires : Commit
``` Sh
$ git commit file1 file2 folder1 folder2
```

La commande `git commit -a` permet de commiter tout ce qu'il y a à commiter… Mais je l'aime pas trop, tout simplement parce que ça va vous donner envie de faire de gros commits qui veulent rien dire; Lister les fichiers et/ou dossier de cette manière est donc plus intéressant.

On peut utiliser l'option -m pour rajouter le message de validation directement. Sans cela, un éditeur va s'ouvrir pour recueillir ce message. S'il est vide, le processus de validation est abandonné.

Si l'éditeur par défaut est vim : i pour être insertion, entrer le message, :wq pour quitter.

### Vérifier le statut du dépôt
``` Sh
$ git status
```
Permet de vérifier les fichiers à ajouter, supprimer ou commiter, à faire systématiquement avant un commit.

## Voir le différentiel entre les fichiers actuels et la dernière validation
``` Sh
$ git diff
```

## Supprimer toutes les modifications depuis le dépôt
``` Sh
$ git reset --hard
```
Le dépôt revient à son état après le dernier commit, mais cette commande ne touche pas les fichiers ne faisant pas partie du dépôt. Mais faites attention… Ça supprimera toutes vos modifications que vous n'avez pas commit, ça peut être dangereux.

## Intéragir avec le serveur
### Et on `push`
Pour envoyer vos modifications vers le serveur, c'est : 
``` Sh
$ git push origin master
```
Pour envoyer vers la branche principale.

Pour envoyer vers une branche distante, ça sera 
``` Sh
$ git push origin mySuperBranch
```

Faites des push régulièrement, dans l'idéal, une feature = un push. J'aimerai bien qu'on mette en place un bon workflow… On va voir de comment vous vous en sortez ;)

### Et maintenant, on `pull`
Pour récupérer les modifications
``` Sh
$ git pull origin master
```

Et pour récupérer les modifications d'une branche particulière

``` Sh
$ git push origin mySuperBranch
```

## Les branches
### Lister les branches disponibles sur le dépôt local
``` Sh
$ git branch
```
### Lister les branches disponibles sur les dépôts local et distants
``` Sh
$ git branch -a
```

### Créer une nouvelle branche
``` Sh
$ git checkout -b new_branch
```

### Basculer vers une branche
``` Sh
$ git checkout branch_name
```

### Merger une branche vers la branche courante
``` Sh
$ git merge nom_branche
```

## Ressources
* Dans l'idéal, j'aimerai bien qu'on arrive à mettre ça en place : http://nvie.com/posts/a-successful-git-branching-model/ Mais bon, ça dépendra de vous… :p
* http://nvie.com/posts/a-successful-git-branching-model/
* http://openclassrooms.com/courses/gerez-vos-codes-source-avec-git
* https://try.github.io/levels/1/challenges/1 (juste parce que c'est fun)
* http://www.vogella.com/tutorials/Git/article.html
* http://rogerdudler.github.io/git-guide/

## Configuration file
``` gitconfig
[color]
    diff = auto
    status = auto
    branch = auto
[user]
    name = PrÉNOM NOM
    email = ADRESSE@MAIL.FR
[pager]
    diff =
[alias]
    st = status
    lg = log --pretty=format:'%Cred%h%Creset -%C(yellow)%d%Creset %s %Cgreen(%cr) %C(bold blue)<%an>%Creset' --abbrev-commit

[color "status"]
    added = green
    changed = yellow
    untracked = cyan

[push]
    default = matching
```
