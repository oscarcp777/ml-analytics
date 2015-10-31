import com.ml.analytics.Analytics
import com.ml.analytics.Search
import com.ml.analytics.UserML
import com.ml.security.SecRole
import com.ml.security.SecUserSecRole
class BootStrap {

    def init = { 
		createSystemUser()
	}

    def destroy = {
    }
	
	void createSystemUser(){		
		
		if (UserML.findByUsername("oscar.cp")==null) {
		def adminRole = SecRole.findByAuthority('ROLE_ADMIN') ?: new SecRole(authority: 'ROLE_ADMIN').save(failOnError: true)
		def userRole = SecRole.findByAuthority('ROLE_USER') ?: new SecRole(authority: 'ROLE_USER').save(failOnError: true)

		def user1 = UserML.findByUsername('desaml1') ?: new UserML(username: 'julian.klas', password: 'pass',enabled: true, firstName: 'Julian', lastName: 'Klas').save(failOnError: true)
			
		def user2=new UserML(username: 'oscar.cp', password: 'pass',enabled: true, firstName: 'Oscar', lastName: 'Caceres')
			user2.save(failOnError: true)
			
			SecUserSecRole.create user1, userRole, true
			SecUserSecRole.create user2, userRole, true
			SecUserSecRole.create user2, adminRole, true
		
		assert UserML.count() == 2
		assert SecRole.count() == 2
		assert SecUserSecRole.count() == 3
		}else{
		println "Existing admin user, skipping creation"
		}
		
	}
}
