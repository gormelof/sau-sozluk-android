package gormelof.net.sausozluk.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gormelof.net.sausozluk.R;

public class ChangeEmailFragment extends Fragment {

    public ChangeEmailFragment() {
        // Required empty public constructor
    }

    public static ChangeEmailFragment newInstance() {
        ChangeEmailFragment fragment = new ChangeEmailFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View changeEmailView = inflater.inflate(R.layout.fragment_change_email, container, false);

        return changeEmailView;
    }

}
