package de.toporaz

class Birth {
    String note
    ToporazDate dateOfBirth
    String location
    Document reference
    static belongsTo = [person:Person]
    static constraints = {
        note blank: true, nullable: true, type: 'text'
        location blank: true, nullable: true
        reference nullable: true
    }
}
