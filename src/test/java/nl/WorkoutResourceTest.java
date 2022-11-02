package nl;

import io.quarkus.test.junit.QuarkusTest;
import org.jboss.resteasy.reactive.RestResponse.StatusCode;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class WorkoutResourceTest {

    @Test
    public void Test_Workout_Endpoint() {
        given()
                .when().get()
                .then()
                .statusCode(StatusCode.OK);
    }
}
