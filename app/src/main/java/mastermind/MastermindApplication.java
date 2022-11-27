package mastermind;
import android.app.Application;
import android.content.Context;
//import ClassA


public class MastermindApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Context context = getApplicationContext();
        //ClassA.initialize(context)
    }
}
