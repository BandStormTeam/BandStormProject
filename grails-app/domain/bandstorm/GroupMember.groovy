package bandstorm

/**
 * A domain class who link an user and a group
 */
class GroupMember {

    Date dateCreated
    Band band
    User user

    static constraints = {
    }
}
