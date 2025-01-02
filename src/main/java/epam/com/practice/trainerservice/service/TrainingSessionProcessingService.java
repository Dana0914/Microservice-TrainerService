package epam.com.practice.trainerservice.service;


import epam.com.practice.trainerservice.dto.TrainingDTO;
import epam.com.practice.trainerservice.handler.exceptions.ResourceNotFoundException;
import epam.com.practice.trainerservice.model.*;
import epam.com.practice.trainerservice.repo.TrainerRepository;
import epam.com.practice.trainerservice.repo.TrainingRepository;
import epam.com.practice.trainerservice.repo.TrainingSummaryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.util.*;



@Service
public class TrainingSessionProcessingService {

    private final TrainerRepository trainerRepository;
    private final TrainingRepository trainingRepository;
    private final TrainingService trainingService;
    private final SequenceGenerator sequenceGenerator;
    private final TrainingSummaryRepository trainingSummaryRepository;
    private final TrainingSummaryService trainingSummaryService;

    private static final Logger logger = LoggerFactory.getLogger(TrainingSessionProcessingService.class);


    public TrainingSessionProcessingService(

            TrainerRepository trainerRepository,
            TrainingRepository trainingRepository,
            TrainingService trainingService,
            SequenceGenerator sequenceGenerator,
            TrainingSummaryRepository trainingSummaryRepository,
            TrainingSummaryService trainingSummaryService) {

        this.trainerRepository = trainerRepository;
        this.trainingRepository = trainingRepository;
        this.sequenceGenerator = sequenceGenerator;
        this.trainingSummaryRepository = trainingSummaryRepository;
        this.trainingSummaryService = trainingSummaryService;
        this.trainingService = trainingService;

    }


    public void updateTrainerRecord(TrainingDTO request) throws ResourceNotFoundException {
        Optional<Trainer> trainer = trainerRepository.findByUsername(request.getTrainerUsername());

        if (trainer.isEmpty()) {
            trainer = Optional.of(new Trainer());
            trainer.get().setId(BigInteger.valueOf(sequenceGenerator.generateSequence(Trainer.SEQUENCE_NAME)));
            trainer.get().setUsername(request.getTrainerUsername());
            trainer.get().setFirstname(request.getTrainerFirstname());
            trainer.get().setLastname(request.getTrainerLastname());
            trainer.get().setIsActive(request.getIsActive());

            trainerRepository.save(trainer.get());
            logger.info("Trainer created {} ", trainer);

        }

        Training training = new Training();
        training.setDuration(request.getTrainingDuration());
        training.setTrainer(trainer.get());
        training.setDate(request.getTrainingDate());
        training.setActionType(request.getActionType().name());
        training.setId(BigInteger.valueOf(sequenceGenerator.generateSequence(Training.SEQUENCE_NAME)));

        trainingRepository.save(training);
        logger.info("Training created {} ", training);

        calculateTrainingSummaryPerYearAndMonth(training);

    }
    public void calculateTrainingSummaryPerYearAndMonth(Training training) throws ResourceNotFoundException {
        Optional<TrainingSummary> trainingSummary = trainingSummaryRepository
                .findTrainingSummaryByYearAndMonth(
                        training.getDate().getYear(), training.getDate().getMonthValue());



        if (trainingSummary.isPresent()) {
            if (Objects.equals(training.getActionType(), ActionType.ADD.name())) {
                trainingSummary.get().setTrainingSummaryDuration(training.getDuration() +
                        trainingSummary.get().getTrainingSummaryDuration());
                trainingSummaryService.update(trainingSummary.get().getId(), trainingSummary.get());
                logger.info("Training summary incremented {} ", trainingSummary.get());
            } else if (Objects.equals(training.getActionType(), ActionType.DELETE.name())) {
                trainingSummary.get().setTrainingSummaryDuration(trainingSummary.get().getTrainingSummaryDuration() -
                        training.getDuration());
                trainingSummaryService.update(trainingSummary.get().getId(), trainingSummary.get());
                logger.info("Training summary decremented {} ", trainingSummary.get());
            }

        } else {
            TrainingSummary summary = new TrainingSummary();
            summary.setTrainingSummaryDuration(training.getDuration());
            summary.setId(BigInteger.valueOf(sequenceGenerator.generateSequence(TrainingSummary.SEQUENCE_NAME)));
            summary.setYear(training.getDate().getYear());
            summary.setMonth(training.getDate().getMonthValue());
            summary.setTraining(training);

            trainingSummaryRepository.save(summary);
            logger.info("Training summary created {} ", summary);
        }
    }

}




