package epam.com.practice.trainerservice.repo;


import epam.com.practice.trainerservice.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

    @Query(value = "select * from Training where TRAINER_ID = :id", nativeQuery = true)
    List<Training> findTrainingSessionByTrainerId(@Param("id") Long trainerId);


}
