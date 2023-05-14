package ElectoralSystem;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ElectionTest {
  @Test
  public void isValidTest() {
    String electionPassword = "password";

    Election currentElection = new Election.Builder().password(electionPassword).build();

    assertTrue(currentElection.isValid(electionPassword));
    assertFalse(currentElection.isValid("wrong"));
  }
}
