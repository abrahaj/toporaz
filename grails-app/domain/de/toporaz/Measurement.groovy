package de.toporaz

class Measurement {
    //** Measurement **/
    String name
    String unit
    String value
    Person measuredBy
    ToporazDate date

    static constraints = {
        name nullable: false
    }
    String toString(){
        return name
    }
}
