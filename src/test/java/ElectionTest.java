package ElectoralSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

  @Test
  public void testShowFinalResult() {
    String electionPassword = "password";

    Election election = new Election.Builder().password(electionPassword).build();

    Voter v1 = new Voter.Builder().name("v1").electoralCard("123456789012").location("MG").build();
    Voter v2 = new Voter.Builder().name("v2").electoralCard("223456789022").location("MG").build();
    Voter v3 = new Voter.Builder().name("v3").electoralCard("333456789033").location("MG").build();

    President presidentCandidate1 =
        new President.Builder().name("João").number(123).party("PDS1").build();
    election.addCandidate(presidentCandidate1);
    President presidentCandidate2 =
        new President.Builder().name("Maria").number(124).party("ED").build();
    election.addCandidate(presidentCandidate2);
    FederalDeputy federalDeputyCandidate1 =
        new FederalDeputy.Builder()
            .name("Carlos")
            .number(12345)
            .party("PDS1")
            .location("MG")
            .build();
    election.addCandidate(federalDeputyCandidate1);
    FederalDeputy federalDeputyCandidate2 =
        new FederalDeputy.Builder()
            .name("Cleber")
            .number(54321)
            .party("PDS2")
            .location("MG")
            .build();
    election.addCandidate(federalDeputyCandidate2);
    FederalDeputy federalDeputyCandidate3 =
        new FederalDeputy.Builder().name("Sofia").number(11211).party("IHC").location("MG").build();
    election.addCandidate(federalDeputyCandidate3);

    election.start(electionPassword);

    election.addVote(President.type, presidentCandidate1.getNumber());
    election.addVote(President.type, presidentCandidate1.getNumber());
    election.addVote(President.type, presidentCandidate2.getNumber());
    election.addVote(FederalDeputy.type, federalDeputyCandidate1.getNumber());
    election.addVote(FederalDeputy.type, federalDeputyCandidate1.getNumber());
    election.addVote(FederalDeputy.type, federalDeputyCandidate2.getNumber());
    election.addVote(FederalDeputy.type, federalDeputyCandidate1.getNumber());
    election.addVote(FederalDeputy.type, 0);
    election.addVote(FederalDeputy.type, 0);
    election.finish(electionPassword);

    String ans =
        """
Resultado da eleicao:
      Votos presidente:
      Total: 3
      Votos nulos: 0 (0.00%)
      Votos brancos: 0 (0.00%)
        Numero - Partido - Nome  - Votos  - % dos votos totais
        123 - PDS1 - João - 2 - 66.67%
        124 - ED - Maria - 1 - 33.33%


      Presidente eleito:
      João do PDS1 com 66.67% dos votos

    =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=



      Votos deputado federal:
      Votos nulos: 2 (33.33%)
      Votos brancos: 0 (0.00%)
      Total: 6
        Numero - Partido - Nome - Estado - Votos - % dos votos totais
        12345 - PDS1 - MG - Carlos - 3 - 50.00%
        54321 - PDS2 - MG - Cleber - 1 - 16.67%
        11211 - IHC - MG - Sofia - 0 - 0.00%


      Deputados eleitos:
      1º Carlos do PDS1 com 50.00% dos votos
      2º Cleber do PDS2 com 16.67% dos votos
""";
    String expected = Utils.formatMultilineStr(ans);
    String actual = Utils.formatMultilineStr(election.getResults(electionPassword));
    assertEquals(expected, actual);
  }
}
