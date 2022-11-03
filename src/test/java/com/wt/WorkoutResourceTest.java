package com.wt;

import com.wt.resource.WorkoutResource;
import io.quarkus.logging.Log;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.jboss.resteasy.reactive.RestResponse;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;

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

    @Test
    public void When_Get_Workouts_Not_Empty() {
        given()
                .when().get()
                .then()
                .statusCode(RestResponse.StatusCode.OK)
                .body("size()", not(0));
    }

    @Test
    public void When_Get_Workout_By_Id_Then_Workout_Is_Found() {
        given().contentType(ContentType.JSON).param("id", "32b5646c-cecb-4518-ae17-bcd296990db7")
                .when().get()
                .then()
                .statusCode(RestResponse.StatusCode.OK)
                .body("name", hasItem("Push"));
    }
}
