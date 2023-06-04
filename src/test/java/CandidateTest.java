package ElectoralSystem;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CandidateTest {
  @Test
  public void testCandidateConstructor() {
    String name = "Alberto";
    String party = "PV";
    int number = 123;
    String loc = "location1";

    Candidate candidate = new Candidate(name, party, number, loc);

    assertTrue(candidate.getName().equals(name));
    assertTrue(candidate.getParty().equals(party));
    assertTrue(candidate.getNumber() == number);
    assertTrue(candidate.getLocation() == loc);
  }
}
