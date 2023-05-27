package ElectoralSystem;

import java.util.HashMap;
import java.util.Map;

// UrnaAdmin represents the backend where an administrator can start or end an
// election using their credentials.
//
// TODO: don't use anything TSE-specific, everything should be just
// CertifiedProfessional -aholmqusit 2023-05-27
class UrnaAdmin {
  private final Map<String, TSEProfessional> TSEMap = new HashMap<>();

  private Election election;
  private boolean sessionStarted = false;

  public UrnaAdmin(Election election) {
    this.election = election;
    loadTestTSEProfessionals();
  }

  private void print(String s) {
    System.out.println(s);
  }

  public void menu() {
    try {
      print("Certified Professional login\n");
      TSEProfessional tseProfessional = getTSEProfessional();
      if (tseProfessional == null) return;
      print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
      boolean back = false;
      while (!back) {
        print("(1) Iniciar sessão");
        print("(2) Finalizar sessão");
        print("(3) Mostrar resultados");
        print("(0) Sair");
        int command = PluginUtils.readInt();
        switch (command) {
          case 1 -> startSession((CertifiedProfessional) tseProfessional);
          case 2 -> endSession((CertifiedProfessional) tseProfessional);
          case 3 -> showResults((CertifiedProfessional) tseProfessional);
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

  private void startSession(CertifiedProfessional tseProfessional) {
    try {
      print("Insira a senha da urna");
      String pwd = PluginUtils.readString();
      tseProfessional.startSession(election, pwd);
      sessionStarted = true;
      print("Sessão inicializada");
      print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
    } catch (Warning e) {
      print(e.getMessage());
    }
  }

  private void endSession(CertifiedProfessional tseProfessional) {
    try {
      print("Insira a senha da urna:");
      String pwd = PluginUtils.readString();
      tseProfessional.endSession(election, pwd);
      print("Sessão finalizada com sucesso");
      print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
    } catch (Warning e) {
      print(e.getMessage());
    }
  }

  private TSEProfessional getTSEProfessional() {
    print("Insira seu usuário:");
    String user = PluginUtils.readString();
    TSEProfessional tseProfessional = TSEMap.get(user);
    if (tseProfessional == null) {
      print(
          "Funcionário do TSE não encontrado, por favor confirme se a entrada está correta e tente"
              + " novamente");
    } else {
      print("Insira sua senha:");
      String password = PluginUtils.readString();
      // Deveria ser um hash na pratica
      if (tseProfessional.password.equals(password)) return tseProfessional;
      print("Senha inválida, tente novamente");
      print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
    }
    return null;
  }

  private void showResults(CertifiedProfessional tseProfessional) {
    try {
      print("Insira a senha da urna");
      String pwd = PluginUtils.readString();
      print(tseProfessional.getFinalResult(election, pwd));
      print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
    } catch (Warning e) {
      print(e.getMessage());
    }
  }

  private void loadTestTSEProfessionals() {
    TSEMap.put("cert", new CertifiedProfessional.Builder().user("cert").password("1").build());
    TSEMap.put("emp", new TSEEmployee.Builder().user("emp").password("1").build());
  }
}
