package com.api.automation;

import static com.api.constants.Role.FD;
import static com.api.utils.specUtil.*;
import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.api.pojo.CreateJobRequestBody;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class CreateJobAPITest {

	
	@Test
	public void createJobTest() {
		
		Customer customer = new Customer("Radhika","dhairya","9876532901","","radhika@gmail.com","");
		CustomerAddress customer_address1 = new CustomerAddress("101", "Orchid", "Navi Mumbai", "Landmark: mumbai", "Area:mumbai", "48789", "India", "Mumbai");
		CustomerProduct customer_product = new CustomerProduct("2023-02-05T18:30:00.000Z","795874674375307","795874674375307","795874674375307","2023-02-05T18:30:00.000Z",1,1);
		Problems problems = new Problems(1,"Battery Issue");
		//Problems[] problemArray = new Problems[1];
		List<Problems> problemList = new ArrayList<Problems>();
		
		//problemArray[0] = problems;
		problemList.add(problems);
		
		CreateJobRequestBody requestBody = new CreateJobRequestBody(0,2,1,1,customer,customer_address1,customer_product,problemList);
		
		
		given()
		  .spec(requestBuilderWithBody(FD,requestBody))
				.when()
					.post("/job/create")
				.then()
					.spec(responseBuilder_Ok())
					.and()
					.body(matchesJsonSchemaInClasspath("ResponseSchema/CreateJobAPIResponseSchema.json"))
					.and()
					.body("message", equalTo("Job created successfully. "))
					.and()
					.body("data.mst_service_location_id", equalTo(1))
					.and()
					.body("data.job_number",startsWith("JOB_"));
	}
}
