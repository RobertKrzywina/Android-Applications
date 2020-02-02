package pl.android.client.activity;

import java.util.List;
import java.util.Arrays;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import pl.android.client.R;

import com.google.gson.Gson;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import pl.android.client.user.dto.UserDto;
import pl.android.client.util.NotificationUtil;

public class UsersActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            NotificationUtil.showErrorNotification(getApplication(), "Something went wrong!");
            return;
        }

        String listAsString = extras.getString("users");
        List<UserDto> users = Arrays.asList(new Gson().fromJson(listAsString, UserDto[].class));
        listView = findViewById(R.id.listView);

        showUsers(users);
    }

    private void showUsers(List<UserDto> users) {

        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users));
    }
}
