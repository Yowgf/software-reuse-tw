package ElectoralSystem;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class VoterTest {
  @Test
  public void testVoterBuilder() {
    String electoralCard = "123456789012";
    String name = "Roberto";
    String state = "MG";

    Voter voter = new Voter.Builder().electoralCard(electoralCard).name(name).state(state).build();

    assertTrue(voter.getElectoralCard().equals(electoralCard));
    assertTrue(voter.getName().equals(name));
    assertTrue(voter.getState().equals(state));
  }
}
