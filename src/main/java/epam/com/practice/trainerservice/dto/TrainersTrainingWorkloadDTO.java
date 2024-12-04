package epam.com.practice.trainerservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import epam.com.practice.trainerservice.model.ActionType;

import java.time.LocalDate;

public class TrainersTrainingWorkloadDTO {
    private String trainerUsername;
    private String trainerFirstname;
    private String trainerLastname;
    @JsonProperty(value = "isActive")
    private Boolean isActive;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate trainingDate;
    private Integer trainingDuration;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private ActionType actionType;

    public TrainersTrainingWorkloadDTO() {

    }

    public TrainersTrainingWorkloadDTO(String trainerUsername, String trainerFirstname,
                                       String trainerLastname, Boolean isActive, LocalDate trainingDate,
                                       Integer trainingDuration, ActionType actionType) {
        this.trainerUsername = trainerUsername;
        this.trainerFirstname = trainerFirstname;
        this.trainerLastname = trainerLastname;
        this.isActive = isActive;
        this.actionType = actionType;
        this.trainingDate = trainingDate;
        this.trainingDuration = trainingDuration;
    }

    public String getTrainerUsername() {
        return trainerUsername;
    }
    public void setTrainerUsername(String trainerUsername) {
        this.trainerUsername = trainerUsername;
    }
    public String getTrainerFirstname() {
        return trainerFirstname;
    }
    public void setTrainerFirstname(String trainerFirstname) {
        this.trainerFirstname = trainerFirstname;
    }
    public String getTrainerLastname() {
        return trainerLastname;
    }
    public void setTrainerLastname(String trainerLastname) {
        this.trainerLastname = trainerLastname;
    }
    public Boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    public LocalDate getTrainingDate() {
        return trainingDate;
    }
    public void setTrainingDate(LocalDate trainingDate) {
        this.trainingDate = trainingDate;
    }
    public Integer getTrainingDuration() {
        return trainingDuration;
    }
    public void setTrainingDuration(Integer trainingDuration) {
        this.trainingDuration = trainingDuration;
    }

    public ActionType getActionType() {
        return actionType;
    }
    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

}
