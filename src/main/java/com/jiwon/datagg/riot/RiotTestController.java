package com.jiwon.datagg.riot;

import com.jiwon.datagg.riot.dto.RiotAccountResponse;
import com.jiwon.datagg.riot.dto.RiotLeagueEntryResponse;
import com.jiwon.datagg.riot.dto.RiotMatchDetailResponse;
import com.jiwon.datagg.riot.dto.RiotSummonerResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RiotTestController {

    private final RiotApiClient riotApiClient;

    public RiotTestController(RiotApiClient riotApiClient) {
        this.riotApiClient = riotApiClient;
    }

    @GetMapping("/api/test/riot/account")
    public RiotAccountResponse getAccount(
            @RequestParam String gameName,
            @RequestParam String tagLine
    ) {
        return riotApiClient.getAccountByRiotId(gameName, tagLine);
    }

    @GetMapping("/api/test/riot/summoner")
    public RiotSummonerResponse getSummoner(
            @RequestParam String puuid
    ) {
        return riotApiClient.getSummonerByPuuid(puuid);
    }

    @GetMapping("/api/test/riot/league")
    public List<RiotLeagueEntryResponse> getLeagueEntries(
            @RequestParam String puuid
    ) {
        return riotApiClient.getLeagueEntriesByPuuid(puuid);
    }

    @GetMapping("/api/test/riot/matches")
    public List<String> getMatchIds(
            @RequestParam String puuid
    ) {
        return riotApiClient.getMatchIdsByPuuid(puuid);
    }

    @GetMapping("/api/test/riot/match")
    public RiotMatchDetailResponse getMatch(
            @RequestParam String matchId
    ) {
        return riotApiClient.getMatchDetail(matchId);
    }
}