package mastermind

import android.app.Application
import mastermind.services.SettingsSaveStateService.initialize

class MastermindApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val context = applicationContext
        initialize(context)
    }
}