import de.toporaz.SecurityConfiguration
import example.GormUserDetailsService


// Place your Spring DSL code here
beans = {
    webSecurityConfiguration(SecurityConfiguration)
    userDetailsService(GormUserDetailsService)
}
