package ui.swagger.testbase.extractingresponsedata;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SearchJsonPathExample {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }


    // 1) Extract the value of limit
    @Test
    public void test001() {

        int limit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");

    }

    // 2) Extract the value of total
    @Test
    public void test002() {

        int total = response.extract().path("total");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the value of total: " + total);
        System.out.println("------------------End of Test---------------------------");

    }

    // 3) Extract the name of 5th store
    @Test
    public void test003() {
        String name = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the name of 5th store: " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    // 4)  Extract the names of all the store
    @Test
    public void test004() {
        List<String> allName = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the names of all the store : " + allName);
        System.out.println("------------------End of Test---------------------------");
    }

    // 5)  Extract the storeId of all the store
    @Test
    public void test005() {
        List<Integer> storeId = response.extract().path("data.services.storeservices.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the storeId of all the store : " + storeId);
        System.out.println("------------------End of Test---------------------------");
    }

    // 6)  Print the size of the data list
    @Test
    public void test006() {
        List<Integer> storeId = response.extract().path("data.services.storeservices.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data list : " + storeId.size());
        System.out.println("------------------End of Test---------------------------");
    }

    // 7)  Get all the value of the store where store name = St Cloud
    @Test
    public void test007() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name=='St Cloud'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.print("Get all the value of the store where store name = St Cloud : " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    // 8)  Get the address of the store where store name = Rochester
    @Test
    public void test008() {
        List<HashMap<String, ?>> storeName = response.extract().path("data.findAll{it.name=='Rochester'}.address");
        System.out.println("------------------StartingTest---------------------------");
        System.out.print("Get the address of the store where store name = Rochester : " + storeName);
        System.out.println("------------------End of Test---------------------------");
    }

    // 9)  Get all the services of 8th store
    @Test
    public void test009() {
        List<HashMap<String, ?>> storeServices = response.extract().path("data[7].services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.print("Get all the services of 8th store :" + storeServices);
        System.out.println("------------------End of Test---------------------------");
    }

    // 10)   Get store services of the store where service name = Windows Store
    @Test
    public void test010() {
        List<HashMap<String, ?>> serviceName = response.extract().path("data.findAll{it.name=='Windows Store'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.print("Get storeservices of the store where service name = Windows Store :" + serviceName);
        System.out.println("------------------End of Test---------------------------");
    }

    // 11)    Get all the storeId of all the store
    @Test
    public void test011() {
        List<?> storeId = response.extract().path("data.services.storeservices.findAll{it.storeId}.storeId");
        Iterator<?> itr = storeId.iterator();
        while (itr.hasNext()) {
            // System.out.println(itr.next());
            List<?> check = (List<?>) itr.next();
            System.out.println("List of name of all stores id are : " + check.get(0));
        }
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeId of all the store : " + storeId);
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the store names Where state = ND
    @Test
    public void test013() {
        List<Integer> storeId = response.extract().path("data.findAll{it.state == 'ND'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the store names Where state = ND : " + storeId);
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014() {
        List<?> numberOfServices = response.extract().path("data.findAll{it.name=='Rochester'}.services.find{it.name}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the Total number of services for the store where store name = Rochester: " + numberOfServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test15() {
        List<String> createdAt = response.extract().path("data.find{it.services}.services.findAll{it.name=='Windows Store'}.createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the createdAt for all services whose name = “Windows Store” : " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    // Find the name of all services Where store name = “Fargo”
    @Test
    public void test16() {
        List<String> allServicesWhereStoreNameIsFargo = response.extract().path("data.find{it.name = 'Fargo'}.services.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the name of all services Where store name = “Fargo” : " + allServicesWhereStoreNameIsFargo);
        System.out.println("------------------End of Test---------------------------");
    }

    //  Find the zip of all the store
    @Test
    public void test17() {
        List<Integer> zip = response.extract().path("data.zip");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The createdAt : " + zip);
        System.out.println("------------------End of Test---------------------------");
    }

    // Find the zip of store name = Roseville
    @Test
    public void test18() {
        List<Integer> zipList = response.extract().path("data.findAll{it.name=='Roseville'}.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The createdAt : " + zipList);
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the store services details of the service name = Magnolia Home Theater
    @Test
    public void test19() {
        List<?> storeServices = response.extract().path("data.findAll{it.name=='Magnolia Home Theater'}.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the store services details of the service name = Magnolia Home Theater : " + storeServices);
        System.out.println("------------------End of Test---------------------------");
    }

    // Find the lat of all the stores
    @Test
    public void test20() {
        List<Integer> listOfLat = response.extract().path("data.lat");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the lat of all the stores : " + listOfLat);
        System.out.println("------------------End of Test---------------------------");
    }
}