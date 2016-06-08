package de.toporaz

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PersonRelationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond PersonRelation.list(params), model:[personRelationCount: PersonRelation.count()]
    }

    def show(PersonRelation personRelation) {
        respond personRelation
    }

    def create() {
        respond new PersonRelation(params)
    }

    @Transactional
    def save(PersonRelation personRelation) {
        if (personRelation == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (personRelation.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond personRelation.errors, view:'create'
            return
        }

        personRelation.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'personRelation.label', default: 'PersonRelation'), personRelation.id])
                redirect personRelation
            }
            '*' { respond personRelation, [status: CREATED] }
        }
    }

    def edit(PersonRelation personRelation) {
        respond personRelation
    }

    @Transactional
    def update(PersonRelation personRelation) {
        if (personRelation == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (personRelation.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond personRelation.errors, view:'edit'
            return
        }

        personRelation.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'personRelation.label', default: 'PersonRelation'), personRelation.id])
                redirect personRelation
            }
            '*'{ respond personRelation, [status: OK] }
        }
    }

    @Transactional
    def delete(PersonRelation personRelation) {

        if (personRelation == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        personRelation.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'personRelation.label', default: 'PersonRelation'), personRelation.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'personRelation.label', default: 'PersonRelation'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
