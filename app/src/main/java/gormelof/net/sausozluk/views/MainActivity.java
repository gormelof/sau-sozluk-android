package gormelof.net.sausozluk.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import gormelof.net.sausozluk.R;
import gormelof.net.sausozluk.helpers.BottomNavigationViewHelper;
import gormelof.net.sausozluk.models.ApiResponse;
import gormelof.net.sausozluk.models.topics.TopicResponse;
import gormelof.net.sausozluk.networking.ApiService;
import gormelof.net.sausozluk.networking.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName().toUpperCase();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_discover:
                    return true;
                case R.id.navigation_search:
                    return true;
                case R.id.navigation_account:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);

        ApiService apiService = ServiceGenerator.createService(ApiService.class);
        Call<ApiResponse<TopicResponse>> topicResponseCall = apiService.getTopics("20");
        topicResponseCall.enqueue(new Callback<ApiResponse<TopicResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<TopicResponse>> call, Response<ApiResponse<TopicResponse>> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "SUCCESS!");
                    Log.i(TAG, Boolean.toString(response.body().isSuccess()));
                    Log.i(TAG, Integer.toString(response.body().getData().getEntriesCount()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<TopicResponse>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
