package pl.edu.pwr.swim.paluszek.bmi2;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Maksymilian on 25.03.2017.
 */

public class bmiUICalcTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void validateBMIKG(){
        BmiCalcForKgM bmiCalcForKgM = new BmiCalcForKgM();
        onView(withId(R.id.massET)).perform(typeText("80"));
        onView(withId(R.id.heightET)).perform(typeText("1.85"));
        closeSoftKeyboard();
        onView(withId(R.id.countBT)).perform(click());
        float bmi = bmiCalcForKgM.countBmi(80f,1.85f);
        onView(withId(R.id.resultTV)).check(matches(withText(String.format("%.2f", bmi))));
    }

    @Test
    public void validateBMIIB(){
        BmiCalcForIbFt bmiCalcForIbFt = new BmiCalcForIbFt();
        onView(withId(R.id.changeUnitToMKG)).perform(click());
        onView(withId(R.id.changeUnitToIBFT)).perform(click());
        onView(withId(R.id.changeUnitToMKG)).perform(click());
        onView(withId(R.id.changeUnitToIBFT)).perform(click());
        onView(withId(R.id.massET)).perform(typeText("80"));
        onView(withId(R.id.heightET)).perform(typeText("5"));
        closeSoftKeyboard();
        onView(withId(R.id.countBT)).perform(click());
        float bmi = bmiCalcForIbFt.countBmi(80f,5f);
        onView(withId(R.id.resultTV)).check(matches(withText(String.format("%.2f", bmi))));
    }
}
