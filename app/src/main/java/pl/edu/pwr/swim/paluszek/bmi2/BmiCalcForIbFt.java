package pl.edu.pwr.swim.paluszek.bmi2;

import android.graphics.Color;

/**
 * Created by Maksymilian on 25.03.2017.
 */

public class BmiCalcForIbFt implements IBmiCalc {
    public static final float minMass = 5f;
    public static final float maxMass = 450f;
    public static final float minHeight = 2f;
    public static final float maxHeight = 8.5f;

    @Override
    public float countBmi(float mass, float height) {
        if(isValidMass(mass) && isValidHeight(height)){
            return  ( (mass*4.88f)/(height*height) );
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean isValidMass(float mass) {
        return mass < maxMass && mass > minMass;
    }

    @Override
    public boolean isValidHeight(float height) {
        return height < maxHeight && height > minHeight;
    }

    public static int colorBmi(float bmi){
        int color = 0;
        if(bmi < 18.5){
            color = Color.RED;
        }
        else if(18.5 <= bmi && bmi < 25 ){
            color = Color.GREEN;
        }
        else if(25 <= bmi && bmi < 30) {
            color = Color.YELLOW;
        }
        else{
            color = Color.RED;
        }
        return color;
    }
}
