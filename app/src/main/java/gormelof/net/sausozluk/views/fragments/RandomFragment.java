package gormelof.net.sausozluk.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import gormelof.net.sausozluk.R;
import gormelof.net.sausozluk.entities.response.api.ApiResponse;
import gormelof.net.sausozluk.entities.response.random.RandomResponse;
import gormelof.net.sausozluk.views.adapters.HomeAdapter;
import gormelof.net.sausozluk.helpers.SeparatorDecoration;
import gormelof.net.sausozluk.api.ApiService;
import gormelof.net.sausozluk.api.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RandomFragment extends Fragment {

    private static final String TAG = RandomFragment.class.getSimpleName().toUpperCase();

    public RandomFragment() {}

    public static RandomFragment newInstance() {
        RandomFragment fragment = new RandomFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        ApiService apiService = ServiceGenerator.createService(ApiService.class);
        Call<ApiResponse<List<RandomResponse>>> randomCall = apiService.getRandom();
        randomCall.enqueue(new Callback<ApiResponse<List<RandomResponse>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<RandomResponse>>> call, Response<ApiResponse<List<RandomResponse>>> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "SUCCESS!");

                    RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_fragment_home_random_list);
                    SeparatorDecoration decoration = new SeparatorDecoration(getContext(), LinearLayoutManager.VERTICAL, 16);
                    HomeAdapter homeAdapter = new HomeAdapter(getContext(), response.body().getData());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.addItemDecoration(decoration);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(homeAdapter);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<RandomResponse>>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });

        return view;
    }
}
