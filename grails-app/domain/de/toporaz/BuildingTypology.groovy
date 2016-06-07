package de.toporaz

class BuildingTypology {

    BuildingTypologyEnum buildingTypology
    String note

    ToporazDate typologyStart
    ToporazDate typologyEnd

    static hasMany = [reference: Document]


    static mappedBy = [typologyStart: "none", typologyEnd: "none"]

    static constraints = {
        buildingTypology nullable: false
        note blank: true, nullable: true, type: 'text'
        typologyStart nullable: true
        typologyEnd nullable: true
        reference nullable: true
    }

    enum BuildingTypologyEnum {
        Basilika,
        Civic,
        Hallenkirche,
        Kreuzkirche,
        Querkirche,
        Reihenhaus,
        Saalbau,
        Turmkirche,
        Winkelkirche,
        Zeilenhaus,
        Zentralbau
    }
}
