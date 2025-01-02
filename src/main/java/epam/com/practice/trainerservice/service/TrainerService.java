package epam.com.practice.trainerservice.service;



import epam.com.practice.trainerservice.handler.exceptions.ResourceNotFoundException;
import epam.com.practice.trainerservice.model.Trainer;
import epam.com.practice.trainerservice.repo.TrainerRepository;
import org.springframework.stereotype.Service;



@Service
public class TrainerService {
    private final TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public Trainer createTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    public Trainer findTrainerById(long id) {
        return trainerRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found"));
    }

    public Trainer findTrainerByUsername(String username) {
        return trainerRepository
                .findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found"));
    }

}
