package ElectoralSystem;

public class Candidate {
  protected final String name;
  protected final String party;
  protected final int number;
  protected final String location;

  public Candidate(String name, String party, int number, String location) {

    if (name == null) throw new IllegalArgumentException("name mustn't be null");

    if (name.isEmpty()) throw new IllegalArgumentException("name mustn't be empty");

    if (party == null) throw new IllegalArgumentException("party mustn't be empty");

    if (party.isEmpty()) throw new IllegalArgumentException("party mustn't be empty");

    if (number <= 0) throw new IllegalArgumentException("number must be greater or equal to 1");

    if (location == null) throw new IllegalArgumentException("location mustn't be null");

    this.name = name;
    this.party = party;
    this.number = number;
    this.location = location;
  }

  public String getName() {
    return this.name;
  }

  public String getParty() {
    return this.party;
  }

  public int getNumber() {
    return this.number;
  }

  public String getLocation() {
    return this.location;
  }

  // getType returns a candidate type, which should encompass information like
  // if the candidate is running for elections in a specific location.
  public CandidateType getType() {
    return new CandidateType("undefined", false);
  }
}
