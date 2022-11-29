package mastermind.services

import android.content.Context
import android.content.SharedPreferences
import mastermind.services.SettingsSaveStateService

object SettingsSaveStateService {
    private const val FILE_NAME = "mastermind.preferences"
    private const val SAVESTATE_KEY = "save"
    private const val LANGUAGE = "language"
    private const val REPEAT_COLORS = "repeatable"
    private const val EMPTY = "none"
    private const val DARKMODE = "darkmode"
    private var preferences: SharedPreferences? = null
    @JvmStatic
    fun initialize(context: Context) {
        preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    }

    fun hasSavedGame(): Boolean {
        val savestate = savedGame
        return savestate != EMPTY
    }

    val savedGame: String?
        get() = preferences!!.getString(SAVESTATE_KEY, EMPTY)

    fun saveGame(gamestate: String?) {
        preferences!!.edit().putString(SAVESTATE_KEY, gamestate).apply()
    }

    fun hasLanguage(): Boolean {
        val language = language
        return language != EMPTY
    }

    val language: String?
        get() = preferences!!.getString(LANGUAGE, EMPTY)

    fun saveLanguage(language: String?) {
        preferences!!.edit().putString(LANGUAGE, language).apply()
    }

    fun hasRepeatColors(): Boolean {
        val repeatable = repeatColors
        return repeatable != EMPTY
    }

    val repeatColors: String?
        get() = preferences!!.getString(REPEAT_COLORS, EMPTY)

    fun saveRepeatColors(repeatable: String?) {
        preferences!!.edit().putString(REPEAT_COLORS, repeatable).apply()
    }

    fun saveGame(savestring: String?, repeatable: String?) {
        saveGame(savestring)
        saveRepeatColors(repeatable)
    }

    val isDarkmodeOn: Boolean
        get() = "true" == preferences!!.getString(DARKMODE, EMPTY)

    fun saveDarkmode(isDarkmode: Boolean) {
        preferences!!.edit().putString(DARKMODE, if (isDarkmode) "true" else "false").apply()
    }
}