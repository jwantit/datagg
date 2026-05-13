package com.jiwon.datagg.riot;

import com.jiwon.datagg.riot.config.RiotApiProperties;
import com.jiwon.datagg.riot.dto.RiotAccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

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
                .uri(properties.accountBaseUrl() + "/riot/account/v1/accounts/by-riot-id/{gameName}/{tagLine}",
                        gameName,
                        tagLine)
                .header("X-Riot-Token", properties.key())
                .retrieve()
                .body(RiotAccountResponse.class);
    }
}