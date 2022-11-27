package mastermind;
import android.app.Application;
import android.content.Context;

import mastermind.services.SettingsSaveStateService;


public class MastermindApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Context context = getApplicationContext();
        SettingsSaveStateService.initialize(context);
    }
}
