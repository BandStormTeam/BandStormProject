package bandstorm

class GroupMember {

    Date dateCreated;

    static hasOne = [group : Group, user : User]

    static constraints = {
    }
}
