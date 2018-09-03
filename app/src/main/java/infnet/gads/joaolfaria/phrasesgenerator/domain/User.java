package infnet.gads.joaolfaria.phrasesgenerator.domain;

public class User {

    private String Name;

    private String Email;
    private String Password;
    private String ConfirmPassword;
    private String CPF;


    public User(){

    }

    public User(String name, String email, String password, String confirmPassword, String CPF) {
        Name = name;
        Email = email;
        Password = password;
        ConfirmPassword = confirmPassword;
        this.CPF = CPF;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
}
