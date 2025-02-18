package epam.com.practice.trainerservice.jms;


import com.fasterxml.jackson.databind.ObjectMapper;
import epam.com.practice.trainerservice.dto.TrainingDTO;
import epam.com.practice.trainerservice.service.TrainingSessionProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class TrainingSessionConsumerService {

    private final JmsTemplate jmsTemplate;
    private final TrainingSessionProcessingService trainerWorkloadService;
    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(TrainingSessionConsumerService.class);


    public TrainingSessionConsumerService(JmsTemplate jmsTemplate,
                                          TrainingSessionProcessingService trainerWorkloadService,
                                          ObjectMapper objectMapper) {

        this.jmsTemplate = jmsTemplate;
        this.trainerWorkloadService = trainerWorkloadService;
        this.objectMapper = objectMapper;
    }

    @JmsListener(destination = "trainingDTO queue", containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(String message) {
        try {
            TrainingDTO trainingDTO = objectMapper.readValue(message, TrainingDTO.class);
            logger.info("Received Message: {}", message);

            trainerWorkloadService.updateTrainingRecordIfTrainerExists(trainingDTO);

        } catch (Exception e) {
            logger.error("Error while receiving message {}", e.getMessage());
        }
    }
}
