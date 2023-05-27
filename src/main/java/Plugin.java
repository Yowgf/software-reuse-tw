package ElectoralSystem;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.util.Scanner;

// Plugin represents a way to interact with the voters. This can differ largely
// depending on the type of election we are dealing with, so we leave it totally
// to the implementer of the plugin. Also, a plugin decides how to compute the
// winners of an election.
//
// Note that the plugin must still reuse some of the code we have built, such as
// the Voter class, to achieve what is desired.
//
// Any of the methods of Plugin can be overwritten by plugins.
abstract public class Plugin extends Urna {
    abstract public boolean menu();
    abstract public ArrayList<CandidateID>
        electionWinners(Map<CandidateID, Integer> votes);

  protected final Map<String, Voter> voterMap = new HashMap<>();
    protected Election election;

    public Plugin(Election election) {
        this.election = election;
    }

  protected void voteAll(Voter voter, ArrayList<CandidateType> voteSequence) {
      print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
      print("Vamos começar!\n");
      print("OBS:\n" + "- 'nulo' para votar nulo\n" + "- 'br' para votar em branco\n");
      print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
    for (int i = 0; i < voteSequence.size(); i++) {
      boolean voteConfirmed = false;
      while (!voteConfirmed) {
        voteConfirmed = vote(voter, voteSequence.get(i));
      }
      print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
    }
  }

  // vote processes a vote for a candidate type, and returns true in case of
  // success. In case the vote was not successfully processed, returns false.
  protected boolean vote(Voter voter, CandidateType candidateType) {
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

    protected void loadVoters() {
        loadVoters("examples/voterLoad.txt");
    }

  protected void loadVoters(String votersFpath) {
    try {
      File myObj = new File(votersFpath);
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        var voterData = data.split(",");
        voterMap.put(
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
      System.exit(1);
    }
  }
}
