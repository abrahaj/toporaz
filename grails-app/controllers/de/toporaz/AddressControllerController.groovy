package de.toporaz

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AddressControllerController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond AddressController.list(params), model:[addressControllerCount: AddressController.count()]
    }

    def show(AddressController addressController) {
        respond addressController
    }

    def create() {
        respond new AddressController(params)
    }

    @Transactional
    def save(AddressController addressController) {
        if (addressController == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (addressController.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond addressController.errors, view:'create'
            return
        }

        addressController.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'addressController.label', default: 'AddressController'), addressController.id])
                redirect addressController
            }
            '*' { respond addressController, [status: CREATED] }
        }
    }

    def edit(AddressController addressController) {
        respond addressController
    }

    @Transactional
    def update(AddressController addressController) {
        if (addressController == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (addressController.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond addressController.errors, view:'edit'
            return
        }

        addressController.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'addressController.label', default: 'AddressController'), addressController.id])
                redirect addressController
            }
            '*'{ respond addressController, [status: OK] }
        }
    }

    @Transactional
    def delete(AddressController addressController) {

        if (addressController == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        addressController.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'addressController.label', default: 'AddressController'), addressController.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'addressController.label', default: 'AddressController'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
