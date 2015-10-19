import bandstorm.*
import bandstorm.service.InitializationService

class BootStrap {

    InitializationService initializationService

    def init = { servletContext ->
        initializationService.populate()
    }
    def destroy = {
    }
}
