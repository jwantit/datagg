package com.jiwon.datagg.match;

import com.jiwon.datagg.match.dto.MatchSummaryResponse;
import com.jiwon.datagg.riot.RiotApiClient;
import com.jiwon.datagg.riot.dto.RiotMatchDetailResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    private final RiotApiClient riotApiClient;

    public MatchService(RiotApiClient riotApiClient) {
        this.riotApiClient = riotApiClient;
    }

    public Optional<RiotMatchDetailResponse.Participant> findParticipant(String matchId, String puuid) {
        RiotMatchDetailResponse matchDetail = riotApiClient.getMatchDetail(matchId);
        return findParticipant(matchDetail, puuid);
    }

    public List<MatchSummaryResponse> getRecentMatches(String puuid) {
        List<String> matchIds = riotApiClient.getMatchIdsByPuuid(puuid);
        return matchIds.stream()
                .flatMap(matchId -> toSummary(matchId, puuid).stream())
                .toList();
    }

    private Optional<MatchSummaryResponse> toSummary(String matchId, String puuid) {
        RiotMatchDetailResponse matchDetail = riotApiClient.getMatchDetail(matchId);
        return findParticipant(matchDetail, puuid)
                .map(participant -> new MatchSummaryResponse(
                        matchId,
                        matchDetail.info().gameDuration(),
                        participant.championName(),
                        participant.kills(),
                        participant.deaths(),
                        participant.assists(),
                        participant.win()
                ));
    }

    private Optional<RiotMatchDetailResponse.Participant> findParticipant(RiotMatchDetailResponse matchDetail, String puuid) {
        return matchDetail.info().participants().stream()
                .filter(p -> p.puuid().equals(puuid))
                .findFirst();
    }
}
