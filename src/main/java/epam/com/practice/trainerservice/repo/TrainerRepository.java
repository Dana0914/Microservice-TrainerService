package epam.com.practice.trainerservice.repo;

import epam.com.practice.trainerservice.model.Trainer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TrainerRepository extends MongoRepository<Trainer, Long> {
    Optional<Trainer> findByUsername(String username);

}
