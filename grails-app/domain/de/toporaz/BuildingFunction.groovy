package de.toporaz

class BuildingFunction {

    BuildingFunctionsEnum buildingFunction
    String note

    ToporazDate functionStart
    ToporazDate functionEnd

    static hasMany = [reference: Document]


    static mappedBy = [ functionStart: "none", functionEnd: "none" ]

    static constraints = {
        buildingFunction nullable: false
        note blank: true, nullable: true, type: 'text'
        functionStart nullable: true
        functionEnd nullable: true
        reference nullable:true
    }

    enum BuildingFunctionsEnum {
        ACCOMODATION,
        CIVIC,
        COMMERCIAL,
        SACRAL
    }
}
