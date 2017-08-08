package cn.edu.cup.lps.hydraulic

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class HydraulicVertexController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond HydraulicVertex.list(params), model:[hydraulicVertexCount: HydraulicVertex.count()]
    }

    def show(HydraulicVertex hydraulicVertex) {
        respond hydraulicVertex
    }

    def create() {
        respond new HydraulicVertex(params)
    }

    @Transactional
    def save(HydraulicVertex hydraulicVertex) {
        if (hydraulicVertex == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (hydraulicVertex.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond hydraulicVertex.errors, view:'create'
            return
        }

        hydraulicVertex.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'hydraulicVertex.label', default: 'HydraulicVertex'), hydraulicVertex.id])
                redirect hydraulicVertex
            }
            '*' { respond hydraulicVertex, [status: CREATED] }
        }
    }

    def edit(HydraulicVertex hydraulicVertex) {
        respond hydraulicVertex
    }

    @Transactional
    def update(HydraulicVertex hydraulicVertex) {
        if (hydraulicVertex == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (hydraulicVertex.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond hydraulicVertex.errors, view:'edit'
            return
        }

        hydraulicVertex.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'hydraulicVertex.label', default: 'HydraulicVertex'), hydraulicVertex.id])
                redirect hydraulicVertex
            }
            '*'{ respond hydraulicVertex, [status: OK] }
        }
    }

    @Transactional
    def delete(HydraulicVertex hydraulicVertex) {

        if (hydraulicVertex == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        hydraulicVertex.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'hydraulicVertex.label', default: 'HydraulicVertex'), hydraulicVertex.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'hydraulicVertex.label', default: 'HydraulicVertex'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
