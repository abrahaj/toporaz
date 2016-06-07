package de.toporaz

class PersonRelation {
    RelationType relationType
    Person person
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
