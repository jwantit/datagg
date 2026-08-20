package com.jiwon.datagg.riot.dto;

public record RiotLeagueEntryResponse(
        String queueType,  //랭크 큐 종류
        String tier,       //티어
        String rank,       //티어 내 단계
        int leaguePoints,  //LP
        int wins,          //승리 수
        int losses,        //패배 수
        boolean hotStreak, //연승 상태 여부
        boolean veteran,   //해당 리그의 베태랑 여부
        boolean freshBlood,//최근 해당 리그에 진입한 플레이어인지 여부
        boolean inactive   //비활성 상태 여부
) {
}
