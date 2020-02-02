package pl.android.client.user.dto;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("email")
    private String email;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("age")
    private Integer age;

    @NonNull
    @Override
    public String toString() {

        return "UUID = " + uuid + '\n'
                + "email = " + email + '\n'
                + "first name = " + firstName + '\n'
                + "last name = " + lastName + '\n'
                + "age = " + age;
    }
}