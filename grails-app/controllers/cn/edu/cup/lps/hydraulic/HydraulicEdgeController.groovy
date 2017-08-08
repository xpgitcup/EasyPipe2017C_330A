package cn.edu.cup.lps.hydraulic

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class HydraulicEdgeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond HydraulicEdge.list(params), model:[hydraulicEdgeCount: HydraulicEdge.count()]
    }

    def show(HydraulicEdge hydraulicEdge) {
        respond hydraulicEdge
    }

    def create() {
        HydraulicVertex start = params.start
        HydraulicVertex end = params.end
        respond HydraulicEdge.create(start, end)
    }

    @Transactional
    def save(HydraulicEdge hydraulicEdge) {
        if (hydraulicEdge == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (hydraulicEdge.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond hydraulicEdge.errors, view:'create'
            return
        }

        hydraulicEdge.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'hydraulicEdge.label', default: 'HydraulicEdge'), hydraulicEdge.id])
                redirect hydraulicEdge
            }
            '*' { respond hydraulicEdge, [status: CREATED] }
        }
    }

    def edit(HydraulicEdge hydraulicEdge) {
        respond hydraulicEdge
    }

    @Transactional
    def update(HydraulicEdge hydraulicEdge) {
        if (hydraulicEdge == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (hydraulicEdge.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond hydraulicEdge.errors, view:'edit'
            return
        }

        hydraulicEdge.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'hydraulicEdge.label', default: 'HydraulicEdge'), hydraulicEdge.id])
                redirect hydraulicEdge
            }
            '*'{ respond hydraulicEdge, [status: OK] }
        }
    }

    @Transactional
    def delete(HydraulicEdge hydraulicEdge) {

        if (hydraulicEdge == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        hydraulicEdge.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'hydraulicEdge.label', default: 'HydraulicEdge'), hydraulicEdge.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'hydraulicEdge.label', default: 'HydraulicEdge'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
