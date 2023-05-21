package ElectoralSystem;

public class TSEProfessional {
  protected final String user;

  protected final String password;

  public String getUser() {
    return this.user;
  }

  public String getPassword() {
    return this.password;
  }

  public TSEProfessional(String user, String password) {
    this.user = user;
    this.password = password;
  }
}
