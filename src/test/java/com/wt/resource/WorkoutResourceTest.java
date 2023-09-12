package com.wt.resource;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.apache.http.HttpHeaders;
import org.jboss.resteasy.reactive.RestResponse;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

@QuarkusTest
@TestHTTPEndpoint(WorkoutResource.class)
public class WorkoutResourceTest {
    @Test
    @TestSecurity(user = "admin@gmail.nl", roles = "user")
    public void When_Get_Workouts_Not_Empty() {
        given()
                .when().get("/user/admin@gmail.nl")
                .then()
                .statusCode(RestResponse.StatusCode.OK)
                .body("size()", not(0));
    }

    @Test
    @Transactional
    @TestSecurity(user = "admin@gmail.nl", roles = "user")
    public void When_Post_Workout_Succeeds() {
        given()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when().post("/" + "Legs" + "/" + "admin@gmail.nl")
                .then()
                .statusCode(201);

        given()
                .when().get("/user/admin@gmail.nl")
                .then()
                .statusCode(200)
                .body("$.size()", is(3));
    }
}
