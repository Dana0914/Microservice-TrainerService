package epam.com.practice.trainerservice.cucumber.steps;

import epam.com.practice.trainerservice.model.ActionType;
import epam.com.practice.trainerservice.model.Trainer;
import epam.com.practice.trainerservice.model.Training;
import epam.com.practice.trainerservice.model.TrainingSummary;
import epam.com.practice.trainerservice.service.TrainingSummaryService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TrainingSummaryStepDefinition {
    private final TrainingSummaryService trainingSummaryService;
    private final MongoTemplate mongoTemplate;
    private TrainingSummary trainingSummary;
    private TrainingSummary foundTrainingSummary;


    public TrainingSummaryStepDefinition(TrainingSummaryService trainingSummaryService,
                                         MongoTemplate mongoTemplate) {
        this.trainingSummaryService = trainingSummaryService;
        this.mongoTemplate = mongoTemplate;
    }

    @Given("the training summary exists with the following details:")
    public void theTrainingSummaryExistsWithTheFollowingDetails(DataTable dataTable) {
        Trainer trainer = new Trainer();
        trainer.setId(BigInteger.ONE);
        trainer.setUsername("John Doe");
        trainer.setFirstname("John");
        trainer.setLastname("Doe");
        trainer.setIsActive(true);

        Training training = new Training();
        training.setId(BigInteger.ONE);
        training.setActionType(ActionType.ADD.name());
        training.setDate(LocalDate.now());
        training.setDuration(2);
        training.setTrainer(trainer);
        List<Map<String, String>> trainingData = dataTable.asMaps(String.class, String.class);
        Map<String, String> trainingMap = trainingData.getFirst();

        trainingSummary = new TrainingSummary();
        trainingSummary.setTraining(training);
        trainingSummary.setYear(Integer.valueOf(trainingMap.get("year")));
        trainingSummary.setMonth(Integer.valueOf(trainingMap.get("month")));
        trainingSummary.setId(BigInteger.valueOf(Integer.parseInt(trainingMap.get("id"))));
        trainingSummary.setTrainingSummaryDuration(Integer.valueOf(trainingMap.get("trainingSummaryDuration") + training.getDuration()));

        trainingSummaryService.createTrainingSummary(trainingSummary);

    }

    @Given("create training summary with the following details:")
    public void createTrainingSummaryWithTheFollowingDetails(DataTable dataTable) {
        Trainer trainer = new Trainer();
        trainer.setId(BigInteger.ONE);
        trainer.setUsername("John Doe");
        trainer.setFirstname("John");
        trainer.setLastname("Doe");
        trainer.setIsActive(true);

        Training training = new Training();
        training.setId(BigInteger.ONE);
        training.setActionType(ActionType.ADD.name());
        training.setDate(LocalDate.now());
        training.setDuration(2);
        training.setTrainer(trainer);

        List<Map<String, String>> trainingData = dataTable.asMaps(String.class, String.class);
        Map<String, String> trainingMap = trainingData.getFirst();

        trainingSummary = new TrainingSummary();
        trainingSummary.setTraining(training);
        trainingSummary.setYear(Integer.valueOf(trainingMap.get("year")));
        trainingSummary.setMonth(Integer.valueOf(trainingMap.get("month")));
        trainingSummary.setId(BigInteger.valueOf(Integer.parseInt(trainingMap.get("id"))));
        trainingSummary.setTrainingSummaryDuration(Integer.valueOf(trainingMap.get("trainingSummaryDuration")));

        trainingSummaryService.createTrainingSummary(trainingSummary);
    }

    @When("I update the training summary by id {int}")
    public void i_Update_The_Training_Summary_By_Id(Integer id) {
        trainingSummaryService.updateTrainingSummary(BigInteger.valueOf(id), trainingSummary);

    }
    @When("I find the training by year {int} and month {int}")
    public void iFindTheTrainingByYearAndMonth(int arg0, int arg1) {
        foundTrainingSummary = trainingSummaryService.findTrainingSummaryByYearAndMonth(arg0, arg1).orElseThrow();

    }
    @When("I find the training summary by id {int}")
    public void iFindTheTrainingSummaryById(int arg0) {
        foundTrainingSummary = trainingSummaryService.findTrainingSummaryById(BigInteger.valueOf(arg0));
    }

    @Then("the updated training summary should include the following details:")
    public void theTrainingSummaryShouldIncludeTheFollowingDetails(DataTable dataTable) {
        List<Map<String, String>> trainingData = dataTable.asMaps(String.class, String.class);
        Map<String, String> trainingMap = trainingData.getFirst();

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(trainingMap.get("id")));

        TrainingSummary summary = mongoTemplate.findOne(query, TrainingSummary.class);

        Assertions.assertEquals(BigInteger.valueOf(Integer.parseInt(trainingMap.get("id"))),
                summary.getId());

    }

    @Then("the training summary should return the following details:")
    public void theTrainingSummaryShouldReturnTheFollowingDetails(DataTable dataTable) {
        List<Map<String, String>> trainingData = dataTable.asMaps(String.class, String.class);
        Map<String, String> trainingMap = trainingData.getFirst();

        Assertions.assertEquals(Integer.parseInt(trainingMap.get("year")), foundTrainingSummary.getYear());
        Assertions.assertEquals(Integer.parseInt(trainingMap.get("month")), foundTrainingSummary.getMonth());
        Assertions.assertEquals(BigInteger.valueOf(Integer.parseInt(trainingMap.get("id"))),
                foundTrainingSummary.getId());
    }

    @When("I search for training summary with year {int} and month {int}")
    public void iSearchForTrainingSummaryWithYearAndMonth(int arg0, int arg1) {
        foundTrainingSummary = trainingSummaryService.findTrainingSummaryByYearAndMonth(arg0, arg1).orElse(null);
    }

    @Then("training summary should return no result")
    public void trainingSummaryShouldReturnNoResult() {
        Assertions.assertNull(foundTrainingSummary);

    }
}
