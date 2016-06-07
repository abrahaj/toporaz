package de.toporaz

class Building {
    String name
    String type
    String note
    String location

    BuildingReference firstReference
    BuildingReference desctructionReference



    static mappedBy = [ firstReference: "none", desctructionReference: "none" ]
    static hasMany = [buildingFunction:BuildingFunction, buildingTypology:BuildingTypology, address: Address, buildingPart:BuildingPart, buildingOwnership: BuildingOwnership]


    static constraints = {
        name blank: false, nullable: false
        note blank: true, nullable: true, type: 'text'
        type blank: true, nullable: true

        location blank: true, nullable: true, type: 'text'

        firstReference nullable: true
        desctructionReference  nullable: true
        buildingOwnership nullable:true
        buildingFunction nullable:true
        buildingTypology nullable:true
        address nullable:true
        buildingPart nullable:true
        buildingOwnership nullable:true


    }

    String toString(){
        $("name")
    }
}
