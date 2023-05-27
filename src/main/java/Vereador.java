package ElectoralSystem;

public class Vereador extends Candidate {
  public static CandidateType type = new CandidateType("Vereador", false);

  public static class Builder {
    protected String name;
    protected String party;
    protected String city;
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

    public Builder city(String city) {
      this.city = city;
      return this;
    }

    public Vereador build() {
      return new Vereador(this.name, this.party, this.number, this.city);
    }
  }

  protected Vereador(String name, String party, int number, String city) {
    super(name, party, number, city);
  }

  @Override
  public CandidateType getType() {
    return Vereador.type;
  }
}
