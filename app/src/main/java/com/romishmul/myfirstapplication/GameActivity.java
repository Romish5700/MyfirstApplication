package com.romishmul.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    Button btnRange, btnCheck;
    EditText etCheck;  // נשאר רק EditText עבור ניחוש
    int randomNumber, count;
    boolean isNumberGenerated = false; // משתנה לבדיקת אם הוגרל מספר

    RadioGroup radioGroup; // הקבוצה שבה נמצאים כפתורי הרדיו
    RadioButton radioButton1, radioButton2, radioButton3; // כפתורי הרדיו עצמם

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initViews();
    }

    private void initViews() {
        etCheck = findViewById(R.id.check);  // רק עבור ניחוש

        // אתחול RadioGroup ו-RadioButtons
        radioGroup = findViewById(R.id.radioGroup);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);

        btnRange = findViewById(R.id.btnRange);
        btnRange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int iMin = 0;
                int iMax = 0;

                // בודק איזה כפתור רדיו נבחר
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId == R.id.radioButton1) {
                    iMin = 0;
                    iMax = 100;
                } else if (selectedId == R.id.radioButton2) {
                    iMin = 50;
                    iMax = 200;
                } else if (selectedId == R.id.radioButton3) {
                    iMin = 100;
                    iMax = 500;
                }

                // בודק אם נבחר טווח תקין
                if (iMin < iMax) {
                    randomNumber = iMin + (int) ((iMax - iMin + 1) * Math.random());
                    Toast.makeText(GameActivity.this, "המספר הוגרל!", Toast.LENGTH_SHORT).show();
                    isNumberGenerated = true;

                    // הפעל את כפתור ה-check לאחר ההגרלה
                    btnCheck.setEnabled(true);
                } else {
                    Toast.makeText(GameActivity.this, "נא לבחור טווח תקין.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCheck = findViewById(R.id.btncheck);
        btnCheck.setEnabled(false); // כפתור ה-check מושבת עד שלא הוגרל מספר
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isNumberGenerated) {
                    Toast.makeText(GameActivity.this, "נא להגריל מספר קודם!", Toast.LENGTH_SHORT).show();
                } else {
                    count++;

                    try {
                        int iCheck = Integer.parseInt(etCheck.getText().toString());

                        if (iCheck == randomNumber) {
                            Toast.makeText(GameActivity.this, "ניחשת נכון! המספר היה " + randomNumber, Toast.LENGTH_LONG).show();
                            Intent result = new Intent();
                            result.putExtra("num_gueses", count);
                            result.putExtra("user_name", "bbb");
                            setResult(RESULT_OK, result);
                            finish();
                        } else {
                            Toast.makeText(GameActivity.this, "ניחשת לא נכון. המספר היה " + randomNumber, Toast.LENGTH_LONG).show();
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(GameActivity.this, "נא הכנס מספר לבדוק.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
