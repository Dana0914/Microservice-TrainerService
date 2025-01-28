package epam.com.practice.trainerservice.service;


import epam.com.practice.trainerservice.dto.TrainingDTO;
import epam.com.practice.trainerservice.handler.exceptions.ResourceNotFoundException;
import epam.com.practice.trainerservice.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.util.*;



@Service
public class TrainingSessionProcessingService {

    private final TrainingService trainingService;
    private final SequenceGenerator sequenceGenerator;
    private final TrainingSummaryService trainingSummaryService;
    private final TrainerService trainerService;

    private static final Logger logger = LoggerFactory.getLogger(TrainingSessionProcessingService.class);


    public TrainingSessionProcessingService(
            TrainingService trainingService,
            SequenceGenerator sequenceGenerator,
            TrainingSummaryService trainingSummaryService,
            TrainerService trainerService) {

        this.sequenceGenerator = sequenceGenerator;
        this.trainingSummaryService = trainingSummaryService;
        this.trainingService = trainingService;
        this.trainerService = trainerService;
    }


    public void updateTrainingRecordIfTrainerExists(TrainingDTO request) throws ResourceNotFoundException {
        Optional<Trainer> trainer = trainerService.findTrainerByUsername(request.getTrainerUsername());

        if (trainer.isEmpty()) {
            trainer = Optional.of(new Trainer());
            trainer.get().setId(BigInteger.valueOf(sequenceGenerator.generateSequence(Trainer.SEQUENCE_NAME)));
            trainer.get().setUsername(request.getTrainerUsername());
            trainer.get().setFirstname(request.getTrainerFirstname());
            trainer.get().setLastname(request.getTrainerLastname());
            trainer.get().setIsActive(request.getIsActive());

            trainerService.createTrainer(trainer.get());
            logger.info("Trainer created {} ", trainer);

        }

        Training training = new Training();
        training.setDuration(request.getTrainingDuration());
        training.setTrainer(trainer.get());
        training.setDate(request.getTrainingDate());
        training.setActionType(request.getActionType().name());
        training.setId(BigInteger.valueOf(sequenceGenerator.generateSequence(Training.SEQUENCE_NAME)));

        trainingService.createTraining(training);
        logger.info("Training created {} ", training);

        calculateTrainingSummaryPerYearAndMonth(training);


    }

    public void calculateTrainingSummaryPerYearAndMonth(Training training) throws ResourceNotFoundException {
        Optional<TrainingSummary> trainingSummary = trainingSummaryService
                .findTrainingSummaryByYearAndMonth(
                        training.getDate().getYear(), training.getDate().getMonthValue());

        if (trainingSummary.isPresent()) {
            if (Objects.equals(training.getActionType(), ActionType.ADD.name())) {

                trainingSummary.get().setTrainingSummaryDuration(training.getDuration() +
                        trainingSummary.get().getTrainingSummaryDuration());

                trainingSummaryService.updateTrainingSummary(trainingSummary.get().getId(), trainingSummary.get());
                logger.info("Training summary incremented {} ", trainingSummary.get());

            } else if (Objects.equals(training.getActionType(), ActionType.DELETE.name())) {

                trainingSummary.get().setTrainingSummaryDuration(trainingSummary.get().getTrainingSummaryDuration() -
                        training.getDuration());

                trainingSummaryService.updateTrainingSummary(trainingSummary.get().getId(), trainingSummary.get());
                logger.info("Training summary decremented {} ", trainingSummary.get());
            }

        } else {


            TrainingSummary summary = new TrainingSummary();
            summary.setTrainingSummaryDuration(training.getDuration());
            summary.setId(BigInteger.valueOf(sequenceGenerator.generateSequence(TrainingSummary.SEQUENCE_NAME)));
            summary.setYear(training.getDate().getYear());
            summary.setMonth(training.getDate().getMonthValue());
            summary.setTraining(training);

            trainingSummaryService.createTrainingSummary(summary);
            logger.info("Training summary created {} ", summary);
        }
    }

}




