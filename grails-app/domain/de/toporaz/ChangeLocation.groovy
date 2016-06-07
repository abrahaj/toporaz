package de.toporaz

class ChangeLocation {

    String name
    Building transferredFrom
    Building transferredTo
    ToporazDate date
    Document locationReference

    static mappedBy = [ transferredFrom: "none", transferredTo: "none" ]
    static constraints = {
        name nullable: false
    }

    String toString(){
        $("name")
    }
}
