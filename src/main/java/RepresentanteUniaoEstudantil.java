package ElectoralSystem;

public class RepresentanteUniaoEstudantil extends Candidate {
  public static CandidateType type = new CandidateType("Representante da Uniao Estudantil", false);

  public static class Builder {
    protected String name;
    protected String depart;
    protected int number;

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder depart(String depart) {
      this.depart = depart;
      return this;
    }

    public Builder number(int number) {
      this.number = number;
      return this;
    }

    public RepresentanteUniaoEstudantil build() {
      return new RepresentanteUniaoEstudantil(this.name, this.depart, this.number);
    }
  }

  protected RepresentanteUniaoEstudantil(String name, String depart, int number) {
    super(name, depart, number, "");
  }

  @Override
  public CandidateType getType() {
    return RepresentanteUniaoEstudantil.type;
  }
}
