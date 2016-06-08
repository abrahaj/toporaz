package de.toporaz

class PersonRelation {
    RelationType relationType
    static hasMany=[person:Person]
    static constraints = {
    }

    enum RelationType {
        Sibling,
        Father,
        Lover,
        Mentor,
        Mother
    }
}
