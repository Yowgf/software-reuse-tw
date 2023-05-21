package ElectoralSystem;

import static java.lang.System.exit;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class UrnaEleitor extends Urna {
  private final Map<String, Voter> VoterMap = new HashMap<>();

  public UrnaEleitor(Election election) {
    super(election);
    loadTestVoters();
  }

  public boolean menu() {
    try {
      Voter voter = getVoter();
      if (voter == null) return false;
      print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
      print("Vamos começar!\n");
      print("OBS:\n" + "- 'nulo' para votar nulo\n" + "- 'br' para votar em branco\n");
      print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
      ArrayList<String> voteSequence = new ArrayList<String>();
      voteSequence.add("President");
      voteSequence.add("FederalDeputy");
      voteSequence.add("FederalDeputy");
      voteAll(voteSequence);
      print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
    } catch (Warning e) {
      print(e.getMessage());
    } catch (StopTrap e) {
      print(e.getMessage());
    } catch (Exception e) {
      print("Erro inesperado");
    }
    return true;
  }

  private void voteAll(ArrayList<String> voteSequence) {
    for (int i = 0; i < voteSequence.size(); i++) {
      boolean voteConfirmed = false;
      while (!voteConfirmed) {
        voteConfirmed = vote(voteSequence.get(i));
      }
    }
  }

  private Voter getVoter() {
    print("Insira seu título de eleitor:");
    String electoralCard = readString();
    Voter voter = VoterMap.get(electoralCard);
    if (voter == null) {
      print(
          "Eleitor não encontrado, por favor confirme se a entrada está correta e tente novamente");
    } else {
      print("Olá, você é " + voter.name + " de " + voter.state + "?\n");
      print("(1) Sim\n(2) Não");
      int command = readInt();
      if (command == 1) return voter;
      else if (command == 2) print("Ok, você será redirecionado para o menu inicial");
      else {
        print("Entrada inválida, tente novamente");
        return getVoter();
      }
    }
    return null;
  }

  // vote processes a vote for a candidate type, and returns true in case of
  // success. In case the vote was not successfully processed, returns false.
  private boolean vote(String candidateType) {
    print("Vote for " + candidateType + ": ");
    String vstr = readString();
    switch (vstr) {
      case "br":
        currentElection.addProtestVote(candidateType);
        return true;
      case "nulo":
        currentElection.addNullVote(candidateType);
        return true;
      default:
        String candidateNumber = vstr;
        currentElection.addVote(candidateType, candidateNumber);
    }
    return true;
  }

  private void loadTestVoters() {
    try {
      File myObj = new File("voterLoad.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        var voterData = data.split(",");
        VoterMap.put(
            voterData[0],
            new Voter.Builder()
                .electoralCard(voterData[0])
                .name(voterData[1])
                .state(voterData[2])
                .build());
      }
      myReader.close();
    } catch (Exception e) {
      print("Erro na inicialização dos dados");
      exit(1);
    }
  }
}
