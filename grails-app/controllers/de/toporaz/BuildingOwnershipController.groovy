package de.toporaz

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BuildingOwnershipController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond BuildingOwnership.list(params), model:[buildingOwnershipCount: BuildingOwnership.count()]
    }

    def show(BuildingOwnership buildingOwnership) {
        respond buildingOwnership
    }

    def create() {
        respond new BuildingOwnership(params)
    }

    @Transactional
    def save(BuildingOwnership buildingOwnership) {
        if (buildingOwnership == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (buildingOwnership.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond buildingOwnership.errors, view:'create'
            return
        }

        buildingOwnership.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'buildingOwnership.label', default: 'BuildingOwnership'), buildingOwnership.id])
                redirect buildingOwnership
            }
            '*' { respond buildingOwnership, [status: CREATED] }
        }
    }

    def edit(BuildingOwnership buildingOwnership) {
        respond buildingOwnership
    }

    @Transactional
    def update(BuildingOwnership buildingOwnership) {
        if (buildingOwnership == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (buildingOwnership.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond buildingOwnership.errors, view:'edit'
            return
        }

        buildingOwnership.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'buildingOwnership.label', default: 'BuildingOwnership'), buildingOwnership.id])
                redirect buildingOwnership
            }
            '*'{ respond buildingOwnership, [status: OK] }
        }
    }

    @Transactional
    def delete(BuildingOwnership buildingOwnership) {

        if (buildingOwnership == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        buildingOwnership.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'buildingOwnership.label', default: 'BuildingOwnership'), buildingOwnership.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'buildingOwnership.label', default: 'BuildingOwnership'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
