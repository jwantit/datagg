package com.jiwon.datagg.riot.dto;

public record RiotSummonerResponse(
        String puuid,
        int profileIconId,
        long revisionDate,
        int summonerLevel
) {
}
