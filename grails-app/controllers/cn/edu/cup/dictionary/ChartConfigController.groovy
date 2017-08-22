package cn.edu.cup.dictionary

import grails.gorm.transactions.Transactional
import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class ChartConfigController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ChartConfig.list(params), model:[chartConfigCount: ChartConfig.count()]
    }

    def show(ChartConfig chartConfig) {
        respond chartConfig
    }

    def create() {
        respond new ChartConfig(params)
    }

    @Transactional
    def save(ChartConfig chartConfig) {
        if (chartConfig == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (chartConfig.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond chartConfig.errors, view:'create'
            return
        }

        chartConfig.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'chartConfig.label', default: 'ChartConfig'), chartConfig.id])
                redirect chartConfig
            }
            '*' { respond chartConfig, [status: CREATED] }
        }
    }

    def edit(ChartConfig chartConfig) {
        respond chartConfig
    }

    @Transactional
    def update(ChartConfig chartConfig) {
        if (chartConfig == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (chartConfig.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond chartConfig.errors, view:'edit'
            return
        }

        chartConfig.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'chartConfig.label', default: 'ChartConfig'), chartConfig.id])
                redirect chartConfig
            }
            '*'{ respond chartConfig, [status: OK] }
        }
    }

    @Transactional
    def delete(ChartConfig chartConfig) {

        if (chartConfig == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        chartConfig.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'chartConfig.label', default: 'ChartConfig'), chartConfig.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'chartConfig.label', default: 'ChartConfig'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
