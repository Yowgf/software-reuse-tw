package ElectoralSystem;

public class CandidateID {
  private static String sep = "_";

  private CandidateType typ;
  private String location;
  private int candidateNum;

  public CandidateID(CandidateType typ, String location, int num) {
    this.typ = typ;
    this.location = location;
    this.candidateNum = num;
  }

  // Overload of CandidateID
  public CandidateID(CandidateType typ, String location, String num) {
    this(typ, location, Integer.parseInt(num));
  }

  public String marshal() {
    String s = this.typ.name;
    if (!this.location.isEmpty() && this.typ.isLocationSensitive()) {
      s += CandidateID.sep + this.location;
    }
    s += CandidateID.sep + this.candidateNum;
    return s;
  }

  public CandidateType getType() {
    return this.typ;
  }

  public String getLocation() {
    return this.location;
  }

  public int getCandidateNumber() {
    return this.candidateNum;
  }

  @Override
  public String toString() {
    return this.marshal();
  }

  // equals exists to allow usage of CandidateID as a custom key in a hash map
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    CandidateID other = (CandidateID) o;
    return this.toString().equals(other.toString());
  }

  // hashCode exists to allow usage of CandidateID as a custom key in a hash
  // map.
  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }
}
