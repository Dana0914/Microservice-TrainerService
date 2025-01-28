package epam.com.practice.trainerservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.List;

@Document(collection = "training_summary")
public class TrainingSummary {

    @Transient
    public static final String SEQUENCE_NAME = "training_summary_sequence";

    @Id
    private BigInteger id;
    private Integer year;
    private Integer month;
    private Integer trainingSummaryDuration;

    @DBRef
    @JsonIgnore
    private Training training;


    public TrainingSummary() {

    }
    public TrainingSummary(BigInteger id, Integer year, Integer month, Integer trainingSummaryDuration) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.trainingSummaryDuration = trainingSummaryDuration;
    }
    public BigInteger getId() {
        return id;
    }
    public void setId(BigInteger id) {
        this.id = id;
    }
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public Integer getMonth() {
        return month;
    }
    public void setMonth(Integer month) {
        this.month = month;
    }
    public Integer getTrainingSummaryDuration() {
        return trainingSummaryDuration;
    }
    public void setTrainingSummaryDuration(Integer trainingSummaryDuration) {
        this.trainingSummaryDuration = trainingSummaryDuration;
    }

    public Training getTraining() {
        return training;
    }
    public void setTraining(Training training) {
        this.training = training;
    }

    @Override
    public String toString() {
        return "TrainingSummary{" +
                "id=" + id +
                ", year=" + year +
                ", month=" + month +
                ", trainingSummaryDuration=" + trainingSummaryDuration +
                '}';
    }
}
