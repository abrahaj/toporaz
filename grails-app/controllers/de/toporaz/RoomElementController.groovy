package de.toporaz

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RoomElementController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond RoomElement.list(params), model:[roomElementCount: RoomElement.count()]
    }

    def show(RoomElement roomElement) {
        respond roomElement
    }

    def create() {
        respond new RoomElement(params)
    }

    @Transactional
    def save(RoomElement roomElement) {
        if (roomElement == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (roomElement.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond roomElement.errors, view:'create'
            return
        }

        roomElement.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'roomElement.label', default: 'RoomElement'), roomElement.id])
                redirect roomElement
            }
            '*' { respond roomElement, [status: CREATED] }
        }
    }

    def edit(RoomElement roomElement) {
        respond roomElement
    }

    @Transactional
    def update(RoomElement roomElement) {
        if (roomElement == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (roomElement.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond roomElement.errors, view:'edit'
            return
        }

        roomElement.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'roomElement.label', default: 'RoomElement'), roomElement.id])
                redirect roomElement
            }
            '*'{ respond roomElement, [status: OK] }
        }
    }

    @Transactional
    def delete(RoomElement roomElement) {

        if (roomElement == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        roomElement.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'roomElement.label', default: 'RoomElement'), roomElement.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'roomElement.label', default: 'RoomElement'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
