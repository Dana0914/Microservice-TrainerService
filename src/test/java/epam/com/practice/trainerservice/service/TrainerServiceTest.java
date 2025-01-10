package epam.com.practice.trainerservice.service;


import epam.com.practice.trainerservice.model.Trainer;
import epam.com.practice.trainerservice.repo.TrainerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import java.math.BigInteger;
import java.util.Optional;




@DataMongoTest
@ExtendWith(MockitoExtension.class)
class TrainerServiceTest {

    @InjectMocks
    private TrainerService trainerService;
    @Mock
    private TrainerRepository trainerRepository;

    @BeforeEach
    void setUp() {
        trainerService = new TrainerService(trainerRepository);
    }


    @Test
    void whenTrainerSavedThenReturnValidTrainerEntity() {
        Trainer trainer = new Trainer();
        trainer.setId(BigInteger.valueOf(1));
        trainer.setUsername("John Doe");
        trainer.setFirstname("John");
        trainer.setLastname("Doe");
        trainer.setIsActive(true);

        Mockito.when(trainerRepository.save(Mockito.any(Trainer.class))).thenReturn(trainer);

        trainerService.createTrainer(trainer);

        Assertions.assertEquals("John Doe", trainer.getUsername());
        Assertions.assertEquals("John", trainer.getFirstname());
        Assertions.assertEquals("Doe", trainer.getLastname());
        Assertions.assertEquals(BigInteger.ONE, trainer.getId());
        Assertions.assertTrue(trainer.getIsActive());

        Mockito.verify(trainerRepository, Mockito.times(1)).save(trainer);




    }

    @Test
    void whenTrainerFoundByIdThenReturnValidTrainerEntity() {
        Trainer trainer = new Trainer();
        trainer.setId(BigInteger.ONE);
        trainer.setUsername("John Doe");
        trainer.setFirstname("John");
        trainer.setLastname("Doe");
        trainer.setIsActive(true);

        trainerService.createTrainer(trainer);

        Mockito.when(trainerRepository.findById(trainer.getId())).thenReturn(Optional.of(trainer));

        trainerService.findById(trainer.getId());

        Assertions.assertEquals("John Doe", trainer.getUsername());
        Assertions.assertEquals("John", trainer.getFirstname());
        Assertions.assertEquals("Doe", trainer.getLastname());
        Assertions.assertEquals(BigInteger.ONE, trainer.getId());
        Assertions.assertTrue(trainer.getIsActive());

        Mockito.verify(trainerRepository, Mockito.times(1)).findById(trainer.getId());


    }

    @Test
    void whenTrainerFoundByUsernameThenReturnValidTrainerEntity() {
        Trainer trainer = new Trainer();
        trainer.setId(BigInteger.ONE);
        trainer.setUsername("John Doe");
        trainer.setFirstname("John");
        trainer.setLastname("Doe");
        trainer.setIsActive(true);

        trainerService.createTrainer(trainer);

        Mockito.when(trainerRepository.findByUsername(trainer.getUsername())).thenReturn(Optional.of(trainer));

        trainerService.findTrainerByUsername(trainer.getUsername());

        Assertions.assertEquals("John Doe", trainer.getUsername());
        Assertions.assertEquals("John", trainer.getFirstname());
        Assertions.assertEquals("Doe", trainer.getLastname());
        Assertions.assertEquals(BigInteger.ONE, trainer.getId());
        Assertions.assertTrue(trainer.getIsActive());

        Mockito.verify(trainerRepository, Mockito.times(1)).findByUsername(trainer.getUsername());


    }
}