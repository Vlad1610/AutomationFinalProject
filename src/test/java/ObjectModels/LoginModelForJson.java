package ObjectModels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoginModelForJson {
    private String usernameJs;
    private String passwordJs;
    private String loginErrorJs;
    private String successfulLoginMsgJs;
    private String browserName;

    @JsonCreator
    public LoginModelForJson(@JsonProperty("browserName") String browserName,
                             @JsonProperty("usernameJs") String usernameJs,
                          @JsonProperty("passwordJs") String passwordJs,
                          @JsonProperty("loginErrorJs") String loginErrorJs,
                          @JsonProperty("successfulLoginMsgJs") String successfulLoginMsgJs) {
        this.browserName = browserName;
        this.usernameJs = usernameJs;
        this.passwordJs = passwordJs;
        this.loginErrorJs = loginErrorJs;
        this.successfulLoginMsgJs = successfulLoginMsgJs;
    }
    public String getBrowserName() {
        return browserName;
    }
    public String getUsernameJs() {
        return usernameJs;
    }
    public String getPasswordJs() {
        return passwordJs;
    }
    public String getLoginErrorJs() {
        return loginErrorJs;
    }
    public String getSuccessfulLoginMsgJs() {
        return successfulLoginMsgJs;
    }
}
