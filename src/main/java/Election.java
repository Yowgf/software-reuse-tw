package ElectoralSystem;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Election {
  private final String password;

  private boolean finished;
  private ElectionResult result = new ElectionResult();
  private Map<CandidateID, Candidate> candidates = new HashMap<CandidateID, Candidate>();

  public static class Builder {
    protected String password;

    public Builder password(String password) {
      this.password = password;
      return this;
    }

    public Election build() {
      if (password == null) throw new IllegalArgumentException("password mustn't be null");

      if (password.isEmpty()) throw new IllegalArgumentException("password mustn't be empty");

      return new Election(this.password);
    }
  }

  public Boolean isValid(String password) {
    return this.password.equals(password);
  }

  protected Election(String password) {
    this.password = password;
    this.finished = false;
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
    var candidateId = new CandidateID(typ, location, candidateNumber);
    boolean exists = candidates.containsKey(candidateId);
    if (!exists) {
      return false;
    }
    result.addVote(candidateId);
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
    var candidateId = new CandidateID(
            candidate.getType(), candidate.getLocation(), candidate.getNumber());
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

    var builder = new StringBuilder();
    builder.append("Resultado da eleicao:\n");
    builder.append(result.prettyString());

    return builder.toString();
  }
}
