package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RestAssuredTests extends TestBase{

  @BeforeClass
  public void init(){
    RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
  }

  @Test
  public void testsCreateIssue() {
    Set<Issue> oldIssues = getIssues();
    Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues = getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }

  @Test
  public void testsEditSubject(){
    skipIfNotFixed(1040);
    long now = System.currentTimeMillis();
    Set<Issue> issues = getIssues();
    Issue issue;
    if (issues.iterator().hasNext()){
      issue = issues.iterator().next();
    } else throw new Error("Create issue!");
    int issueId = issue.getId();
    String oldSubject = issue.getSubject();
    editSubject(issue, oldSubject + now);
    String newSubject = getIssueById(issueId).getSubject();
    assertTrue(newSubject.equals(oldSubject + now));
  }

  private Set<Issue> getIssues() {
    String json = RestAssured.get("http://bugify.stqa.ru/api/issues.json?limit=200").asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
  }

  private int createIssue(Issue newIssue) {
    String json = RestAssured.given()
            .parameter("subject", newIssue.getSubject())
            .parameter("description", newIssue.getDescription())
            .post("http://bugify.stqa.ru/api/issues.json?limit=200").asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

  private void editSubject(Issue issue, String updatedSubject){
    RestAssured.given()
            .parameter("issue[subject]", updatedSubject)
            .parameter("method", "update")
            .post(String.format("http://bugify.stqa.ru/api/issues/%s.json", issue.getId())).asString();
  }

}
