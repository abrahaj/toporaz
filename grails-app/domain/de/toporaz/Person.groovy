package de.toporaz

class Person {
    static belongsTo = [Marriage,PersonRelation]

    String name
    String note
    Profession profession
    static hasMany = [alternativeName: String, marriage:Marriage, relation:PersonRelation, resident:Resident, administrativeFunction:AdministrativeFunction]

    Birth birth
    Death death
    static embedded = ['birth', 'death']
    SocialStatusType socialStatus

    String toString(){
        return name
    }

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
