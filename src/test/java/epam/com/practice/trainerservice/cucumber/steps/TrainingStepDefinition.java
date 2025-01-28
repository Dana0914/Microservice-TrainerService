package epam.com.practice.trainerservice.cucumber.steps;

import epam.com.practice.trainerservice.model.Trainer;
import epam.com.practice.trainerservice.model.Training;
import epam.com.practice.trainerservice.service.TrainingService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class TrainingStepDefinition {
    private final TrainingService trainingService;
    private Training training;
    private Training foundTraining;


    public TrainingStepDefinition(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @Given("create training with the following details:")
    public void createTrainingWithTheFollowingDetails(DataTable dataTable) {
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

    @Given("the training exists with the following details:")
    public void the_training_exists_with_the_following_details(DataTable dataTable) {
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

    @When("I find the training by id {int}")
    public void iFindTheTrainingById(int arg0) {
        foundTraining = trainingService.findById(BigInteger.valueOf(arg0));
    }

    @Then("the training should include the following details:")
    public void theTrainingShouldIncludeTheFollowingDetails(DataTable dataTable) {
        List<Map<String, String>> trainingData = dataTable.asMaps(String.class, String.class);
        Map<String, String> trainingMap = trainingData.getFirst();

        Assertions.assertEquals(BigInteger.valueOf(Integer.parseInt(trainingMap.get("id"))), foundTraining.getId());

    }

    @When("I search for training with id {int}")
    public void iSearchForTrainingWithId(int arg0) {
        foundTraining = trainingService.findById(BigInteger.valueOf(arg0));
    }

    @Then("training return no result")
    public void trainingReturnNoResult() {
        Assertions.assertNull(foundTraining);
    }
}
