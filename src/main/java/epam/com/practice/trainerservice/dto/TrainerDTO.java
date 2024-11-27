package epam.com.practice.trainerservice.dto;

import epam.com.practice.trainerservice.model.ActionType;
import epam.com.practice.trainerservice.model.Status;

import java.time.LocalDateTime;

public class TrainerDTO {
    private String username;
    private String firstname;
    private String lastname;
    private Status isActive;
    private LocalDateTime date;
    private ActionType actionType;

    public TrainerDTO() {

    }

    public TrainerDTO(String username, String firstname, String lastname,
                      Status isActive, LocalDateTime date,
                      ActionType actionType
    ) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.isActive = isActive;
        this.date = date;
        this.actionType = actionType;

    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public Status getIsActive() {
        return isActive;
    }
    public void setIsActive(Status isActive) {
        this.isActive = isActive;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public ActionType getActionType() {
        return actionType;
    }
    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

}
