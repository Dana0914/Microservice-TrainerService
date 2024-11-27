package epam.com.practice.trainerservice.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "training")
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private Integer duration;
    @Enumerated(EnumType.STRING)
    @Column(name = "action_type")
    private ActionType actionType;

    public Training(LocalDateTime date, Integer duration, ActionType actionType) {
        this.date = date;
        this.duration = duration;
        this.actionType = actionType;

    }

    public Training() {

    }

    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public Integer getDuration() {
        return duration;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public ActionType getActionType() {
        return actionType;
    }
    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }
}
