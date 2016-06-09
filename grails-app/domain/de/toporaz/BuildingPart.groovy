package de.toporaz

class BuildingPart {
    String name
    BuildingTypeEnum buildingTypeEnum
    String note

    String material
    static hasMany = [commissionedBy: Person, buildingPartPhase: BuildingPartPhase, facade:Facade, roof:Roof,level:Level]

    Building building
    static belongsTo=[Building, BuildingPartPhase, Facade, Roof, Level]


    static constraints = {
        name blank: false, nullable: false
        buildingTypeEnum nullable: false
        note blank: true, nullable: true, type: 'text'
        material nullable: true
        commissionedBy nullable: true
        buildingPartPhase nullable:true
        facade nullable:true
        roof nullable:true
        level nullable:true

    }

    enum BuildingTypeEnum {
        Hauptgebaeude,
        Nebengebaeude
    }

    String toString(){
        return name
    }
}
