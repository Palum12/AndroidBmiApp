package pl.edu.pwr.swim.paluszek.bmi2;

/**
 * Created by Maksymilian on 14.03.2017.
 */

public interface IBmiCalc {
    float countBmi(float mass, float height);
    boolean isValidMass(float mass);
    boolean isValidHeight(float height);
}
