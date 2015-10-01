package bandstorm

/**
 * A domain class who link a follower user and a followed user
 */
class Follow {

    Date dateCreated
    User follower
    User followed

    static constraints = {
    }
}
