package easypipe2017c_330a

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration

class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        //Locale.setDefault(Locale.SIMPLIFIED_CHINESE)  //没有用
        GrailsApp.run(Application, args)
    }
}