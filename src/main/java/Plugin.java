package ElectoralSystem;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

// Plugin represents a way to interact with the voters. This can differ largely
// depending on the type of election we are dealing with, so we leave it totally
// to the implementer of the plugin. Also, a plugin decides how to compute the
// winners of an election.
//
// Note that the plugin must still reuse some of the code we have built, such as
// the Voter class, to achieve what is desired.
//
// Any of the methods of Plugin can be overwritten by plugins.
abstract public class Plugin {
  protected final Map<String, Voter> voterMap = new HashMap<>();

    abstract public ArrayList<CandidateID>
        electionWinners(Map<CandidateID, Integer> votes);
    abstract public boolean vote(Election election);
}
