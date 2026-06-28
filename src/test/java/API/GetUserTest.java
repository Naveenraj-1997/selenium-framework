package API;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetUserTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
    }

    @Test
    public void getSingleUser() {
        given().when().get("/users/1").then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", equalTo("Leanne Graham"))
                .body("email", equalTo("Sincere@april.biz"))
                .body("address.city", equalTo("Gwenborough"))
                .body("address.geo.lat", equalTo("-37.3159"));
        System.out.println("GET single user test passed!");

    }

    @Test
    public void getAllUsers() {
        Response response =
                given().when()
                        .get("/users")
                        .then().statusCode(200)
                        .body("size()", equalTo(10))
                        .extract().response();
        System.out.println("Total users: " +
                response.jsonPath().getList("$").size());

    }

    @Test
    public void getNotFound() {
        given().when().get("/users/999")
                .then().statusCode(404);
        System.out.println("404 verified");
    }

    @Test
    public void createUser() {

        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("name","Naveen");
        requestBody.put("username","naveenraj");
        requestBody.put("email","naveen@test.com");
        given().contentType(ContentType.JSON).body(requestBody)
                .when().post("/users").then().statusCode(201).body("name", equalTo("Naveen"))
                .body("email", equalTo("naveen@test.com"));
        System.out.println("User created successfully!");
    }

    @Test
    public void updateUser()
    {
        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("name","Naveen_updated");
        requestBody.put("username","naveenraj");
        requestBody.put("email","naveen_updated@test.com");
        given().contentType(ContentType.JSON).body(requestBody)
                .when().put("/users/1").then().statusCode(200).body("name", equalTo("Naveen_updated"));
        System.out.println("User updated successfully!");
    }

    @Test
    public void deleteUser()
    {
        given().when().delete("/users/1").then().statusCode(200);
        System.out.println("User deleted successfully!");
    }
    @Test
    public void extractSingleValue()
    {
        Response response = given().when().get("/users/1").then().statusCode(200)
                .extract().response();
        String name = response.jsonPath().getString("name");
        String email = response.jsonPath().getString("email");
        String city = response.jsonPath().getString("address.city");
        int id = response.jsonPath().getInt("id");

        Assert.assertEquals(name, "Leanne Graham");
        Assert.assertEquals(email, "Sincere@april.biz");
        Assert.assertEquals(id, 1);
        Assert.assertEquals(city, "Gwenborough");
    }
    @Test
    public void extractListValues()
    {
        Response response = given().when().get("/users").then().statusCode(200).extract().response();
        List<String> names = response.jsonPath().getList("name");
        List<Integer> ids  = response.jsonPath().getList("id");
        System.out.println("All Names " + names);
        System.out.println("All Ids " + ids);
        System.out.println("Total Users" + names.size());
        Assert.assertEquals(names.size(), 10);
        Assert.assertTrue(names.contains("Leanne Graham"));
        Assert.assertTrue(ids.contains(1));
    }
    @Test
    public void extractAndUseInNextRequest()
    {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "Naveen");
        requestBody.put("email", "naveen@test.com");
        int userId = given().contentType(ContentType.JSON).body(requestBody).when().post("/users").then()
                .statusCode(201).extract().path("id");
        System.out.println("Created user ID: " + userId);
        Assert.assertTrue(userId > 0);
        System.out.println("Valid ID returned: " + userId);

       String name = given().when().get("/users/1").then().statusCode(200).extract().path("name");
        System.out.println("Retrieved user name: " + name);
        Assert.assertEquals(name, "Leanne Graham");
    }


}
