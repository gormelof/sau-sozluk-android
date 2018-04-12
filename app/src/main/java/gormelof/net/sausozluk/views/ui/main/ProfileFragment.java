package gormelof.net.sausozluk.views.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import gormelof.net.sausozluk.R;

public class ProfileFragment extends Fragment {

    private static final String TAG = ProfileFragment.class.getSimpleName().toUpperCase();

    public ProfileFragment() {}

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View profileView = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView tvProfileUsername = profileView.findViewById(R.id.tv_fragment_profile_username);
        TextView tvProfileStatus = profileView.findViewById(R.id.tv_fragment_profile_status);
        TextView tvProfileGeneration = profileView.findViewById(R.id.tv_fragment_profile_generation);
        TextView tvEntryCount = profileView.findViewById(R.id.tv_fragment_profile_entry_count);

        TextView tvProfileChangePassword = profileView.findViewById(R.id.tv_fragment_profile_change_password);
        TextView tvProfileChangeEmail = profileView.findViewById(R.id.tv_fragment_profile_change_email);
        TextView tvProfileDestroySession = profileView.findViewById(R.id.tv_fragment_profile_destroy_session);
        TextView tvProfileDestroyAllSession = profileView.findViewById(R.id.tv_fragment_profile_destroy_all_sessions);

        tvProfileChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // todo: go change password fragment
            }
        });

        tvProfileChangeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // todo: go change email fragment
            }
        });

        tvProfileDestroySession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // todo: destroy session
            }
        });

        tvProfileDestroyAllSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // todo: destroy all sessions
            }
        });

        return profileView;
    }

}
