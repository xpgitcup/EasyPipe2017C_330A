package cn.edu.cup.lps.hydraulic

import grails.gorm.transactions.Transactional
import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class MileageAndElevationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond MileageAndElevation.list(params), model:[mileageAndElevationCount: MileageAndElevation.count()]
    }

    def show(MileageAndElevation mileageAndElevation) {
        respond mileageAndElevation
    }

    def create() {
        respond new MileageAndElevation(params)
    }

    @Transactional
    def save(MileageAndElevation mileageAndElevation) {
        if (mileageAndElevation == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (mileageAndElevation.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond mileageAndElevation.errors, view:'create'
            return
        }

        mileageAndElevation.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'mileageAndElevation.label', default: 'MileageAndElevation'), mileageAndElevation.id])
                redirect mileageAndElevation
            }
            '*' { respond mileageAndElevation, [status: CREATED] }
        }
    }

    def edit(MileageAndElevation mileageAndElevation) {
        respond mileageAndElevation
    }

    @Transactional
    def update(MileageAndElevation mileageAndElevation) {
        if (mileageAndElevation == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (mileageAndElevation.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond mileageAndElevation.errors, view:'edit'
            return
        }

        mileageAndElevation.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'mileageAndElevation.label', default: 'MileageAndElevation'), mileageAndElevation.id])
                redirect mileageAndElevation
            }
            '*'{ respond mileageAndElevation, [status: OK] }
        }
    }

    @Transactional
    def delete(MileageAndElevation mileageAndElevation) {

        if (mileageAndElevation == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        mileageAndElevation.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'mileageAndElevation.label', default: 'MileageAndElevation'), mileageAndElevation.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'mileageAndElevation.label', default: 'MileageAndElevation'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
