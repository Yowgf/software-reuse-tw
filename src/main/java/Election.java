package ElectoralSystem;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Election {
  private final String password;

  private boolean finished;
  private ElectionResult result = new ElectionResult();
  private Map<String, Candidate> candidates = new HashMap<String, Candidate>();

  public static class Builder {
    protected String password;

    public Builder password(String password) {
      this.password = password;
      return this;
    }

    public Election build() {
      if (password == null) throw new IllegalArgumentException("password mustn't be null");

      if (password.isEmpty()) throw new IllegalArgumentException("password mustn't be empty");

      return new Election(this.password);
    }
  }

  public Boolean isValid(String password) {
    return this.password.equals(password);
  }

  protected Election(String password) {
    this.password = password;
    this.finished = false;
  }

  // addVote handles a vote for some candidate.
  public void addVote(String candidateType, String candidateNumber) {
    String candidateId = composeCandidateId(candidateType, candidateNumber);
    result.addVote(candidateId);
  }

  public void addNullVote(String candidateType) {
    result.addNullVote(candidateType);
  }

  public void addProtestVote(String candidateType) {
    result.addProtestVote(candidateType);
  }

  public boolean getFinished() {
    return this.finished;
  }

  public void start(String password) {
    if (!isValid(password)) throw new Warning("Senha inválida");
    this.finished = false;
  }

  public void finish(String password) {
    if (!isValid(password)) throw new Warning("Senha inválida");
    this.finished = true;
  }

  public void addCandidate(Candidate candidate) {
    candidates.put(candidate.getId(), candidate);
  }

  public Candidate getCandidateByNumber(String candidateType, String candidateNumber) {
    return candidates.get(composeCandidateId(candidateType, candidateNumber));
  }

  public String getResults(String password) {
    if (!isValid(password)) throw new Warning("Senha inválida");

    if (!this.finished)
      throw new StopTrap("Eleição ainda não finalizou, não é possível gerar o resultado");

    var decimalFormater = new DecimalFormat("0.00");
    var presidentRank = new ArrayList<President>();
    var federalDeputyRank = new ArrayList<FederalDeputy>();

    var builder = new StringBuilder();

    builder.append("Resultado da eleicao:\n");

    // int totalVotesP = presidentProtestVotes + nullPresidentVotes;
    // for (Map.Entry<Integer, President> candidateEntry : presidentCandidates.entrySet()) {
    //   President candidate = candidateEntry.getValue();
    //   totalVotesP += candidate.numVotes;
    //   presidentRank.add(candidate);
    // }

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

    return builder.toString();
  }

  private String composeCandidateId(String candidateType, String candidateNumber) {
    return candidateType + "_" + candidateNumber;
  }
}
