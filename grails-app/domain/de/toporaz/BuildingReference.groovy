package de.toporaz

class BuildingReference {
    Date date
    Document document

    static constraints = {
        document blank: false, nullable: false
    }
}
