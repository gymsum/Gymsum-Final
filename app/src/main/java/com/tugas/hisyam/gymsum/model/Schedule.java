package com.tugas.hisyam.gymsum.model;

/**
 * Created by Hans Gracia on May/02/2018.
 */

public class Schedule {

    String userId;
    String idSchedule;
    String daysName;
    String exerciseName;

    public Schedule(){

    }

    public Schedule(String userId, String idSchedule, String daysName, String exerciseName) {
        this.userId = userId;
        this.idSchedule = idSchedule;
        this.daysName = daysName;
        this.exerciseName = exerciseName;
    }

    public String getUserId() {
        return userId;
    }

    public String getIdSchedule() {
        return idSchedule;
    }

    public String getDaysName() { return daysName;    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setIdSchedule(String idSchedule) {
        this.idSchedule = idSchedule;
    }

    public void setDaysName(String daysName) {
        this.daysName = daysName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }
}
