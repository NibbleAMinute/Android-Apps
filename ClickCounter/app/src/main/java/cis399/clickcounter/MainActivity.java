package cis399.clickcounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.view.View;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class MainActivity extends AppCompatActivity {

    private TextView total;
    private SharedPreferences savedValues;

    public void click(View v) {
        int count = Integer.parseInt(total.getText().toString());
        total.setText(Integer.toString(++count));
    }

    public void reset(View v) {
        total.setText("0");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        total = (TextView)findViewById(R.id.totalLabel);
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);

        total.setText("0");
    }

    @Override
    public void onPause() {
        Editor editor = savedValues.edit();
        editor.putString("Count", total.getText().toString());
        editor.commit();

        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        total.setText(savedValues.getString("Count", "0"));
    }

}
