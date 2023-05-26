package ElectoralSystem;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TSEEmployeeTest {
  @Test
  public void testTSEEmployeeBuilder() {
    String user = "Alberto";
    String password = "123";

    TSEEmployee tseEmployee = new TSEEmployee.Builder().user(user).password(password).build();

    assertTrue(tseEmployee.getUser().equals(user));
  }
}
