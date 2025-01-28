package epam.com.practice.trainerservice.service;


import com.mongodb.client.result.UpdateResult;
import epam.com.practice.trainerservice.model.ActionType;
import epam.com.practice.trainerservice.model.Trainer;
import epam.com.practice.trainerservice.model.Training;
import epam.com.practice.trainerservice.model.TrainingSummary;
import epam.com.practice.trainerservice.repo.TrainingSummaryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.math.BigInteger;
import java.time.LocalDate;


@DataMongoTest
@ExtendWith(MockitoExtension.class)
class TrainingSummaryServiceTest {

    @InjectMocks
    private TrainingSummaryService trainingSummaryService;
    @Mock
    private TrainingSummaryRepository trainingSummaryRepository;
    @Mock
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void setUp() {
        trainingSummaryService = new TrainingSummaryService(trainingSummaryRepository, mongoTemplate);
    }

    @Test
    void whenTrainingSummaryUpdatedByYearAndMonthIsValidThenUpdateSuccessfully() {
        Trainer trainer = new Trainer();
        trainer.setId(BigInteger.ONE);
        trainer.setUsername("John.Doe");
        trainer.setFirstname("John");
        trainer.setLastname("Doe");
        trainer.setIsActive(true);

        Training training = new Training();
        training.setId(BigInteger.ONE);
        training.setDuration(2);
        training.setActionType(ActionType.ADD.name());
        training.setTrainer(trainer);
        training.setDate(LocalDate.now());

        TrainingSummary oldTrainingSummary = new TrainingSummary();
        oldTrainingSummary.setId(BigInteger.ONE);
        oldTrainingSummary.setTrainingSummaryDuration(training.getDuration());
        oldTrainingSummary.setYear(2025);
        oldTrainingSummary.setMonth(1);
        oldTrainingSummary.setTraining(training);

        trainingSummaryService.createTrainingSummary(oldTrainingSummary);


        TrainingSummary trainingSummary = new TrainingSummary();
        trainingSummary.setTraining(training);
        trainingSummary.setId(BigInteger.TWO);
        trainingSummary.setMonth(1);
        trainingSummary.setYear(2025);
        trainingSummary.setTrainingSummaryDuration(training.getDuration()
                + oldTrainingSummary.getTrainingSummaryDuration());

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(oldTrainingSummary.getId())
                .and("year").is(training.getDate().getYear())
                .and("month").is(training.getDate().getMonthValue()));

        Update update = new Update();
        update.set("trainingSummaryDuration", trainingSummary.getTrainingSummaryDuration());


        mongoTemplate.upsert(query, update, TrainingSummary.class);






        Assertions.assertNotNull(query);
        Assertions.assertNotNull(update);
        Assertions.assertNotNull(trainingSummary);
        Assertions.assertNotNull(training);
        Assertions.assertNotNull(trainer);

        Assertions.assertEquals(trainer.getUsername(), "John.Doe");
        Assertions.assertEquals(trainer.getFirstname(), "John");
        Assertions.assertEquals(trainer.getLastname(), "Doe");
        Assertions.assertTrue(trainer.getIsActive());
        Assertions.assertEquals(trainer.getId(), BigInteger.ONE);

        Assertions.assertEquals(training.getDate(), LocalDate.now());
        Assertions.assertEquals(training.getDuration(), 2);
        Assertions.assertEquals(training.getId(), BigInteger.ONE);
        Assertions.assertEquals(training.getTrainer(), trainer);
        Assertions.assertEquals(training.getActionType(), ActionType.ADD.name());

        Assertions.assertEquals(trainingSummary.getId(), BigInteger.TWO);
        Assertions.assertEquals(trainingSummary.getTraining(), training);
        Assertions.assertEquals(trainingSummary.getTrainingSummaryDuration(), 4);
        Assertions.assertEquals(trainingSummary.getMonth(), 1);
        Assertions.assertEquals(trainingSummary.getYear(), 2025);

