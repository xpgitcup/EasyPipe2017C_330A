package cn.edu.cup.lps

import grails.gorm.transactions.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class PipeNetworkController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond PipeNetwork.list(params), model:[pipeNetworkCount: PipeNetwork.count()]
    }

    def show(PipeNetwork pipeNetwork) {
        respond pipeNetwork
    }

    def create() {
        respond new PipeNetwork(params)
    }

    @Transactional
    def save(PipeNetwork pipeNetwork) {
        if (pipeNetwork == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (pipeNetwork.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond pipeNetwork.errors, view:'create'
            return
        }

        pipeNetwork.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'pipeNetwork.label', default: 'PipeNetwork'), pipeNetwork.id])
                redirect pipeNetwork
            }
            '*' { respond pipeNetwork, [status: CREATED] }
        }
    }

    def edit(PipeNetwork pipeNetwork) {
        respond pipeNetwork
    }

    @Transactional
    def update(PipeNetwork pipeNetwork) {
        if (pipeNetwork == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (pipeNetwork.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond pipeNetwork.errors, view:'edit'
            return
        }

        pipeNetwork.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'pipeNetwork.label', default: 'PipeNetwork'), pipeNetwork.id])
                redirect pipeNetwork
            }
            '*'{ respond pipeNetwork, [status: OK] }
        }
    }

    @Transactional
    def delete(PipeNetwork pipeNetwork) {

        if (pipeNetwork == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        pipeNetwork.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'pipeNetwork.label', default: 'PipeNetwork'), pipeNetwork.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pipeNetwork.label', default: 'PipeNetwork'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
