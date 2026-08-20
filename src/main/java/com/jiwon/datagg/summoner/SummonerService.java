package com.jiwon.datagg.summoner;

import com.jiwon.datagg.riot.RiotApiClient;
import com.jiwon.datagg.riot.dto.RiotAccountResponse;
import com.jiwon.datagg.riot.dto.RiotLeagueEntryResponse;
import com.jiwon.datagg.riot.dto.RiotSummonerResponse;
import com.jiwon.datagg.summoner.dto.RankResponse;
import com.jiwon.datagg.summoner.dto.SummonerSearchResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SummonerService {

    private static final String RANKED_SOLO_5x5 = "RANKED_SOLO_5x5";

    private final RiotApiClient riotApiClient;

    public SummonerService(RiotApiClient riotApiClient) {
        this.riotApiClient = riotApiClient;
    }

    public SummonerSearchResponse search(String gameName, String tagLine) {
        RiotAccountResponse account = riotApiClient.getAccountByRiotId(gameName, tagLine);
        RiotSummonerResponse summoner = riotApiClient.getSummonerByPuuid(account.puuid());
        List<RiotLeagueEntryResponse> leagueEntries = riotApiClient.getLeagueEntriesByPuuid(account.puuid());

        RankResponse soloRank = findSoloRank(leagueEntries).orElse(null);

        return new SummonerSearchResponse(
                account.gameName(),
                account.tagLine(),
                account.puuid(),
                summoner.profileIconId(),
                summoner.summonerLevel(),
                soloRank
        );
    }

    private Optional<RankResponse> findSoloRank(List<RiotLeagueEntryResponse> leagueEntries) {
        return leagueEntries.stream()
                .filter(entry -> entry.queueType().equals(RANKED_SOLO_5x5))
                .findFirst()
                .map(entry -> new RankResponse(
                        entry.tier(),
                        entry.rank(),
                        entry.leaguePoints(),
                        entry.wins(),
                        entry.losses()
                ));
    }
}
