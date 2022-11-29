package mastermind.activities

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lf.mastermind.R
import mastermind.services.SettingsSaveStateService
import androidx.appcompat.app.AppCompatDelegate
import android.content.Intent
import android.content.res.Configuration
import android.widget.*
import java.util.*

class MainActivity : LanguageActivity() {
    private var language = "en"
    private var repeatable = "false"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val continueBtn = findViewById<Button>(R.id.main_continue)
        val newGameBtn = findViewById<Button>(R.id.main_new)
        val repeatableChk = findViewById<CheckBox>(R.id.main_repeatable)
        val darkmodeChk = findViewById<CheckBox>(R.id.main_darkmode)
        language = lastLanguage.lowercase()

        val loc : Locale = dLocale ?: Locale("en")
        if (language != loc.language){changeLanguage(language)}

        val prevRepeatable = lastRepeatable
        val prevSaveState = lastSaveState
        val darkmode = SettingsSaveStateService.isDarkmodeOn
        val rLanguageG = findViewById<RadioGroup>(R.id.main_language)
        val rDE = findViewById<RadioButton>(R.id.main_de)
        val rEN = findViewById<RadioButton>(R.id.main_english)

        val lListener = RadioGroup.OnCheckedChangeListener { _: RadioGroup?, _: Int ->
            if (rDE.isChecked) {
                language = "de"
            } else if (rEN.isChecked) {
                language = "en"
            }
            changeLanguage(language)
            persistLanguage()
        }
        rLanguageG.setOnCheckedChangeListener(null)
        when (language) {
            "de" -> rDE.isChecked = true
            "en" -> rEN.isChecked = true
        }
        rLanguageG.setOnCheckedChangeListener(lListener)
        repeatableChk.isChecked = "true" == prevRepeatable
        continueBtn.setOnClickListener { startGame(prevSaveState, prevRepeatable) }
        newGameBtn.setOnClickListener {
            persistRepeatable()
            startGame("", repeatable)
        }
        repeatableChk.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean -> repeatable = if (isChecked) "true" else "false" }
        darkmodeChk.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            SettingsSaveStateService.saveDarkmode(isChecked)
        }
        if (darkmode) {
            darkmodeChk.isChecked = true
        }
    }

    private fun startGame(save_state: String, repeat: String) {
        val intent = Intent(this, PlayActivity::class.java)
        intent.putExtra("Savestate", save_state)
        intent.putExtra("Repeateable", repeat)
        startActivity(intent)
    }

    private val lastLanguage: String
        get() = if (SettingsSaveStateService.hasLanguage()) SettingsSaveStateService.language?:language else language

    private fun persistLanguage() {
        SettingsSaveStateService.saveLanguage(language)
    }

    private fun persistRepeatable() {
        SettingsSaveStateService.saveRepeatColors(repeatable)
    }

    private val lastRepeatable: String
         get() = if (SettingsSaveStateService.hasRepeatColors()) SettingsSaveStateService.repeatColors?:repeatable else repeatable
    private val lastSaveState: String
         get() = if (SettingsSaveStateService.hasSavedGame()) SettingsSaveStateService.savedGame?:"" else ""

    private fun changeLanguage(lan: String) {
        dLocale = Locale(lan)
        restartActivity()
    }


    private fun restartActivity() {
        val intent = intent
        finish()
        startActivity(intent)
    }
}