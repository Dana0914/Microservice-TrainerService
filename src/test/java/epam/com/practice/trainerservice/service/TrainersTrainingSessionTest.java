package epam.com.practice.trainerservice.service;


import epam.com.practice.trainerservice.dto.TrainersTrainingWorkloadDTO;
import epam.com.practice.trainerservice.model.ActionType;
import epam.com.practice.trainerservice.model.Trainer;
import epam.com.practice.trainerservice.model.TrainerWorkload;
import epam.com.practice.trainerservice.model.Training;
import epam.com.practice.trainerservice.repo.TrainerRepository;
import epam.com.practice.trainerservice.repo.TrainerWorkloadRepository;
import epam.com.practice.trainerservice.repo.TrainingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Year;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
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
    public void whenAddTrainerWorkload_WithValidEntity_thenReturnTrainerWorkload() {
        TrainersTrainingWorkloadDTO request = new TrainersTrainingWorkloadDTO();
        request.setTrainerUsername("John.Doe");
        request.setTrainerFirstname("John");
        request.setTrainerLastname("Doe");
        request.setTrainingDate(LocalDate.now());
        request.setTrainingDuration(2);
        request.setIsActive(true);
        request.setActionType(ActionType.ADD);

        Trainer trainer = new Trainer(request.getTrainerUsername(),
                request.getTrainerFirstname(),
                request.getTrainerLastname(),
                request.getIsActive());
        trainer.setId(1L);

        Training training = new Training(request.getTrainingDate(),
                request.getTrainingDuration(),
                request.getActionType().name(),
                trainer);
        training.setId(1L);

        TrainerWorkload trainerWorkload = new TrainerWorkload();
        trainerWorkload.setId(1L);
        trainerWorkload.setTrainer(trainer);
        trainerWorkload.setYear(Year.of(request.getTrainingDate().getYear()).getValue());
        trainerWorkload.setMonth(request.getTrainingDate().getMonth().getValue());
        trainerWorkload.setTrainingHours(request.getTrainingDuration());

        Assertions.assertEquals(trainer.getUsername(), request.getTrainerUsername());
        Assertions.assertEquals(trainer.getFirstname(), request.getTrainerFirstname());
        Assertions.assertEquals(trainer.getLastname(), request.getTrainerLastname());
        Assertions.assertEquals(training.getDate(), request.getTrainingDate());
        Assertions.assertEquals(training.getDuration(), request.getTrainingDuration());
        Assertions.assertEquals(training.getActionType(), request.getActionType().name());
        Assertions.assertEquals(trainer.getActive(), request.getIsActive());

        when(trainerWorkloadRepository.findById(1L)).thenReturn(Optional.of(trainerWorkload));

        trainerWorkloadService.updateTrainerWorkload(trainerWorkload.getId());

        verify(trainerWorkloadRepository, times(1)).findById(1L);

        trainerWorkloadService.calculateTrainingSessionPerTrainer(trainer.getId(), request.getActionType());

        trainerWorkloadRepository.save(trainerWorkload);
        trainerRepository.save(trainer);
        trainingRepository.save(training);

        verify(trainingRepository, times(1)).save(any(Training.class));
        verify(trainerRepository, times(1)).save(any(Trainer.class));




    }
}
