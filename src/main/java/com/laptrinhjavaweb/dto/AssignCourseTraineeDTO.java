package com.laptrinhjavaweb.dto;

public class AssignCourseTraineeDTO {
    private long[] ids;
    private long traineeId;

    public long[] getIds() {
        return ids;
    }

    public void setIds(long[] ids) {
        this.ids = ids;
    }

    public long getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(long traineeId) {
        this.traineeId = traineeId;
    }
}
