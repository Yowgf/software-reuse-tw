package ElectoralSystem;

import java.util.HashMap;
import java.util.Map;

// UrnaAdmin represents the backend where an administrator can start or end an
// election using their credentials.
class UrnaAdmin {
  private final Map<String, CertifiedProfessional> CertifiedProfessionalMap = new HashMap<>();

  private Election election;
  private boolean sessionStarted = false;

  public UrnaAdmin(Election election) {
    this.election = election;
    loadTestCertifiedProfessionals();
  }

  private void print(String s) {
    System.out.println(s);
  }

  public void menu() {
    try {
      print("Certified Professional login\n");
      CertifiedProfessional certifiedProfessional = getCertifiedProfessional();
      if (certifiedProfessional == null) return;
      boolean back = false;
      while (!back) {
        print("(1) Iniciar sessão");
        print("(2) Finalizar sessão");
        print("(3) Mostrar resultados");
        print("(0) Sair");
        int command = PluginUtils.readInt();
        switch (command) {
          case 1 -> startSession((CertifiedProfessional) certifiedProfessional);
          case 2 -> endSession((CertifiedProfessional) certifiedProfessional);
          case 3 -> showResults((CertifiedProfessional) certifiedProfessional);
          case 0 -> back = true;
          default -> print("Comando inválido\n");
        }
      }
    } catch (Warning e) {
      print(e.getMessage());
    }
    return;
  }

  public boolean getSessionStarted() {
    return sessionStarted;
  }

  private void startSession(CertifiedProfessional certifiedProfessional) {
    try {
      print("Insira a senha da urna");
      String pwd = PluginUtils.readString();
      certifiedProfessional.startSession(election, pwd);
      sessionStarted = true;
      print("Sessão inicializada");
      print("\n");
    } catch (Warning e) {
      print(e.getMessage());
    }
  }

  private void endSession(CertifiedProfessional certifiedProfessional) {
    try {
      print("Insira a senha da urna:");
      String pwd = PluginUtils.readString();
      certifiedProfessional.endSession(election, pwd);
      print("Sessão finalizada com sucesso");
      print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
    } catch (Warning e) {
      print(e.getMessage());
    }
  }

  private CertifiedProfessional getCertifiedProfessional() {
    print("Insira seu usuário:");
    String user = PluginUtils.readString();
    CertifiedProfessional certifiedProfessional = CertifiedProfessionalMap.get(user);
    if (certifiedProfessional == null) {
      print(
          "Funcionário certificado não encontrado, por favor confirme se a entrada está correta e"
              + " tente novamente");
    } else {
      print("Insira sua senha:");
      String password = PluginUtils.readString();
      // Deveria ser um hash na pratica
      if (certifiedProfessional.password.equals(password)) return certifiedProfessional;
      print("Senha inválida, tente novamente");
      print("\n");
    }
    return null;
  }

  private void showResults(CertifiedProfessional certifiedProfessional) {
    try {
      print("Insira a senha da urna");
      String pwd = PluginUtils.readString();
      print(certifiedProfessional.getFinalResult(election, pwd));
      print("\n");
    } catch (Warning e) {
      print(e.getMessage());
    }
  }

  private void loadTestCertifiedProfessionals() {
    CertifiedProfessionalMap.put(
        "cert", new CertifiedProfessional.Builder().user("cert").password("1").build());
  }
}
