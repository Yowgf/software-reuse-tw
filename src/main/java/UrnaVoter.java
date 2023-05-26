package ElectoralSystem;

import static java.lang.System.exit;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class UrnaVoter extends Urna {
  private final Map<String, Voter> VoterMap = new HashMap<>();

  public UrnaVoter(Election election, String votersFpath) {
    currentElection = election;
    loadTestVoters(votersFpath);
  }

  // menu returns whether we should exit the menu or not. For now, as an
  // artificial mechanism (wouldn't exist in a real system), we exit from the
  // menu if the voter is not found.
  public boolean menu() {
    try {
      Voter voter = getVoter();
      if (voter == null) return true;
      print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
      print("Vamos começar!\n");
      print("OBS:\n" + "- 'nulo' para votar nulo\n" + "- 'br' para votar em branco\n");
      print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
      ArrayList<CandidateType> voteSequence = new ArrayList<CandidateType>();
      voteSequence.add(President.type);
      voteSequence.add(FederalDeputy.type);
      voteSequence.add(FederalDeputy.type);
      voteAll(voter, voteSequence);
      print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
    } catch (Warning e) {
      print(e.getMessage());
    } catch (StopTrap e) {
      print(e.getMessage());
    } catch (Exception e) {
      print("Erro inesperado");
    }
    return false;
  }

  private void voteAll(Voter voter, ArrayList<CandidateType> voteSequence) {
    for (int i = 0; i < voteSequence.size(); i++) {
      boolean voteConfirmed = false;
      while (!voteConfirmed) {
        voteConfirmed = vote(voter, voteSequence.get(i));
      }
    }
  }

  // vote processes a vote for a candidate type, and returns true in case of
  // success. In case the vote was not successfully processed, returns false.
  private boolean vote(Voter voter, CandidateType candidateType) {
    print("Vote para " + candidateType.name + ": ");
    String vstr = readString();
    switch (vstr) {
      case "br":
        currentElection.addProtestVote(candidateType, voter.getLocation());
        break;
      case "nulo":
        currentElection.addNullVote(candidateType, voter.getLocation());
        break;
      default:
        String candidateNumber = vstr;
        boolean candidateExists =
            currentElection.addVote(candidateType, voter.getLocation(), candidateNumber);
        if (!candidateExists) {
          print(
              "Não há candidato para "
                  + candidateType.name
                  + " com número "
                  + candidateNumber
                  + ".");
          return false;
        }
    }
    return true;
  }

  private Voter getVoter() {
    print("Insira seu título de eleitor:");
    String electoralCard = readString();
    Voter voter = VoterMap.get(electoralCard);
    if (voter == null) {
      print("Eleitor não encontrado.");
      return null;
    }
    return voter;
  }

  private void loadTestVoters(String votersFpath) {
    try {
      File myObj = new File(votersFpath);
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        var voterData = data.split(",");
        VoterMap.put(
            voterData[0],
            new Voter.Builder()
                .electoralCard(voterData[0])
                .name(voterData[1])
                .location(voterData[2])
                .build());
      }
      myReader.close();
    } catch (Exception e) {
      print("Erro na inicialização dos dados");
      exit(1);
    }
  }
}
