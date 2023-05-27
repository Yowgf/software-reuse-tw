package ElectoralSystem;

import java.util.Map;
import java.util.ArrayList;

public class Plugins extends Plugin {
    private ArrayList<Plugin> plugins;

    @Override
    public boolean menu() {
        for (var plugin : plugins) {
            if (plugin.menu()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<CandidateID>
        electionWinners(Map<CandidateID, Integer> votes) {
        return new ArrayList<CandidateID>();
    }

    private Plugins(Election election, ArrayList<Plugin> plugins) {
        super(election);
        this.plugins = plugins;
    }

    public static Plugins factory(Election election, String typ) {
        var plugins = new ArrayList<Plugin>();
      switch (typ) {
      case "federal":
          plugins.add(new PluginFederal(election));
          break;
      case "municipal":
          plugins.add(new PluginMunicipal(election));
          break;
      case "todos":
          plugins.add(new PluginFederal(election));
          plugins.add(new PluginMunicipal(election));
          break;
      default:
          throw new IllegalArgumentException("invalid election type " + typ);
      }
        return new Plugins(election, plugins);
    }
}
