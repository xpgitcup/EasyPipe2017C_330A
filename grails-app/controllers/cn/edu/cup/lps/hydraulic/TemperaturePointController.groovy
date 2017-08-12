package cn.edu.cup.lps.hydraulic

import grails.gorm.transactions.Transactional
import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class TemperaturePointController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TemperaturePoint.list(params), model:[temperaturePointCount: TemperaturePoint.count()]
    }

    def show(TemperaturePoint temperaturePoint) {
        respond temperaturePoint
    }

    def create() {
        respond new TemperaturePoint(params)
    }

    @Transactional
    def save(TemperaturePoint temperaturePoint) {
        if (temperaturePoint == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (temperaturePoint.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond temperaturePoint.errors, view:'create'
            return
        }

        temperaturePoint.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'temperaturePoint.label', default: 'TemperaturePoint'), temperaturePoint.id])
                redirect temperaturePoint
            }
            '*' { respond temperaturePoint, [status: CREATED] }
        }
    }

    def edit(TemperaturePoint temperaturePoint) {
        respond temperaturePoint
    }

    @Transactional
    def update(TemperaturePoint temperaturePoint) {
        if (temperaturePoint == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (temperaturePoint.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond temperaturePoint.errors, view:'edit'
            return
        }

        temperaturePoint.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'temperaturePoint.label', default: 'TemperaturePoint'), temperaturePoint.id])
                redirect temperaturePoint
            }
            '*'{ respond temperaturePoint, [status: OK] }
        }
    }

    @Transactional
    def delete(TemperaturePoint temperaturePoint) {

        if (temperaturePoint == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        temperaturePoint.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'temperaturePoint.label', default: 'TemperaturePoint'), temperaturePoint.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'temperaturePoint.label', default: 'TemperaturePoint'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
