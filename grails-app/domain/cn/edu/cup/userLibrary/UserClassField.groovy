package cn.edu.cup.userLibrary

class UserClassField {

    String name
    String fieldType

    static belongsTo = [userClass: UserClass]

    static constraints = {
        name()
        fieldType()
    }

    String toString() {
        return "${name}/${fieldType}"
    }
}
