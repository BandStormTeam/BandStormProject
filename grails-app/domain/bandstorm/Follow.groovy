package bandstorm

class Follow {

    Date dateCreated;

    static hasOne = [follower : User, followed : User]

    static constraints = {
    }
}
