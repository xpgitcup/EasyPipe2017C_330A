package cn.edu.cup.dictionary

import grails.gorm.transactions.Transactional
import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class DataKeyAController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DataKeyA.list(params), model:[dataKeyACount: DataKeyA.count()]
    }

    def show(DataKeyA dataKeyA) {
        respond dataKeyA
    }

    def create() {
        respond new DataKeyA(params)
    }

    @Transactional
    def save(DataKeyA dataKeyA) {
        if (dataKeyA == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dataKeyA.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dataKeyA.errors, view:'create'
            return
        }

        dataKeyA.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dataKeyA.label', default: 'DataKeyA'), dataKeyA.id])
                redirect dataKeyA
            }
            '*' { respond dataKeyA, [status: CREATED] }
        }
    }

    def edit(DataKeyA dataKeyA) {
        respond dataKeyA
    }

    @Transactional
    def update(DataKeyA dataKeyA) {
        if (dataKeyA == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dataKeyA.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dataKeyA.errors, view:'edit'
            return
        }

        dataKeyA.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'dataKeyA.label', default: 'DataKeyA'), dataKeyA.id])
                redirect dataKeyA
            }
            '*'{ respond dataKeyA, [status: OK] }
        }
    }

    @Transactional
    def delete(DataKeyA dataKeyA) {

        if (dataKeyA == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        dataKeyA.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'dataKeyA.label', default: 'DataKeyA'), dataKeyA.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dataKeyA.label', default: 'DataKeyA'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
