package com.wt.resource;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.jboss.resteasy.reactive.RestResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;

@QuarkusTest
@TestHTTPEndpoint(ExerciseResource.class)
class ExerciseResourceTest {

    @Test
    @TestSecurity(user = "carlovankessel@yahoo.nl", roles = "user")
    void When_Get_Exercises_Not_Empty() {
        given()
                .when().get()
                .then()
                .statusCode(RestResponse.StatusCode.OK)
                .body("size()", not(0));
    }
}
