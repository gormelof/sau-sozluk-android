package gormelof.net.sausozluk.views.entrypoint;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import gormelof.net.sausozluk.R;
import gormelof.net.sausozluk.views.base.BaseActivity;
import gormelof.net.sausozluk.views.main.HomeFragment;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class EntryPointActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_point);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_activity_entry_point_fragment_container, LoginFragment.newInstance());
        transaction.commit();

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
