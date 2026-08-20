# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Working Rules

- This project is for learning as well as implementation.
- Before implementing a feature, explain the proposed approach and files to be changed.
- Do not make major architectural changes without explicit approval.
- When asked about an error, explain the root cause before modifying code.
- Do not automatically implement core business logic when the user is trying to learn it. Explain the approach first.
- After modifying code, summarize what changed and why.
- Suggest how to test the implementation after changes.
- Prefer the existing project structure and coding style.
- Avoid unnecessary abstractions, dependencies, and over-engineering.
- Use Java 21 and Spring Boot conventions.
- Keep answers concise unless a detailed explanation is requested.

## Commands

Build:
```bash
./gradlew build
```

Run the app locally:
```bash
./gradlew bootRun
```

Run all tests:
```bash
./gradlew test
```

Run a single test class:
```bash
./gradlew test --tests "com.jiwon.datagg.DataggApplicationTests"
```

Run a single test method:
```bash
./gradlew test --tests "com.jiwon.datagg.DataggApplicationTests.contextLoads"
```

## Architecture

Spring Boot 3.5 / Java 21 project (`com.jiwon.datagg`), packaged by feature under `src/main/java/com/jiwon/datagg/`:

- `health/` — basic health-check endpoint.
- `riot/` — integration with Riot Games API. `RiotApiClient` wraps Spring's `RestClient` and calls Riot's Account-V1 endpoint, authenticating via the `X-Riot-Token` header. `RiotTestController` is a throwaway endpoint for manually exercising the client. `riot/config/RiotApiProperties` binds the `riot.api.*` prefix (`key`, `account-base-url`, `
- `) via `@ConfigurationProperties`, enabled in `DataggApplication` with `@EnableConfigurationProperties`. `riot/dto/` holds response records for Riot API payloads.

Two Riot API routing base URLs are configured:

- `regionalBaseUrl` (`https://asia.api.riotgames.com`) — used for regional APIs such as Account-V1 and Match-V5.
- `platformBaseUrl` (`https://kr.api.riotgames.com`) — used for KR platform APIs such as Summoner-V4 and League-V4.

Only `regionalBaseUrl` is used by client code today (Account-V1 in `RiotApiClient`); `platformBaseUrl` is reserved for future platform-scoped endpoints.

### Configuration and secrets

`application.properties` sets `spring.profiles.include=secret`, which pulls in `application-secret.properties` (gitignored, not present in this checkout) for the actual `riot.api.key` value and any other credentials. When adding config that needs a real secret locally, create `src/main/resources/application-secret.properties` with the needed properties — it will be picked up automatically and never committed.

### Persistence

`spring-boot-starter-data-jpa` plus H2 (runtime, in-memory: `jdbc:h2:mem:datagg`) and MariaDB driver are on the classpath with `spring.jpa.hibernate.ddl-auto=create`, but no `@Entity`/repository classes exist yet — persistence is wired but unused. When adding entities, MariaDB is the implied production target and H2 the implied local/test target.