package de.toporaz

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ProfessionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Profession.list(params), model:[professionCount: Profession.count()]
    }

    def show(Profession profession) {
        respond profession
    }

    def create() {
        respond new Profession(params)
    }

    @Transactional
    def save(Profession profession) {
        if (profession == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (profession.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond profession.errors, view:'create'
            return
        }

        profession.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'profession.label', default: 'Profession'), profession.id])
                redirect profession
            }
            '*' { respond profession, [status: CREATED] }
        }
    }

    def edit(Profession profession) {
        respond profession
    }

    @Transactional
    def update(Profession profession) {
        if (profession == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (profession.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond profession.errors, view:'edit'
            return
        }

        profession.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'profession.label', default: 'Profession'), profession.id])
                redirect profession
            }
            '*'{ respond profession, [status: OK] }
        }
    }

    @Transactional
    def delete(Profession profession) {

        if (profession == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        profession.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'profession.label', default: 'Profession'), profession.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'profession.label', default: 'Profession'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
