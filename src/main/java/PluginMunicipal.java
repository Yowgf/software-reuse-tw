package ElectoralSystem;

import java.util.Map;
import java.util.ArrayList;

class PluginMunicipal extends Plugin {
    public PluginMunicipal(Election election) {
        super(election);
        loadVoters();
    }

    @Override
    public boolean menu() {
        Voter voter = getVoter();
        if (voter == null) {
            // `true` indicates "please exit"
            return true;
        }
        var voteSequence = new ArrayList<CandidateType>();
        voteSequence.add(Mayor.type);
        voteSequence.add(Vereador.type);
        voteSequence.add(Vereador.type);
        for (var candidateType : voteSequence) {
            voteAll(voter, voteSequence);
        }
        return false;
    }

    @Override
    public ArrayList<CandidateID>
        electionWinners(Map<CandidateID, Integer> votes) {
        return new ArrayList<CandidateID>();
    }

    public Voter getVoter() {
        print("Insira seu título de eleitor:");
        String electoralCard = readString();
        Voter voter = voterMap.get(electoralCard);
        if (voter == null) {
            print("Eleitor não encontrado.");
            return null;
        }
        return voter;
    }
}
