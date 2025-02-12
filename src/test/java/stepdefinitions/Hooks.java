package stepdefinitions;

import api.base.BaseAPI;
import api.utils.ConfigManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import org.apache.log4j.Logger;

public class Hooks {
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_CYAN = "\u001B[36m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_GREEN = "\u001B[32m";

    public Logger logger = Logger.getLogger(Hooks.class);

    @Before("@api")
    public void beforeScenario(Scenario scenario)  {
        System.out.println(TEXT_CYAN+"SCENARIO-->>"+ scenario.getName()+ TEXT_RESET);
        RestAssured.baseURI= ConfigManager.getProperty("base.Url");
    }

    @After("@api")
    public void afterScenario(Scenario scenario){
        String status= scenario.getStatus().toString();
        String sceName=scenario.getName();
        if(status.equals("PASSED")){
            System.out.println(TEXT_GREEN+"Scenario: "+sceName+" : "+status);
        }else{
            System.out.println(TEXT_RED+"Scenario: "+sceName+" : "+status);
        }

    }

}

