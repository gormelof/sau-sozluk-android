package gormelof.net.sausozluk.views;

import android.graphics.Color;
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
import gormelof.net.sausozluk.adapters.HomeAdapter;
import gormelof.net.sausozluk.helpers.SeparatorDecoration;
import gormelof.net.sausozluk.models.ApiResponse;
import gormelof.net.sausozluk.models.random.Random;
import gormelof.net.sausozluk.networking.ApiService;
import gormelof.net.sausozluk.networking.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private static final String TAG = HomeFragment.class.getSimpleName().toUpperCase();

    public HomeFragment() {}

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
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
        Call<ApiResponse<List<Random>>> randomCall = apiService.getRandoms();
        randomCall.enqueue(new Callback<ApiResponse<List<Random>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Random>>> call, Response<ApiResponse<List<Random>>> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "SUCCESS!");

                    RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_fragment_home_random_list);
                    SeparatorDecoration decoration = new SeparatorDecoration(getContext(), getResources().getColor(R.color.gray5), 0.5f);
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
            public void onFailure(Call<ApiResponse<List<Random>>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });

        return view;
    }
}
