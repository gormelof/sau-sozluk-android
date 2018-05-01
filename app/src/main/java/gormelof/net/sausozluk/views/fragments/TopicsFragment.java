package gormelof.net.sausozluk.views.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import java.util.List;

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

    // log tag
    private static final String TAG = TopicsFragment.class.getSimpleName().toUpperCase();

    // fields
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView mRvTopicList;
    private View mTopicView;
    private ProgressDialog mPdLoading;
    private ProgressBar mPbLoadMore;
    private TopicAdapter mTopicAdapter;
    private SwipeRefreshLayout mSrlTopicsContainer;
    private List<Topic> topics;

    private boolean isScrolling = false;
    private int currentItems, totalItems, scrollOutItems;

    // constructor
    public TopicsFragment() {
    }

    // static constructor
    public static TopicsFragment newInstance() {
        return new TopicsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mTopicView = inflater.inflate(R.layout.fragment_topics, container, false);

        initViews();

        fetchTopicsAsync();

        return mTopicView;
    }

    /**
     * asenkron olarak başlıkları getirir
     * fragment onCreateView olayında tetiklenir
     * veriler getirilene kadar ekranda "yükleniyor..." progress'i gösterir
     */
    public void fetchTopicsAsync() {
        ApiService apiService = ServiceGenerator.createService(ApiService.class);
        Call<ApiResponse<TopicsResponse>> topicCall = apiService.getTopics(20);

        mPdLoading.setMessage("yükleniyor...");
        mPdLoading.show();

        topicCall.enqueue(new Callback<ApiResponse<TopicsResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<TopicsResponse>> call, final Response<ApiResponse<TopicsResponse>> response) {
                if (response.isSuccessful()) {
                    topics = response.body().getData().getTopics();

                    mTopicAdapter = new TopicAdapter(getContext(), topics);
                    mRvTopicList.setAdapter(mTopicAdapter);

                    initListItemClick();

                    mPdLoading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<TopicsResponse>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });

    }

    /**
     * asenkron olarak başlıkları yeniler
     */
    public void refreshTopicsAsync() {
        ApiService apiService = ServiceGenerator.createService(ApiService.class);
        Call<ApiResponse<TopicsResponse>> topicCall = apiService.getTopics(20);


        topicCall.enqueue(new Callback<ApiResponse<TopicsResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<TopicsResponse>> call, final Response<ApiResponse<TopicsResponse>> response) {
                if (response.isSuccessful()) {
                    clearTopics(mTopicAdapter);
                    topics = response.body().getData().getTopics();
                    mTopicAdapter = new TopicAdapter(getContext(), topics);
                    mRvTopicList.setAdapter(mTopicAdapter);
                    mTopicAdapter.notifyDataSetChanged();
                    initListItemClick();
                    mSrlTopicsContainer.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<TopicsResponse>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    /**
     * başlıkları temizler
     * yenileme işlemi için kullanılır
     * @param topicAdapter başlık adaptörü
     */
    public void clearTopics(TopicAdapter topicAdapter) {
        topicAdapter.clear();
    }

    /**
     * view'lar hazırlar
     */
    public void initViews() {
        initRecyclerView();
        initProgressView();
        initRefreshLayout();
        test();
    }

    /**
     * recycler view'ı hazırlar
     */
    public void initRecyclerView() {
        mRvTopicList = mTopicView.findViewById(R.id.rv_fragment_topics_topic_list);

        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvTopicList.setLayoutManager(mLinearLayoutManager);

        SeparatorDecoration decoration = new SeparatorDecoration(getContext(), LinearLayoutManager.VERTICAL, 16);
        mRvTopicList.addItemDecoration(decoration);

        mRvTopicList.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * progress view' hazırlar
     */
    public void initProgressView() {
        mPdLoading = new ProgressDialog(getContext());
        mPdLoading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mPdLoading.setIndeterminate(true);
        mPdLoading.setCancelable(true);

        mPbLoadMore = mTopicView.findViewById(R.id.rv_fragment_topics_load_more_progress);
    }

    /**
     * refresh layout'u hazırlar
     */
    public void initRefreshLayout() {
        mSrlTopicsContainer = mTopicView.findViewById(R.id.srl_fragment_topics_container);
        mSrlTopicsContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshTopicsAsync();
            }
        });
    }

    /**
     * liste elemanlarına tıklama olayını hazırlar
     */
    public void initListItemClick() {
        mRvTopicList.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), mRvTopicList,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Topic topic = topics.get(position);
                        Intent intent = new Intent(getContext(), EntriesActivity.class);
                        intent.putExtra("TOPIC_ID", topic.getId());
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // nothing
                    }

                }));
    }

    public void test() {
        mRvTopicList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                currentItems = mLinearLayoutManager.getChildCount();
                totalItems = mLinearLayoutManager.getItemCount();
                scrollOutItems = mLinearLayoutManager.findFirstCompletelyVisibleItemPosition();

                if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                    // fetch data

                    // get list last item
                    Topic lastTopic = topics.get(topics.size() - 1);

                    ApiService apiService = ServiceGenerator.createService(ApiService.class);
                    Call<ApiResponse<TopicsResponse>> topicCall = apiService.getMoreTopics(20, lastTopic.getCreatedAt());
                    mPbLoadMore.setVisibility(View.VISIBLE);
                    topicCall.enqueue(new Callback<ApiResponse<TopicsResponse>>() {
                        @Override
                        public void onResponse(Call<ApiResponse<TopicsResponse>> call, final Response<ApiResponse<TopicsResponse>> response) {
                            if (response.isSuccessful()) {
                                List<Topic> oldTopics = response.body().getData().getTopics();

                                topics.addAll(oldTopics);
                                mTopicAdapter.notifyDataSetChanged();
                                initListItemClick();
                                mPbLoadMore.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse<TopicsResponse>> call, Throwable t) {
                            Log.e(TAG, t.getMessage());
                        }
                    });

                }
            }
        });
    }

}
