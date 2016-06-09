package de.toporaz

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FacadeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Facade.list(params), model:[facadeCount: Facade.count()]
    }

    def show(Facade facade) {
        respond facade
    }

    def create() {
        respond new Facade(params)
    }

    @Transactional
    def save(Facade facade) {
        if (facade == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (facade.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond facade.errors, view:'create'
            return
        }

        facade.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'facade.label', default: 'Facade'), facade.id])
                redirect facade
            }
            '*' { respond facade, [status: CREATED] }
        }
    }

    def edit(Facade facade) {
        respond facade
    }

    @Transactional
    def update(Facade facade) {
        if (facade == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (facade.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond facade.errors, view:'edit'
            return
        }

        facade.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'facade.label', default: 'Facade'), facade.id])
                redirect facade
            }
            '*'{ respond facade, [status: OK] }
        }
    }

    @Transactional
    def delete(Facade facade) {

        if (facade == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        facade.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'facade.label', default: 'Facade'), facade.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'facade.label', default: 'Facade'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
