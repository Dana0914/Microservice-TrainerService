package epam.com.practice.trainerservice.controller;


import epam.com.practice.trainerservice.dto.TrainersTrainingWorkloadDTO;
import epam.com.practice.trainerservice.service.TrainerWorkloadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/workload")
public class TrainersTrainingSessionController {

    private final TrainerWorkloadService trainerWorkloadService;

    public TrainersTrainingSessionController(TrainerWorkloadService trainerWorkloadService) {
        this.trainerWorkloadService = trainerWorkloadService;
    }

    @PostMapping(value = "/trainers/trainings")
    public ResponseEntity<String> updateTrainersTrainingWorkload(
            @RequestBody TrainersTrainingWorkloadDTO trainersTrainingWorkloadRequest) {

        trainerWorkloadService.addTrainerWorkload(
                trainersTrainingWorkloadRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body("Training workload created successfully");
    }



    @GetMapping(value = "/trainers/trainings/monthly-summary")
    public ResponseEntity<Void> getTrainingMonthlySummary() {
        trainerWorkloadService.getTrainingMonthlySummary();
        return ResponseEntity.status(HttpStatus.OK).build();

    }



}
