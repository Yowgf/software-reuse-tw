package ElectoralSystem;

class ElectoralSystem {
  private UrnaAdmin urnaAdmin;
  private UrnaVoter urnaVoter;
  private String electionPassword = "password";
  private Election election;

  public ElectoralSystem() {
    election = new Election.Builder().password(electionPassword).build();
    addTestData(election);
    urnaAdmin = new UrnaAdmin(election);
    urnaVoter = new UrnaVoter(election, "examples/voterLoad.txt");
  }

  public void startMenu() {
    boolean votersStopped = false;
    // Exit with ctrl-C
    while (true) {
      if (!urnaAdmin.getSessionStarted() || votersStopped) {
        urnaAdmin.menu();
      } else {
        // This is an artificial mechanism to enable transitioning back to the
        // administrator Urna. In a real system there would be no such thing.
        votersStopped = urnaVoter.menu();
      }
    }
  }

  private void addTestData(Election election) {
    President presidentCandidate1 =
        new President.Builder().name("João").number(123).party("PDS1").build();
    President presidentCandidate2 =
        new President.Builder().name("Maria").number(124).party("ED").build();
    FederalDeputy federalDeputyCandidate1 =
        new FederalDeputy.Builder()
            .name("Carlos")
            .number(12345)
            .party("PDS1")
            .location("MG")
            .build();
    FederalDeputy federalDeputyCandidate2 =
        new FederalDeputy.Builder()
            .name("Cleber")
            .number(54321)
            .party("PDS2")
            .location("MG")
            .build();
    FederalDeputy federalDeputyCandidate3 =
        new FederalDeputy.Builder().name("Sofia").number(11211).party("IHC").location("MG").build();
    election.addCandidate(presidentCandidate1);
    election.addCandidate(presidentCandidate2);
    election.addCandidate(federalDeputyCandidate1);
    election.addCandidate(federalDeputyCandidate2);
    election.addCandidate(federalDeputyCandidate3);
  }
}
