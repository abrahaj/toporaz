package de.toporaz

class BuildingReference {
    ToporazDate date
    Document document

    static constraints = {
        document blank: false, nullable: false
    }
}
