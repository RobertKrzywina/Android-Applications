package pl.android.client.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import pl.android.client.R;
import pl.android.client.util.UrlUtil;
import pl.android.client.user.UserHttpTask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;

public class AddUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
    }

    public void add(View view) throws ExecutionException, InterruptedException {

        EditText emailField = findViewById(R.id.emailField);
        EditText firstNameField = findViewById(R.id.firstNameField);
        EditText lastNameField = findViewById(R.id.lastNameField);
        EditText ageField = findViewById(R.id.ageField);

        String email = emailField.getText().toString();
        String firstName = firstNameField.getText().toString();
        String lastName = lastNameField.getText().toString();
        String age = ageField.getText().toString();

        new UserHttpTask(getApplication()).execute(UrlUtil.ADD_USER, email, firstName, lastName, age).get();
    }
}
