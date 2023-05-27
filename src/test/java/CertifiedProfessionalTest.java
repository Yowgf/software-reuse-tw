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
    Election election = new Election(electionPassword, null);

    certifiedProfessional.startSession(election, electionPassword);
    assertFalse(election.getFinished());
  }

  @Test
  public void testStartSessionWrong() {
    String user = "Alberto";
    String password = "123";
    CertifiedProfessional certifiedProfessional =
        new CertifiedProfessional.Builder().user(user).password(password).build();

    String electionPassword = "123";
    Election election = new Election(electionPassword, null);

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
    Election election = new Election(electionPassword, null);

    certifiedProfessional.startSession(election, electionPassword);
    certifiedProfessional.endSession(election, electionPassword);
    assertTrue(election.getFinished());
  }

  @Test
  public void testEndSessionWrong() {
    String user = "Alberto";
    String password = "123";
    CertifiedProfessional certifiedProfessional =
        new CertifiedProfessional.Builder().user(user).password(password).build();

    String electionPassword = "123";
    Election election = new Election(electionPassword, null);

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
}
