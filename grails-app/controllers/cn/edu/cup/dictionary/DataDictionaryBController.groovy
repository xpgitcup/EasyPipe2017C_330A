package cn.edu.cup.dictionary

import grails.gorm.transactions.Transactional
import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class DataDictionaryBController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DataDictionaryB.list(params), model:[dataDictionaryBCount: DataDictionaryB.count()]
    }

    def show(DataDictionaryB dataDictionaryB) {
        respond dataDictionaryB
    }

    def create() {
        respond new DataDictionaryB(params)
    }

    @Transactional
    def save(DataDictionaryB dataDictionaryB) {
        if (dataDictionaryB == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dataDictionaryB.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dataDictionaryB.errors, view:'create'
            return
        }

        dataDictionaryB.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dataDictionaryB.label', default: 'DataDictionaryB'), dataDictionaryB.id])
                redirect dataDictionaryB
            }
            '*' { respond dataDictionaryB, [status: CREATED] }
        }
    }

    def edit(DataDictionaryB dataDictionaryB) {
        respond dataDictionaryB
    }

    @Transactional
    def update(DataDictionaryB dataDictionaryB) {
        if (dataDictionaryB == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dataDictionaryB.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dataDictionaryB.errors, view:'edit'
            return
        }

        dataDictionaryB.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'dataDictionaryB.label', default: 'DataDictionaryB'), dataDictionaryB.id])
                redirect dataDictionaryB
            }
            '*'{ respond dataDictionaryB, [status: OK] }
        }
    }

    @Transactional
    def delete(DataDictionaryB dataDictionaryB) {

        if (dataDictionaryB == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        dataDictionaryB.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'dataDictionaryB.label', default: 'DataDictionaryB'), dataDictionaryB.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dataDictionaryB.label', default: 'DataDictionaryB'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
