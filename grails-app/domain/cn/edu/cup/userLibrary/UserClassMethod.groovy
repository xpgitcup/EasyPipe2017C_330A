package cn.edu.cup.userLibrary

class UserClassMethod {

    String name
    String resultType

    static belongsTo = [userClass: UserClass]

    static constraints = {
        name()
        resultType()
    }

    String toString() {
        return "${name}/${resultType}"
    }
}
