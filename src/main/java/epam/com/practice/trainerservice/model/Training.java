package epam.com.practice.trainerservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;



@Entity
@Table(name = "training")
public class Training implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @Column(name = "training_date", columnDefinition = "DATE")
    private LocalDate date;
    @Column(name = "training_duration")
    private Integer duration;
    @Column(name = "action_type")
    private String actionType;
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    public Training(LocalDate date,
                    Integer duration,
                    String actionType,
                    Trainer trainer) {
        this.date = date;
        this.duration = duration;
        this.actionType = actionType;
        this.trainer = trainer;

    }

    public Training() {

    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
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
    public String getActionType() {
        return actionType;
    }
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
    public Trainer getTrainer() {
        return trainer;
    }
    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", date=" + date +
                ", duration=" + duration +
                ", actionType=" + actionType +
                ", trainer=" + trainer +
                '}';
    }
}
