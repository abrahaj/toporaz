package de.toporaz

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ResidentController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Resident.list(params), model:[residentCount: Resident.count()]
    }

    def show(Resident resident) {
        respond resident
    }

    def create() {
        respond new Resident(params)
    }

    @Transactional
    def save(Resident resident) {
        if (resident == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (resident.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond resident.errors, view:'create'
            return
        }

        resident.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'resident.label', default: 'Resident'), resident.id])
                redirect resident
            }
            '*' { respond resident, [status: CREATED] }
        }
    }

    def edit(Resident resident) {
        respond resident
    }

    @Transactional
    def update(Resident resident) {
        if (resident == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (resident.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond resident.errors, view:'edit'
            return
        }

        resident.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'resident.label', default: 'Resident'), resident.id])
                redirect resident
            }
            '*'{ respond resident, [status: OK] }
        }
    }

    @Transactional
    def delete(Resident resident) {

        if (resident == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        resident.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'resident.label', default: 'Resident'), resident.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'resident.label', default: 'Resident'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
