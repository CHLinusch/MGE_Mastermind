package mastermind.activities;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mastermind.R;

import mastermind.services.SettingsSaveStateService;

public class MainActivity extends AppCompatActivity {

    private String language = "en";
    private String repeatable = "false";
    private String lastsave = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        language = getLastLanguage();
        String prevRepeatable = getLastRepeatable();
        String prevSaveState = getLastSaveState();


    }

    private String getLastLanguage() {
        return SettingsSaveStateService.hasLanguage()
                ? SettingsSaveStateService.getLanguage() : language;
    }

    private String getLastRepeatable() {
        return SettingsSaveStateService.hasRepeatColors()
                ? SettingsSaveStateService.getRepeatColors() : repeatable;
    }

    private String getLastSaveState() {
        return SettingsSaveStateService.hasSavedGame()
                ? SettingsSaveStateService.getSavedGame() : lastsave;
    }
}
