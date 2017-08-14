package cn.edu.cup.lps.hydraulic

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class HeatTransferCoefficientPointController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond HeatTransferCoefficientPoint.list(params), model:[heatTransferCoefficientPointCount: HeatTransferCoefficientPoint.count()]
    }

    def show(HeatTransferCoefficientPoint heatTransferCoefficientPoint) {
        respond heatTransferCoefficientPoint
    }

    def create() {
        respond new HeatTransferCoefficientPoint(params)
    }

    @Transactional
    def save(HeatTransferCoefficientPoint heatTransferCoefficientPoint) {
        if (heatTransferCoefficientPoint == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (heatTransferCoefficientPoint.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond heatTransferCoefficientPoint.errors, view:'create'
            return
        }

        heatTransferCoefficientPoint.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'heatTransferCoefficientPoint.label', default: 'HeatTransferCoefficientPoint'), heatTransferCoefficientPoint.id])
                redirect heatTransferCoefficientPoint
            }
            '*' { respond heatTransferCoefficientPoint, [status: CREATED] }
        }
    }

    def edit(HeatTransferCoefficientPoint heatTransferCoefficientPoint) {
        respond heatTransferCoefficientPoint
    }

    @Transactional
    def update(HeatTransferCoefficientPoint heatTransferCoefficientPoint) {
        if (heatTransferCoefficientPoint == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (heatTransferCoefficientPoint.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond heatTransferCoefficientPoint.errors, view:'edit'
            return
        }

        heatTransferCoefficientPoint.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'heatTransferCoefficientPoint.label', default: 'HeatTransferCoefficientPoint'), heatTransferCoefficientPoint.id])
                redirect heatTransferCoefficientPoint
            }
            '*'{ respond heatTransferCoefficientPoint, [status: OK] }
        }
    }

    @Transactional
    def delete(HeatTransferCoefficientPoint heatTransferCoefficientPoint) {

        if (heatTransferCoefficientPoint == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        heatTransferCoefficientPoint.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'heatTransferCoefficientPoint.label', default: 'HeatTransferCoefficientPoint'), heatTransferCoefficientPoint.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'heatTransferCoefficientPoint.label', default: 'HeatTransferCoefficientPoint'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
