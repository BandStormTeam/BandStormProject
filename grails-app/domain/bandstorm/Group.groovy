package bandstorm

class Group {
    String name
    Date dateCreated
    String description

    static constraints = {
        name blank:false
        dateCreated date: true
        description blank: false
    }
}
