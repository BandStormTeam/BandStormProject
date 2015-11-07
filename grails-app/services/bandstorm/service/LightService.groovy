package bandstorm.service

import bandstorm.Light
import bandstorm.Status
import bandstorm.User
import grails.transaction.Transactional

@Transactional
class LightService {

    /**
     * Light a status who the user like
     * @param user The user who lighting
     * @param status The status to lighting
     */
    def lightAStatus(User user, Status status) {
        Light newLight = new Light(user: user, status: status)
        newLight.save(flush: true)
        newLight
    }

    /**
     * Light a status who the user like
     * @param user The user who lighting
     * @param status The status to lighting
     */
    def unlightAStatus(User user, Status status) {
        Light.findByUserAndStatus(user, status)?.delete(flush: true)
    }


    /**
     * Return true if the user light the status
     * @param user
     * @param status
     */
    def isLightingStatus(User user, Status status) {
        Status[] buffStatus = Status.findAllByUser(user)

        buffStatus.each( {
            if(it == status) {
                return true;
            }
        });
        return false;
    }

    Light findByUserAndStatus(User u, Status s) {
        return Light.findByUserAndStatus(u, s);
    }
}
