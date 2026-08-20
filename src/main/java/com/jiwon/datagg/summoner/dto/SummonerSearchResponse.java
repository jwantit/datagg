package com.jiwon.datagg.summoner.dto;

public record SummonerSearchResponse(
        String gameName,
        String tagLine,
        String puuid,
        int profileIconId,
        int summonerLevel,
        RankResponse soloRank
) {
}
