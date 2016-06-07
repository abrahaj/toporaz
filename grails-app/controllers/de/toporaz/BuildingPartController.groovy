package de.toporaz

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BuildingPartController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond BuildingPart.list(params), model:[buildingPartCount: BuildingPart.count()]
    }

    def show(BuildingPart buildingPart) {
        respond buildingPart
    }

    def create() {
        respond new BuildingPart(params)
    }

    @Transactional
    def save(BuildingPart buildingPart) {
        if (buildingPart == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (buildingPart.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond buildingPart.errors, view:'create'
            return
        }

        buildingPart.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'buildingPart.label', default: 'BuildingPart'), buildingPart.id])
                redirect buildingPart
            }
            '*' { respond buildingPart, [status: CREATED] }
        }
    }

    def edit(BuildingPart buildingPart) {
        respond buildingPart
    }

    @Transactional
    def update(BuildingPart buildingPart) {
        if (buildingPart == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (buildingPart.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond buildingPart.errors, view:'edit'
            return
        }

        buildingPart.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'buildingPart.label', default: 'BuildingPart'), buildingPart.id])
                redirect buildingPart
            }
            '*'{ respond buildingPart, [status: OK] }
        }
    }

    @Transactional
    def delete(BuildingPart buildingPart) {

        if (buildingPart == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        buildingPart.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'buildingPart.label', default: 'BuildingPart'), buildingPart.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'buildingPart.label', default: 'BuildingPart'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
