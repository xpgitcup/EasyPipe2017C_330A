package cn.edu.cup.userLibrary

class UserLibraryClassify {

    String name
    String path
    String description

    static hasMany = [userLibraries: UserLibrary]

    static mapping = {
    }

    static constraints = {
        name(unique: true)
        path(nullable: false)
        description(nullable: true)
    }

    String toString() {
        return "${name}/${path}"
    }
}
