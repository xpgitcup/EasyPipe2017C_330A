package cn.edu.cup.userLibrary

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserLibraryClassifyController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond UserLibraryClassify.list(params), model:[userLibraryClassifyCount: UserLibraryClassify.count()]
    }

    def show(UserLibraryClassify userLibraryClassify) {
        respond userLibraryClassify
    }

    def create() {
        respond new UserLibraryClassify(params)
    }

    @Transactional
    def save(UserLibraryClassify userLibraryClassify) {
        if (userLibraryClassify == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (userLibraryClassify.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userLibraryClassify.errors, view:'create'
            return
        }

        userLibraryClassify.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userLibraryClassify.label', default: 'UserLibraryClassify'), userLibraryClassify.id])
                redirect userLibraryClassify
            }
            '*' { respond userLibraryClassify, [status: CREATED] }
        }
    }

    def edit(UserLibraryClassify userLibraryClassify) {
        respond userLibraryClassify
    }

    @Transactional
    def update(UserLibraryClassify userLibraryClassify) {
        if (userLibraryClassify == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (userLibraryClassify.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userLibraryClassify.errors, view:'edit'
            return
        }

        userLibraryClassify.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'userLibraryClassify.label', default: 'UserLibraryClassify'), userLibraryClassify.id])
                redirect userLibraryClassify
            }
            '*'{ respond userLibraryClassify, [status: OK] }
        }
    }

    @Transactional
    def delete(UserLibraryClassify userLibraryClassify) {

        if (userLibraryClassify == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        userLibraryClassify.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'userLibraryClassify.label', default: 'UserLibraryClassify'), userLibraryClassify.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userLibraryClassify.label', default: 'UserLibraryClassify'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
