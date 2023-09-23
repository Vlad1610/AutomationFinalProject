package ObjectModels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
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
                             @JsonProperty("registerEmailInput") String registerEmailInput, @JsonProperty("absentEmailError") String absentEmailError, @JsonProperty("emailAlreadyRegisteredError") String emailAlreadyRegisteredError,
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
    @XmlElement
    public void setRegisterEmailInput(String registerEmailInput) {
        this.registerEmailInput = registerEmailInput;
    }
    public String getAbsentEmailError() {
        return absentEmailError;
    }
    @XmlElement
    public void setAbsentEmailError(String absentEmailError) {
        this.absentEmailError = absentEmailError;
    }
    public String getEmailAlreadyRegisteredError() {
        return emailAlreadyRegisteredError;
    }
    @XmlElement
    public void setEmailAlreadyRegisteredError(String emailAlreadyRegisteredError) {
        this.emailAlreadyRegisteredError = emailAlreadyRegisteredError;
    }
    public String getSuccessRegistrationMessage() {
        return successRegistrationMessage;
    }
    @XmlElement
    public void setSuccessRegistrationMessage(String successRegistrationMessage) {
        this.successRegistrationMessage = successRegistrationMessage;
    }

    @Override
    public String toString() {
        return "Registration Data{" +
                "email used: " + registerEmailInput +
                "}, absentEmailError= '" + absentEmailError + '\'' +
                ", emailAlreadyRegisteredError= '" + emailAlreadyRegisteredError + '\'' +
                ", successRegisterMessage= '" + successRegistrationMessage + '\'' +
                '}';
    }
}
