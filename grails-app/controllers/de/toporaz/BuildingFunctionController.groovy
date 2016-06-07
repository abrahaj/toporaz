package de.toporaz

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BuildingFunctionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond BuildingFunction.list(params), model:[buildingFunctionCount: BuildingFunction.count()]
    }

    def show(BuildingFunction buildingFunction) {
        respond buildingFunction
    }

    def create() {
        respond new BuildingFunction(params)
    }

    @Transactional
    def save(BuildingFunction buildingFunction) {
        if (buildingFunction == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (buildingFunction.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond buildingFunction.errors, view:'create'
            return
        }

        buildingFunction.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'buildingFunction.label', default: 'BuildingFunction'), buildingFunction.id])
                redirect buildingFunction
            }
            '*' { respond buildingFunction, [status: CREATED] }
        }
    }

    def edit(BuildingFunction buildingFunction) {
        respond buildingFunction
    }

    @Transactional
    def update(BuildingFunction buildingFunction) {
        if (buildingFunction == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (buildingFunction.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond buildingFunction.errors, view:'edit'
            return
        }

        buildingFunction.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'buildingFunction.label', default: 'BuildingFunction'), buildingFunction.id])
                redirect buildingFunction
            }
            '*'{ respond buildingFunction, [status: OK] }
        }
    }

    @Transactional
    def delete(BuildingFunction buildingFunction) {

        if (buildingFunction == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        buildingFunction.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'buildingFunction.label', default: 'BuildingFunction'), buildingFunction.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'buildingFunction.label', default: 'BuildingFunction'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
