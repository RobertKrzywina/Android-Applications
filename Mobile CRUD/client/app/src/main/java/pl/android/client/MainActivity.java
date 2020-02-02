package pl.android.client;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import pl.android.client.activity.AddUserActivity;
import pl.android.client.activity.UsersActivity;
import pl.android.client.user.UserHttpTask;
import pl.android.client.user.dto.UserDto;
import pl.android.client.util.NotificationUtil;
import pl.android.client.util.UrlUtil;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void navigateToAddUserActivity(View view) {
        Intent intent = new Intent(getBaseContext(), AddUserActivity.class);
        startActivity(intent);
    }

    public void getAll(View view) throws ExecutionException, InterruptedException {
        ResponseEntity<UserDto[]> response = (ResponseEntity<UserDto[]>) new UserHttpTask(getApplication()).execute(UrlUtil.BASE).get();

        if (response == null) {
            NotificationUtil.showErrorNotification(getApplication(), "Users do not exists!");
            return;
        }

        List<UserDto> users = Arrays.asList(response.getBody());
        String listAsString = new Gson().toJson(users);
        Intent intent = new Intent(getBaseContext(), UsersActivity.class);
        intent.putExtra("users", listAsString);
        startActivity(intent);
    }

    public void getByEmail(View view) {

    }

    public void updateAge(View view) {

    }

    public void deleteByEmail(View view) {

    }

    public void deleteAll(View view) {

    }
}