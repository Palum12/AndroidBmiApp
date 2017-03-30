package pl.edu.pwr.swim.paluszek.bmi2;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Maksymilian on 14.03.2017.
 */

public class bmiCalcTest {


    @Test
    public void massUnderZeroIsInvalid() throws Exception{
        //GIVEN
        float testMass = -10f;
        //WHEN
        BmiCalcForKgM bmiCalcForKgM = new BmiCalcForKgM();
        BmiCalcForIbFt bmiCalcForIbFt = new BmiCalcForIbFt();
        //THEN
        assertFalse(bmiCalcForKgM.isValidMass(testMass));
        assertFalse(bmiCalcForIbFt.isValidMass(testMass));
    }

    @Test
    public void massOver9KIsInvalid() throws Exception{
        //GIVEN
        float testMass = 10000f;
        //WHEN
        BmiCalcForKgM bmiCalcForKgM = new BmiCalcForKgM();
        BmiCalcForIbFt bmiCalcForIbFt = new BmiCalcForIbFt();
        //THEN
        assertFalse(bmiCalcForKgM.isValidMass(testMass));
        assertFalse(bmiCalcForIbFt.isValidMass(testMass));
    }

    @Test
    public void heightUnderZeroIsInvalid() throws Exception{
        //GIVEN
        float testHeight = -10f;
        //WHEN
        BmiCalcForKgM bmiCalcForKgM = new BmiCalcForKgM();
        BmiCalcForIbFt bmiCalcForIbFt = new BmiCalcForIbFt();
        //THEN
        assertFalse(bmiCalcForKgM.isValidHeight(testHeight));
        assertFalse(bmiCalcForIbFt.isValidHeight(testHeight));
    }

    @Test
    public void heightOver9KIsInvalid() throws Exception{
        //GIVEN
        float testHeight = 10000f;
        //WHEN
        BmiCalcForKgM bmiCalcForKgM = new BmiCalcForKgM();
        BmiCalcForIbFt bmiCalcForIbFt = new BmiCalcForIbFt();
        //THEN
        assertFalse(bmiCalcForKgM.isValidHeight(testHeight));
        assertFalse(bmiCalcForIbFt.isValidHeight(testHeight));
    }

    @Test
    public void countBmiTEst() throws Exception{
        //GIVEN
        float testHeight = 2.2f;
        float testMass = 80f;
        //WHEN
        BmiCalcForKgM bmiCalcForKgM = new BmiCalcForKgM();
        BmiCalcForIbFt bmiCalcForIbFt = new BmiCalcForIbFt();

        //THEN
        assertTrue(bmiCalcForKgM.countBmi(testMass, testHeight) == (testMass / (testHeight * testHeight)));
        assertTrue(bmiCalcForIbFt.countBmi(testMass, testHeight) ==  ( (testMass*4.88f)/(testHeight*testHeight) ));
    }
}
