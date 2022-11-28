package mastermind.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;

import com.lf.mastermind.R;
import mastermind.services.SettingsSaveStateService;

public class MainActivity extends AppCompatActivity {

    private String language = "en";
    private String repeatable = "false";
    private String lastsave = "";
    Button continue_btn, new_game_btn;
    CheckBox repeatable_chk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        continue_btn = findViewById(R.id.main_continue);
        new_game_btn = findViewById(R.id.main_new);
        repeatable_chk = findViewById(R.id.main_repeatable);

        language = getLastLanguage();
        String prevRepeatable = getLastRepeatable();
        String prevSaveState = getLastSaveState();

        if (prevRepeatable == "true"){
            repeatable_chk.setChecked(true);
        }else{
            repeatable_chk.setChecked(false);
        }


        continue_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startGame(prevSaveState, prevRepeatable);
            }
        });

        new_game_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startGame("", repeatable);
            }
        });

        repeatable_chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                repeatable = isChecked?"true":"false";
            }
        });
    }


    private void startGame(String save_state, String repeat) {

        Intent intent = new Intent(this, PlayActivity.class);
        intent.putExtra("Savestate", save_state);
        intent.putExtra("Repeateable", repeat);
        startActivity(intent);
    }
    private void startGame(String save_state){
        startGame(save_state, "");
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
                ? SettingsSaveStateService.getSavedGame() : lastsave;
    }
}
