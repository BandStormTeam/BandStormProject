package bandstorm

class Tag {
    String name;

    static constraints = {
        name blank: false, size: 1..16
    }
}
