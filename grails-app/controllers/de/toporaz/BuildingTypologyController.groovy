package de.toporaz

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BuildingTypologyController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond BuildingTypology.list(params), model:[buildingTypologyCount: BuildingTypology.count()]
    }

    def show(BuildingTypology buildingTypology) {
        respond buildingTypology
    }

    def create() {
        respond new BuildingTypology(params)
    }

    @Transactional
    def save(BuildingTypology buildingTypology) {
        if (buildingTypology == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (buildingTypology.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond buildingTypology.errors, view:'create'
            return
        }

        buildingTypology.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'buildingTypology.label', default: 'BuildingTypology'), buildingTypology.id])
                redirect buildingTypology
            }
            '*' { respond buildingTypology, [status: CREATED] }
        }
    }

    def edit(BuildingTypology buildingTypology) {
        respond buildingTypology
    }

    @Transactional
    def update(BuildingTypology buildingTypology) {
        if (buildingTypology == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (buildingTypology.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond buildingTypology.errors, view:'edit'
            return
        }

        buildingTypology.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'buildingTypology.label', default: 'BuildingTypology'), buildingTypology.id])
                redirect buildingTypology
            }
            '*'{ respond buildingTypology, [status: OK] }
        }
    }

    @Transactional
    def delete(BuildingTypology buildingTypology) {

        if (buildingTypology == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        buildingTypology.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'buildingTypology.label', default: 'BuildingTypology'), buildingTypology.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'buildingTypology.label', default: 'BuildingTypology'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