        Mockito.verify(mongoTemplate, Mockito.times(1)).upsert(query, update, TrainingSummary.class);
    }

    @Test
    void whenEntityCreatedIsValidThenSaveSuccessfully() {
        Trainer trainer = new Trainer();
        trainer.setId(BigInteger.ONE);
        trainer.setUsername("John.Doe");
        trainer.setFirstname("John");
        trainer.setLastname("Doe");
        trainer.setIsActive(true);

        Training training = new Training();
        training.setId(BigInteger.ONE);
        training.setDuration(2);
        training.setActionType(ActionType.ADD.name());
        training.setTrainer(trainer);
        training.setDate(LocalDate.now());

        TrainingSummary trainingSummary = new TrainingSummary();
        trainingSummary.setTraining(training);
        trainingSummary.setId(BigInteger.ONE);
        trainingSummary.setMonth(1);
        trainingSummary.setYear(2025);
        trainingSummary.setTrainingSummaryDuration(training.getDuration());

        Mockito.when(trainingSummaryRepository.save(trainingSummary)).thenReturn(trainingSummary);
        trainingSummaryService.createTrainingSummary(trainingSummary);

        Assertions.assertNotNull(trainingSummary);
        Assertions.assertNotNull(training);
        Assertions.assertNotNull(trainer);

        Assertions.assertEquals(trainer.getUsername(), "John.Doe");
        Assertions.assertEquals(trainer.getFirstname(), "John");
        Assertions.assertEquals(trainer.getLastname(), "Doe");
        Assertions.assertTrue(trainer.getIsActive());
        Assertions.assertEquals(trainer.getId(), BigInteger.ONE);

        Assertions.assertEquals(training.getDate(), LocalDate.now());
        Assertions.assertEquals(training.getDuration(), 2);
        Assertions.assertEquals(training.getId(), BigInteger.ONE);
        Assertions.assertEquals(training.getTrainer(), trainer);
        Assertions.assertEquals(training.getActionType(), ActionType.ADD.name());

        Assertions.assertEquals(trainingSummary.getId(), BigInteger.ONE);
        Assertions.assertEquals(trainingSummary.getTraining(), training);
        Assertions.assertEquals(trainingSummary.getTrainingSummaryDuration(), 2);
        Assertions.assertEquals(trainingSummary.getMonth(), 1);
        Assertions.assertEquals(trainingSummary.getYear(), 2025);

        Mockito.verify(trainingSummaryRepository, Mockito.times(1)).save(trainingSummary);

    }

    @Test
    void findTrainingSummaryByYearAndMonth() {
        Trainer trainer = new Trainer();
        trainer.setId(BigInteger.ONE);
        trainer.setUsername("John.Doe");
        trainer.setFirstname("John");
        trainer.setLastname("Doe");
        trainer.setIsActive(true);

        Training training = new Training();
        training.setId(BigInteger.ONE);
        training.setDuration(2);
        training.setActionType(ActionType.ADD.name());
        training.setTrainer(trainer);
        training.setDate(LocalDate.now());

        TrainingSummary trainingSummary = new TrainingSummary();
        trainingSummary.setTraining(training);
        trainingSummary.setId(BigInteger.ONE);
        trainingSummary.setMonth(1);
        trainingSummary.setYear(2025);
        trainingSummary.setTrainingSummaryDuration(training.getDuration());

        trainingSummaryService.createTrainingSummary(trainingSummary);

        Query query = new Query();
        query.addCriteria(Criteria
                .where("year").is(training.getDate().getYear())
                .and("month").is(training.getDate().getMonthValue()));

        Mockito.when(mongoTemplate.findOne(query, TrainingSummary.class)).thenReturn(trainingSummary);

        trainingSummaryService.findTrainingSummaryByYearAndMonth(trainingSummary.getYear(), trainingSummary.getMonth());


        Assertions.assertNotNull(query);
        Assertions.assertNotNull(trainingSummary);
        Assertions.assertNotNull(training);
        Assertions.assertNotNull(trainer);

        Assertions.assertEquals(trainer.getUsername(), "John.Doe");
        Assertions.assertEquals(trainer.getFirstname(), "John");
        Assertions.assertEquals(trainer.getLastname(), "Doe");
        Assertions.assertTrue(trainer.getIsActive());
        Assertions.assertEquals(trainer.getId(), BigInteger.ONE);

        Assertions.assertEquals(training.getDate(), LocalDate.now());
        Assertions.assertEquals(training.getDuration(), 2);
        Assertions.assertEquals(training.getId(), BigInteger.ONE);
        Assertions.assertEquals(training.getTrainer(), trainer);
        Assertions.assertEquals(training.getActionType(), ActionType.ADD.name());

        Assertions.assertEquals(trainingSummary.getId(), BigInteger.ONE);
        Assertions.assertEquals(trainingSummary.getTraining(), training);
        Assertions.assertEquals(trainingSummary.getTrainingSummaryDuration(), 2);
        Assertions.assertEquals(trainingSummary.getMonth(), 1);
        Assertions.assertEquals(trainingSummary.getYear(), 2025);

        Mockito.verify(mongoTemplate, Mockito.times(1)).findOne(query, TrainingSummary.class);


    }
}