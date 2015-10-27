package bandstorm

import bandstorm.dao.UserDaoService

/**
 * Domain class that represents users,
 * and different event, lights and groups related
 * to them
 */
class User extends SecUser {

    String email
    String firstName
    String lastName
    Date birthDate
    String country
    Date dateCreated
    String urlAvatar

    static hasMany = [posts : Status, interests : Tag, participates : Event, manages : Event, groupsFollowed : Band ]


    static constraints = {
        username blank: false, nullable: false, minSize: 3
        email email : true, blank : false
        firstName blank: false, nullable: false
        lastName blank: false, nullable: false
        country blank: false, nullable: false
        birthDate max: new Date()
        posts nullable: true
        interests nullable: true
        participates nullable: true
        manages nullable: true
        groupsFollowed nullable: true
        urlAvatar blank: true, nullable: true, url: true, minSize   : 0
        password blank: false, nullable: false, minSize: 6
    }

    User(String username, String password, String email,
         String firstName, String lastName,
         Date birthDate, String country, String urlavatar) {
        super(username, password)
        this.email = email
        this.firstName = firstName
        this.lastName = lastName
        this.birthDate = birthDate
        this.country = country
        this.urlAvatar = urlAvatar;
    }

    transient userDaoService

    /**
     * Check if a follow relation exist between two users
     * @param follower User follower
     * @return true if relation exist else false
     */
    def isFollowed(User follower){
        boolean res = false
        if(userDaoService.findFollowByFollowerAndFollowed(follower,this)){
            res = true
        }
        res
    }
}
