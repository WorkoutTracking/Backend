package com.wt;

import com.wt.resource.WorkoutResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.jboss.resteasy.reactive.RestResponse;
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
                .statusCode(RestResponse.StatusCode.OK);
    }

}
