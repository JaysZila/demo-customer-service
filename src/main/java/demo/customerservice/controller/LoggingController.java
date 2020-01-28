package demo.customerservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@RestController
public class LoggingController {
	
	private Log logger = LogFactory.getLog(LoggingController.class);
	
	@GetMapping("/logs")
	public String index() {
		logger.trace("Trace Message");
		logger.debug("Debug Message");
		logger.info("Info Message");
		logger.warn("Warn Message");
		logger.error("Eroor Message");
		return "Check out the logs to see the output";
	}

}
