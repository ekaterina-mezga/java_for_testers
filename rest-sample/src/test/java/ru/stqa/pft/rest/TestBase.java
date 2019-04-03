package ru.stqa.pft.rest;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;

public class TestBase {

  public void skipIfNotFixed(int issueId) {
    if (isIssueOpen(issueId)) {
      System.out.println("Ignored because of issue " + issueId);
      throw new SkipException("");
    }
  }

  public boolean isIssueOpen(int issueId){
    Issue issue = getIssueById(issueId);
    if (issue.getState().equals("Open")){
      return true;
    } else return false;
  }

  public Issue getIssueById(int id){
    String json = RestAssured.get(String.format("http://bugify.stqa.ru/api/issues/%s.json", id)).asString();
    JsonElement parsed = new JsonParser().parse(json);
    String subject = parsed.getAsJsonObject()
            .get("issues").getAsJsonArray().get(0).getAsJsonObject().get("subject").getAsString();
    String description = parsed.getAsJsonObject()
            .get("issues").getAsJsonArray().get(0).getAsJsonObject().get("description").getAsString();
    String state = parsed.getAsJsonObject()
            .get("issues").getAsJsonArray().get(0).getAsJsonObject().get("state_name").getAsString();
    return new Issue().withId(id).withSubject(subject).withDescription(description).withState(state);
  }


}
