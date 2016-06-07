package de.toporaz

class Resident {
    String note
    ToporazDate start
    ToporazDate end
    Document reference
    static mappedBy = [start: "none", end: "none"]
    static constraints = {
        note blank: true, nullable: true, type: 'text'
        start blank: true, nullable: true
        end blank: true, nullable: true
    }
}
