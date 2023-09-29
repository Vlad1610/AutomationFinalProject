package ObjectModels;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoginModel {

    private String username;
    private String password;
    private String loginError;

    public LoginModel(String username, String password, String loginError) {
        this.username = username;
        this.password = password;
        this.loginError = loginError;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getLoginError() {
        return loginError;
    }
    @Override
    public String toString() {
        return "Login Data{" +
                "account={username: " + username + ", password: " + password +
                "}, login Error= '" + loginError + '\'' +
                '}';
    }
}
