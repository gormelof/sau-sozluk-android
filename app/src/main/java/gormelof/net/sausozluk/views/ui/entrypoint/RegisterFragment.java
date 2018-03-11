package gormelof.net.sausozluk.views.ui.entrypoint;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import gormelof.net.sausozluk.R;
import gormelof.net.sausozluk.models.ApiResponse;
import gormelof.net.sausozluk.models.user.RegisterRequestBody;
import gormelof.net.sausozluk.networking.ApiService;
import gormelof.net.sausozluk.networking.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {
    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View registerView = inflater.inflate(R.layout.fragment_register, container, false);

        final EditText etNick = registerView.findViewById(R.id.et_fragment_register_nick);
        final EditText etEmail = registerView.findViewById(R.id.et_fragment_register_email);
        final EditText etPassword = registerView.findViewById(R.id.et_fragment_register_password);

        TextView tvGoLogin = registerView.findViewById(R.id.tv_fragment_register_go_login_page);

        Button btnRegister = registerView.findViewById(R.id.btn_fragment_register_register);

        tvGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fl_activity_entry_point_fragment_container, LoginFragment.newInstance());
                transaction.commit();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nick = etNick.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                // check validate
                // trim vs.

                register(nick, email, password);
            }
        });


        return registerView;
    }

    private void register(String nick, String email, String password) {
        RegisterRequestBody registerRequestBody = new RegisterRequestBody(nick, email, password);

        final ProgressDialog progress = new ProgressDialog(getContext());
        progress.setMessage("Kayıt Yapılıyor...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setCancelable(true);
        progress.show();

        ApiService apiService = ServiceGenerator.createService(ApiService.class);
        Call<ApiResponse> registerCall = apiService.register(registerRequestBody);

        registerCall.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSuccess()) {
                        CharSequence successMessage = "Kayıt Başarılı!";
                        Toast.makeText(getContext(), successMessage, Toast.LENGTH_LONG).show();

                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fl_activity_entry_point_fragment_container, LoginFragment.newInstance());
                        transaction.commit();
                    } else {
                        CharSequence errorMessage = response.body().getMessage();
                        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // show error message
            }
        });
    }
}
