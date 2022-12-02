package mastermind.activities

import android.content.res.Configuration
import android.view.ContextThemeWrapper
import androidx.appcompat.app.AppCompatActivity
import java.util.*

open class LanguageActivity: AppCompatActivity() {

    companion object {
        var dLocale: Locale? = null
    }

    init {
        updateConfig(this)
    }

    private fun updateConfig(wrapper: ContextThemeWrapper) {
        if(dLocale==null)  // Do nothing if dLocale is null
            return
        dLocale?.let { Locale.setDefault(it) }
        val configuration = Configuration()
        configuration.setLocale(dLocale)
        wrapper.applyOverrideConfiguration(configuration)
    }
}