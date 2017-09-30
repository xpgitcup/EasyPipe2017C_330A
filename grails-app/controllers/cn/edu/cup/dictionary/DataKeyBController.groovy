package cn.edu.cup.dictionary

import grails.gorm.transactions.Transactional
import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class DataKeyBController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DataKeyB.list(params), model:[dataKeyBCount: DataKeyB.count()]
    }

    def show(DataKeyB dataKeyB) {
        respond dataKeyB
    }

    def create() {
        respond new DataKeyB(params)
    }

    @Transactional
    def save(DataKeyB dataKeyB) {
        if (dataKeyB == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dataKeyB.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dataKeyB.errors, view:'create'
            return
        }

        dataKeyB.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dataKeyB.label', default: 'DataKeyB'), dataKeyB.id])
                redirect dataKeyB
            }
            '*' { respond dataKeyB, [status: CREATED] }
        }
    }

    def edit(DataKeyB dataKeyB) {
        respond dataKeyB
    }

    @Transactional
    def update(DataKeyB dataKeyB) {
        if (dataKeyB == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dataKeyB.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dataKeyB.errors, view:'edit'
            return
        }

        dataKeyB.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'dataKeyB.label', default: 'DataKeyB'), dataKeyB.id])
                redirect dataKeyB
            }
            '*'{ respond dataKeyB, [status: OK] }
        }
    }

    @Transactional
    def delete(DataKeyB dataKeyB) {

        if (dataKeyB == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        dataKeyB.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'dataKeyB.label', default: 'DataKeyB'), dataKeyB.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dataKeyB.label', default: 'DataKeyB'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
