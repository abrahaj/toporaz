package de.toporaz

class Document {
    String name
    String type
    ToporazDate publishedDate
    String publishedLocation
    String shelfmark
    String scale
    String measurement
    String volume
    String placeOfStorage
    String digitalCopy

    static hasMany = [author: Person, referencePerson: Person, referenceBuilding: Building, changeLocation: ChangeLocation]


    static constraints = {
        name blank: false, nullable: false
        type blank: true, nullable: true
        author nullable: true
        publishedDate blank: true, nullable: true
        publishedLocation blank: true, nullable: true
        changeLocation nullable: true
        shelfmark blank: true, nullable: true
        scale blank: true, nullable: true
        measurement blank: true, nullable: true
        volume blank: true, nullable: true
        placeOfStorage blank: true, nullable: true
        digitalCopy blank: true, nullable: true
        referencePerson nullable: true
        referenceBuilding nullable: true
    }
    String toString(){
        return name
    }
}
