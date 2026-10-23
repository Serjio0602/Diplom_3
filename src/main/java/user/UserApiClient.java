package user;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApiClient {

    private final String BASE_URL = "https://qa-stellarburgers.education-services.ru/";

    @Step("Создать пользователя")
    public Response register(User user) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(user)
                .when()
                .post("/api/auth/register");
    }

    @Step("Удалить пользователя")
    public Response delete(String accessToken) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user");
    }

    public String getAccessToken(Response response) {
        return response.path("accessToken");
    }
}
