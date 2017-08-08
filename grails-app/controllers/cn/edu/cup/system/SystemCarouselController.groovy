package cn.edu.cup.system

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SystemCarouselController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond SystemCarousel.list(params), model:[systemCarouselCount: SystemCarousel.count()]
    }

    def show(SystemCarousel systemCarousel) {
        respond systemCarousel
    }

    def create() {
        respond new SystemCarousel(params)
    }

    @Transactional
    def save(SystemCarousel systemCarousel) {
        if (systemCarousel == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (systemCarousel.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond systemCarousel.errors, view:'create'
            return
        }

        systemCarousel.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'systemCarousel.label', default: 'SystemCarousel'), systemCarousel.id])
                redirect systemCarousel
            }
            '*' { respond systemCarousel, [status: CREATED] }
        }
    }

    def edit(SystemCarousel systemCarousel) {
        respond systemCarousel
    }

    @Transactional
    def update(SystemCarousel systemCarousel) {
        if (systemCarousel == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (systemCarousel.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond systemCarousel.errors, view:'edit'
            return
        }

        systemCarousel.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'systemCarousel.label', default: 'SystemCarousel'), systemCarousel.id])
                redirect systemCarousel
            }
            '*'{ respond systemCarousel, [status: OK] }
        }
    }

    @Transactional
    def delete(SystemCarousel systemCarousel) {

        if (systemCarousel == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        systemCarousel.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'systemCarousel.label', default: 'SystemCarousel'), systemCarousel.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'systemCarousel.label', default: 'SystemCarousel'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
