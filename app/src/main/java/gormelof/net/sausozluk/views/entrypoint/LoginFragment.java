package gormelof.net.sausozluk.views.entrypoint;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import gormelof.net.sausozluk.R;

public class LoginFragment extends Fragment {

    public LoginFragment() {
        // Required empty public constructor
    }

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

        TextView tvGoRegister = loginView.findViewById(R.id.tv_fragment_login_go_register_page);
        TextView tvGoForgetPassword = loginView.findViewById(R.id.tv_fragment_login_go_forgot_password_page);

        tvGoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fl_activity_entry_point_fragment_container, RegisterFragment.newInstance());
                transaction.commit();
            }
        });

        tvGoForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fl_activity_entry_point_fragment_container, ForgotPasswordFragment.newInstance());
                transaction.commit();
            }
        });

        return loginView;
    }

}
