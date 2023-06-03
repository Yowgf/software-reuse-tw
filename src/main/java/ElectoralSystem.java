package ElectoralSystem;

class ElectoralSystem {
  private UrnaAdmin urnaAdmin;
  private UrnaVoter urnaVoter;
  private String electionPassword = "pass";
  private Election election;

  public ElectoralSystem(String pluginType) {
    var plugin = Plugins.factory(pluginType);
    election = new Election(electionPassword, plugin);
    addTestData(election);
    urnaAdmin = new UrnaAdmin(election);
    urnaVoter = new UrnaVoter(election, plugin);
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
    addTestDataFederal(election);
    addTestDataMunicipal(election);
    addTestDataUniversidade(election);
  }

  private void addTestDataFederal(Election election) {
    President presidentCandidate1 =
        new President.Builder().name("João").number(123).party("PDS1").build();
    President presidentCandidate2 =
        new President.Builder().name("Maria").number(124).party("ED").build();
    FederalDeputy federalDeputyCandidate1 =
        new FederalDeputy.Builder().name("Carlos").number(12345).party("PDS1").state("MG").build();
    FederalDeputy federalDeputyCandidate2 =
        new FederalDeputy.Builder().name("Cleber").number(54321).party("PDS2").state("MG").build();
    FederalDeputy federalDeputyCandidate3 =
        new FederalDeputy.Builder().name("Sofia").number(11211).party("IHC").state("MG").build();
    election.addCandidate(presidentCandidate1);
    election.addCandidate(presidentCandidate2);
    election.addCandidate(federalDeputyCandidate1);
    election.addCandidate(federalDeputyCandidate2);
    election.addCandidate(federalDeputyCandidate3);
  }

  private void addTestDataMunicipal(Election election) {
    Mayor m1 =
        new Mayor.Builder()
            .name("Joe")
            .number(11)
            .party("Republicans")
            .city("Belo Horizonte")
            .build();
    Mayor m2 =
        new Mayor.Builder()
            .name("Mary")
            .number(22)
            .party("Democrats")
            .city("Belo Horizonte")
            .build();
    Vereador v1 =
        new Vereador.Builder()
            .name("Joe vereador")
            .number(11111)
            .party("Republicans")
            .city("Belo Horizonte")
            .build();
    Vereador v2 =
        new Vereador.Builder()
            .name("Mary vereador")
            .number(22222)
            .party("Democrats")
            .city("Belo Horizonte")
            .build();
    election.addCandidate(m1);
    election.addCandidate(m2);
    election.addCandidate(v1);
    election.addCandidate(v2);
  }

  private void addTestDataUniversidade(Election election) {
    ChefeDepartamento ChefeDepartamentoCandidate1 =
        new ChefeDepartamento.Builder().name("Daniel").number(23).party("DCC").build();
    ChefeDepartamento ChefeDepartamentoCandidate2 =
        new ChefeDepartamento.Builder().name("Leticia").number(24).party("DCC").build();

    RepresentanteUniaoEstudantil RepresentanteUniaoEstudantilCandidate1 = 
        new RepresentanteUniaoEstudantil.Builder().name("Felipe").number(1).party("DCC").build();
    RepresentanteUniaoEstudantil RepresentanteUniaoEstudantilCandidate2 = 
        new RepresentanteUniaoEstudantil.Builder().name("Alexandre").number(2).party("DCC").build();
  
    election.addCandidate(ChefeDepartamentoCandidate1);
    election.addCandidate(ChefeDepartamentoCandidate2);

    election.addCandidate(RepresentanteUniaoEstudantilCandidate1);
    election.addCandidate(RepresentanteUniaoEstudantilCandidate2);
  }

}
