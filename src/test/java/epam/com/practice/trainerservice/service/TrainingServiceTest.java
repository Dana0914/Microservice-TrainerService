package epam.com.practice.trainerservice.service;

import epam.com.practice.trainerservice.model.ActionType;
import epam.com.practice.trainerservice.model.Trainer;
import epam.com.practice.trainerservice.model.Training;
import epam.com.practice.trainerservice.repo.TrainingRepository;
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
import java.math.BigInteger;
import java.time.LocalDate;



@DataMongoTest
@ExtendWith(MockitoExtension.class)
class TrainingServiceTest {
    @InjectMocks
    private TrainingService trainingService;

    @Mock
    private TrainingRepository trainingRepository;

    @Mock
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void setUp() {
        trainingService = new TrainingService(mongoTemplate, trainingRepository);

    }

    @Test
    void whenEntityCreatedThenCreateValidTraining() {
        Trainer trainer = new Trainer();
        trainer.setId(BigInteger.ONE);
        trainer.setUsername("John.Doe");
        trainer.setFirstname("John");
        trainer.setLastname("Doe");
        trainer.setIsActive(true);

        Training training = new Training();
        training.setId(BigInteger.ONE);
        training.setDate(LocalDate.now());
        training.setTrainer(trainer);
        training.setDuration(2);
        training.setActionType(ActionType.ADD.name());


        Mockito.when(trainingRepository.save(training)).thenReturn(training);
        trainingService.createTraining(training);


        Assertions.assertNotNull(training);
        Assertions.assertEquals(training.getId(), BigInteger.ONE);
        Assertions.assertEquals(training.getDate(), LocalDate.now());
        Assertions.assertEquals(training.getTrainer(), trainer);
        Assertions.assertEquals(training.getDuration(), 2);
        Assertions.assertEquals(training.getActionType(), ActionType.ADD.name());

        Mockito.verify(trainingRepository, Mockito.times(1)).save(training);

    }
}