package cn.edu.cup.system

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SystemSponserController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond SystemSponser.list(params), model:[systemSponserCount: SystemSponser.count()]
    }

    def show(SystemSponser systemSponser) {
        respond systemSponser
    }

    def create() {
        respond new SystemSponser(params)
    }

    @Transactional
    def save(SystemSponser systemSponser) {
        if (systemSponser == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (systemSponser.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond systemSponser.errors, view:'create'
            return
        }

        systemSponser.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'systemSponser.label', default: 'SystemSponser'), systemSponser.id])
                redirect systemSponser
            }
            '*' { respond systemSponser, [status: CREATED] }
        }
    }

    def edit(SystemSponser systemSponser) {
        respond systemSponser
    }

    @Transactional
    def update(SystemSponser systemSponser) {
        if (systemSponser == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (systemSponser.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond systemSponser.errors, view:'edit'
            return
        }

        systemSponser.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'systemSponser.label', default: 'SystemSponser'), systemSponser.id])
                redirect systemSponser
            }
            '*'{ respond systemSponser, [status: OK] }
        }
    }

    @Transactional
    def delete(SystemSponser systemSponser) {

        if (systemSponser == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        systemSponser.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'systemSponser.label', default: 'SystemSponser'), systemSponser.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'systemSponser.label', default: 'SystemSponser'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
