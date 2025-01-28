package epam.com.practice.trainerservice.cucumber.steps;

import epam.com.practice.trainerservice.model.Trainer;
import epam.com.practice.trainerservice.model.Training;
import epam.com.practice.trainerservice.model.TrainingSummary;
import epam.com.practice.trainerservice.service.TrainerService;
import epam.com.practice.trainerservice.service.TrainingService;
import epam.com.practice.trainerservice.service.TrainingSummaryService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class TrainingSessionProcessingStepDefinition {

    private Training training;
    private Trainer trainer;
    private TrainingSummary trainingSummary;

    private final TrainingService trainingService;
    private final TrainerService trainerService;
    private final TrainingSummaryService trainingSummaryService;

    public TrainingSessionProcessingStepDefinition(TrainingService trainingService,
                                                   TrainerService trainerService,
                                                   TrainingSummaryService trainingSummaryService) {
        this.trainingService = trainingService;
        this.trainerService = trainerService;
        this.trainingSummaryService = trainingSummaryService;
    }


    @Given("I update the training with the following details:")
    public void iUpdateTheTrainingWithTheFollowingDetails(DataTable dataTable) {
        Trainer trainer = new Trainer();
        trainer.setUsername("John Doe");
        trainer.setFirstname("John");
        trainer.setLastname("Doe");
        trainer.setId(BigInteger.valueOf(1));
        trainer.setIsActive(true);

        List<Map<String, String>> trainingData = dataTable.asMaps(String.class, String.class);
        Map<String, String> trainingMap = trainingData.getFirst();

        training = new Training();
        training.setId(BigInteger.valueOf(Integer.parseInt(trainingMap.get("id"))));
        training.setTrainer(trainer);
        training.setDuration(Integer.parseInt(trainingMap.get("duration")));
        training.setDate(LocalDate.parse(trainingMap.get("date")));
        training.setActionType(trainingMap.get("actionType"));
    }


    @When("I update the training")
    public void iUpdateTheTraining() {
        trainingService.createTraining(training);

    }

    @Then("the training session should include the following details:")
    public void theTrainingSessionShouldIncludeTheFollowingDetails(DataTable dataTable) {
        List<Map<String, String>> trainingData = dataTable.asMaps(String.class, String.class);
        Map<String, String> trainingMap = trainingData.getFirst();

        Assertions.assertEquals(BigInteger.valueOf(Integer.parseInt(trainingMap.get("id"))), training.getId());


    }


    @Given("I create the trainer by username with the following details:")
    public void iCreateTheTrainerByUsernameWithTheFollowingDetails(DataTable dataTable) {
        List<Map<String, String>> trainerData = dataTable.asMaps(String.class, String.class);
        Map<String, String> trainerMap = trainerData.getFirst();

        trainer = new Trainer();
        trainer.setId(BigInteger.valueOf(Integer.parseInt(trainerMap.get("id"))));
        trainer.setUsername(trainerMap.get("username"));
        trainer.setFirstname(trainerMap.get("firstname"));
        trainer.setLastname(trainerMap.get("lastname"));
        trainer.setIsActive(Boolean.valueOf(trainerMap.get("isActive")));
    }

    @When("I create the trainer")
    public void iCreateTheTrainer() {
        trainerService.createTrainer(trainer);
    }


    @Then("the trainer record should include the following details:")
    public void theTrainerRecordShouldIncludeTheFollowingDetails(DataTable dataTable) {
        List<Map<String, String>> trainerData = dataTable.asMaps(String.class, String.class);
        Map<String, String> trainerMap = trainerData.getFirst();

        Assertions.assertEquals(BigInteger.valueOf(Integer.parseInt(trainerMap.get("id"))), trainer.getId());
    }

    @Given("I create the trainer with the following details:")
    public void iCreateTheTrainerWithTheFollowingDetails(DataTable dataTable) {

        List<Map<String, String>> trainerData = dataTable.asMaps(String.class, String.class);
        Map<String, String> trainerMap = trainerData.getFirst();

        trainer = new Trainer();
        trainer.setId(BigInteger.valueOf(Integer.parseInt(trainerMap.get("id"))));
        trainer.setUsername(trainerMap.get("username"));
        trainer.setFirstname(trainerMap.get("firstname"));
        trainer.setLastname(trainerMap.get("lastname"));
        trainer.setIsActive(Boolean.valueOf(trainerMap.get("isActive")));
    }

    @When("I save the trainer")
    public void iSaveTheTrainer() {
        trainerService.createTrainer(trainer);
    }

}
