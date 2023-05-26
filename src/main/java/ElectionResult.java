package ElectoralSystem;

import java.util.HashMap;
import java.util.Map;

public class ElectionResult {
  private Map<CandidateID, Integer> votes = new HashMap<CandidateID, Integer>();
  private Map<CandidateID, Integer> nullVotes = new HashMap<CandidateID, Integer>();
  private Map<CandidateID, Integer> protestVotes = new HashMap<CandidateID, Integer>();

  public void addVote(CandidateID id) {
    if (!votes.containsKey(id)) {
      votes.put(id, 0);
      return;
    }
    int numVotes = votes.get(id);
    votes.put(id, numVotes + 1);
  }

  public void addNullVote(CandidateID id) {
    if (!votes.containsKey(id)) {
      votes.put(id, 0);
      return;
    }
    int numVotes = nullVotes.get(id);
    nullVotes.put(id, numVotes + 1);
  }

  public void addProtestVote(CandidateID id) {
    if (!votes.containsKey(id)) {
      votes.put(id, 0);
      return;
    }
    int numVotes = protestVotes.get(id);
    protestVotes.put(id, numVotes + 1);
  }

    public String prettyString() {
        var s = new StringBuilder();

        for (Map.Entry<CandidateID, Integer> vote : votes.entrySet()) {
            var id = vote.getKey();
            var numVotes = vote.getValue();
            s.append(id + ": " + numVotes);
        }

    // int totalVotesFD = federalDeputyProtestVotes + nullFederalDeputyVotes;
    // for (Map.Entry<String, FederalDeputy> candidateEntry : federalDeputyCandidates.entrySet()) {
    //   FederalDeputy candidate = candidateEntry.getValue();
    //   totalVotesFD += candidate.numVotes;
    //   federalDeputyRank.add(candidate);
    // }

    // var sortedFederalDeputyRank =
    //     federalDeputyRank.stream()
    //         .sorted((o1, o2) -> o1.numVotes == o2.numVotes ? 0 : o1.numVotes < o2.numVotes ? 1 :
    // -1)
    //         .collect(Collectors.toList());

    // var sortedPresidentRank =
    //     presidentRank.stream()
    //         .sorted((o1, o2) -> o1.numVotes == o2.numVotes ? 0 : o1.numVotes < o2.numVotes ? 1 :
    // -1)
    //         .collect(Collectors.toList());

    // builder.append("  Votos presidente:\n");
    // builder.append("  Total: " + totalVotesP + "\n");
    // builder.append(
    //     "  Votos nulos: "
    //         + nullPresidentVotes
    //         + " ("
    //         + decimalFormater.format((double) nullPresidentVotes / (double) totalVotesFD * 100)
    //         + "%)\n");
    // builder.append(
    //     "  Votos brancos: "
    //         + presidentProtestVotes
    //         + " ("
    //         + decimalFormater.format((double) presidentProtestVotes / (double) totalVotesFD *
    // 100)
    //         + "%)\n");
    // builder.append("\tNumero - Partido - Nome  - Votos  - % dos votos totais\n");
    // for (President candidate : sortedPresidentRank) {
    //   builder.append(
    //       "\t"
    //           + candidate.number
    //           + " - "
    //           + candidate.party
    //           + " - "
    //           + candidate.name
    //           + " - "
    //           + candidate.numVotes
    //           + " - "
    //           + decimalFormater.format((double) candidate.numVotes / (double) totalVotesP * 100)
    //           + "%\n");
    // }

    // President electPresident = sortedPresidentRank.get(0);
    // builder.append("\n\n  Presidente eleito:\n");
    // builder.append(
    //     "  "
    //         + electPresident.name
    //         + " do "
    //         + electPresident.party
    //         + " com "
    //         + decimalFormater.format((double) electPresident.numVotes / (double) totalVotesP *
    // 100)
    //         + "% dos votos\n");
    // builder.append("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");

    // builder.append("\n\n  Votos deputado federal:\n");
    // builder.append(
    //     "  Votos nulos: "
    //         + nullFederalDeputyVotes
    //         + " ("
    //         + decimalFormater.format((double) nullFederalDeputyVotes / (double) totalVotesFD *
    // 100)
    //         + "%)\n");
    // builder.append(
    //     "  Votos brancos: "
    //         + federalDeputyProtestVotes
    //         + " ("
    //         + decimalFormater.format(
    //             (double) federalDeputyProtestVotes / (double) totalVotesFD * 100)
    //         + "%)\n");
    // builder.append("  Total: " + totalVotesFD + "\n");
    // builder.append("\tNumero - Partido - Nome - Estado - Votos - % dos votos totais\n");
    // for (FederalDeputy candidate : sortedFederalDeputyRank) {
    //   builder.append(
    //       "\t"
    //           + candidate.number
    //           + " - "
    //           + candidate.party
    //           + " - "
    //           + candidate.state
    //           + " - "
    //           + candidate.name
    //           + " - "
    //           + candidate.numVotes
    //           + " - "
    //           + decimalFormater.format((double) candidate.numVotes / (double) totalVotesFD * 100)
    //           + "%\n");
    // }

    // FederalDeputy firstDeputy = sortedFederalDeputyRank.get(0);
    // FederalDeputy secondDeputy = sortedFederalDeputyRank.get(1);
    // builder.append("\n\n  Deputados eleitos:\n");
    // builder.append(
    //     "  1º "
    //         + firstDeputy.name
    //         + " do "
    //         + firstDeputy.party
    //         + " com "
    //         + decimalFormater.format((double) firstDeputy.numVotes / (double) totalVotesFD * 100)
    //         + "% dos votos\n");
    // builder.append(
    //     "  2º "
    //         + secondDeputy.name
    //         + " do "
    //         + secondDeputy.party
    //         + " com "
    //         + decimalFormater.format((double) secondDeputy.numVotes / (double) totalVotesFD *
    // 100)
    //         + "% dos votos\n");

        return s.toString();
    }
}
