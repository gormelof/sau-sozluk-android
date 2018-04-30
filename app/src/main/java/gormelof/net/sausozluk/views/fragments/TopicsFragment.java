package gormelof.net.sausozluk.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import gormelof.net.sausozluk.R;
import gormelof.net.sausozluk.api.ApiService;
import gormelof.net.sausozluk.api.ServiceGenerator;
import gormelof.net.sausozluk.entities.Topic;
import gormelof.net.sausozluk.entities.response.api.ApiResponse;
import gormelof.net.sausozluk.entities.response.topics.TopicsResponse;
import gormelof.net.sausozluk.helpers.SeparatorDecoration;
import gormelof.net.sausozluk.views.activities.EntriesActivity;
import gormelof.net.sausozluk.views.adapters.TopicAdapter;
import gormelof.net.sausozluk.listeners.RecyclerItemClickListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopicsFragment extends Fragment {
    private static final String TAG = TopicsFragment.class.getSimpleName().toUpperCase();

    public TopicsFragment() {}

    public static TopicsFragment newInstance() {
        TopicsFragment fragment = new TopicsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View topicView = inflater.inflate(R.layout.fragment_discover, container, false);

        ApiService apiService = ServiceGenerator.createService(ApiService.class);
        Call<ApiResponse<TopicsResponse>> topicCall = apiService.getTopics(20);

        topicCall.enqueue(new Callback<ApiResponse<TopicsResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<TopicsResponse>> call, final Response<ApiResponse<TopicsResponse>> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "SUCCESS!");

                    RecyclerView recyclerView = (RecyclerView) topicView.findViewById(R.id.rv_fragment_discover_topic_list);
                    SeparatorDecoration decoration = new SeparatorDecoration(getContext(), getResources().getColor(R.color.grey300), 0.5f);
                    TopicAdapter topicAdapter = new TopicAdapter(getContext(), response.body().getData().getTopics());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.addItemDecoration(decoration);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(topicAdapter);

                    recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Log.i(TAG, "ITEM CLICKED");
                            Topic topic = response.body().getData().getTopics().get(position);

                            Intent intent = new Intent(getContext(), EntriesActivity.class);
                            intent.putExtra("TOPIC_ID", topic.getId());
                            startActivity(intent);
                        }

                        @Override
                        public void onLongItemClick(View view, int position) {

                        }

                    }));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<TopicsResponse>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });

        return topicView;
    }
}
