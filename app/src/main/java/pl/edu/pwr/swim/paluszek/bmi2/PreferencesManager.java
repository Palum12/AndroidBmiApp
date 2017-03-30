package pl.edu.pwr.swim.paluszek.bmi2;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.RadioButton;

/**
 * Created by Maksymilian on 30.03.2017.
 */

public abstract class PreferencesManager {


    public static boolean saveMassAndHeightInputs(Context AppContext ,Float mass, Float height){
        savePreferencesFloat(AppContext, "Mass", mass);
        savePreferencesFloat(AppContext, "Height", height);
        return true;
    }

    public static void savePreferencesFloat(Context appContext, String key, float value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(appContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    public static void savePreferencesBoolean(Context appContext, String key, boolean value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(appContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }


    public static void loadSavedMassAndHeight (Context appContext, EditText editTextMass, EditText editTextHeight) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(appContext);
        float massValue = sharedPreferences.getFloat("Mass", 0.0f);
        float heightValue = sharedPreferences.getFloat("Height", 0.0f);
        editTextMass.setText(Float.toString(massValue));
        editTextHeight.setText(Float.toString(heightValue));
    }

    public static void loadRadioButtonState (Context appContext, RadioButton UnitMkgRB, RadioButton UnitMkgIbFt) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(appContext);
        boolean isMkg = sharedPreferences.getBoolean("isMkg", true);
        if (isMkg) {
            UnitMkgRB.setChecked(true);
            UnitMkgIbFt.setChecked(false);
        }
        else {
            UnitMkgRB.setChecked(false);
            UnitMkgIbFt.setChecked(true);
        }

    }

    public static void saveRadioButtonState (Context appContext, boolean isMkg) {
        savePreferencesBoolean(appContext, "isMkg", isMkg);
    }
}
