package epam.com.practice.trainerservice.service;


import epam.com.practice.trainerservice.model.TrainingSummary;
import epam.com.practice.trainerservice.repo.TrainingSummaryRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigInteger;
import java.util.Optional;


@Service
@Transactional
public class TrainingSummaryService {
    private final TrainingSummaryRepository trainingSummaryRepository;
    private final MongoTemplate mongoTemplate;

    public TrainingSummaryService(TrainingSummaryRepository trainingSummaryRepository,
                                  MongoTemplate mongoTemplate) {
        this.trainingSummaryRepository = trainingSummaryRepository;
        this.mongoTemplate = mongoTemplate;
    }


    public void updateTrainingSummary(BigInteger id, TrainingSummary trainingSummary) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id)
                .and("year").is(trainingSummary.getYear())
                .and("month").is(trainingSummary.getMonth()));

        Update update = new Update();
        update.set("trainingSummaryDuration", trainingSummary.getTrainingSummaryDuration());
        update.set("year", trainingSummary.getYear());
        update.set("month", trainingSummary.getMonth());
        update.set("training", trainingSummary.getTraining());

        mongoTemplate.upsert(query, update, TrainingSummary.class);
    }

    public void createTrainingSummary(TrainingSummary trainingSummary) {
        trainingSummaryRepository.save(trainingSummary);
    }

    public Optional<TrainingSummary> findTrainingSummaryByYearAndMonth(int year, int month) {
        Query query = new Query();
        query.addCriteria(Criteria
                .where("year").is(year)
                .and("month").is(month));
        return Optional.ofNullable(mongoTemplate.findOne(query, TrainingSummary.class));

    }

    public TrainingSummary findTrainingSummaryById(BigInteger id) {
        return trainingSummaryRepository.findById(id).orElse(null);
    }
}
