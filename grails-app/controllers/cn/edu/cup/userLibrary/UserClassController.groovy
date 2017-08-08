package cn.edu.cup.userLibrary

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserClassController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond UserClass.list(params), model:[userClassCount: UserClass.count()]
    }

    def show(UserClass userClass) {
        respond userClass
    }

    def create() {
        respond new UserClass(params)
    }

    @Transactional
    def save(UserClass userClass) {
        if (userClass == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (userClass.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userClass.errors, view:'create'
            return
        }

        userClass.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userClass.label', default: 'UserClass'), userClass.id])
                redirect userClass
            }
            '*' { respond userClass, [status: CREATED] }
        }
    }

    def edit(UserClass userClass) {
        respond userClass
    }

    @Transactional
    def update(UserClass userClass) {
        if (userClass == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (userClass.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userClass.errors, view:'edit'
            return
        }

        userClass.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'userClass.label', default: 'UserClass'), userClass.id])
                redirect userClass
            }
            '*'{ respond userClass, [status: OK] }
        }
    }

    @Transactional
    def delete(UserClass userClass) {

        if (userClass == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        userClass.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'userClass.label', default: 'UserClass'), userClass.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userClass.label', default: 'UserClass'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
