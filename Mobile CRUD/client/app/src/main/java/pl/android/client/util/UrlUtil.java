package pl.android.client.util;

public final class UrlUtil {

    private static final String BASE_ADDRESS = "http://10.0.2.2:8080/api";

    public static final String GET_ALL = BASE_ADDRESS + "/user";
    public static final String ADD_USER = BASE_ADDRESS + "/user/save";
    public static final String DELETE_ALL = BASE_ADDRESS + "/user/delete";
    public static final String UPDATE_AGE_BY_EMAIL = "http://10.0.2.2:8080/api/user/update-age";
}
