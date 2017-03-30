package pl.edu.pwr.swim.paluszek.bmi2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R2.id.heightET)
    EditText editTextHeight;
    @BindView(R2.id.massET)
    EditText editTextMass;
    @BindView(R2.id.resultTV)
    TextView resultTextView;
    @BindView(R2.id.countBT)
    Button countButton;
    @BindView(R2.id.changeUnitToMKG)
    RadioButton UnitMkgRB;
    @BindView(R2.id.changeUnitToIBFT)
    RadioButton UnitIBFTRB;



    private boolean isMkgActive = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R2.id.countBT)
    public void countButtonClick(View v){
        IBmiCalc bmiCalc;
        float mass = 0f;
        float height = 0f;
        float bmi = 0f;
        if (isDataCorrect()) {
            mass = Float.parseFloat(editTextMass.getText().toString());
            height = Float.parseFloat(editTextHeight.getText().toString());

            if (UnitMkgRB.isChecked()) {
                bmiCalc = new BmiCalcForKgM();
                bmi = bmiCalc.countBmi(mass, height);
            }
            else if (UnitIBFTRB.isChecked()) {
                bmiCalc = new BmiCalcForIbFt();
                bmi = bmiCalc.countBmi(mass, height);
            }

            InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            resultTextView.setText(String.format("%.2f", bmi));
            resultTextView.setTextColor(BmiCalcForKgM.colorBmi(bmi));
        } else {
            resultTextView.setText("");
            Toast.makeText(getApplicationContext(), "Enter correct data !", Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R2.id.changeUnitToMKG)
    public void countMkg(View v){
        if (!isMkgActive && isDataEntered()){
            float massValue = Float.valueOf(editTextMass.getText().toString());
            massValue *= 0.4536f;
            float heightValue = Float.valueOf(editTextHeight.getText().toString());
            heightValue *= 0.3048f;
            editTextMass.setText(Float.toString(massValue));
            editTextHeight.setText(Float.toString(heightValue));
            isMkgActive = true;
        }
    }

    @OnClick(R2.id.changeUnitToIBFT)
    public void countIbFt(View v){
        if (isMkgActive && isDataEntered()){
            float massValue = Float.valueOf(editTextMass.getText().toString());
            massValue /= 0.4536f;
            float heightValue = Float.valueOf(editTextHeight.getText().toString());
            heightValue *= 3.28f;
            editTextMass.setText(Float.toString(massValue));
            editTextHeight.setText(Float.toString(heightValue));
            isMkgActive = false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.MI_Save) {
            Toast.makeText(getApplicationContext(), "I work", Toast.LENGTH_LONG).show();
            if(isDataEntered()){
                PreferencesManager.saveMassAndHeightInputs(this,
                        Float.valueOf(editTextMass.getText().toString()),
                        Float.valueOf(editTextHeight.getText().toString()));
                PreferencesManager.saveRadioButtonState(this, isMkgActive);
            }
            return true;
        }
        else if(id == R.id.MI_Restore){
            PreferencesManager.loadSavedMassAndHeight(this, editTextMass, editTextHeight);
            PreferencesManager.loadRadioButtonState(this, UnitMkgRB, UnitIBFTRB);
            isMkgActive = UnitMkgRB.isChecked();
        }
        else if(id==R.id.MI_About){
            Intent myIntent = new Intent(MainActivity.this, AboutAuthor.class);
            MainActivity.this.startActivity(myIntent);
            return true;
        }
        else if(id == R.id.MI_Share){
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Moje Bmi wynosi! " +
                    resultTextView.getText().toString());
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent, "Choose sharing method"));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("BMI", resultTextView.getText().toString());
        savedInstanceState.putInt("ColorBmi", resultTextView.getCurrentTextColor());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        resultTextView.setText(savedInstanceState.getString("BMI"));
        resultTextView.setTextColor(savedInstanceState.getInt("ColorBmi"));
    }


    private boolean isDataCorrect() {
        float mass = 0f;
        float height = 0f;
        if(isDataEntered()) {
            height = Float.parseFloat(editTextHeight.getText().toString());
            mass = Float.parseFloat(editTextMass.getText().toString());
        }
        else{
            return false;
        }
        if(height > 100){
            height /= 100f;
            editTextHeight.setText(Float.toString(height));
        }
        IBmiCalc bmiCalc;
        if (UnitMkgRB.isChecked()) {
            bmiCalc = new BmiCalcForKgM();
            return bmiCalc.isValidMass(mass) && bmiCalc.isValidHeight(height);
        } else {
            bmiCalc = new BmiCalcForIbFt();
            return bmiCalc.isValidMass(mass) && bmiCalc.isValidHeight(height);
        }
    }

    private boolean isDataEntered(){
        try{
            Float.parseFloat(editTextHeight.getText().toString());
            Float.parseFloat(editTextMass.getText().toString());
        }
        catch (Exception e){
            return false;
        }
        return true;
    }



}


/*todo
activity about autor
-zmien na cm
-funkcja sprawdzajaca poprawnosc
-share na facebook
-image view
-zapisz dane ??? shared preferences
-button zapisz jest nie wazny jezeli dane sa nie poprawne
 */