package beta;
import org.apache.logging.log4j.*;
import org.testng.annotations.Test;
import org.apache.*;

@Test
public class Demob {

private static Logger log = LogManager.getLogger(Demob.class.getName());

public static void logtest() {
	log.debug("I am debugging");
	
	if(2<0) {
		log.debug("Object is present");
		log.info("Object is present in here");
	}
	else {
		log.error("Object is not present");
		log.fatal("this is fatal");
		log.debug("Object is not nice present");
	}
}


}
