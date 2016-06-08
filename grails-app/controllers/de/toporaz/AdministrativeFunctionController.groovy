package de.toporaz

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AdministrativeFunctionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond AdministrativeFunction.list(params), model:[administrativeFunctionCount: AdministrativeFunction.count()]
    }

    def show(AdministrativeFunction administrativeFunction) {
        respond administrativeFunction
    }

    def create() {
        respond new AdministrativeFunction(params)
    }

    @Transactional
    def save(AdministrativeFunction administrativeFunction) {
        if (administrativeFunction == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (administrativeFunction.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond administrativeFunction.errors, view:'create'
            return
        }

        administrativeFunction.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'administrativeFunction.label', default: 'AdministrativeFunction'), administrativeFunction.id])
                redirect administrativeFunction
            }
            '*' { respond administrativeFunction, [status: CREATED] }
        }
    }

    def edit(AdministrativeFunction administrativeFunction) {
        respond administrativeFunction
    }

    @Transactional
    def update(AdministrativeFunction administrativeFunction) {
        if (administrativeFunction == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (administrativeFunction.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond administrativeFunction.errors, view:'edit'
            return
        }

        administrativeFunction.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'administrativeFunction.label', default: 'AdministrativeFunction'), administrativeFunction.id])
                redirect administrativeFunction
            }
            '*'{ respond administrativeFunction, [status: OK] }
        }
    }

    @Transactional
    def delete(AdministrativeFunction administrativeFunction) {

        if (administrativeFunction == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        administrativeFunction.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'administrativeFunction.label', default: 'AdministrativeFunction'), administrativeFunction.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'administrativeFunction.label', default: 'AdministrativeFunction'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
