package epam.com.practice.trainerservice.cucumber.steps;

import epam.com.practice.trainerservice.handler.exceptions.ResourceNotFoundException;
import epam.com.practice.trainerservice.model.Trainer;
import epam.com.practice.trainerservice.service.TrainerService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class TrainerStepDefinition {
    private final TrainerService trainerService;
    Trainer trainer;
    Trainer foundTrainer;

    public TrainerStepDefinition(TrainerService trainerService) {
        this.trainerService = trainerService;

    }

    @Given("a trainer exists with the following details:")
    public void a_trainer_exists_with_the_following_details(DataTable dataTable) {
        List<Map<String, String>> trainerData = dataTable.asMaps(String.class, String.class);
        Map<String, String> trainerMap = trainerData.getFirst();

        trainer = new Trainer();
        trainer.setId(BigInteger.valueOf(Integer.parseInt(trainerMap.get("id"))));
        trainer.setUsername(trainerMap.get("username"));
        trainer.setFirstname(trainerMap.get("firstname"));
        trainer.setLastname(trainerMap.get("lastname"));
        trainer.setIsActive(Boolean.valueOf(trainerMap.get("isActive")));

        trainerService.createTrainer(trainer);

    }

    @Given("create trainer with the following details:")
    public void createTrainerWithTheFollowingDetails(DataTable dataTable) {
        List<Map<String, String>> trainerData = dataTable.asMaps(String.class, String.class);
        Map<String, String> trainerMap = trainerData.getFirst();

        trainer = new Trainer();
        trainer.setId(BigInteger.valueOf(Integer.parseInt(trainerMap.get("id"))));
        trainer.setUsername(trainerMap.get("username"));
        trainer.setFirstname(trainerMap.get("firstname"));
        trainer.setLastname(trainerMap.get("lastname"));
        trainer.setIsActive(Boolean.valueOf(trainerMap.get("isActive")));

        trainerService.createTrainer(trainer);

    }


    @When("I find the trainer by id {int}")
    public void i_find_the_trainer_by_id(Integer int1) {
        foundTrainer = trainerService.findById(BigInteger.valueOf(int1)).orElse(null);

    }

    @When("I find the trainer by username {string}")
    public void i_find_the_trainer_by_username(String arg0) {
        foundTrainer = trainerService.findTrainerByUsername(arg0).orElseThrow();

    }

    @Then("the response should include the following details:")
    public void the_response_should_include_the_following_details(DataTable dataTable) {
        List<Map<String, String>> trainerData = dataTable.asMaps(String.class, String.class);
        Map<String, String> trainerMap = trainerData.getFirst();

        Assertions.assertEquals(BigInteger.valueOf(Integer.parseInt(trainerMap.get("id"))), foundTrainer.getId());
        Assertions.assertEquals(trainerMap.get("username"), foundTrainer.getUsername());

    }

    @When("I search for trainer with id {int}")
    public void iSearchForTrainerWithId(int arg0)  {
        foundTrainer = trainerService.findById(BigInteger.valueOf(arg0)).orElse(null);
    }

    @When("I search for trainer with username {string}")
    public void iSearchForTrainerWithUsername(String arg0) {
        foundTrainer = trainerService.findTrainerByUsername(arg0).orElse(null);
    }

    @Then("return no result")
    public void returnNoResult() {
        Assertions.assertNull(foundTrainer);
    }

}