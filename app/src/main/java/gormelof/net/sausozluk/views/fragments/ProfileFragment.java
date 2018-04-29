package gormelof.net.sausozluk.views.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import gormelof.net.sausozluk.R;
import gormelof.net.sausozluk.UserSession;
import gormelof.net.sausozluk.api.ApiService;
import gormelof.net.sausozluk.api.ServiceGenerator;
import gormelof.net.sausozluk.entities.response.api.ApiResponse;
import gormelof.net.sausozluk.entities.response.profile.ProfileResponse;
import gormelof.net.sausozluk.views.activities.EntryPointActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        final TextView tvProfileUsername = profileView.findViewById(R.id.tv_fragment_profile_username);
        final TextView tvProfileStatus = profileView.findViewById(R.id.tv_fragment_profile_status);
        final TextView tvProfileGeneration = profileView.findViewById(R.id.tv_fragment_profile_generation);
        final TextView tvEntryCount = profileView.findViewById(R.id.tv_fragment_profile_entry_count);

        TextView tvProfileChangePassword = profileView.findViewById(R.id.tv_fragment_profile_change_password);
        TextView tvProfileChangeEmail = profileView.findViewById(R.id.tv_fragment_profile_change_email);
        TextView tvProfileDestroySession = profileView.findViewById(R.id.tv_fragment_profile_destroy_session);
        TextView tvProfileDestroyAllSession = profileView.findViewById(R.id.tv_fragment_profile_destroy_all_sessions);

        final UserSession userSession = new UserSession(getContext());

        ApiService apiService = ServiceGenerator.createService(ApiService.class);
        Call<ApiResponse<ProfileResponse>> profile = apiService.getProfile(
                userSession.getUserDetails().get(UserSession.KEY_USERNAME_SLUG));

        final ProgressDialog progress = new ProgressDialog(getContext());
        progress.setMessage("yükleniyor...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setCancelable(true);
        progress.show();

        profile.enqueue(new Callback<ApiResponse<ProfileResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<ProfileResponse>> call, Response<ApiResponse<ProfileResponse>> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSuccess()) {
                        progress.dismiss();
                        ProfileResponse profileResponse = response.body().getData();

                        tvProfileUsername.setText(profileResponse.getUsername());
                        tvProfileStatus.setText(profileResponse.getStatus());
                        tvProfileGeneration.setText(profileResponse.getGeneration() + ". nesil");
                        tvEntryCount.setText(profileResponse.getEntryCount().toString() + " entry");
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<ProfileResponse>> call, Throwable t) {
                // error
            }
        });

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
                ApiService apiService = ServiceGenerator.createService(ApiService.class);
                Call<ApiResponse> logout = apiService.logout(userSession.getToken());
                logout.enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body().isSuccess()) {
                                clearSession();
                                goEntryPoingActity();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        // error
                    }
                });
            }
        });

        tvProfileDestroyAllSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService apiService = ServiceGenerator.createService(ApiService.class);
                Call<ApiResponse> logout = apiService.exit(userSession.getToken(),
                        userSession.getUserDetails().get(UserSession.KEY_USERNAME_SLUG));
                logout.enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body().isSuccess()) {
                                clearSession();
                                goEntryPoingActity();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        // error
                    }
                });
            }
        });

        return profileView;
    }

    private void goEntryPoingActity() {
        Intent intent = new Intent(getContext(), EntryPointActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(intent);
        getActivity().finish();
    }

    private void clearSession() {
        UserSession userSession = new UserSession(getContext());
        userSession.clearUserData();
    }

}
