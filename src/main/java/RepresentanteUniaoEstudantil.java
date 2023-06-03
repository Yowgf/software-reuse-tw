package ElectoralSystem;

public class RepresentanteUniaoEstudantil extends Candidate {
  public static CandidateType type = new CandidateType("Representante da Uniao Estudantil", false);
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

    public RepresentanteUniaoEstudantil build() {
      return new RepresentanteUniaoEstudantil(this.name, this.party, this.number);
    }
  }

  protected RepresentanteUniaoEstudantil(String name, String party, int number) {
    super(name, party, number, "");
  }

  @Override
  public CandidateType getType() {
    return RepresentanteUniaoEstudantil.type;
  }
}