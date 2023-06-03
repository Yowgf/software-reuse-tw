package ElectoralSystem;

public class President extends Candidate {
  public static CandidateType type = new CandidateType("Presidente", false); //False pq não depende de localização

  public static class Builder {
    protected String name;
    protected String party;
    protected int number;

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

    public President build() {
      return new President(this.name, this.party, this.number);
    }
  }

  protected President(String name, String party, int number) {
    super(name, party, number, "");
  }

  @Override
  public CandidateType getType() {
    return President.type;
  }
}
