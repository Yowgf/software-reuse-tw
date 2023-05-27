package ElectoralSystem;

import java.util.ArrayList;
import java.util.Map;

public class Plugins extends Plugin {
  private ArrayList<Plugin> plugins;

  @Override
  public boolean vote(Election election) {
    for (var plugin : plugins) {
      if (plugin.vote(election)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public ArrayList<CandidateID> electionWinners(Map<CandidateID, Integer> votes) {
    return new ArrayList<CandidateID>();
  }

  private Plugins(ArrayList<Plugin> plugins) {
    this.plugins = plugins;
  }

  public static Plugins factory(String typ) {
    var plugins = new ArrayList<Plugin>();
    switch (typ) {
      case "federal":
        plugins.add(new PluginFederal());
        break;
      case "municipal":
        plugins.add(new PluginMunicipal());
        break;
      case "todos":
        plugins.add(new PluginFederal());
        plugins.add(new PluginMunicipal());
        break;
      default:
        throw new IllegalArgumentException("invalid election type " + typ);
    }
    return new Plugins(plugins);
  }
}
