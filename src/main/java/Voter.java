package ElectoralSystem;

public class Voter {
  protected final String electoralCard;

  protected final String name;

  protected final String location;

  public String getName() {
    return name;
  }

  public String getElectoralCard() {
    return electoralCard;
  }

  public String getLocation() {
    return location;
  }

  public static class Builder {
    private String electoralCard;
    private String name;
    private String location;

    public Builder electoralCard(String electoralCard) {
      this.electoralCard = electoralCard;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder location(String location) {
      this.location = location;
      return this;
    }

    public Voter build() {
      if (electoralCard == null)
        throw new IllegalArgumentException("electoralCard mustn't be null");

      if (electoralCard.isEmpty())
        throw new IllegalArgumentException("electoralCard mustn't be empty");

      if (name == null) throw new IllegalArgumentException("name mustn't be null");

      if (name.isEmpty()) throw new IllegalArgumentException("name mustn't be empty");

      if (location == null) throw new IllegalArgumentException("location mustn't be null");

      if (location.isEmpty()) throw new IllegalArgumentException("location mustn't be empty");

      return new Voter(electoralCard, name, location);
    }
  }

  protected Voter(String electoralCard, String name, String location) {
    this.electoralCard = electoralCard;
    this.name = name;
    this.location = location;
  }
}
