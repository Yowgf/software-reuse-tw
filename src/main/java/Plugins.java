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

  public Plugins(ArrayList<Plugin> plugins) {
    this.plugins = plugins;
  }
}