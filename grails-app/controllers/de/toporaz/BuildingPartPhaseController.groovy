package de.toporaz

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BuildingPartPhaseController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond BuildingPartPhase.list(params), model:[buildingPartPhaseCount: BuildingPartPhase.count()]
    }

    def show(BuildingPartPhase buildingPartPhase) {
        respond buildingPartPhase
    }

    def create() {
        respond new BuildingPartPhase(params)
    }

    @Transactional
    def save(BuildingPartPhase buildingPartPhase) {
        if (buildingPartPhase == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (buildingPartPhase.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond buildingPartPhase.errors, view:'create'
            return
        }

        buildingPartPhase.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'buildingPartPhase.label', default: 'BuildingPartPhase'), buildingPartPhase.id])
                redirect buildingPartPhase
            }
            '*' { respond buildingPartPhase, [status: CREATED] }
        }
    }

    def edit(BuildingPartPhase buildingPartPhase) {
        respond buildingPartPhase
    }

    @Transactional
    def update(BuildingPartPhase buildingPartPhase) {
        if (buildingPartPhase == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (buildingPartPhase.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond buildingPartPhase.errors, view:'edit'
            return
        }

        buildingPartPhase.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'buildingPartPhase.label', default: 'BuildingPartPhase'), buildingPartPhase.id])
                redirect buildingPartPhase
            }
            '*'{ respond buildingPartPhase, [status: OK] }
        }
    }

    @Transactional
    def delete(BuildingPartPhase buildingPartPhase) {

        if (buildingPartPhase == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        buildingPartPhase.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'buildingPartPhase.label', default: 'BuildingPartPhase'), buildingPartPhase.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'buildingPartPhase.label', default: 'BuildingPartPhase'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
