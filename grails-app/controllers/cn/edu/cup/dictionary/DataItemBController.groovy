package cn.edu.cup.dictionary

import grails.gorm.transactions.Transactional
import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class DataItemBController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DataItemB.list(params), model:[dataItemBCount: DataItemB.count()]
    }

    def show(DataItemB dataItemB) {
        respond dataItemB
    }

    def create() {
        respond new DataItemB(params)
    }

    @Transactional
    def save(DataItemB dataItemB) {
        if (dataItemB == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dataItemB.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dataItemB.errors, view:'create'
            return
        }

        dataItemB.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dataItemB.label', default: 'DataItemB'), dataItemB.id])
                redirect dataItemB
            }
            '*' { respond dataItemB, [status: CREATED] }
        }
    }

    def edit(DataItemB dataItemB) {
        respond dataItemB
    }

    @Transactional
    def update(DataItemB dataItemB) {
        if (dataItemB == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dataItemB.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dataItemB.errors, view:'edit'
            return
        }

        dataItemB.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'dataItemB.label', default: 'DataItemB'), dataItemB.id])
                redirect dataItemB
            }
            '*'{ respond dataItemB, [status: OK] }
        }
    }

    @Transactional
    def delete(DataItemB dataItemB) {

        if (dataItemB == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        dataItemB.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'dataItemB.label', default: 'DataItemB'), dataItemB.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dataItemB.label', default: 'DataItemB'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
