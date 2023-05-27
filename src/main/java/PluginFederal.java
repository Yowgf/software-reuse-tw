package ElectoralSystem;

import java.util.ArrayList;
import java.util.Map;

class PluginFederal extends Plugin {
  private final ArrayList<CandidateType> voteSequence = new ArrayList<CandidateType>();

  public PluginFederal() {
    PluginUtils.loadVoters(this.voterMap);
    voteSequence.add(President.type);
    voteSequence.add(FederalDeputy.type);
    voteSequence.add(FederalDeputy.type);
  }

  @Override
  public boolean vote(Election election) {
    Voter voter = getVoter();
    if (voter == null) {
      // `true` indicates "please exit"
      return true;
    }
    for (var candidateType : voteSequence) {
      PluginUtils.voteAll(election, voter, voteSequence);
    }
    return false;
  }

  @Override
  public ArrayList<CandidateID> electionWinners(Map<CandidateID, Integer> votes) {
    return PluginUtils.ElectionWinnersAbsoluteNumber(votes);
  }

  public Voter getVoter() {
    print("Insira seu título de eleitor:");
    String electoralCard = PluginUtils.readString();
    Voter voter = voterMap.get(electoralCard);
    if (voter == null) {
      print("Eleitor não encontrado.");
      return null;
    }
    return voter;
  }

  private void print(String s) {
    System.out.println(s);
  }
}
