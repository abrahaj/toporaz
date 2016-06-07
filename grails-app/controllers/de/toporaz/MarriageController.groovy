package de.toporaz

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MarriageController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Marriage.list(params), model:[marriageCount: Marriage.count()]
    }

    def show(Marriage marriage) {
        respond marriage
    }

    def create() {
        respond new Marriage(params)
    }

    @Transactional
    def save(Marriage marriage) {
        if (marriage == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (marriage.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond marriage.errors, view:'create'
            return
        }

        marriage.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'marriage.label', default: 'Marriage'), marriage.id])
                redirect marriage
            }
            '*' { respond marriage, [status: CREATED] }
        }
    }

    def edit(Marriage marriage) {
        respond marriage
    }

    @Transactional
    def update(Marriage marriage) {
        if (marriage == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (marriage.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond marriage.errors, view:'edit'
            return
        }

        marriage.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'marriage.label', default: 'Marriage'), marriage.id])
                redirect marriage
            }
            '*'{ respond marriage, [status: OK] }
        }
    }

    @Transactional
    def delete(Marriage marriage) {

        if (marriage == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        marriage.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'marriage.label', default: 'Marriage'), marriage.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'marriage.label', default: 'Marriage'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
