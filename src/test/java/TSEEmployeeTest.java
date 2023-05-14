package ElectoralSystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TSEEmployeeTest {
  @Test
  public void testTSEEmployeeBuilder(){
    String user = "Alberto";
    String password = "123";

		TSEEmployee tseEmployee = new TSEEmployee.Builder()
      .user(user)
      .password(password)
      .build();

    // assertTrue(tseEmployee.getUser().equals(user));
	}

  @Test
  public void addCandidateTest(){
    String user = "Alberto";
    String password = "123";

		// TSEEmployee tseEmployee = new TSEEmployee.Builder()
    //   .user(user)
    //   .password(password)
    //   .build();
    
    // String electionPassword = "password";

    // Election currentElection = new Election.Builder()
    //   .password(electionPassword)
    //   .build();

    // President presidentCandidate1 = new President.Builder()
    //     .name("João").number(123).party("PDS1").build();
    // FederalDeputy federalDeputyCandidate1 = new FederalDeputy.Builder()
    //     .name("Carlos").number(12345).party("PDS1");
    
    // tseEmployee.addCandidate(presidentCandidate1, currentElection, electionPassword);
    // tseEmployee.addCandidate(federalDeputyCandidate1, currentElection, electionPassword);

    // assertTrue(currentElection.getPresidentByNumber(123).getName().equals("João"));
    // assertTrue(currentElection.getPresidentByNumber(12345).getName().equals("Carlos"));
  }

  @Test
  public void removeCandidateTest(){
    String user = "Alberto";
    String password = "123";

		TSEEmployee tseEmployee = new TSEEmployee.Builder()
        .user(user)
        .password(password)
        .build();
    
    String electionPassword = "password";

    Election currentElection = new Election.Builder()
      .password(electionPassword)
      .build();

    // President presidentCandidate1 = new President.Builder().name("João")
    //     .number(123).party("PDS1").build();
    // FederalDeputy federalDeputyCandidate1 = new FederalDeputy.Builder()
    //     .name("Carlos").number(12345).party("PDS1");
    
    // tseEmployee.addCandidate(presidentCandidate1, currentElection, electionPassword);
    // tseEmployee.addCandidate(federalDeputyCandidate1, currentElection, electionPassword);

    // tseEmployee.removeCandidate(presidentCandidate1, currentElection, electionPassword);
    // tseEmployee.removeCandidate(federalDeputyCandidate1, currentElection, electionPassword);


    // assertFalse(currentElection.getPresidentCandidates.containsKey(123));
    // assertFalse(currentElection.getFederalDeputyCandidates.containsKey(12345));
  }
}
