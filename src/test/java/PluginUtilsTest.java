package ElectoralSystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

public class PluginUtilsTest {
    @Test
    public void testElectionWinnersAbsoluteNumber_OneCandidateType() {
        var votes = new HashMap<CandidateID, Integer>();
        var president1 = new CandidateID(President.type, "", 11);
        var president2 = new CandidateID(President.type, "", 22);
        votes.put(president1, 2);
        votes.put(president2, 5);
        var winnerIDs = PluginUtils.electionWinnersAbsoluteNumber(votes);
        var expectedIDs = new ArrayList<CandidateID>();
        expectedIDs.add(president2);
        assertEquals(expectedIDs, winnerIDs);
    }

    @Test
    public void testElectionWinnersAbsoluteNumber_TwoCandidateTypes() {
        var votes = new HashMap<CandidateID, Integer>();
        var president1 = new CandidateID(President.type, "", 11);
        var president2 = new CandidateID(President.type, "", 22);
        var depmg1 = new CandidateID(FederalDeputy.type, "MG", 11);
        var depmg2 = new CandidateID(FederalDeputy.type, "MG", 22);
        var depsp1 = new CandidateID(FederalDeputy.type, "SP", 11);
        var depsp2 = new CandidateID(FederalDeputy.type, "SP", 22);
        votes.put(president1, 2);
        votes.put(president2, 5);
        votes.put(depmg1, 10);
        votes.put(depmg2, 5);
        votes.put(depsp1, 1000);
        votes.put(depsp2, 1234);
        var winnerIDs = PluginUtils.electionWinnersAbsoluteNumber(votes);
        var expectedIDs = new ArrayList<CandidateID>();
        expectedIDs.add(president2);
        expectedIDs.add(depmg1);
        expectedIDs.add(depsp2);
        assertEquals(expectedIDs, winnerIDs);
    }
}
