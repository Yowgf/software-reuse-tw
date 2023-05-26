package ElectoralSystem;

import java.util.Set;

public class FederalDeputy extends Candidate {
  public static CandidateType type = new CandidateType("Deputado Federal", true);

  public static class Builder {
    protected String name;
    protected String party;
    protected int number;
    protected String location;

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder party(String party) {
      this.party = party;
      return this;
    }

    public Builder number(int number) {
      this.number = number;
      return this;
    }

    public Builder location(String location) {
      this.location = location;
      return this;
    }

    public FederalDeputy build() {
      if (number <= 0)
        throw new IllegalArgumentException("number mustn't be less than or equal to 0");

      if (name == null) throw new IllegalArgumentException("name mustn't be null");

      if (name.isEmpty()) throw new IllegalArgumentException("name mustn't be empty");

      if (party == null) throw new IllegalArgumentException("party mustn't be null");

      if (party.isEmpty()) throw new IllegalArgumentException("party mustn't be empty");

      if (location == null) throw new IllegalArgumentException("location mustn't be null");

      if (location.isEmpty()) throw new IllegalArgumentException("location mustn't be empty");

      Set<String> validLocations =
          Set.of(
              "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
              "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO");

      if (!validLocations.contains(location))
        throw new IllegalArgumentException("location is invalid");

      return new FederalDeputy(this.name, this.party, this.number, this.location);
    }
  }

  protected FederalDeputy(String name, String party, int number, String location) {
    super(name, party, number, location);
  }

  @Override
  public String toString() {
    return super.name + super.party + super.number + this.location;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;

    if (!(obj instanceof FederalDeputy)) return false;

    var fd = (FederalDeputy) obj;

    return this.toString().equals(fd.toString());
  }

  @Override
  public CandidateType getType() {
    return FederalDeputy.type;
  }
}
