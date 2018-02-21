package gormelof.net.sausozluk.application;

import android.app.Application;

import gormelof.net.sausozluk.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class SauSozlukApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source_sans_pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

}
