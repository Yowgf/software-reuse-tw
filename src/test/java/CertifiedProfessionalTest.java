package ElectoralSystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CertifiedProfessionalTest {
  @Test
  public void testCertifiedProfessionalBuilder() {
    String user = "Alberto";
    String password = "123";

    CertifiedProfessional certifiedProfessional =
        new CertifiedProfessional.Builder().user(user).password(password).build();

    assertTrue(certifiedProfessional.getUser().equals(user));
  }

  @Test
  public void testStartSession() {
    String user = "Alberto";
    String password = "123";
    CertifiedProfessional certifiedProfessional =
        new CertifiedProfessional.Builder().user(user).password(password).build();

    String electionPassword = "123";
    Election election = new Election.Builder().password(electionPassword).build();

    certifiedProfessional.startSession(election, electionPassword);

    assertTrue(election.getStatus() == true);
  }

  @Test
  public void testStartSessionWrong() {
    String user = "Alberto";
    String password = "123";
    CertifiedProfessional certifiedProfessional =
        new CertifiedProfessional.Builder().user(user).password(password).build();

    String electionPassword = "123";
    Election election = new Election.Builder().password(electionPassword).build();

    Error exception =
        assertThrows(
            Error.class,
            () -> {
              certifiedProfessional.startSession(election, "12");
            });

    String expectedMessage = "Senha inválida";
    String actualMessage = exception.getMessage();

    assertTrue(expectedMessage.equals(actualMessage));
  }

  @Test
  public void testEndSession() {
    String user = "Alberto";
    String password = "123";
    CertifiedProfessional certifiedProfessional =
        new CertifiedProfessional.Builder().user(user).password(password).build();

    String electionPassword = "123";
    Election election = new Election.Builder().password(electionPassword).build();

    certifiedProfessional.startSession(election, electionPassword);
    certifiedProfessional.endSession(election, electionPassword);

    assertTrue(election.getStatus() == false);
  }

  @Test
  public void testEndSessionWrong() {
    String user = "Alberto";
    String password = "123";
    CertifiedProfessional certifiedProfessional =
        new CertifiedProfessional.Builder().user(user).password(password).build();

    String electionPassword = "123";
    Election election = new Election.Builder().password(electionPassword).build();

    certifiedProfessional.startSession(election, electionPassword);
    Warning exception =
        assertThrows(
            Warning.class,
            () -> {
              certifiedProfessional.endSession(election, "12");
            });

    String expectedMessage = "Senha inválida";
    String actualMessage = exception.getMessage();

    assertTrue(expectedMessage.equals(actualMessage));
  }

  @Test
  public void testShowFinalResult() {

    String electionPassword = "password";

    Election currentElection = new Election.Builder().password(electionPassword).build();

    Voter v1 = new Voter.Builder().name("v1").electoralCard("123456789012").state("MG").build();
    Voter v2 = new Voter.Builder().name("v2").electoralCard("223456789022").state("MG").build();
    Voter v3 = new Voter.Builder().name("v3").electoralCard("333456789033").state("MG").build();

    President presidentCandidate1 =
        new President.Builder().name("João").number(123).party("PDS1").build();
    currentElection.addPresidentCandidate(presidentCandidate1, electionPassword);
    President presidentCandidate2 =
        new President.Builder().name("Maria").number(124).party("ED").build();
    currentElection.addPresidentCandidate(presidentCandidate2, electionPassword);
    FederalDeputy federalDeputyCandidate1 =
        new FederalDeputy.Builder().name("Carlos").number(12345).party("PDS1").state("MG").build();
    currentElection.addFederalDeputyCandidate(federalDeputyCandidate1, electionPassword);
    FederalDeputy federalDeputyCandidate2 =
        new FederalDeputy.Builder().name("Cleber").number(54321).party("PDS2").state("MG").build();
    currentElection.addFederalDeputyCandidate(federalDeputyCandidate2, electionPassword);
    FederalDeputy federalDeputyCandidate3 =
        new FederalDeputy.Builder().name("Sofia").number(11211).party("IHC").state("MG").build();
    currentElection.addFederalDeputyCandidate(federalDeputyCandidate3, electionPassword);

    currentElection.start(electionPassword);

    v1.vote(123, currentElection, "President", false);
    v2.vote(123, currentElection, "President", false);
    v3.vote(124, currentElection, "President", false);
    v1.vote(12345, currentElection, "FederalDeputy", false);
    v1.vote(0000, currentElection, "FederalDeputy", false);
    v2.vote(12345, currentElection, "FederalDeputy", false);
    v2.vote(54321, currentElection, "FederalDeputy", false);
    v3.vote(12345, currentElection, "FederalDeputy", false);
    v3.vote(0, currentElection, "FederalDeputy", true);
    currentElection.finish(electionPassword);

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
    String actual = Utils.formatMultilineStr(currentElection.getResults(electionPassword));
    assertEquals(expected, actual);
  }
}
