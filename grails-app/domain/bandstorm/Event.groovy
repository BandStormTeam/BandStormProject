package bandstorm

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
