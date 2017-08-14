package cn.edu.cup.lps.hydraulic

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class OverallHeatTransferCoefficientController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond OverallHeatTransferCoefficient.list(params), model:[overallHeatTransferCoefficientCount: OverallHeatTransferCoefficient.count()]
    }

    def show(OverallHeatTransferCoefficient overallHeatTransferCoefficient) {
        respond overallHeatTransferCoefficient
    }

    def create() {
        respond new OverallHeatTransferCoefficient(params)
    }

    @Transactional
    def save(OverallHeatTransferCoefficient overallHeatTransferCoefficient) {
        if (overallHeatTransferCoefficient == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (overallHeatTransferCoefficient.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond overallHeatTransferCoefficient.errors, view:'create'
            return
        }

        overallHeatTransferCoefficient.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'overallHeatTransferCoefficient.label', default: 'OverallHeatTransferCoefficient'), overallHeatTransferCoefficient.id])
                redirect overallHeatTransferCoefficient
            }
            '*' { respond overallHeatTransferCoefficient, [status: CREATED] }
        }
    }

    def edit(OverallHeatTransferCoefficient overallHeatTransferCoefficient) {
        respond overallHeatTransferCoefficient
    }

    @Transactional
    def update(OverallHeatTransferCoefficient overallHeatTransferCoefficient) {
        if (overallHeatTransferCoefficient == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (overallHeatTransferCoefficient.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond overallHeatTransferCoefficient.errors, view:'edit'
            return
        }

        overallHeatTransferCoefficient.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'overallHeatTransferCoefficient.label', default: 'OverallHeatTransferCoefficient'), overallHeatTransferCoefficient.id])
                redirect overallHeatTransferCoefficient
            }
            '*'{ respond overallHeatTransferCoefficient, [status: OK] }
        }
    }

    @Transactional
    def delete(OverallHeatTransferCoefficient overallHeatTransferCoefficient) {

        if (overallHeatTransferCoefficient == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        overallHeatTransferCoefficient.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'overallHeatTransferCoefficient.label', default: 'OverallHeatTransferCoefficient'), overallHeatTransferCoefficient.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'overallHeatTransferCoefficient.label', default: 'OverallHeatTransferCoefficient'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
