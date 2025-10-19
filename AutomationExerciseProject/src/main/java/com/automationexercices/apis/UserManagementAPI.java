package com.automationexercices.apis;

import com.automationexercices.utils.logs.LogsManager;
import com.automationexercices.validations.Verification;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class UserManagementAPI {
    RequestSpecification requestSpec;
    Response response;
    Verification verification;


    public UserManagementAPI(){
        requestSpec = RestAssured.given();
        verification = new Verification();
    }


    //endpoints
    private static final String createAccount_endpoint = "/createAccount";
    private static final String deleteAccount_endpoint = "/deleteAccount";

    //api methods
    @Step("Create new user account")
    public UserManagementAPI createAccount(String name, String email, String password,
                                           String title, String birth_date, String birth_month,
                                           String birth_year, String firstname, String lastname,
                                           String company, String address1, String address2,
                                           String country, String zipcode, String state,
                                           String city, String mobile_number)
    {
            Map<String,String> formParams= new HashMap<>();
            formParams.put("name",name);
            formParams.put("email",email);
            formParams.put("password",password);
            formParams.put("title",title);
            formParams.put("birth_date",birth_date);
            formParams.put("birth_month",birth_month);
            formParams.put("birth_year",birth_year);
            formParams.put("firstname",firstname);
            formParams.put("lastname",lastname);
            formParams.put("company",company);
            formParams.put("address1",address1);
            formParams.put("address2",address2);
            formParams.put("country",country);
            formParams.put("zipcode",zipcode);
            formParams.put("state",state);
            formParams.put("city",city);
            formParams.put("mobile_number",mobile_number);
            response = requestSpec.spec(Builder.getUserManagementRequestSpecification(formParams))
                    .post(createAccount_endpoint);
        LogsManager.info(response.asPrettyString());

        return this;
    }

    @Step("Create new user account with minimum required fields")
    public UserManagementAPI createAccountMinReqFields(String name, String email, String password,
                                           String firstname, String lastname)
    {
        Map<String,String> formParams= new HashMap<>();
        formParams.put("name",name);
        formParams.put("email",email);
        formParams.put("password",password);
        formParams.put("title","Mr");
        formParams.put("birth_date","1");
        formParams.put("birth_month","1");
        formParams.put("birth_year","2000");
        formParams.put("firstname",firstname);
        formParams.put("lastname",lastname);
        formParams.put("company","ITI");
        formParams.put("address1","address1");
        formParams.put("address2","address2");
        formParams.put("country","India");
        formParams.put("zipcode","12345");
        formParams.put("state","state");
        formParams.put("city","city");
        formParams.put("mobile_number","1234567890");
        response = requestSpec.spec(Builder.getUserManagementRequestSpecification(formParams))
                .post(createAccount_endpoint);
        LogsManager.info(response.asPrettyString());

        return this;
    }







    @Step("Delete user account")
    public UserManagementAPI deleteAccount(String email, String password){
        Map<String,String> formParams= new HashMap<>();
        formParams.put("email",email);
        formParams.put("password",password);
        response = requestSpec.spec(Builder.getUserManagementRequestSpecification(formParams))
                .delete(deleteAccount_endpoint);
        LogsManager.info(response.asPrettyString());
        return this;
    }



    //validation
    @Step("Verify that user is created successfully")
    public UserManagementAPI verifyUserCreatedSuccessfully(){
        verification.Equals(response.jsonPath().get("message"),"User created!",
                "User isn't created successfully");
        return this;
    }

    @Step("Verify that user is deleted successfully")
    public UserManagementAPI verifyUserDeletedSuccessfully(){
        verification.Equals(response.jsonPath().get("message"),"Account deleted!","User isn't deleted successfully");
        return this;
    }
}
