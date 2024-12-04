package epam.com.practice.trainerservice.controller;


import epam.com.practice.trainerservice.config.SwaggerConfiguration;
import epam.com.practice.trainerservice.dto.TrainersTrainingWorkloadDTO;
import epam.com.practice.trainerservice.service.TrainerWorkloadService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;

@RestController
@RequestMapping(value = "api/workload")
@Api(tags = {SwaggerConfiguration.TRAINER_WORKLOAD_TAG})
@Import({SpringDataRestConfiguration.class, BeanValidatorPluginsConfiguration.class})
public class TrainersTrainingSessionController {

    private final TrainerWorkloadService trainerWorkloadService;

    public TrainersTrainingSessionController(TrainerWorkloadService trainerWorkloadService) {
        this.trainerWorkloadService = trainerWorkloadService;
    }

    @Operation(summary = "Update training session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the training session",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TrainersTrainingWorkloadDTO.class))}),
            @ApiResponse(responseCode = "201", description = "Training session created",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TrainersTrainingWorkloadDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden - You don't have permission", content = @Content),
            @ApiResponse(responseCode = "404", description = "Training not found", content = @Content)
    })
    @PostMapping(value = "/trainers/trainings")
    public ResponseEntity<String> updateTrainersTrainingWorkload(
            @RequestBody TrainersTrainingWorkloadDTO trainersTrainingWorkloadRequest) {

        trainerWorkloadService.addTrainerWorkload(
                trainersTrainingWorkloadRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body("Training workload created successfully");
    }

}
