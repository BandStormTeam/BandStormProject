package bandstorm

/**
 * A domain class who link an user and a group
 */
class GroupMember {

    Date dateCreated
    Group group
    User user

    static constraints = {
    }
}
