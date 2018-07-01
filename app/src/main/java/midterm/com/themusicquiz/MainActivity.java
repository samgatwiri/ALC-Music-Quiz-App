package midterm.com.themusicquiz;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int score = 0;

    private RadioGroup groupInstruments;

    private EditText editTextCountry;
    private EditText editTextDynamic;

    private CheckBox check3;
    private CheckBox check6;
    private CheckBox check4;

    private boolean isChoiceFour = false;
    private boolean isChoiceSax = false;

    private CompoundButton.OnCheckedChangeListener chkCheckedListener;
    private RadioGroup.OnCheckedChangeListener radioCheckChangeListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize CheckBoxes, Edit Text, and Radio Group
        check3 = findViewById(R.id.chk3);
        check6 = findViewById(R.id.chk6);
        check4 = findViewById(R.id.chk4);

        editTextCountry = (EditText) findViewById(R.id.edtCountry);
        editTextDynamic= (EditText) findViewById(R.id.edtDynamic);
        groupInstruments = (RadioGroup) findViewById(R.id.instrument);

        setListeners();
    }


    public void setListeners() {

        chkCheckedListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton selectedCheckBox, boolean isChecked) {
                // Check which checkbox was clicked
                switch (selectedCheckBox.getId()) {
                    case R.id.chk3:
                        if (isChecked) {
                            isChoiceFour = false;
                        }
                        break;
                    case R.id.chk6:
                        if (isChecked) {
                            isChoiceFour = false;
                        }
                        break;
                    case R.id.chk4:
                        if (isChecked) {
                            isChoiceFour = true;
                        }
                        break;
                }
            }
        };

        check3.setOnCheckedChangeListener(chkCheckedListener);
        check6.setOnCheckedChangeListener(chkCheckedListener);
        check4.setOnCheckedChangeListener(chkCheckedListener);

        // RadioGroup Listeners
        radioCheckChangeListener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int radioButtonId) {
                switch (radioGroup.getId()) {
                    case R.id.instrument:
                        if (R.id.radioTuba == radioButtonId) {
                            isChoiceSax = false;
                        }

                        if (R.id.radioSax == radioButtonId) {
                            isChoiceSax = true;
                        }
                        break;
                }
            }
        };

        groupInstruments.setOnCheckedChangeListener(radioCheckChangeListener);
    }


    public void display(String message) {

        TextView ScoreView = findViewById(R.id.viewScore);
        ScoreView.setText(message);
    }

    public void submit(View view) {

        if (isChoiceFour) {
            score = +1;
        }
        if (isChoiceSax) {
            score += 1;
        }

        String countryAnswer = editTextCountry.getText().toString();
        if (countryAnswer == "Italy") {
            score += 1;
        }
        String dynamicAnswer = editTextDynamic.getText().toString();
        if (dynamicAnswer == "mf") {
            score += 1;
        }


        display("Your Score Is: " + score+ " Congrats!");
    }

    public void reset(View view) {
        score = 0;

        isChoiceFour = false;
        isChoiceSax = false;


        check3.setChecked(false);
        check6.setChecked(false);
        check4.setChecked(false);

        for (int i = 0; i < groupInstruments.getChildCount(); i++) {

            RadioButton childButton = (RadioButton) groupInstruments.getChildAt(i);
            childButton.setChecked(false);
        }

        display("Your Score Is:" + score);
    }
}