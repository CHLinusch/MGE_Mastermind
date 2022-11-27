package mastermind.services;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingsSaveStateService {
    private static final String FILE_NAME = "mastermind.preferences";
    private static final String SAVESTATE_KEY = "save";
    private static final String LANGUAGE = "language";
    private static final String REPEAT_COLORS = "repeatable";
    private static final String EMPTY = "none";
    private static SharedPreferences preferences;

    public static void initialize(Context context) {
        preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public static boolean hasSavedGame() {
        String savestate = getSavedGame();
        return !savestate.equals(EMPTY);
    }

    public static String getSavedGame() {
        return preferences.getString(SAVESTATE_KEY, EMPTY);
    }

    public static void saveGame(String gamestate) {
        preferences.edit().putString(SAVESTATE_KEY, gamestate).apply();
    }
    public static boolean hasLanguage() {
        String language = getLanguage();
        return !language.equals(EMPTY);
    }

    public static String getLanguage() {
        return preferences.getString(LANGUAGE, EMPTY);
    }

    public static void saveLanguage(String language) {
        preferences.edit().putString(LANGUAGE, language).apply();
    }
    public static boolean hasRepeatColors() {
        String repeatable = getRepeatColors();
        return !repeatable.equals(EMPTY);
    }

    public static String getRepeatColors() {
        return preferences.getString(REPEAT_COLORS, EMPTY);
    }

    public static void saveRepeatColors(String repeatable) {
        preferences.edit().putString(REPEAT_COLORS, repeatable).apply();
    }


}
