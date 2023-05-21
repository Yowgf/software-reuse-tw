package ElectoralSystem;

import java.util.HashMap;
import java.util.Map;

public class ElectionResult {
  private Map<String, Integer> votes = new HashMap<String, Integer>();
  private Map<String, Integer> nullVotes = new HashMap<String, Integer>();
  private Map<String, Integer> protestVotes = new HashMap<String, Integer>();

  public void addVote(String candidateId) {
    if (!votes.containsKey(candidateId)) {
      votes.put(candidateId, 0);
      return;
    }
    int numVotes = votes.get(candidateId);
    votes.put(candidateId, numVotes + 1);
  }

  public void addNullVote(String candidateType) {
    if (!votes.containsKey(candidateType)) {
      votes.put(candidateType, 0);
      return;
    }
    int numVotes = nullVotes.get(candidateType);
    nullVotes.put(candidateType, numVotes + 1);
  }

  public void addProtestVote(String candidateType) {
    if (!votes.containsKey(candidateType)) {
      votes.put(candidateType, 0);
      return;
    }
    int numVotes = protestVotes.get(candidateType);
    protestVotes.put(candidateType, numVotes + 1);
  }
}
