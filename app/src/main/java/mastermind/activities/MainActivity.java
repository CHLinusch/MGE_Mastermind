package mastermind.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.lf.mastermind.R;

import java.util.Locale;

import mastermind.services.SettingsSaveStateService;

public class MainActivity extends AppCompatActivity {

    private String language = "en";
    private String repeatable = "false";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button continue_btn = findViewById(R.id.main_continue);
        Button new_game_btn = findViewById(R.id.main_new);
        CheckBox repeatable_chk = findViewById(R.id.main_repeatable);
        CheckBox darkmode_chk = findViewById(R.id.main_darkmode);


        language = getLastLanguage().toLowerCase();
        checkLocale(language);

        String prevRepeatable = getLastRepeatable();
        String prevSaveState = getLastSaveState();


        boolean darkmode = SettingsSaveStateService.isDarkmodeOn();

        RadioGroup rLanguageG = findViewById(R.id.main_language);
        RadioButton rDE = findViewById(R.id.main_de);
        RadioButton rEN = findViewById(R.id.main_english);

        RadioGroup.OnCheckedChangeListener lListener = (group, checkedId) -> {
            if (rDE.isChecked()){
                language = "de";

            }else if(rEN.isChecked()){
                language = "en";
            }
            changeLanguage(language);
            persistLanguage();
        };

        rLanguageG.setOnCheckedChangeListener(null);
        switch(language) {
            case "de":
                rDE.setChecked(true);
                break;
            case "en":
                rEN.setChecked(true);
                break;
        }
        rLanguageG.setOnCheckedChangeListener(lListener);
        repeatable_chk.setChecked("true".equals(prevRepeatable));

        continue_btn.setOnClickListener(v -> startGame(prevSaveState, prevRepeatable));

        new_game_btn.setOnClickListener(v -> {
            persistRepeatable();
            startGame("", repeatable);
        });

        repeatable_chk.setOnCheckedChangeListener((buttonView, isChecked) -> repeatable = isChecked?"true":"false");

        darkmode_chk.setOnCheckedChangeListener((buttonView, isChecked) ->{
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            SettingsSaveStateService.saveDarkmode(isChecked);
        });

        if(darkmode){
            darkmode_chk.setChecked(true);
        }
    }



    private void startGame(String save_state, String repeat) {

        Intent intent = new Intent(this, PlayActivity.class);
        intent.putExtra("Savestate", save_state);
        intent.putExtra("Repeateable", repeat);
        startActivity(intent);
    }

    private String getLastLanguage() {
        return SettingsSaveStateService.hasLanguage()
                ? SettingsSaveStateService.getLanguage() : language;
    }

    private void persistLanguage(){
        SettingsSaveStateService.saveLanguage(language);
    }

    private void persistRepeatable(){
        SettingsSaveStateService.saveRepeatColors(repeatable);
    }

    private String getLastRepeatable() {
        return SettingsSaveStateService.hasRepeatColors()
                ? SettingsSaveStateService.getRepeatColors() : repeatable;
    }

    private String getLastSaveState() {
        return SettingsSaveStateService.hasSavedGame()
                ? SettingsSaveStateService.getSavedGame() : "";
    }
    private void changeLanguage(String lan){
            Resources resources = getResources();
            Configuration config = resources.getConfiguration();
            config.setLocale(new Locale(lan.toLowerCase()));
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            restartActivity();
        }
    private void checkLocale(String language) {
        Locale locale = getResources().getConfiguration().locale;
        if (!locale.getLanguage().equals(language)){
            changeLanguage(language);
        }

    }

    private void restartActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
