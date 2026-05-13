package com.jiwon.datagg.riot;

import com.jiwon.datagg.riot.dto.RiotAccountResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}