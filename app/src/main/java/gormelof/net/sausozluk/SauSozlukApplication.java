package gormelof.net.sausozluk;

import android.app.Application;

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
