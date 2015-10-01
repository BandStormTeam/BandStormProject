package bandstorm

class User {

    String username
    String email
    String firstName
    String lastName
    Date birthDate
    String country
    Date dateCreated

    static hasMany = [posts : Status, interests : Tag, participates : Event, manages : Event, groupsFollowed : Group ]

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
    }
}
