package ElectoralSystem;

public class ChefeDepartamento extends Candidate {
  public static CandidateType type = new CandidateType("Chefe de Departamento", false);

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

    public ChefeDepartamento build() {
      return new ChefeDepartamento(this.name, this.depart, this.number);
    }
  }

  protected ChefeDepartamento(String name, String depart, int number) {
    super(name, depart, number, "");
  }

  @Override
  public CandidateType getType() {
    return ChefeDepartamento.type;
  }
}
