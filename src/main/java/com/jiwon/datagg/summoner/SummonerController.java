package com.jiwon.datagg.summoner;

import com.jiwon.datagg.summoner.dto.SummonerSearchResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SummonerController {

    private final SummonerService summonerService;

    public SummonerController(SummonerService summonerService) {
        this.summonerService = summonerService;
    }

    @GetMapping("/api/summoners/search")
    public SummonerSearchResponse search(
            @RequestParam String gameName,
            @RequestParam String tagLine
    ) {
        return summonerService.search(gameName, tagLine);
    }
}
