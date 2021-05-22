package com.laptrinhjavaweb.dto;

public class SearchTraineeDTO extends AbstractDTO<SearchTraineeDTO> {
    private String userName;
    private String mainProgrammingLangugage;
    private String toeicScore;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMainProgrammingLangugage() {
        return mainProgrammingLangugage;
    }

    public void setMainProgrammingLangugage(String mainProgrammingLangugage) {
        this.mainProgrammingLangugage = mainProgrammingLangugage;
    }

    public String getToeicScore() {
        return toeicScore;
    }

    public void setToeicScore(String toeicScore) {
        this.toeicScore = toeicScore;
    }
}
