package pl.android.client.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateUserDto {

    @JsonProperty("email")
    private String email;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("age")
    private String age;

        public CreateUserDto(String email, String firstName, String lastName, String age) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
