package gormelof.net.sausozluk.views.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import gormelof.net.sausozluk.R;
import gormelof.net.sausozluk.UserSession;
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
                UserSession userSession = new UserSession(getApplicationContext());
                Intent intent = new Intent(SplashActivity.this, EntryPointActivity.class);
                if (userSession.isUserLoggedIn()) {
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish();
                /*
                Intent mainIntent = new Intent(SplashActivity.this, EntryPointActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                */
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
