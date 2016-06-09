package de.toporaz

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RoofController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Roof.list(params), model:[roofCount: Roof.count()]
    }

    def show(Roof roof) {
        respond roof
    }

    def create() {
        respond new Roof(params)
    }

    @Transactional
    def save(Roof roof) {
        if (roof == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (roof.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond roof.errors, view:'create'
            return
        }

        roof.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'roof.label', default: 'Roof'), roof.id])
                redirect roof
            }
            '*' { respond roof, [status: CREATED] }
        }
    }

    def edit(Roof roof) {
        respond roof
    }

    @Transactional
    def update(Roof roof) {
        if (roof == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (roof.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond roof.errors, view:'edit'
            return
        }

        roof.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'roof.label', default: 'Roof'), roof.id])
                redirect roof
            }
            '*'{ respond roof, [status: OK] }
        }
    }

    @Transactional
    def delete(Roof roof) {

        if (roof == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        roof.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'roof.label', default: 'Roof'), roof.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'roof.label', default: 'Roof'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
