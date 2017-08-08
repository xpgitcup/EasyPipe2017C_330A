package cn.edu.cup.userLibrary

import java.util.jar.JarFile

class UserLibrary {

    def commonService

    String name
    String description
    String fileName
    String developer
    Date   uploadDate

    static belongsTo = [userLibraryClassify: UserLibraryClassify]

    static hasMany = [classes: UserClass]

    static constraints = {
        name(unique: true)
        description(nullable: true)
        fileName()
        developer(nullable: true)
        uploadDate(nullable: true)
    }

    String toString() {
        return "${name}/${description}"
    }

    //------------------------------------------------------------------------------------------------------------------
    def autoImportClasses() {
        ClassLoader parent = getClass().getClassLoader();
        GroovyClassLoader loader = new GroovyClassLoader(parent);
        def extName = fileName.substring(fileName.lastIndexOf(".")+1)
        def fullFileName = "${userLibraryClassify.path}/${fileName}"
        def cFile = new File(fullFileName)
        def clazzList = []
        if (cFile) {
            switch (extName) {
                case 'jar':
                    println('jar file')
                    break
                case 'java':
                    println('java file')
                    break
                case 'groovy':
                    println('groovy file')
                    Class aClass = loader.parseClass(cFile)
                    println("${aClass}")
                    clazzList.add(aClass)
                    aClass.declaredFields[0].type
                    break
            }
        }
        //--------------------------------------------------------------------------------------------------------------
        clazzList.each {item->
            def nc = new UserClass(
                    name: item.name,
                    description: '待补充',
                    userLibrary: this
            )
            nc.save(flush: true)

            def fs = item.declaredFields
            fs.each {ef ->
                def nf = new UserClassField(
                        userClass: nc,
                        name: ef.name,
                        fieldType: "${ef.type}"
                )
                nf.save(flush: true)
            }
        }
        //--------------------------------------------------------------------------------------------------------------
        return clazzList
    }


    def getJarEntries() {
        def file = new File(realFileName())
        JarFile jf = new JarFile(file)
        def ens = []
        def es = jf.entries()
        es.each() {e->
            println "${e}"
            ens.add("${e}")
        }
        return ens
    }

    String realPath() {
        def uc = userLibraryClassify.path
        def webRootDir = commonService.getServletContext().getRealPath("/")
        return "${webRootDir}/userLibs/${uc}"
    }

    String realFileName() {
        def path = realPath()
        def destDir = "${path}/${fileName}"
    }

    Boolean status() {
        def rfileName = realFileName()
        def f = new File(rfileName)
        return f.exists()
    }

    static Boolean hasImported(fileName) {
        def found = UserLibrary.findAllByFileName(fileName)
        return (found.size()>0)
    }
}
