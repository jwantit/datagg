package com.jiwon.datagg.summoner.dto;

public record RankResponse(
        String tier,
        String rank,
        int leaguePoints,
        int wins,
        int losses
) {
}
