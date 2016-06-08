package de.toporaz

class Marriage {
    static hasMany = [person: Person]
    Document document
    ToporazDate date

    static constraints = {

    }
    String toString(){
        return "Marriate -"+id
    }
}
