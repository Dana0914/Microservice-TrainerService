package epam.com.practice.trainerservice.service;


import epam.com.practice.trainerservice.model.Training;
import epam.com.practice.trainerservice.repo.TrainingRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Service
@Transactional
public class TrainingService {
    private final MongoTemplate template;
    private final TrainingRepository trainingRepository;

    public TrainingService(MongoTemplate template,
                           TrainingRepository trainingRepository) {

        this.template = template;
        this.trainingRepository = trainingRepository;
    }

    public void createTraining(Training training) {
        trainingRepository.save(training);
    }

    public Training findById(BigInteger id) {
        return trainingRepository.findById(id).orElse(null);
    }
}
