import org.openqa.selenium.firefox.FirefoxDriver

driver = {
	def driverInstance = new FirefoxDriver()
	driverInstance.manage().window().maximize();

	driverInstance
}

waiting = {
    timeout = 120
    retryInterval = 1.0
}

baseNavigatorWaiting = true
atCheckWaiting = true
