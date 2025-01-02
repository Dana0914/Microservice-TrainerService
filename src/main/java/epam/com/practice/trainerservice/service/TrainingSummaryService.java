package epam.com.practice.trainerservice.service;


import epam.com.practice.trainerservice.model.SequenceGenerator;
import epam.com.practice.trainerservice.model.TrainingSummary;
import epam.com.practice.trainerservice.repo.TrainingSummaryRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class TrainingSummaryService {
    private final TrainingSummaryRepository trainingSummaryRepository;
    private final MongoTemplate mongoTemplate;
    private final SequenceGenerator sequenceGenerator;

    public TrainingSummaryService(TrainingSummaryRepository trainingSummaryRepository, MongoTemplate mongoTemplate, SequenceGenerator sequenceGenerator) {
        this.trainingSummaryRepository = trainingSummaryRepository;
        this.mongoTemplate = mongoTemplate;
        this.sequenceGenerator = sequenceGenerator;
    }


    public void update(BigInteger id, TrainingSummary trainingSummary) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id)
                .and("year").is(trainingSummary.getYear())
                .and("month").is(trainingSummary.getMonth()));

        Update update = new Update();
        update.set("trainingSummaryDuration", trainingSummary.getTrainingSummaryDuration());
        update.set("year", trainingSummary.getYear());
        update.set("month", trainingSummary.getMonth());
        update.set("training", trainingSummary.getTraining());


        TrainingSummary summary = mongoTemplate.findOne(query, TrainingSummary.class);
        if (summary == null) {
            trainingSummaryRepository.save(trainingSummary);
        }

        mongoTemplate.upsert(query, update, TrainingSummary.class);

    }
}
