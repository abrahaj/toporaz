package de.toporaz

class Facade {
    String name
    String note
    String direction
    String axes
    String elements
    static hasMany = [reference: Document, furnishing:Furnishing]

    static constraints = {
        name blank: false, nullable: false
        note blank: true, nullable: true, type: 'text'
        direction nullable: true
        axes nullable: true
        elements nullable: true
        furnishing nullable: true
        reference nullable: true
    }
    String toString(){
        $("name")
    }
}
