package de.toporaz

class Resident {
    Building building
    String note

    ToporazDate start
    ToporazDate end
    Document reference
    static mappedBy = [start: "none", end: "none"]
    Person person
    static constraints = {
        building blank:false, nullable: false
        note blank: true, nullable: true, type: 'text'
        start blank: true, nullable: true
        end blank: true, nullable: true
    }
}
