package ojt.demo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * MessageError
 *
 * Version 1.0
 *
 * Date: 06-10-2021
 *
 * Copyright
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 06-10-2021	         LyNTT9           Create
 */
@Data
@Component
@ConfigurationProperties(prefix = "s")
public class MessageError {
    private String phoneMessage;
    private String notFound;
    private String updateSuccess;
    private String deleteSuccess;
    private String addSuccess;
    private String success;
    private String apiRequest;
    private String nameMessage;
    private String registerErrorMessage;
    private String userCanNotDelete;
    private String registerClassMessage;
    private String emailTail;
}
