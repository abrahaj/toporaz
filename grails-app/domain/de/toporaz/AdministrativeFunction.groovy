package de.toporaz

class AdministrativeFunction {
    String name
    String note
    String office
    ToporazDate start
    ToporazDate end
    Document reference

    static mappedBy = [start: "none", end: "none"]


    static constraints = {
        name blank: false, nullable: false, type: 'text'
        note blank: true, nullable: true, type: 'text'
        office blank: true, nullable: true
        start nullable: true
        end  nullable: true
        reference nullable:true
    }
    String toString(){
        $("name")
    }
}
