package de.toporaz

class Address {
    String houseName
    String note
    String street
    String number
    String sNumber
    ToporazDate period

    Building building
    static belongsTo=Building
    static hasMany = [reference: Document]

    static constraints = {
        houseName blank: false, nullable: false
        note blank: true, nullable: true, type: 'text'

        street nullable: true
        number nullable: true
        sNumber nullable: true
        period nullable: true
        reference nullable: true

    }

    String toString(){
        return houseName
    }
}
