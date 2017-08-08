package cn.edu.cup.lps

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class HydraulicProjectController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond HydraulicProject.list(params), model:[hydraulicProjectCount: HydraulicProject.count()]
    }

    def show(HydraulicProject hydraulicProject) {
        respond hydraulicProject
    }

    def create() {
        respond new HydraulicProject(params)
    }

    @Transactional
    def save(HydraulicProject hydraulicProject) {
        if (hydraulicProject == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (hydraulicProject.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond hydraulicProject.errors, view:'create'
            return
        }

        hydraulicProject.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'hydraulicProject.label', default: 'HydraulicProject'), hydraulicProject.id])
                redirect hydraulicProject
            }
            '*' { respond hydraulicProject, [status: CREATED] }
        }
    }

    def edit(HydraulicProject hydraulicProject) {
        respond hydraulicProject
    }

    @Transactional
    def update(HydraulicProject hydraulicProject) {
        if (hydraulicProject == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (hydraulicProject.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond hydraulicProject.errors, view:'edit'
            return
        }

        hydraulicProject.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'hydraulicProject.label', default: 'HydraulicProject'), hydraulicProject.id])
                redirect hydraulicProject
            }
            '*'{ respond hydraulicProject, [status: OK] }
        }
    }

    @Transactional
    def delete(HydraulicProject hydraulicProject) {

        if (hydraulicProject == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        hydraulicProject.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'hydraulicProject.label', default: 'HydraulicProject'), hydraulicProject.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'hydraulicProject.label', default: 'HydraulicProject'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
