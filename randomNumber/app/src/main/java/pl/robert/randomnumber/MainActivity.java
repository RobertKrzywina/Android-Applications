package pl.robert.randomnumber;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView randomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        randomNumber = findViewById(R.id.randomNumber);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        randomNumber.setText(String.valueOf(generateRandomNumber()));
    }

    /**
     * Generate random number from 0 to 1000
     *
     * @return number
     */
    private int generateRandomNumber() {

        return (int) (Math.random() * 1000 + 1);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

        // nothing to do
    }
}
