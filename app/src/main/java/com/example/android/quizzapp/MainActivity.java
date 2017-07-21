package com.example.android.quizzapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import static android.R.id.message;

public class MainActivity extends AppCompatActivity {

    /**
     * Id's of the RadioButton views that should be checked.
     */
    private int[] radioButtonSolutions = {R.id.jaws,
                                    R.id.star_trek,
                                    R.id.batman,
                                    R.id.life_bryan,
                                    R.id.harry_met_sally,
                                    R.id.westworld,
                                    R.id.game_thrones,
                                    R.id.luke_cage,
                                    R.id.wargames};
    /**
     * Id's of the CheckBox views that should be checked.
     */
    private SparseBooleanArray checkBoxSolutions;


    private String textBoxSolution = "yoda";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeCheckBoxSolutions();
    }

    private void initializeCheckBoxSolutions() {
        checkBoxSolutions = new SparseBooleanArray();
        checkBoxSolutions.append(R.id.jeor_mormont, true);
        checkBoxSolutions.append(R.id.robb_stark, true);
        checkBoxSolutions.append(R.id.ros, true);
        checkBoxSolutions.append(R.id.talysa_maegyr, true);
        checkBoxSolutions.append(R.id.kahl_drogo, true);
        checkBoxSolutions.append(R.id.grey_wind, true);
        checkBoxSolutions.append(R.id.ygritte, true);
        checkBoxSolutions.append(R.id.shae, true);
        checkBoxSolutions.append(R.id.jon_snow, false);
        checkBoxSolutions.append(R.id.the_hound, false);
        checkBoxSolutions.append(R.id.little_finger, false);
        checkBoxSolutions.append(R.id.bran_stark, false);
        checkBoxSolutions.append(R.id.jorah_mormont, false);
    }

    /**
     * Check solutions and show results in toast
     * @param view
     */
    public void checkSolution(View view) {
        int errorCount= 0;
        errorCount += checkTextBoxes();
        errorCount += checkRadioButtons();
        errorCount += checkCheckBoxes();

        showToast(errorCount);
    }

    /**
     * Check CheckBox views.
     * @return how many errors have been found.
     */
    private int checkCheckBoxes() {
        int length = checkBoxSolutions.size();
        int errorCount = 0;

        for (int i=0; i<length; i++) {
            int key = checkBoxSolutions.keyAt(i);
            CheckBox checkBox = (CheckBox) findViewById(key);
            if (checkBox.isChecked() != checkBoxSolutions.get(key))
                errorCount++;

        }
        return errorCount;
    }


    /**
     * Check RadioButton views.
     * @return how many errors have been found.
     */
    private int checkRadioButtons() {
        int length = radioButtonSolutions.length;
        int errorCount = 0;

        for (int i=0; i<length; i++) {
            RadioButton radioButton = (RadioButton) findViewById(radioButtonSolutions[i]);
            if (!radioButton.isChecked())
                errorCount++;
        }
        return errorCount;
    }


    /**
     * Check the single EditText view.
     * @return if an error has been found.
     */
    private int checkTextBoxes() {
        EditText solution = (EditText)findViewById(R.id.do_or_do_not_answer);
        String solutionString = solution.getText().toString().trim();

        if(solutionString.equalsIgnoreCase(textBoxSolution))
            return 0;
        return 1;
    }

    /**
     * Show a toast with the result message.
     * @param errorCount how many errors have been found.
     */
    private void showToast(int errorCount) {
        String message;
        if(errorCount > 0) {
            message = getString(R.string.toast_error_message, errorCount);
        }else{
            message = getString(R.string.toast_success_message);
        }
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
