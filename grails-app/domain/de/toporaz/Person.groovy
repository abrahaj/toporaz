package de.toporaz

class Person {
    String name
    String note
    //TODO HAS MORE THAN ONE ALTERNATIVE NAME
    String alternativeName

    Profession profession
    static hasMany = [marriage:Marriage, relation:PersonRelation, resident:Resident, administrativeFunction:AdministrativeFunction]
    static hasOne = [birth:Birth, death:Death]
    static mappedBy = [marriage:"partner"]

    SocialStatusType socialStatus

    String toString(){
        return name
    }

    static constraints = {
        name nullable: false
        note blank: true, nullable: true, type: 'text'
        alternativeName nullable: true
        birth nullable:true
        death nullable:true
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
