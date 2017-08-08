package cn.edu.cup.lps.geometry

class VertexLocation {

    LogicalLocation logicalLocation
    OneDimensional oneDimensional

    static mapping = {}

    static constraints = {
        oneDimensional(nullable: true)
        logicalLocation(nullable: true)
    }

    String toString() {
        return "${oneDimensional}"
    }
}
