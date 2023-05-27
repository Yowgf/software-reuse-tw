package ElectoralSystem;

class UrnaVoter {
  private Election election;
  private Plugin plugin;

  public UrnaVoter(Election election, Plugin plugin) {
    this.election = election;
    this.plugin = plugin;
  }

  // menu returns whether we should exit the menu or not. For now, as an
  // artificial mechanism (wouldn't exist in a real system), we exit from the
  // menu if the voter is not found.
  public boolean menu() {
    try {
      return plugin.vote(election);
    } catch (Warning e) {
      System.err.println(e.getMessage());
    } catch (StopTrap e) {
      System.err.println(e.getMessage());
    } catch (Exception e) {
      System.err.println("Erro inesperado");
    }
    return false;
  }
}
