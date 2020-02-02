package pl.android.client.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import pl.android.client.R;
import pl.android.client.user.UserHttpTask;
import pl.android.client.util.UrlUtil;

public class UpdateUserAgeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_age);
    }

    public void updateAge(View view) {

        EditText usersEmailField = findViewById(R.id.usersEmail);
        EditText newAgeField = findViewById(R.id.newAge);

        String usersEmail = usersEmailField.getText().toString();
        String newAge = newAgeField.getText().toString();

        new UserHttpTask(getApplication()).execute(UrlUtil.UPDATE_AGE_BY_EMAIL, usersEmail, newAge);
    }
}
