package pl.robert.rgb;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    private ColorArea area;

    private TextView red;
    private TextView green;
    private TextView blue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        area = new ColorArea((ConstraintLayout) findViewById(R.id.colorArea));

        red = findViewById(R.id.redValue);
        green = findViewById(R.id.greenValue);
        blue = findViewById(R.id.blueValue);

        SeekBar redSeekBar = findViewById(R.id.redSeekBar);
        SeekBar greenSeekBar = findViewById(R.id.greenSeekBar);
        SeekBar blueSeekBar = findViewById(R.id.blueSeekBar);

        redSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                red.setText(String.valueOf(progress));
                area.setRed(progress);
                area.changeAreaColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////////////////

        greenSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                green.setText(String.valueOf(progress));
                area.setGreen(progress);
                area.changeAreaColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////////////////

        blueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                blue.setText(String.valueOf(progress));
                area.setBlue(progress);
                area.changeAreaColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}
