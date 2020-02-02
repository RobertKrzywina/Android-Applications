package pl.android.client.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateUserAgeDto {

    @JsonProperty("email")
    private String email;

    @JsonProperty("newAge")
    private String newAge;

    public UpdateUserAgeDto(String email, String newAge) {
        this.email = email;
        this.newAge = newAge;
    }
}
