package bandstorm

class Status {

    String url
    String content
    Integer lightCount
    Date dateCreated
    Date lastUpdated
    User author

    static constraints = {
        content maxSize: 300,minSize: 1, nullable: false
        lightCount min : 0, max: 100000
        url nullable: true
    }
}
