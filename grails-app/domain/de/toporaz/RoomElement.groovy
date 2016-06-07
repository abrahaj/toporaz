package de.toporaz

class RoomElement {
    String name
    String note
    String type
    String direction
    Boolean window
    Boolean door
    String material
    static hasMany = [reference: Document]

    static constraints = {
        name blank: false, nullable: false
        note blank: true, nullable: true, type: 'text'
        type nullable: true
        direction nullable: true
        window nullable: false
        door nullable: false
        material nullable: true
        reference nullable: true
    }
    String toString(){
        $("name")
    }
}
