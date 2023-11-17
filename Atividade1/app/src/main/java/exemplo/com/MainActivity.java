package exemplo.com;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    private EditText editTextNumber1;
    private EditText editTextNumber2;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumber1 = findViewById(R.id.editTextNumber1);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        textViewResult = findViewById(R.id.textView);
    }

    public void sum(View v) {
        try {
            double value1 = Double.parseDouble(editTextNumber1.getText().toString());
            double value2 = Double.parseDouble(editTextNumber2.getText().toString());

            double sum = value1 + value2;

            textViewResult.setText(Double.toString(sum));
        } catch (NumberFormatException e) {
            textViewResult.setText("Invalid value. Please enter valid numbers.");
        }
    }
}