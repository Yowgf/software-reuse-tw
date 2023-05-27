package ElectoralSystem;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

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
    // Merge all winners, excluding duplicates.
    Set<CandidateID> set = new LinkedHashSet<>();
    for (var plugin : plugins) {
      set.addAll(plugin.electionWinners(votes));
    }
    return new ArrayList<>(set);
  }

  private Plugins(ArrayList<Plugin> plugins) {
    this.plugins = plugins;
  }

  public static Plugin factory(String typ) {
    switch (typ) {
      case "federal":
        System.out.println("Using federal plugin");
        return new PluginFederal();
      case "municipal":
        System.out.println("Using municipal plugin");
        return new PluginMunicipal();
      case "todos":
        var plugins = new ArrayList<Plugin>();
        System.out.println("Using todos plugin");
        plugins.add(new PluginFederal());
        plugins.add(new PluginMunicipal());
        return new Plugins(plugins);
      default:
        throw new IllegalArgumentException("invalid election type " + typ);
    }
  }
}
