import de.toporaz.Authority
import de.toporaz.Profession
import de.toporaz.User
import de.toporaz.UserAuthority
import grails.util.Environment
import org.hibernate.HibernateException

class BootStrap {

    def init = { servletContext ->

        switch (Environment.current) {
            case Environment.DEVELOPMENT:
                try {
                    def user = new User(username: 'user', password: 'user', enabled: true, accountExpired: false, accountLocked: false, credentialsExpired: false ).save(failOnError: true)
                    def admin = new User(username: 'admin', password: 'admin', enabled: true, accountExpired: false, accountLocked: false, credentialsExpired: false ).save(failOnError: true)

                    def roleUser = new Authority(authority: 'ROLE_USER').save(failOnError: true)
                    def roleAdmin = new Authority(authority: 'ROLE_ADMIN').save(failOnError: true)

                    UserAuthority.create(user, roleUser, true)
                    UserAuthority.create(admin, roleUser, true)
                    UserAuthority.create(admin, roleAdmin, true)

                    def p1 = new Profession(name: 'DOMESTIC', description: 'Household servant').save(failOnError: true)
                    def p2 = new Profession(name: 'DOOR KEEPER', description: 'Guard, janitor, or porter ').save(failOnError: true)
                    def p3 = new Profession(name: 'GATER', description: 'Watchman, Security ').save(failOnError: true)


                }catch (HibernateException h){
                    log.error(h.getLocalizedMessage())
                }catch (Exception e){
                    log.error (e.getLocalizedMessage())
                }


                break
            case Environment.PRODUCTION:

                break
        }
    }
    def destroy = {
    }
}


