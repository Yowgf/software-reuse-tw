package ElectoralSystem;

public abstract class Candidate {
  protected final String name;
  protected final String party;
  protected final String location;
  protected final int number;

  public Candidate(String name, String party, int number, String location) {

    if (name == null) throw new IllegalArgumentException("name mustn't be null");

    if (name.isEmpty()) throw new IllegalArgumentException("name mustn't be empty");

    if (party == null) throw new IllegalArgumentException("party mustn't be empty");

    if (party.isEmpty()) throw new IllegalArgumentException("party mustn't be empty");

    if (number <= 0) throw new IllegalArgumentException("number must be greater or equal to 1");

    this.name = name;
    this.party = party;
    this.location = location;
    this.number = number;
  }

  public String getName() {
    return this.name;
  }

  public String getParty() {
    return this.party;
  }

  public String getLocation() {
    return this.location;
  }

  public int getNumber() {
    return this.number;
  }

  // getType returns a candidate type, which should encompass information like
  // if the candidate is running for elections in a specific location.
  public CandidateType getType() {
    return new CandidateType("undefined", false);
  }
}
