package cn.edu.cup.dictionary

import grails.gorm.transactions.Transactional
import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class DataItemAController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DataItemA.list(params), model:[dataItemACount: DataItemA.count()]
    }

    def show(DataItemA dataItemA) {
        respond dataItemA
    }

    def create() {
        respond new DataItemA(params)
    }

    @Transactional
    def save(DataItemA dataItemA) {
        if (dataItemA == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dataItemA.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dataItemA.errors, view:'create'
            return
        }

        dataItemA.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dataItemA.label', default: 'DataItemA'), dataItemA.id])
                redirect dataItemA
            }
            '*' { respond dataItemA, [status: CREATED] }
        }
    }

    def edit(DataItemA dataItemA) {
        respond dataItemA
    }

    @Transactional
    def update(DataItemA dataItemA) {
        if (dataItemA == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dataItemA.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dataItemA.errors, view:'edit'
            return
        }

        dataItemA.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'dataItemA.label', default: 'DataItemA'), dataItemA.id])
                redirect dataItemA
            }
            '*'{ respond dataItemA, [status: OK] }
        }
    }

    @Transactional
    def delete(DataItemA dataItemA) {

        if (dataItemA == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        dataItemA.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'dataItemA.label', default: 'DataItemA'), dataItemA.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dataItemA.label', default: 'DataItemA'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
