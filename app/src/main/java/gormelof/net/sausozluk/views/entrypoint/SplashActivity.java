package gormelof.net.sausozluk.views.entrypoint;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import gormelof.net.sausozluk.R;
import gormelof.net.sausozluk.views.base.BaseActivity;
import gormelof.net.sausozluk.views.main.MainActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SplashActivity extends BaseActivity {

    private static final String TAG = SplashActivity.class.getSimpleName().toUpperCase();

    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this, EntryPointActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
