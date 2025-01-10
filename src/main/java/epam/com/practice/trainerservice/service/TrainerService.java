package epam.com.practice.trainerservice.service;



import epam.com.practice.trainerservice.handler.exceptions.ResourceNotFoundException;
import epam.com.practice.trainerservice.model.Trainer;
import epam.com.practice.trainerservice.repo.TrainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Optional;


@Service
@Transactional
public class TrainerService {
    private final TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public void createTrainer(Trainer trainer) {
        trainerRepository.save(trainer);
    }

    public Optional<Trainer> findById(BigInteger id) {
        return trainerRepository
                .findById(id);
    }

    public Optional<Trainer> findTrainerByUsername(String username) {
        return trainerRepository
                .findByUsername(username);
    }

}
