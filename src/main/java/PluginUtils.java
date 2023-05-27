package ElectoralSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PluginUtils {
  // ElectionWinnersAbsoluteNumber considers the winner the candidates with
  // the most votes.
  public static ArrayList<CandidateID> electionWinnersAbsoluteNumber(
      Map<CandidateID, Integer> votes) {
    var winners = new HashMap<CandidateID, Integer>();

    // Group candidates by type and location
    for (var entry : votes.entrySet()) {
      // This algorithm is inefficient, but good enough for now.
      var id = entry.getKey();
      var numVotes = entry.getValue();
      var foundExisting = false;
      for (var winner : winners.entrySet()) {
        var winnerID = winner.getKey();
        var winnerNumVotes = winner.getValue();
        if (winnerID.getType().name == id.getType().name
            && winnerID.getLocation() == id.getLocation()) {
          foundExisting = true;
          if (numVotes > winnerNumVotes) {
            winners.put(id, numVotes);
            winners.remove(winnerID);
          }
        }
      }
      if (!foundExisting) {
        winners.put(id, numVotes);
      }
    }

    var winnersList = new ArrayList<CandidateID>();
    for (var winner : winners.entrySet()) {
      winnersList.add(winner.getKey());
    }
    return winnersList;
  }

  public static void voteAll(
      Election election, Voter voter, ArrayList<CandidateType> voteSequence) {
    print("\n" + "- 'nulo' para votar nulo\n" + "- 'br' para votar em branco\n\n");
    for (int i = 0; i < voteSequence.size(); i++) {
      boolean voteConfirmed = false;
      while (!voteConfirmed) {
        voteConfirmed = vote(election, voter, voteSequence.get(i));
      }
      print("");
    }
  }

  // vote processes a vote for a candidate type, and returns true in case of
  // success. In case the vote was not successfully processed, returns false.
  public static boolean vote(Election election, Voter voter, CandidateType candidateType) {
    print("Vote para " + candidateType.name + ": ");
    String vstr = readString();
    switch (vstr) {
      case "br":
        election.addProtestVote(candidateType, voter.getLocation());
        break;
      case "nulo":
        election.addNullVote(candidateType, voter.getLocation());
        break;
      default:
        String candidateNumber = vstr;
        boolean candidateExists =
            election.addVote(candidateType, voter.getLocation(), candidateNumber);
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

  public static void loadVoters(Map<String, Voter> voterMap, String votersFpath) {
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

  public static void print(String output) {
    System.out.println(output);
  }

  public static String readString() {
    BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
    try {
      return scanner.readLine();
    } catch (Exception e) {
      print("\nErro na leitura de entrada, digite novamente");
      return readString();
    }
  }

  public static int readInt() {
    try {
      return Integer.parseInt(readString());
    } catch (Exception e) {
      print("\nErro na leitura de entrada, digite novamente");
      return readInt();
    }
  }
}
