package de.toporaz;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class GrailsUser extends User {

    private static final long serialVersionUID = 1;

    private final Object id;

    /**
     * Constructor.
     *
     * @param username the username presented to the
     *        DaoAuthenticationProvider
     * @param password the password that should be presented to the
     *        DaoAuthenticationProvider
     * @param enabled set to true if the user is enabled
     * @param accountNonExpired set to true if the account has not expired
     * @param credentialsNonExpired set to true if the credentials have not expired
     * @param accountNonLocked set to true if the account is not locked
     * @param authorities the authorities that should be granted to the caller if they
     *        presented the correct username and password and the user is enabled. Not null.
     * @param id the id of the domain class instance used to populate this
     */
    public GrailsUser(String username,
                      String password,
                      boolean enabled,
                      boolean accountNonExpired,
                      boolean credentialsNonExpired,
                      boolean accountNonLocked,
                      Collection authorities,
                      Object id) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired,
                accountNonLocked, authorities);
        this.id = id;
    }

    /**
     * Get the id.
     * @return the id
     */
    public Object getId() {
        return id;
    }
}