package epam.com.practice.trainerservice.service;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import epam.com.practice.trainerservice.dto.TrainingDTO;
import epam.com.practice.trainerservice.model.Trainer;
import epam.com.practice.trainerservice.model.TrainerWorkload;
import epam.com.practice.trainerservice.repo.TrainerRepository;
import epam.com.practice.trainerservice.repo.TrainerWorkloadRepository;
import epam.com.practice.trainerservice.repo.TrainingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrainersTrainingSessionTest {
    @Mock
    private TrainerWorkloadRepository trainerWorkloadRepository;
    @Mock
    private TrainingRepository trainingRepository;
    @Mock
    private TrainerRepository trainerRepository;

    @InjectMocks
    private TrainerWorkloadService trainerWorkloadService;


    @Test
    public void whenAddTrainerWorkload_WithValidEntity_thenReturnTrainerWorkloadSuccessfully() {
        Trainer trainer = new Trainer();
        trainer.setFirstname("John");
        trainer.setLastname("Doe");
        trainer.setIsActive(true);
        trainer.setId(1L);

        TrainerWorkload trainerWorkload = new TrainerWorkload();
        trainerWorkload.setId(1L);
        trainerWorkload.setYear(2025);
        trainerWorkload.setMonth(1);
        trainerWorkload.setTrainingHours(2);
        trainerWorkload.setTrainer(trainer);

        when(trainerWorkloadRepository.save(trainerWorkload)).thenReturn(trainerWorkload);
        when(trainerRepository.save(trainer)).thenReturn(trainer);

        trainerWorkloadRepository.save(trainerWorkload);
        trainerRepository.save(trainer);

        Assertions.assertEquals(trainerWorkload.getTrainer().getUsername(), trainer.getUsername());
        Assertions.assertEquals(trainerWorkload.getTrainer().getFirstname(), trainer.getFirstname());
        Assertions.assertEquals(trainerWorkload.getTrainer().getLastname(), trainer.getLastname());
        Assertions.assertEquals(trainerWorkload.getTrainer().getIsActive(), trainer.getIsActive());

        verify(trainerWorkloadRepository, times(1)).save(trainerWorkload);
        verify(trainerRepository, times(1)).save(trainer);

    }


    @Test
    public void givenObjectHasNoAccessors_whenSerializingWithAllFieldsDetected_thenNoException()
            throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        String dtoAsString = objectMapper.writeValueAsString(new TrainingDTO());


        // Assert the serialized JSON contains the expected fields
        assertThat(dtoAsString, containsString("trainerUsername"));
        assertThat(dtoAsString, containsString("trainerFirstname"));
        assertThat(dtoAsString, containsString("trainerLastname"));
        assertThat(dtoAsString, containsString("trainingDate"));
        assertThat(dtoAsString, containsString("trainingDuration"));
        assertThat(dtoAsString, containsString("actionType"));
        assertThat(dtoAsString, containsString("isActive"));
    }
}
