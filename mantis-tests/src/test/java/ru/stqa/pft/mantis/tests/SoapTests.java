package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SoapTests extends TestBase{

  @Test
  public void testGetProjects() throws RemoteException, ServiceException, MalformedURLException {

    Set<Project> projects = app.soap().getProjects();
    System.out.println(projects.size());
    for (Project project : projects){
      System.out.println(project.getName());
    }
  }

  @Test
  public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException{

    Set<Project> projects = app.soap().getProjects();
    long now = System.currentTimeMillis();
    Issue issue = new Issue().withSummary(String.format("Test Issue%s", now)).withDescription(String.format("Test Issue%s Description", now))
            .withProject(projects.iterator().next());
    Issue created = app.soap().addIssue(issue);
    assertEquals(issue.getSummary(), created.getSummary());
  }

  @Test
  public void testAddNote() throws RemoteException, ServiceException, MalformedURLException {

    long now = System.currentTimeMillis();
    String noteText = String.format("some_note_text%s", now);
    Issue updatedIssue = app.soap().addNote(noteText);
    ArrayList<String> notesText = app.soap().getNotesText(updatedIssue.getNotes());
    assertTrue(notesText.contains(noteText));
  }
}
