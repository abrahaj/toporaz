package de.toporaz

class Level {
    String name
    String location
    String note
    String number
    String ground
    String height
    String material
    static hasMany = [reference: Document, room:Room]

    static constraints = {
        name()
        location blank: false, nullable: false
        note blank: true, nullable: true, type: 'text'
        number nullable: true
        ground nullable: true
        height nullable: true
        material nullable: true
        reference nullable: true
        room nullable: true

    }
    String toString(){
        $("name")
    }
}
