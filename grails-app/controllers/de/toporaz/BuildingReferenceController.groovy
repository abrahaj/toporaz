package de.toporaz

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BuildingReferenceController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond BuildingReference.list(params), model:[buildingReferenceCount: BuildingReference.count()]
    }

    def show(BuildingReference buildingReference) {
        respond buildingReference
    }

    def create() {
        respond new BuildingReference(params)
    }

    @Transactional
    def save(BuildingReference buildingReference) {
        if (buildingReference == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (buildingReference.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond buildingReference.errors, view:'create'
            return
        }

        buildingReference.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'buildingReference.label', default: 'BuildingReference'), buildingReference.id])
                redirect buildingReference
            }
            '*' { respond buildingReference, [status: CREATED] }
        }
    }

    def edit(BuildingReference buildingReference) {
        respond buildingReference
    }

    @Transactional
    def update(BuildingReference buildingReference) {
        if (buildingReference == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (buildingReference.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond buildingReference.errors, view:'edit'
            return
        }

        buildingReference.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'buildingReference.label', default: 'BuildingReference'), buildingReference.id])
                redirect buildingReference
            }
            '*'{ respond buildingReference, [status: OK] }
        }
    }

    @Transactional
    def delete(BuildingReference buildingReference) {

        if (buildingReference == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        buildingReference.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'buildingReference.label', default: 'BuildingReference'), buildingReference.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'buildingReference.label', default: 'BuildingReference'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
