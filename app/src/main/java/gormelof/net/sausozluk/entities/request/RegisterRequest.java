package gormelof.net.sausozluk.entities.request;

public class RegisterRequest {

    private String nick;
    private String email;
    private String password;

    public RegisterRequest(String nick, String email, String password) {
        this.nick = nick;
        this.email = email;
        this.password = password;
    }

}
