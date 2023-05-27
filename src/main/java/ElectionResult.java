package ElectoralSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ElectionResult {
  private Map<CandidateID, Integer> votes = new HashMap<CandidateID, Integer>();
  private Map<CandidateID, Integer> nullVotes = new HashMap<CandidateID, Integer>();
  private Map<CandidateID, Integer> protestVotes = new HashMap<CandidateID, Integer>();

  public void addVote(CandidateID id) {
    if (!votes.containsKey(id)) {
      votes.put(id, 0);
      return;
    }
    var numVotes = votes.get(id);
    votes.put(id, numVotes + 1);
  }

  public void addNullVote(CandidateID id) {
    if (!votes.containsKey(id)) {
      votes.put(id, 0);
      return;
    }
    var numVotes = nullVotes.get(id);
    nullVotes.put(id, numVotes + 1);
  }

  public void addProtestVote(CandidateID id) {
    if (!votes.containsKey(id)) {
      votes.put(id, 0);
      return;
    }
    var numVotes = protestVotes.get(id);
    protestVotes.put(id, numVotes + 1);
  }

  public String prettyString() {
    var s = new StringBuilder();
    s.append("\nResultado da eleição:\n\n");

    var candidateIDsLocation = new ArrayList<CandidateID>();
    var candidateIDsNoLocation = new ArrayList<CandidateID>();
    for (Map.Entry<CandidateID, Integer> vote : votes.entrySet()) {
      var candidateID = vote.getKey();
      if (candidateID.getType().isLocationSensitive()) {
        candidateIDsLocation.add(candidateID);
      } else {
        candidateIDsNoLocation.add(candidateID);
      }
    }

    String title = "";

    // Candidates that are not location specific
    title = tableRow(new String[] {"Tipo", "Número", "Votos"});
    s.append(tableHeader(title.length()));
    s.append(title);
    for (var id : candidateIDsNoLocation) {
      var numVotes = votes.get(id);
      s.append(
          tableRow(
              new String[] {
                id.getType().name, String.valueOf(id.getCandidateNumber()), String.valueOf(numVotes)
              }));
    }
    s.append(tableFooter(title.length()));

    // Candidates that are location specific
    title = tableRow(new String[] {"Tipo", "Localização", "Número", "Votos"});
    s.append(tableHeader(title.length()));
    s.append(title);
    for (var id : candidateIDsLocation) {
      var numVotes = votes.get(id);
      s.append(
          tableRow(
              new String[] {
                id.getType().name,
                id.getLocation(),
                String.valueOf(id.getCandidateNumber()),
                String.valueOf(numVotes)
              }));
    }
    s.append(tableFooter(title.length()));

    return s.toString();
  }

  private String tableRow(String[] elems) {
    var s = new StringBuilder();
    for (var elem : elems) {
      s.append("|");
      s.append(tableElement(elem));
    }
    s.append("|");
    s.append("\n");
    return s.toString();
  }

  private String tableElement(String content) {
    return String.format("%-16s", content);
  }

  private String tableHeader(int rowlen) {
    return dashPadding(rowlen) + "\n";
  }

  private String tableFooter(int rowlen) {
    return dashPadding(rowlen) + "\n\n";
  }

  private String dashPadding(int rowlen) {
    return "-".repeat(rowlen - 1);
  }
}
