package de.toporaz

class BuildingOwnership {
    String note
    Person previousOwner
    Person newOwner
    Date start
    Date end
    Building building
    static belongsTo=Building
    Document reference
    static mappedBy = [start: "none", end: "none", previousOwner:"none", newOwner:'none']
    static constraints = {
        note nullable: true
        previousOwner nullable: true
        newOwner nullable: true
        start nullable: true
        end nullable: true
        reference nullable: true

    }
}
