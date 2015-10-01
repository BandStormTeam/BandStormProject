package bandstorm

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserSpec extends Specification {

    void "is Username valid"() {

        when: "we create a user instance"
        User user = new User(username: userN, email: "test@mydomain.com",
                firstName: "jon", lastName: "doe", birthDate: new Date(), country: "somewhere")

        then: "validation..."
        user.validate() == usernameOK

        where: "username and usernameOK"
        userN  | usernameOK
        "test" | true
        "tt"   | false
        ""     | false
        null   | false
    }

    void "is email valid"() {

        when: "we create a user instance"
        User user = new User(username: "userName", email: emailAd,
                firstName: "jon", lastName: "doe", birthDate: new Date(), country: "somewhere")

        then: "validation..."
        user.validate() == emailOK

        where: "emailAd and emailOK"
        emailAd             | emailOK
        "test@mydomain.com" | true
        "test"              | false
        ""                  | false
        null                | false
    }

    void "is fistNameValid"() {

        when: "we create a user instance"
        User user = new User(username: "userName", email: "test@mydomain.com",
                firstName: fstName, lastName: "doe", birthDate: new Date(), country: "somewhere")
        then: "validation..."
        user.validate() == fstNameOK

        where: "fstName and fstNameOK"
        fstName         | fstNameOK
        "a name"        | true
        "test"          | true
        ""              | false
        null            | false
    }

    void "is Last Name Valid"() {

        when: "we create a user instance"
        User user = new User(username: "userName", email: "test@mydomain.com",
                firstName: "jon", lastName: lstName, birthDate: new Date(), country: "somewhere")
        then: "validation..."
        user.validate() == lstNameOK

        where: "lstName and lstNameOK"
        lstName         | lstNameOK
        "a name"        | true
        "test"          | true
        ""              | false
        null            | false
    }

    void "is country Valid"() {

        when: "we create a user instance"
        User user = new User(username: "userName", email: "test@mydomain.com",
                firstName: "jon", lastName: "doe", birthDate: new Date(), country: cntry)
        then: "validation..."
        user.validate() == cntryOK

        where: "cntry and cntryOK"
        cntry           | cntryOK
        "a name"        | true
        "test"          | true
        ""              | false
        null            | false
    }

    void "is birthDate validate"() {
        when: "we create a user instance"
        User user = new User(username: "userName", email: "test@mydomain.com",
                firstName: "jon", lastName: "doe", birthDate: date, country: "Country")
        then: "validation..."
        user.validate() == dateOK

        where: "date and dateOK"
        date                                                            | dateOK
        new Date()                                                      | true
        Date.parse("yyyy-MM-dd hh:mm:ss", "2014-04-03 1:23:45")         | true
        Date.parse("yyyy-MM-dd hh:mm:ss", "2016-04-03 1:23:45")         | false
        null                                                            | false
    }
}
