package bandstorm

/**
 * represents a category or user preference
 */
class Tag {
    String name;

    static constraints = {
        name blank: false, size: 1..16
    }
}
