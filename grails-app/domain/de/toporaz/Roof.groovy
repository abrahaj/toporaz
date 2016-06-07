package de.toporaz

class Roof {
    String name
    String note
    String shape
    String material
    String direction
    String element
    static hasMany = [reference: Document]

    static constraints = {
        name blank: false, nullable: false
        note blank: true, nullable: true, type: 'text'
        shape nullable: true
        material nullable: true
        direction nullable: true
        element nullable: true
        reference nullable: true
    }
    String toString(){
        return name
    }
}
