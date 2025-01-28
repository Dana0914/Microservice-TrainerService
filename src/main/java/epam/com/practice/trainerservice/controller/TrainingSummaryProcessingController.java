package epam.com.practice.trainerservice.controller;

import epam.com.practice.trainerservice.dto.TrainingDTO;
import epam.com.practice.trainerservice.handler.exceptions.ResourceNotFoundException;
import epam.com.practice.trainerservice.service.TrainingSessionProcessingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "api/workload")
public class TrainingSummaryProcessingController {

    private final TrainingSessionProcessingService processingService;

    public TrainingSummaryProcessingController(TrainingSessionProcessingService processingService) {
        this.processingService = processingService;
    }


    @Operation(summary = "Update training session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the training session",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TrainingDTO.class))}),
            @ApiResponse(responseCode = "201", description = "Training session created",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TrainingDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden - You don't have permission", content = @Content),
            @ApiResponse(responseCode = "404", description = "Training not found", content = @Content)
    })

    @PostMapping
    public ResponseEntity<TrainingDTO> updateTrainersTrainingWorkload(
            @RequestBody TrainingDTO request) {

        try {
            processingService.updateTrainingRecordIfTrainerExists(request);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(request, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(request, HttpStatus.OK);

    }


}
