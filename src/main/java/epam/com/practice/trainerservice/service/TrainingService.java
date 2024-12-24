package epam.com.practice.trainerservice.service;


import epam.com.practice.trainerservice.model.Training;
import epam.com.practice.trainerservice.repo.TrainingRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<Training> findTrainingSessionByTrainerId(Long trainerId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("trainer_id").is(trainerId));
        List<Training> trainings = template.find(query, Training.class);
        return trainings;

    }

    public Training createTraining(Training training) {
        return trainingRepository.save(training);
    }


}
