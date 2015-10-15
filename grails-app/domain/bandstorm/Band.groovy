package bandstorm

/**
 * Represent a group of people (users and external real persons) in the scope of a band.
 * In the future this class could also represent a musical association from a town for instance.
 * */

class Band {
    String name
    Date dateCreated
    String description
    String address

    static constraints = {
        name blank:false
        dateCreated date: true
        description blank: false
        address nullable: true, blank: true
    }
}
