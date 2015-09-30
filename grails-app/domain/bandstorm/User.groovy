package bandstorm

class User {

    String username;
    String email;
    String firstName;
    String LastName;
    String birthDate;
    String country;
    Date dateCreated;

    static hasMany = [posts : Status, interests : Tag, participates : Event, mangages : Event, groupsFollowed : Group ]

    static constraints = {
    }
}
