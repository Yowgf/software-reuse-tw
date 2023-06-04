package ElectoralSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Plugin represents a way to interact with the voters. This can differ largely
// depending on the type of election we are dealing with, so we leave it totally
// to the implementer of the plugin. Also, a plugin decides how to compute the
// winners of an election.
//
// Note that the plugin must still reuse some of the code we have built, such as
// the Voter class, to achieve what is desired.
//
// Any of the methods of Plugin can be overwritten by plugins.
public abstract class Plugin {
  protected final Map<String, Voter> voterMap = new HashMap<>();

  public abstract ArrayList<CandidateID> electionWinners(Map<CandidateID, Integer> votes);

  public abstract boolean vote(Election election);

  public static Plugin factory(String typ) {
    switch (typ) {
      case "":
      case "federal":
        return new PluginFederal();
      case "municipal":
        return new PluginMunicipal();
      case "federal_municipal":
        var plugins = new ArrayList<Plugin>();
        plugins.add(new PluginFederal());
        plugins.add(new PluginMunicipal());
        return new Plugins(plugins);
      case "universidade":
        return new PluginUniversidade();
      default:
        throw new IllegalArgumentException("invalid election type " + typ);
    }
  }
}
