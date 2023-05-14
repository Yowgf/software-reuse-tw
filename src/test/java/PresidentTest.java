package ElectoralSystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PresidentTest {
  @Test
  public void testPresidentBuilder(){
    String name = "Alberto";
    String party = "PV";
    int number = 12;

		President pres = new President.Builder()
        .name(name)
        .party(party)
        .number(number)
        .build();

    assertTrue(pres.getName().equals(name));
    assertTrue(pres.getParty().equals(party));
    assertTrue(pres.getNumber() == number);
	}
}
