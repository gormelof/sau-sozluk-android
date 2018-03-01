package gormelof.net.sausozluk.models.user;

public class LoginRequestBody {
    private String email;
    private String password;

    public LoginRequestBody(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
