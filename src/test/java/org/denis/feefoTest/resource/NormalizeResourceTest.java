package org.denis.feefoTest.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.denis.feefoTest.service.NormalizeService;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class NormalizeResourceTest {
    @Inject
    NormalizeService normalizeService;

    @Test
    public void testNormalizeJobTitleExactMatch() {
        String input = "engineer";
        given()
                .contentType(ContentType.TEXT)
                .body(input)
                .when()
                .post("/normalize")
                .then()
                .statusCode(200)
                .body(equalTo("Software engineer"));
    }

    @Test
    public void testNormalizeJobTitlePartialMatch() {
        String input = "enginer";
        given()
                .contentType(ContentType.TEXT)
                .body(input)
                .when()
                .post("/normalize")
                .then()
                .statusCode(200)
                .body(equalTo("Software engineer"));
    }

    @Test
    public void testNormalizeJobTitleNoMatch() {
        String input = "doctor";
        given()
                .contentType(ContentType.TEXT)
                .body(input)
                .when()
                .post("/normalize")
                .then()
                .statusCode(200)
                .body(equalTo("doctor"));
    }

    @Test
    public void testNormalizeJobTitleExtraCharacters() {
        String input = "chief accountant";
        given()
                .contentType(ContentType.TEXT)
                .body(input)
                .when()
                .post("/normalize")
                .then()
                .statusCode(200)
                .body(equalTo("Accountant"));
    }

    @Test
    public void testNormalizeJobTitleNullInput() {
        given()
                .contentType(ContentType.TEXT)
                .body("")
                .when()
                .post("/normalize")
                .then()
                .statusCode(200)
                .body(equalTo(""));
    }
}
