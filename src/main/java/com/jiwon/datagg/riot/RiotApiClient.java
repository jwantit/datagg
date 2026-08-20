package com.jiwon.datagg.riot;

import com.jiwon.datagg.riot.config.RiotApiProperties;
import com.jiwon.datagg.riot.dto.RiotAccountResponse;
import com.jiwon.datagg.riot.dto.RiotLeagueEntryResponse;
import com.jiwon.datagg.riot.dto.RiotMatchDetailResponse;
import com.jiwon.datagg.riot.dto.RiotSummonerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;

@Component
public class RiotApiClient {

    private final RiotApiProperties properties;
    private final RestClient restClient;

    public RiotApiClient(RiotApiProperties properties) {
        this.properties = properties;
        this.restClient = RestClient.create();
    }

    public RiotAccountResponse getAccountByRiotId(String gameName, String tagLine) {
        return restClient.get()
                .uri(properties.regionalBaseUrl() + "/riot/account/v1/accounts/by-riot-id/{gameName}/{tagLine}",
                        gameName,
                        tagLine)
                .header("X-Riot-Token", properties.key())
                .retrieve()
                .body(RiotAccountResponse.class);
    }

    public RiotSummonerResponse getSummonerByPuuid(String puuid) {
        return restClient.get()
                .uri(properties.platformBaseUrl() + "/lol/summoner/v4/summoners/by-puuid/{puuid}",
                        puuid)
                .header("X-Riot-Token", properties.key())
                .retrieve()
                .body(RiotSummonerResponse.class);
    }

    public List<RiotLeagueEntryResponse> getLeagueEntriesByPuuid(String puuid) {
        RiotLeagueEntryResponse[] entries = restClient.get()
                .uri(properties.platformBaseUrl() + "/lol/league/v4/entries/by-puuid/{puuid}",
                        puuid)
                .header("X-Riot-Token", properties.key())
                .retrieve()
                .body(RiotLeagueEntryResponse[].class);
        return Arrays.asList(entries);
    }

    public List<String> getMatchIdsByPuuid(String puuid) {
        String[] matchIds = restClient.get()
                .uri(properties.regionalBaseUrl() + "/lol/match/v5/matches/by-puuid/{puuid}/ids?start=0&count=10",
                        puuid)
                .header("X-Riot-Token", properties.key())
                .retrieve()
                .body(String[].class);
        return Arrays.asList(matchIds);
    }

    public RiotMatchDetailResponse getMatchDetail(String matchId) {
        return restClient.get()
                .uri(properties.regionalBaseUrl() + "/lol/match/v5/matches/{matchId}",
                        matchId)
                .header("X-Riot-Token", properties.key())
                .retrieve()
                .body(RiotMatchDetailResponse.class);
    }
}