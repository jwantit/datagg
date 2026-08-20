package com.jiwon.datagg.riot.dto;

import java.util.List;

public record RiotMatchDetailResponse(
        Info info
) {
    public record Info(
            long gameDuration,
            List<Participant> participants
    ) {
    }

    public record Participant(
            String puuid,
            String championName,
            int kills,
            int deaths,
            int assists,
            boolean win
    ) {
    }
}
