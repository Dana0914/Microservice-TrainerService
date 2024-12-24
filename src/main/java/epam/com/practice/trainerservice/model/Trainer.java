package epam.com.practice.trainerservice.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.data.mongodb.core.mapping.Document;



@Document
public class Trainer {

    @Transient
    public static final String SEQUENCE_NAME = "trainer_sequence";

    @Id
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    @JsonProperty(value = "isActive")
    private Boolean isActive;


    public Trainer(
                   String username,
                   String firstname,
                   String lastname,
                   Boolean isActive) {
        super();
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.isActive = isActive;
    }

    public Trainer() {

    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public Boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    @Override
    public String toString() {
        return "Trainer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
