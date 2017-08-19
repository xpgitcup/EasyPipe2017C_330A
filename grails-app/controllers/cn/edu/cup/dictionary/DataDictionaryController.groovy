package cn.edu.cup.dictionary

import grails.gorm.transactions.Transactional
import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class DataDictionaryController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DataDictionary.list(params), model:[dataDictionaryCount: DataDictionary.count()]
    }

    def show(DataDictionary dataDictionary) {
        respond dataDictionary
    }

    def create() {
        respond new DataDictionary(params)
    }

    @Transactional
    def save(DataDictionary dataDictionary) {
        if (dataDictionary == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dataDictionary.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dataDictionary.errors, view:'create'
            return
        }

        dataDictionary.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dataDictionary.label', default: 'DataDictionary'), dataDictionary.id])
                redirect dataDictionary
            }
            '*' { respond dataDictionary, [status: CREATED] }
        }
    }

    def edit(DataDictionary dataDictionary) {
        respond dataDictionary
    }

    @Transactional
    def update(DataDictionary dataDictionary) {
        if (dataDictionary == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dataDictionary.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dataDictionary.errors, view:'edit'
            return
        }

        dataDictionary.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'dataDictionary.label', default: 'DataDictionary'), dataDictionary.id])
                redirect dataDictionary
            }
            '*'{ respond dataDictionary, [status: OK] }
        }
    }

    @Transactional
    def delete(DataDictionary dataDictionary) {

        if (dataDictionary == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        dataDictionary.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'dataDictionary.label', default: 'DataDictionary'), dataDictionary.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dataDictionary.label', default: 'DataDictionary'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
