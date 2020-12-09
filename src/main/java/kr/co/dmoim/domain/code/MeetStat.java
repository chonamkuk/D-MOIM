package kr.co.dmoim.domain.code;

public enum MeetStat {
    A("준비"),
    B("진행중"),
    C("종료");

    final private String statName;

    private MeetStat(String statName) {
        this.statName = statName;
    }

    public String getStatName() {
        return statName;
    }

}


