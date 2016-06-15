package de.toporaz

class Person {

    String name
    String note
    Profession profession
    Birth birth
    Death death
    static embedded = ['birth', 'death']
    SocialStatusType socialStatus

    String toString(){
        return name
    }

    static hasMany = [alternativeName: String, marriage:Marriage, relation:PersonRelation, resident:Resident, administrativeFunction:AdministrativeFunction, authorOfBook:Document,referredPerson:Document]

    static belongsTo = [Marriage,PersonRelation, Document]

    static mappedBy = [authorOfBook:"author",referredPerson:"referencePerson"]

    static constraints = {
        name nullable: false
        note blank: true, nullable: true, type: 'text', widget: 'textarea'
        alternativeName nullable: true
        birth nullable:true,  unique: true
        death nullable:true, unique: true
        profession nullable:true
        marriage nullable: true
        relation nullable:true
        resident nullable:true
        socialStatus nullable:true
        administrativeFunction nullable:true
    }

    enum SocialStatusType {
        Patrizier,
        Handwerker
    }
}
