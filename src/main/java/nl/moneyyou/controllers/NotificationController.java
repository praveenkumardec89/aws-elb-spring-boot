package nl.moneyyou.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import nl.moneyyou.models.MessageDetails;
import nl.moneyyou.services.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to provide an API for the notifications.
 */
@RestController
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("/api/notification")
public class NotificationController  {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private NotificationService notificationService;

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(
            value = "Post a message to a recipient",
            notes = "provide a valid email address.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Message posted"),
            @ApiResponse(code = 400, message = "Invalid request")
    })
    @ResponseStatus(HttpStatus.ACCEPTED)
    //Todo : hystrix config file
  //  @HystrixCommand(commandProperties = {@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")}, ignoreExceptions = {ResourceNotFoundException.class, ResourceGoneException.class})
    public String postMessage(@RequestBody MessageDetails messageDetails) {
        LOGGER.info("Received request to post a message to {} with payload {} ", messageDetails.getEmail(), messageDetails.getPayload());
          return notificationService.postMessage(messageDetails);
    }

}
