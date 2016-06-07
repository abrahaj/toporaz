package de.toporaz

class Profession {
    String name
    String description
    static constraints = {
        name nullable: false
        description size: 1..255
    }
    String toString(){
        $("name")
    }
}
