package Projects;
import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class RestAssured {
	RequestSpecification requestSpec;
	String ssHKey;
	int ssHKeyId;
	@BeforeClass
	public void setup() {
		requestSpec = new RequestSpecBuilder()
				                        .setContentType(ContentType.JSON)
		                                .addHeader("Authorization","token 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")
		                                .setBaseUri("https://api.github.com")
		                                .build();
		 ssHKey="ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCZithG5eafo38tBoWiMNXhyluNrfUY4S6KuNocb3WXw/t/o4E/3eBOLt9JAQF2sphYefLBHaA0XCoGQNdhDfxXtVGInLUlEZ6m6rTYeVOeEaFpJbGQLJiBcnVCOwJB9omjk2YTPWEMVf7ms5Sq30CYziK/L4KyVjKUNStis7B/zz0Yp0QhUYG9YMvUxVqPOVz/+HXSYoEZYISYFry+p+kE1Nqq/mHYkQtQ0yjmuGuSTGjYURNZsxumcQcFp8h1yby0mWnxANDQ8S2O6d61U9/tBVx0GUDTc0O/3XQhT1zffNDtm+7/7CZdtdc7aweZ5knQpZdcLzdrZgFAOqPfyCst "; 
		                                
	}
	

	
	@Test(priority=1)
    public void postsshkey() {
	  String reqBody = "{\"title\": \"TestAPIKey\", \"key\": \"" + ssHKey + "\"}";
	  Response response = given().spec(requestSpec)
			  	.body(reqBody)
			  	.when().post("/user/keys");
	  // Print response				  	
	  String body = response.getBody().asPrettyString();				  	
	  System.out.println(body);
	  // Assertion with response specification
	  ssHKeyId = response.then().extract().path("id");
	  response.then().statusCode(201);
    }
  
  @Test(priority=2)
    public void getsshkey() {
	  
	  Response response = given().spec(requestSpec)
			  	.when().get("/user/keys");
	  // Print response				  	
	  String body = response.getBody().asPrettyString();				  	
	  System.out.println(body);
	  
	  // Assertion with response specification				  	
	  response.then().statusCode(200);
    }
  
  @Test(priority=3)
    public void deletesshkey() {
	  
	  Response response = given().spec(requestSpec)
			  .when().pathParam("keyId", "27564321") // Set path parameter
			  	.delete("/user/keys/{keyId}");
	 
	  // Print response				  	
	  String body = response.getBody().asPrettyString();				  	
	  System.out.println(body);
	  
	  // Assertion with response specification				  	
	  response.then().statusCode(404);
    }
  
  
}