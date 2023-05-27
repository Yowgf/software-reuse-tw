package ElectoralSystem;

import java.util.Map;
import java.util.ArrayList;

class PluginMunicipal extends Plugin {
    private final ArrayList<CandidateType> voteSequence =
        new ArrayList<CandidateType>();

    public PluginMunicipal() {
        PluginUtils.loadVoters(this.voterMap);
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
        for (var candidateType : voteSequence) {
            PluginUtils.voteAll(election, voter, voteSequence);
        }
        return false;
    }

    @Override
    public ArrayList<CandidateID>
        electionWinners(Map<CandidateID, Integer> votes) {
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