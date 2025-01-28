package epam.com.practice.trainerservice.model;

import jakarta.persistence.*;



@Entity
@Table(name = "trainer_workload")
public class TrainerWorkload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "training_hours")
    private Integer trainingHours;
    @Column(name = "years")
    private Integer year;
    @Column(name = "months")
    private Integer month;
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;
    public TrainerWorkload() {

    }

    public TrainerWorkload(Integer trainingHours, Trainer trainer, Integer year, Integer month) {
        this.trainingHours = trainingHours;
        this.trainer = trainer;
        this.year = year;
        this.month = month;


    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setTrainingHours(Integer trainingHours) {
        this.trainingHours = trainingHours;

    }
    public Integer getTrainingHours() {
        return trainingHours;
    }
    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
    public Trainer getTrainer() {
        return trainer;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public Integer getYear() {
        return year;
    }
    public void setMonth(Integer month) {
        this.month = month;
    }
    public Integer getMonth() {
        return month;
    }


    @Override
    public String toString() {
        return "TrainerWorkload{" +
                "id=" + id +
                ", trainingHours=" + trainingHours +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", trainer=" + trainer +
                '}';
    }
}
