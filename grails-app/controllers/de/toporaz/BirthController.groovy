package de.toporaz

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BirthController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Birth.list(params), model:[birthCount: Birth.count()]
    }

    def show(Birth birth) {
        respond birth
    }

    def create() {
        respond new Birth(params)
    }

    @Transactional
    def save(Birth birth) {
        if (birth == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (birth.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond birth.errors, view:'create'
            return
        }

        birth.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'birth.label', default: 'Birth'), birth.id])
                redirect birth
            }
            '*' { respond birth, [status: CREATED] }
        }
    }

    def edit(Birth birth) {
        respond birth
    }

    @Transactional
    def update(Birth birth) {
        if (birth == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (birth.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond birth.errors, view:'edit'
            return
        }

        birth.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'birth.label', default: 'Birth'), birth.id])
                redirect birth
            }
            '*'{ respond birth, [status: OK] }
        }
    }

    @Transactional
    def delete(Birth birth) {

        if (birth == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        birth.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'birth.label', default: 'Birth'), birth.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'birth.label', default: 'Birth'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
