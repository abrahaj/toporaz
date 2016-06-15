package de.toporaz

class ChangeLocation {

    String name
    Building transferredFrom
    Building transferredTo
    Date date
    Document referenceForChange

    static belongsTo = [document:Document]

    static mappedBy = [ transferredFrom: "none", transferredTo: "none", document:"changeLocation"]

    static constraints = {
        name nullable: false
        document editable:false
        referenceForChange()
    }

    String toString(){
       return name
    }
}
