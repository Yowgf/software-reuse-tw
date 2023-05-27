package ElectoralSystem;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class FederalDeputyTest {
  @Test
  public void testFederalDeputyBuilder() {
    String name = "Alberto";
    String party = "PV";
    int number = 1324;
    String state = "MG";

    FederalDeputy fedDeputy =
        new FederalDeputy.Builder()
            .name(name)
            .party(party)
            .number(number)
            .state(state)
            .build();

    assertTrue(fedDeputy.getName().equals(name));
    assertTrue(fedDeputy.getParty().equals(party));
    assertTrue(fedDeputy.getNumber() == number);
    assertTrue(fedDeputy.getLocation().equals(state));
  }
}
