package de.toporaz

class User {

    String username
    String password
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean credentialsExpired

    static constraints = {
        username blank: false, unique: true
        password blank: false
    }

    static mapping = {
        password column: '`password`'
    }

    Set getAuthorities() {
        UserAuthority.findAllByUser(this).collect { it.authority }
    }
    String toString(){
        return username
    }

}
