package ElectoralSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ElectionTest {
  @Test
  public void isValidTest(){
    String electionPassword = "password";

    Election currentElection = new Election.Builder()
      .password(electionPassword)
      .build();

    // assertTrue(currentElection.isValid(electionPassword));
    // assertFalse(currentElection.isValid("wrong"));
  }
}
