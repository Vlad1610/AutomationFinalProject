package ObjectModels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RegistrationModel {
    private String browserName;
    private String registerEmailInput;
    private String absentEmailError;
    private String emailAlreadyRegisteredError;
    private String successRegistrationMessage;

    @JsonCreator()
    public RegistrationModel(@JsonProperty("browserName") String browserName,
                             @JsonProperty("registerEmailInput") String registerEmailInput, @JsonProperty("absentEmailError") String absentEmailError,
                             @JsonProperty("emailAlreadyRegisteredError") String emailAlreadyRegisteredError,
                            @JsonProperty("successRegistrationMessage") String successRegistrationMessage) {

        this.browserName = browserName;
        this.registerEmailInput = registerEmailInput;
        this.absentEmailError = absentEmailError;
        this.emailAlreadyRegisteredError = emailAlreadyRegisteredError;
        this.successRegistrationMessage = successRegistrationMessage;
    }

    public String getBrowserName() {
        return browserName;
    }
    public String getRegisterEmailInput() {
        return registerEmailInput;
    }

    public String getAbsentEmailError() {
        return absentEmailError;
    }

    public String getEmailAlreadyRegisteredError() {
        return emailAlreadyRegisteredError;
    }

    public String getSuccessRegistrationMessage() {
        return successRegistrationMessage;
    }

    @Override
    public String toString() {
        return "Registration Data{" +
                "email used: " + registerEmailInput +
                "}, absent Email Error= '" + absentEmailError + '\'' +
                ", email Already Registered Error= '" + emailAlreadyRegisteredError + '\'' +
                ", success Registration Message= '" + successRegistrationMessage + '\'' +
                '}';
    }
}
