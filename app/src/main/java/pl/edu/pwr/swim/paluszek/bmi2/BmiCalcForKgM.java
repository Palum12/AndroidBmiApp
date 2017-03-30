package pl.edu.pwr.swim.paluszek.bmi2;

import android.graphics.Color;

import java.security.InvalidParameterException;

/**
 * Created by Maksymilian on 14.03.2017.
 */

public class BmiCalcForKgM implements IBmiCalc {

    public static final float minMass = 10f;
    public static final float maxMass = 200f;
    public static final float minHeight = 0.5f;
    public static final float maxHeight = 2.5f;


    @Override
    public float countBmi(float mass, float height) {
        if(isValidMass(mass) && isValidHeight(height)){
            return mass/(height*height);
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
