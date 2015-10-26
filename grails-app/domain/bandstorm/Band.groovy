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

    static hasMany = [tags : Tag]

    static constraints = {
        name blank:false,maxSize: 35, minSize: 3
        dateCreated date: true
        description blank: false,maxSize: 550
        address nullable: true, blank: true
    }
}
