package com.wt.resource;

import com.wt.domain.Workout;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import org.apache.http.HttpHeaders;
import org.jboss.resteasy.reactive.RestResponse;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.is;


// integration tests -> meerdere functies die met elkaar verbonden zijn dus deze recource testen

// unit tests -> test je dus 1 functie dit is dus assert en die shit

@QuarkusTest
@TestHTTPEndpoint(WorkoutResource.class)
public class WorkoutResourceTest {
    @Test
    @TestSecurity(user = "admin@gmail.com", roles = "user")
    public void When_Get_Workouts_Not_Empty() {
        given()
                .when().get()
                .then()
                .statusCode(RestResponse.StatusCode.OK)
                .body("size()", not(0));
    }

    @Test
    @TestSecurity(user = "admin@gmail.com", roles = "user")
    public void When_Get_Workout_By_Id_Then_Workout_Is_Found() {
        given().contentType(ContentType.JSON).param("id", "32b5646c-cecb-4518-ae17-bcd296990db7")
                .when().get()
                .then()
                .statusCode(RestResponse.StatusCode.OK)
                .body("name", hasItem("Push"));
    }

    @Test
    @Transactional
    @TestSecurity(user = "admin@gmail.com", roles = "user")
    public void When_Post_Workout_Succeeds() {
        given()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when().post("/"+"Legs"+"/"+"admin@gmail.com")
                .then()
                .statusCode(200);

        given()
                .when().get()
                .then()
                .statusCode(200)
                .body("$.size()", is(3),
                        "[0].user_email", is("admin@gmail.com"),
                        "[0].name", is("Push"),
                        "[1].user_email", is("admin@gmail.com"),
                        "[1].name", is("Pull"),
                        "[2].user_email", is("admin@gmail.com"),
                        "[2].name", is("Legs"));
    }
}
