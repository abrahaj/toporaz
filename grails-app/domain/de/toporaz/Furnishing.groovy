package de.toporaz

class Furnishing {
    String name
    String type
    String note
    String category

    /** Production info **/
    static hasMany = [donor:Person,producer:Person, measurement:Measurement, changeLocation:ChangeLocation, depicts: Person]
    ToporazDate productionDate
    String material
    String technique
    String location
    Document reference
    /** End Production **/

    String conditionState
    String conservationStatus
    Person conservationStatusAssessedBy
    ToporazDate conservationStatusDate
    Document conservationStatusReference

    static mappedBy = [ donor: "none", producer: "none", depicts:"none"]


    static constraints = {
        name blank: false, nullable: false
        note blank: true, nullable: true, type: 'text'
        type blank: true, nullable: true
        category blank: true, nullable: true

        productionDate nullable: true
        material nullable: true
        technique  nullable: true
        location  nullable: true
        reference nullable: true

        conservationStatus nullable: true
        conditionState nullable: true
        conservationStatusAssessedBy  nullable: true
        conservationStatusDate nullable: true
        conservationStatusReference nullable: true
    }
    String toString(){
        return name
    }
}
