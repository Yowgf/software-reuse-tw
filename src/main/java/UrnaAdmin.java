package ElectoralSystem;

import java.util.HashMap;
import java.util.Map;

class UrnaAdmin extends Urna {
  private final Map<String, TSEProfessional> TSEMap = new HashMap<>();

  private boolean sessionStarted = false;

  public UrnaAdmin(Election election) {
    currentElection = election;
    loadTestTSEProfessionals();
  }

  public void menu() {
    try {
      print("TSE login\n");
      TSEProfessional tseProfessional = getTSEProfessional();
      if (tseProfessional == null) return;
      print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
      boolean back = false;
      while (!back) {
        print("(1) Iniciar sessão");
        print("(2) Finalizar sessão");
        print("(3) Mostrar resultados");
        print("(0) Sair");
        int command = readInt();
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
      String pwd = readString();
      tseProfessional.startSession(currentElection, pwd);
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
      String pwd = readString();
      tseProfessional.endSession(currentElection, pwd);
      print("Sessão finalizada com sucesso");
      print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
    } catch (Warning e) {
      print(e.getMessage());
    }
  }

  private TSEProfessional getTSEProfessional() {
    print("Insira seu usuário:");
    String user = readString();
    TSEProfessional tseProfessional = TSEMap.get(user);
    if (tseProfessional == null) {
      print(
          "Funcionário do TSE não encontrado, por favor confirme se a entrada está correta e tente"
              + " novamente");
    } else {
      print("Insira sua senha:");
      String password = readString();
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
      String pwd = readString();
      print(tseProfessional.getFinalResult(currentElection, pwd));
      print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
    } catch (Warning e) {
      print(e.getMessage());
    }
  }

  private void loadTestTSEProfessionals() {
    TSEMap.put("cert", new CertifiedProfessional.Builder().user("cert").password("54321").build());
    TSEMap.put("emp", new TSEEmployee.Builder().user("emp").password("12345").build());
  }
}
