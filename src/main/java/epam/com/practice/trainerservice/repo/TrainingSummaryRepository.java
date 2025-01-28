package epam.com.practice.trainerservice.repo;


import epam.com.practice.trainerservice.model.TrainingSummary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface TrainingSummaryRepository extends MongoRepository<TrainingSummary, BigInteger> {
    @Query(value = "{year: ?0, month: ?1}")
    Optional<TrainingSummary> findTrainingSummaryByYearAndMonth(int year, int month);

}
