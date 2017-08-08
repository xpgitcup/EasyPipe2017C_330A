package cn.edu.cup.userLibrary

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserClassMethodController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond UserClassMethod.list(params), model:[userClassMethodCount: UserClassMethod.count()]
    }

    def show(UserClassMethod userClassMethod) {
        respond userClassMethod
    }

    def create() {
        respond new UserClassMethod(params)
    }

    @Transactional
    def save(UserClassMethod userClassMethod) {
        if (userClassMethod == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (userClassMethod.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userClassMethod.errors, view:'create'
            return
        }

        userClassMethod.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userClassMethod.label', default: 'UserClassMethod'), userClassMethod.id])
                redirect userClassMethod
            }
            '*' { respond userClassMethod, [status: CREATED] }
        }
    }

    def edit(UserClassMethod userClassMethod) {
        respond userClassMethod
    }

    @Transactional
    def update(UserClassMethod userClassMethod) {
        if (userClassMethod == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (userClassMethod.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userClassMethod.errors, view:'edit'
            return
        }

        userClassMethod.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'userClassMethod.label', default: 'UserClassMethod'), userClassMethod.id])
                redirect userClassMethod
            }
            '*'{ respond userClassMethod, [status: OK] }
        }
    }

    @Transactional
    def delete(UserClassMethod userClassMethod) {

        if (userClassMethod == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        userClassMethod.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'userClassMethod.label', default: 'UserClassMethod'), userClassMethod.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userClassMethod.label', default: 'UserClassMethod'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
