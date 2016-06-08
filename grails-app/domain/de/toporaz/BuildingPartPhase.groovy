package de.toporaz

class BuildingPartPhase {
    String name
    String note
    ToporazDate start
    ToporazDate end
    ToporazDate demolitionConversionDate
    static hasMany = [reference: Document]


    static mappedBy = [start: "none", end: "none", demolitionConversionDate: "none"]
    static constraints = {
        name nullable: false
        note blank: true, nullable: true, type: 'text'
        start nullable: true
        end nullable: true
        demolitionConversionDate nullable: true
        reference nullable:true
    }
    String toString(){
        return name
    }
}
