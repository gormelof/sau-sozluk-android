package gormelof.net.sausozluk.views.ui.entrypoint;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import gormelof.net.sausozluk.R;
import gormelof.net.sausozluk.UserSession;
import gormelof.net.sausozluk.models.ApiResponse;
import gormelof.net.sausozluk.models.user.LoginRequestBody;
import gormelof.net.sausozluk.models.user.LoginResponse;
import gormelof.net.sausozluk.networking.ApiService;
import gormelof.net.sausozluk.networking.ServiceGenerator;
import gormelof.net.sausozluk.views.ui.main.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    private final static String TAG = LoginFragment.class.getSimpleName().toUpperCase();

    public LoginFragment() {}

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View loginView = inflater.inflate(R.layout.fragment_login, container, false);

        final EditText etEmail = loginView.findViewById(R.id.et_fragment_login_email);
        final EditText etPassword = loginView.findViewById(R.id.et_fragment_login_password);

        TextView tvGoRegister = loginView.findViewById(R.id.tv_fragment_login_go_register_page);
        TextView tvGoForgetPassword = loginView.findViewById(R.id.tv_fragment_login_go_forgot_password_page);

        Button btnLogin = loginView.findViewById(R.id.btn_fragment_login_login);

        // go register
        tvGoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(RegisterFragment.newInstance());
            }
        });

        // go forgot password
        tvGoForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(ForgotPasswordFragment.newInstance());
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                login(email, password);
            }
        });

        return loginView;
    }

    private void move(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_activity_entry_point_fragment_container, fragment);
        transaction.commit();
    }

    private void login(String email, String password) {
        LoginRequestBody loginRequestBody = new LoginRequestBody(email, password);

        final ProgressDialog progress = new ProgressDialog(getContext());
        progress.setMessage("Giriş Yapılıyor...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setCancelable(true);
        progress.show();

        ApiService apiService = ServiceGenerator.createService(ApiService.class);
        Call<ApiResponse<LoginResponse>> loginCall = apiService.login(loginRequestBody);

        loginCall.enqueue(new Callback<ApiResponse<LoginResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<LoginResponse>> call, Response<ApiResponse<LoginResponse>> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "SUCCESS");
                    progress.dismiss();

                    String user_id = response.body().getData().getUser_id();
                    String email = response.body().getData().getEmail();
                    String username = response.body().getData().getUsername();
                    String token = response.body().getData().getToken();

                    UserSession userSession = new UserSession(getContext());

                    userSession.createUserSession(user_id, email, username, token);

                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    getActivity().finish();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<LoginResponse>> call, Throwable t) {
                Log.i(TAG, "ERROR: " + t.getMessage());
            }
        });

    }
}
