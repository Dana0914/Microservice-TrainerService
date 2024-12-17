package epam.com.practice.trainerservice.service;


import epam.com.practice.trainerservice.model.Training;
import epam.com.practice.trainerservice.repo.TrainingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {
    private final TrainingRepository trainingRepository;

    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public List<Training> findTrainingSessionByTrainerId(long trainerId) {
        return trainingRepository.findTrainingSessionByTrainerId(trainerId);
    }

    public Training createTraining(Training training) {
        return trainingRepository.save(training);
    }


}
