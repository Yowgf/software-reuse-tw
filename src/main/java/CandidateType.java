package ElectoralSystem;

public class CandidateType {
  // This is a data class, so OK to have public fields.
  public final String name;
  public final boolean locSensitive;

  public CandidateType(String name, boolean locSensitive) {
    this.name = name;
    this.locSensitive = locSensitive;
  }

  public boolean isLocationSensitive() {
    return this.locSensitive;
  }
}
