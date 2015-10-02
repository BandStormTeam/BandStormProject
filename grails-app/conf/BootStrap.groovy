import bandstorm.*

class BootStrap {

    def init = { servletContext ->
        def adminRole = new SecRole('ROLE_ADMIN').save()
        def userRole = new SecRole('ROLE_USER').save()

        Date birth = Date.parse("yyyy-MM-dd hh:mm:ss", "1992-04-03 1:23:45")

        def testUser = new User(username:'merry', password:'password', email: 'myeam@somewhere.here', firstName:'jon', lastName: 'doe', birthDate: birth, country: 'IT')
        testUser.save()

        SecUserSecRole.create testUser, adminRole, true

        assert User.count() == 1
        assert SecRole.count() == 2
        assert SecUserSecRole.count() == 1
    }
    def destroy = {
    }
}
