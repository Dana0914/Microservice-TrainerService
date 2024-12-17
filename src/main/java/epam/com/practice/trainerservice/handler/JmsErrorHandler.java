package epam.com.practice.trainerservice.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

@Component
public class JmsErrorHandler implements ErrorHandler {
    private static final Logger logger = LoggerFactory.getLogger(JmsErrorHandler.class);

    @Override
    public void handleError(Throwable t) {
        switch (t) {
            case jakarta.jms.JMSException jmsException ->
                    logger.error("JMSException occurred: {}", t.getMessage(), t);
            case com.fasterxml.jackson.core.JsonProcessingException jsonProcessingException ->
                    logger.error("JSON Processing error: {}", t.getMessage(), t);
            case IllegalArgumentException illegalArgumentException ->
                    logger.warn("Illegal argument in message processing: {}", t.getMessage());
            default ->
                    logger.error("Unexpected error occurred during JMS message processing: {}", t.getMessage(), t);
        }

    }
}
