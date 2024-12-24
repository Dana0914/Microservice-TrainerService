package epam.com.practice.trainerservice.repo;


import epam.com.practice.trainerservice.model.Training;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TrainingRepository extends MongoRepository<Training, Long> {


}
