package cn.edu.cup.lps.hydraulic

import grails.gorm.transactions.Transactional
import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class AmbientTemperatureController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond AmbientTemperature.list(params), model:[ambientTemperatureCount: AmbientTemperature.count()]
    }

    def show(AmbientTemperature ambientTemperature) {
        respond ambientTemperature
    }

    def create() {
        respond new AmbientTemperature(params)
    }

    @Transactional
    def save(AmbientTemperature ambientTemperature) {
        if (ambientTemperature == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (ambientTemperature.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond ambientTemperature.errors, view:'create'
            return
        }

        ambientTemperature.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'ambientTemperature.label', default: 'AmbientTemperature'), ambientTemperature.id])
                redirect ambientTemperature
            }
            '*' { respond ambientTemperature, [status: CREATED] }
        }
    }

    def edit(AmbientTemperature ambientTemperature) {
        respond ambientTemperature
    }

    @Transactional
    def update(AmbientTemperature ambientTemperature) {
        if (ambientTemperature == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (ambientTemperature.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond ambientTemperature.errors, view:'edit'
            return
        }

        ambientTemperature.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ambientTemperature.label', default: 'AmbientTemperature'), ambientTemperature.id])
                redirect ambientTemperature
            }
            '*'{ respond ambientTemperature, [status: OK] }
        }
    }

    @Transactional
    def delete(AmbientTemperature ambientTemperature) {

        if (ambientTemperature == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        ambientTemperature.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ambientTemperature.label', default: 'AmbientTemperature'), ambientTemperature.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ambientTemperature.label', default: 'AmbientTemperature'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
