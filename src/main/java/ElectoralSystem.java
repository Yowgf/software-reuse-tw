package ElectoralSystem;

class ElectoralSystem {
  private UrnaAdmin urnaAdmin;
  private UrnaEleitor urnaEleitor;
  private String electionPassword = "password";

  public ElectoralSystem() {
    Election election = new Election.Builder().password(electionPassword).build();
    addTestData(election);
    urnaAdmin = new UrnaAdmin(election);
    urnaEleitor = new UrnaEleitor(election);
  }

  public void startMenu() {
    boolean exit = false;
    while (!exit) {
      if (!urnaAdmin.getSessionStarted()) {
        exit = urnaAdmin.menu();
      } else {
        exit = urnaEleitor.menu();
      }
    }
  }

  private void addTestData(Election election) {
    President presidentCandidate1 =
        new President.Builder().name("João").number(123).party("PDS1").build();
    election.addCandidate(presidentCandidate1);
    President presidentCandidate2 =
        new President.Builder().name("Maria").number(124).party("ED").build();
    election.addCandidate(presidentCandidate2);
    FederalDeputy federalDeputyCandidate1 =
        new FederalDeputy.Builder().name("Carlos").number(12345).party("PDS1").state("MG").build();
    election.addCandidate(federalDeputyCandidate1);
    FederalDeputy federalDeputyCandidate2 =
        new FederalDeputy.Builder().name("Cleber").number(54321).party("PDS2").state("MG").build();
    election.addCandidate(federalDeputyCandidate2);
    FederalDeputy federalDeputyCandidate3 =
        new FederalDeputy.Builder().name("Sofia").number(11211).party("IHC").state("MG").build();
    election.addCandidate(federalDeputyCandidate3);
  }
}
