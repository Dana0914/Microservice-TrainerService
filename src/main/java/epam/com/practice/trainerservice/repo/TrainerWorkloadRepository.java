package epam.com.practice.trainerservice.repo;

import epam.com.practice.trainerservice.model.TrainerWorkload;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TrainerWorkloadRepository extends MongoRepository<TrainerWorkload, Long> {

}
