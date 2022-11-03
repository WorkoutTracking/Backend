package nl;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import nl.resource.WorkoutResource;
import org.jboss.resteasy.reactive.RestResponse.StatusCode;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestHTTPEndpoint(WorkoutResource.class)
public class WorkoutResourceTest {

    @Test
    public void Test_Workout_Endpoint() {
        given()
                .when().get()
                .then()
                .statusCode(StatusCode.OK);
    }
}
