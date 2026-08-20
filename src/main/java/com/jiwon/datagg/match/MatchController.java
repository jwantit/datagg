package com.jiwon.datagg.match;

import com.jiwon.datagg.match.dto.MatchSummaryResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/api/matches/recent")
    public List<MatchSummaryResponse> getRecentMatches(
            @RequestParam String puuid
    ) {
        return matchService.getRecentMatches(puuid);
    }
}
