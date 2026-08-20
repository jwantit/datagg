package com.jiwon.datagg.match.dto;

public record MatchSummaryResponse(
        String matchId,
        long gameDuration,
        String championName,
        int kills,
        int deaths,
        int assists,
        boolean win
) {
}
