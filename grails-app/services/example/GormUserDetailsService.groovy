package example

import grails.transaction.Transactional
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import de.toporaz.GrailsUser

@Transactional
class GormUserDetailsService implements UserDetailsService {

    @Transactional(readOnly = true, noRollbackFor = [IllegalArgumentException, UsernameNotFoundException])
    UserDetails loadUserByUsername(String username, boolean loadRoles) throws UsernameNotFoundException {

        def user = User.findWhere(username: username)
        if (!user) {
            log.warn "User not found: $username"
            throw new UsernameNotFoundException('User not found')
        }

        Collection authorities = loadAuthorities(user, username, loadRoles)
        createUserDetails user, authorities
    }

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        loadUserByUsername username, true
    }

    protected Collection loadAuthorities(user, String username, boolean loadRoles) {
        if (!loadRoles) {
            return []
        }

        Collection userAuthorities = user.authorities
        def authorities = userAuthorities.collect { new SimpleGrantedAuthority(it.authority) }
        return authorities ?: [NO_ROLE]
    }

    protected UserDetails createUserDetails(user, Collection authorities) {
        new GrailsUser(user.username, user.password, user.enabled, !user.accountExpired, !user.credentialsExpired,
                !user.accountLocked, authorities, user.id)
    }
}