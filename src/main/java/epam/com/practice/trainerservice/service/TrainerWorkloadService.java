package epam.com.practice.trainerservice.service;


import epam.com.practice.trainerservice.dto.TrainingDTO;
import epam.com.practice.trainerservice.handler.exceptions.ResourceNotFoundException;
import epam.com.practice.trainerservice.model.ActionType;
import epam.com.practice.trainerservice.model.Trainer;
import epam.com.practice.trainerservice.model.TrainerWorkload;
import epam.com.practice.trainerservice.model.Training;
import epam.com.practice.trainerservice.repo.TrainerRepository;
import epam.com.practice.trainerservice.repo.TrainerWorkloadRepository;
import epam.com.practice.trainerservice.repo.TrainingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.time.Year;
import java.time.YearMonth;
import java.util.*;

@Service
public class TrainerWorkloadService {

    private final TrainerRepository trainerRepository;
    private final TrainingRepository trainingRepository;
    private final TrainerWorkloadRepository trainerWorkloadRepository;

    private Map<YearMonth, Integer> yearMonthHours;
    private static final Logger logger = LoggerFactory.getLogger(TrainerWorkloadService.class);




    public TrainerWorkloadService(

            TrainerRepository trainerRepository,
            TrainingRepository trainingRepository,
            TrainerWorkloadRepository trainerWorkloadRepository) {

        this.trainerRepository = trainerRepository;
        this.trainingRepository = trainingRepository;
        this.trainerWorkloadRepository = trainerWorkloadRepository;
        yearMonthHours = new HashMap<>();

    }


    public void updateTrainerWorkload(TrainingDTO request) throws ResourceNotFoundException {

        Trainer trainer = new Trainer(request.getTrainerUsername(),
                request.getTrainerFirstname(),
                request.getTrainerLastname(),
                request.getIsActive());

        trainerRepository.save(trainer);
        logger.info("Trainer created {} ", trainer);

        Training training = new Training(request.getTrainingDate(),
                request.getTrainingDuration(),
                request.getActionType().name(),
                trainer);

        trainingRepository.save(training);
        logger.info("Training created {} ", training);

        TrainerWorkload trainerWorkload = new TrainerWorkload();
        trainerWorkload.setTrainer(trainer);
        trainerWorkload.setYear(Year.of(request.getTrainingDate().getYear()).getValue());
        trainerWorkload.setMonth(request.getTrainingDate().getMonth().getValue());
        trainerWorkload.setTrainingHours(request.getTrainingDuration());

        trainerWorkloadRepository.save(trainerWorkload);
        logger.info("Training workload created {} ", trainerWorkload);

        updateTrainerWorkload(trainerWorkload.getId());

        calculateTrainingSessionPerTrainer(trainer.getId(), request.getActionType());


    }

    public void calculateTrainingSessionPerTrainer(long trainerId, ActionType actionType) throws ResourceNotFoundException {
        List<Training> trainingSessions = trainingRepository.findTrainingSessionByTrainerId(trainerId);
        for (Training training : trainingSessions) {
            if (training.getTrainer().getId() != trainerId) {
                throw new ResourceNotFoundException("Trainer with id " + trainerId + " does not exist");
            }
        }
        logger.info("Training session found by id");

        if (actionType == ActionType.ADD) {

            // fetch year and month per session
            for (Training training : trainingSessions) {
                YearMonth yearMonth = YearMonth.of(
                        training
                                .getDate()
                                .getYear(),

                        training
                                .getDate()
                                .getMonth());
                yearMonthHours.put(yearMonth, yearMonthHours
                        .getOrDefault(yearMonth, 0) +
                        training.getDuration());

                logger.info("Fetched month, year and duration added to map {} ", yearMonthHours);
            }
        }

        if (actionType == ActionType.DELETE) {

            for (Training training : trainingSessions) {
                YearMonth yearMonth = YearMonth.of(
                        training
                                .getDate()
                                .getYear(),

                        training
                                .getDate()
                                .getMonth());

                yearMonthHours.put(yearMonth, yearMonthHours
                        .getOrDefault(yearMonth, 0) -
                        training.getDuration());
                logger.info("Fetched month, year and duration deleted from map {} ", yearMonthHours);
            }
        }
    }

    public void updateTrainerWorkload(long id) throws ResourceNotFoundException {
        TrainerWorkload trainerWorkload = trainerWorkloadRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Entity not found by id " + id));

        logger.info("Trainer workload found by id {} ", trainerWorkload);

        for (YearMonth yearMonth : yearMonthHours.keySet()) {
            trainerWorkload.setYear(yearMonth.getYear());
            trainerWorkload.setMonth(yearMonth.getMonth().getValue());

        }
        for (Integer yearMonth : yearMonthHours.values()) {
            trainerWorkload.setTrainingHours(yearMonth);
        }

        trainerWorkloadRepository.save(trainerWorkload);
        logger.info("Trainer workload updated {} ", trainerWorkload);

    }

    public List<TrainerWorkload> getTrainingMonthlySummary() {
        // list of monthly summary of training sessions per trainer
        List<TrainerWorkload> trainerMonthlySummary = trainerWorkloadRepository.getTrainerMonthlySummary();
        logger.info("Trainer monthly summary retrieved {}",  trainerWorkloadRepository.getTrainerMonthlySummary());

        return trainerMonthlySummary;

    }
}
