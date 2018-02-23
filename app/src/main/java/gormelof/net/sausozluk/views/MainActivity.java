package gormelof.net.sausozluk.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        selectedFragment = HomeFragment.newInstance();
                        return true;
                    case R.id.navigation_discover:
                        selectedFragment = HomeFragment.newInstance();
                        return true;
                    case R.id.navigation_search:
                        selectedFragment = HomeFragment.newInstance();
                        return true;
                    case R.id.navigation_account:
                        selectedFragment = HomeFragment.newInstance();
                        return true;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fl_activity_main_fragment_container, selectedFragment);
                transaction.commit();
                return true;
            }
        });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_activity_main_fragment_container, HomeFragment.newInstance());
        transaction.commit();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
