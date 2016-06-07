package de.toporaz

class Room {
    String name
    String note
    RoomFunctionEnum function
    Boolean heated

    static hasMany = [reference: Document, roomElement:RoomElement]

    static constraints = {
        name blank: false, nullable: false
        note blank: true, nullable: true, type: 'text'
        function nullable: true
        heated nullable: false
        reference nullable: true
        roomElement nullable: true
    }

    enum RoomFunctionEnum {
        Kapelle,
        Kitchen,
        Lager,
        LivingRoom,
        Schlafzimmer,
        Speisezimmer,
        StairwayHall,
        Verkaufsraum
    }
    String toString(){
        return name
    }
}
