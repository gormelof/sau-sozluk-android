package gormelof.net.sausozluk.views.entrypoint;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import gormelof.net.sausozluk.R;

public class ForgotPasswordFragment extends Fragment {
    public ForgotPasswordFragment() {
        // Required empty public constructor
    }

    public static ForgotPasswordFragment newInstance() {
        ForgotPasswordFragment fragment = new ForgotPasswordFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View forgotPasswordView = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        TextView tvGoLogin = forgotPasswordView.findViewById(R.id.tv_fragment_forgot_password_go_login_page);

        tvGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fl_activity_entry_point_fragment_container, LoginFragment.newInstance());
                transaction.commit();
            }
        });

        return forgotPasswordView;
    }
}
