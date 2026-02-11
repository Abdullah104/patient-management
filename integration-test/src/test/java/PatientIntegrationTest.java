import io.restassured.RestAssured;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class PatientIntegrationTest {
    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://localhost:4004";
    }

    @Test
    public void shouldReturnPatientsWithValidToken() {
        String loginPayload = "{\"email\": \"testuser@test.com\", \"password\": \"password123\"}";

        String token = given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response()
                .jsonPath()
                .getString("token");

        given()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .when()
                .get("/api/patients")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("patients", notNullValue());
    }
}
