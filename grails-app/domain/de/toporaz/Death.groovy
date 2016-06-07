package de.toporaz

class Death {
    String note
    ToporazDate dateOfDeath
    String buried
    Document reference
    static belongsTo = [person:Person]
    static constraints = {
        note blank: true, nullable: true, type: 'text'
        buried blank: true, nullable: true
        reference nullable: true
    }

}
