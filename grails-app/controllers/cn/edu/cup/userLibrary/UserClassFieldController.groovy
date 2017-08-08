package cn.edu.cup.userLibrary

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserClassFieldController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond UserClassField.list(params), model:[userClassFieldCount: UserClassField.count()]
    }

    def show(UserClassField userClassField) {
        respond userClassField
    }

    def create() {
        respond new UserClassField(params)
    }

    @Transactional
    def save(UserClassField userClassField) {
        if (userClassField == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (userClassField.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userClassField.errors, view:'create'
            return
        }

        userClassField.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userClassField.label', default: 'UserClassField'), userClassField.id])
                redirect userClassField
            }
            '*' { respond userClassField, [status: CREATED] }
        }
    }

    def edit(UserClassField userClassField) {
        respond userClassField
    }

    @Transactional
    def update(UserClassField userClassField) {
        if (userClassField == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (userClassField.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userClassField.errors, view:'edit'
            return
        }

        userClassField.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'userClassField.label', default: 'UserClassField'), userClassField.id])
                redirect userClassField
            }
            '*'{ respond userClassField, [status: OK] }
        }
    }

    @Transactional
    def delete(UserClassField userClassField) {

        if (userClassField == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        userClassField.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'userClassField.label', default: 'UserClassField'), userClassField.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userClassField.label', default: 'UserClassField'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
