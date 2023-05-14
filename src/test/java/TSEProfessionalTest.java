package ElectoralSystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TSEProfessionalTest {
  @Test
  public void testTSEProfessionalConstructor(){
    String user = "Alberto";
    String password = "123";

		TSEProfessional tseProfessional = new TSEProfessional(user, password);

    // assertTrue(tseProfessional.getUser().equals(user));
	}
}
