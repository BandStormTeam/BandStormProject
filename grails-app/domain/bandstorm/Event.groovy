package bandstorm

/**
 * Represent the event an user can organize or participate to.
 * Basically, for the first version of the product an event is a Concert or Festival.
 * In the future we can imagine event to also be a collaborative music project development
 * or crowdfunding musical projects and so on. */

class Event {

    String name
    Date dateCreated
    String address
    String description

    static hasMany = [tags : Tag]

    static constraints = {
        name blank: false, maxSize: 35, minSize: 3
        address maxSize: 200, minSize: 10
        description minSize: 2
        tags nullable: true
    }
}
