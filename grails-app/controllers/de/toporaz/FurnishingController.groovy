package de.toporaz

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FurnishingController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Furnishing.list(params), model:[furnishingCount: Furnishing.count()]
    }

    def show(Furnishing furnishing) {
        respond furnishing
    }

    def create() {
        respond new Furnishing(params)
    }

    @Transactional
    def save(Furnishing furnishing) {
        if (furnishing == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (furnishing.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond furnishing.errors, view:'create'
            return
        }

        furnishing.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'furnishing.label', default: 'Furnishing'), furnishing.id])
                redirect furnishing
            }
            '*' { respond furnishing, [status: CREATED] }
        }
    }

    def edit(Furnishing furnishing) {
        respond furnishing
    }

    @Transactional
    def update(Furnishing furnishing) {
        if (furnishing == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (furnishing.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond furnishing.errors, view:'edit'
            return
        }

        furnishing.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'furnishing.label', default: 'Furnishing'), furnishing.id])
                redirect furnishing
            }
            '*'{ respond furnishing, [status: OK] }
        }
    }

    @Transactional
    def delete(Furnishing furnishing) {

        if (furnishing == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        furnishing.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'furnishing.label', default: 'Furnishing'), furnishing.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'furnishing.label', default: 'Furnishing'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
