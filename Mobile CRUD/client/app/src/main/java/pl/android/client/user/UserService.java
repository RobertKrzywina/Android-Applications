package pl.android.client.user;

import android.os.AsyncTask;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import pl.android.client.user.dto.UserDto;

public class UserService extends AsyncTask<String, Void, Object> {

    @Override
    protected ResponseEntity<UserDto[]> doInBackground(String... uri) {

        final String url = uri[0];
        RestTemplate restTemplate = new RestTemplate();

        try {
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), UserDto[].class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}