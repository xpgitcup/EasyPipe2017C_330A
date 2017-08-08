package cn.edu.cup.userLibrary

class UserClass {

    String name
    String description

    static belongsTo = [userLibrary: UserLibrary]

    static hasMany = [fields: UserClassField, methods: UserClassMethod]

    static constraints = {
        name()
        description()
        userLibrary()
        fields()
        methods()
    }

    String toString() {
        return "${name}"
    }

    //------------------------------------------------------------------------------------------------------------------
    /*
     * 调入类的代码
     * */
    def loadClass() {
        ClassLoader parent = getClass().getClassLoader();
        GroovyClassLoader loader = new GroovyClassLoader(parent);
        def libFile = new File(userLibrary.realFileName())
        println "classInstance ${libFile}"
        loader.addURL(libFile.toURL())
        //def clazz = loader.parseClass(name)
        def clazz = loader.loadClass(name)
        println "classInstance ${clazz}"
        return clazz
    }

    /*
     * 创建类的实例
     * */
    def classInstance() {
        def object = loadClass().newInstance()
        return object
    }

    /*
     * 返回类定义的方法
     * */
    def classInstanceMethods() {
        def cc = loadClass()
        def ms = cc.getDeclaredMethods()
        return ms
    }

    /*
     * 返回类定义的字段
     * */
    def classInstanceFields() {
        def cc = loadClass()
        def ms = cc.getDeclaredFields()
        return ms
    }

}
