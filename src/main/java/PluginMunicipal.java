package ElectoralSystem;

import java.util.ArrayList;
import java.util.Map;

class PluginMunicipal extends Plugin {
  private final ArrayList<CandidateType> voteSequence = new ArrayList<CandidateType>();

  public PluginMunicipal() {
    PluginUtils.loadVoters(this.voterMap, "examples/voters.txt");
    voteSequence.add(Mayor.type);
    voteSequence.add(Vereador.type);
    voteSequence.add(Vereador.type);
  }

  @Override
  public boolean vote(Election election) {
    Voter voter = getVoter();
    if (voter == null) {
      // `true` indicates "please exit"
      return true;
    }
    PluginUtils.voteAll(election, voter, voteSequence);
    return false;
  }

  @Override
  public ArrayList<CandidateID> electionWinners(Map<CandidateID, Integer> votes) {
    return PluginUtils.electionWinnersAbsoluteNumber(votes);
  }

  public Voter getVoter() {
    print("Insira seu título de eleitor:");
    String electoralCard = PluginUtils.readString();
    Voter voter = voterMap.get(electoralCard);
    if (voter == null) {
      print("\n\nVotos finalizados. Aguardando Profissional Certificado.");
      return null;
    }
    return voter;
  }

  private void print(String s) {
    System.out.println(s);
  }
}
