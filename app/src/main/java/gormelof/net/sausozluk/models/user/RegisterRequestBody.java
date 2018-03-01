package gormelof.net.sausozluk.models.user;

public class RegisterRequestBody {
    private String nick;
    private String email;
    private String password;

    public RegisterRequestBody(String nick, String email, String password) {
        this.nick = nick;
        this.email = email;
        this.password = password;
    }
}
