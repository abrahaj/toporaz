package de.toporaz

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ChangeLocationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ChangeLocation.list(params), model:[changeLocationCount: ChangeLocation.count()]
    }

    def show(ChangeLocation changeLocation) {
        respond changeLocation
    }

    def create() {
        respond new ChangeLocation(params)
    }

    @Transactional
    def save(ChangeLocation changeLocation) {
        if (changeLocation == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (changeLocation.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond changeLocation.errors, view:'create'
            return
        }

        changeLocation.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'changeLocation.label', default: 'ChangeLocation'), changeLocation.id])
                redirect changeLocation
            }
            '*' { respond changeLocation, [status: CREATED] }
        }
    }

    def edit(ChangeLocation changeLocation) {
        respond changeLocation
    }

    @Transactional
    def update(ChangeLocation changeLocation) {
        if (changeLocation == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (changeLocation.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond changeLocation.errors, view:'edit'
            return
        }

        changeLocation.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'changeLocation.label', default: 'ChangeLocation'), changeLocation.id])
                redirect changeLocation
            }
            '*'{ respond changeLocation, [status: OK] }
        }
    }

    @Transactional
    def delete(ChangeLocation changeLocation) {

        if (changeLocation == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        changeLocation.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'changeLocation.label', default: 'ChangeLocation'), changeLocation.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'changeLocation.label', default: 'ChangeLocation'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
