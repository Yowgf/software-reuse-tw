package ElectoralSystem;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Election follows a microkernal architecture, where each type of election is a
// plugin.
public class Election {
  private final String password;
  private boolean finished;
  private ElectionResult result;
  private Map<CandidateID, Candidate> candidates = new HashMap<CandidateID, Candidate>();

  public Election(String password, Plugin plugin) {
    this.password = password;
    this.result = new ElectionResult(plugin);
    this.finished = false;
  }

  public Boolean isValid(String password) {
    return this.password.equals(password);
  }

  // addVote overload
  public boolean addVote(CandidateType candidateType, int num) {
    return addVote(candidateType, "", num);
  }

  // addVote overload
  public boolean addVote(CandidateType candidateType, String location, int num) {
    return addVote(candidateType, location, String.valueOf(num));
  }

  // addVote handles a vote for some candidate.
  public boolean addVote(CandidateType typ, String location, String candidateNumber) {
    var candidateID = new CandidateID(typ, location, candidateNumber);
    return addVote(candidateID);
  }

    public boolean addVote(CandidateID id) {
        boolean exists = candidates.containsKey(id);
        if (!exists) {
            return false;
        }
        result.addVote(id);
        return true;
    }

  public void addNullVote(CandidateType candidateType, String location) {
    var candidateId = new CandidateID(candidateType, location, 0);
    result.addNullVote(candidateId);
  }

  public void addProtestVote(CandidateType candidateType, String location) {
    var candidateId = new CandidateID(candidateType, location, 0);
    result.addProtestVote(candidateId);
  }

  public boolean getFinished() {
    return this.finished;
  }

  public void start(String password) {
    if (!isValid(password)) throw new Warning("Senha inválida");
    this.finished = false;
  }

  public void finish(String password) {
    if (!isValid(password)) throw new Warning("Senha inválida");
    this.finished = true;
  }

  public void addCandidate(Candidate candidate) {
    var candidateId =
        new CandidateID(candidate.getType(), candidate.getLocation(), candidate.getNumber());
    candidates.put(candidateId, candidate);
  }

  // Overload of getCandidate
  public Candidate getCandidate(CandidateType candidateType, String location, int num) {
    return getCandidate(candidateType, location, String.valueOf(num));
  }

  public Candidate getCandidate(
      CandidateType candidateType, String location, String candidateNumber) {
    return candidates.get(new CandidateID(candidateType, location, candidateNumber));
  }

  public String getResults(String password) {
    if (!isValid(password)) throw new Warning("Senha inválida");

    if (!this.finished) return "Eleição ainda não finalizou, não é possível gerar o resultado";

    var decimalFormater = new DecimalFormat("0.00");
    var presidentRank = new ArrayList<President>();
    var federalDeputyRank = new ArrayList<FederalDeputy>();

    return result.prettyString();
  }
}
