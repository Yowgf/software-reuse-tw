package ElectoralSystem;

class UrnaVoter extends Urna {
    private Plugin plugin;

    public UrnaVoter(Plugin plugin) {
        this.plugin = plugin;
    }

  // menu returns whether we should exit the menu or not. For now, as an
  // artificial mechanism (wouldn't exist in a real system), we exit from the
  // menu if the voter is not found.
  public boolean menu() {
    try {
      return plugin.menu();
    } catch (Warning e) {
      print(e.getMessage());
    } catch (StopTrap e) {
      print(e.getMessage());
    } catch (Exception e) {
      print("Erro inesperado");
    }
    return false;
  }
}
