package com.automationexercices.apis;

import com.automationexercices.utils.dataReader.PropertyReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.util.Map;

import static io.restassured.RestAssured.baseURI;

public class Builder {
    private static String baseURI= PropertyReader.getProperty("baseUrlApi");

    private  Builder(){
        //private constructor to prevent instantiation
    }

    //just build request specification
    public static RequestSpecification getUserManagementRequestSpecification(Map <String, ?> formParams)
    {
        return new RequestSpecBuilder().setBaseUri(baseURI)
                .setContentType(ContentType.URLENC)
                .addFormParams(formParams)
                .build();
    }
}
