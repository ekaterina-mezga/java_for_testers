package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {

  private ApplicationManager app;

  public SoapHelper(ApplicationManager app) {
    this.app = app;
  }

  private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
    return new MantisConnectLocator()
            .getMantisConnectPort(new URL(app.getProperty("soap.connectUrl")));
  }

  public Set<Project> getProjects() throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    ProjectData[] projects = mc.mc_projects_get_user_accessible(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    return Arrays.asList(projects).stream().map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
  }

  public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    String[] categories = mc.mc_project_get_categories(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(issue.getProject().getId()));
    IssueData issueData = new IssueData();
    issueData.setSummary(issue.getSummary());
    issueData.setDescription(issue.getDescription());
    issueData.setCategory(categories[0]);
    issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
    BigInteger issueId = mc.mc_issue_add(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), issueData);
    IssueData createdIssueData = mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), issueId);
    return new Issue().withId(createdIssueData.getId().intValue()).withSummary(createdIssueData.getSummary())
            .withDescription(createdIssueData.getDescription())
            .withProject(new Project().withId(createdIssueData.getProject().getId().intValue())
                                      .withName(createdIssueData.getProject().getName()));
  }

  public Issue addNote(String noteText) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    IssueNoteData note = new IssueNoteData();
    note.setText(noteText);
    Issue issue = getSomeIssue();
    mc.mc_issue_note_add(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(issue.getId()), note);
    IssueData updatedIssue = mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(issue.getId()));
    return new Issue().withId(updatedIssue.getId().intValue()).withSummary(updatedIssue.getSummary())
            .withDescription(updatedIssue.getDescription())
            .withProject(new Project().withId(updatedIssue.getProject().getId().intValue()).withName(updatedIssue.getProject().getName()))
            .withNotes(new ArrayList<>(Arrays.asList(updatedIssue.getNotes())));
  }

  public Issue getSomeIssue() throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    BigInteger issueId = mc.mc_issue_get_biggest_id(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"),
            BigInteger.valueOf(getProjects().iterator().next().getId()));
    IssueData issue = mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), issueId);
    return new Issue().withId(issueId.intValue()).withSummary(issue.getSummary())
            .withDescription(issue.getDescription())
            .withProject(new Project().withId(issue.getProject().getId().intValue()).withName(issue.getProject().getName()))
            .withNotes(new ArrayList<>(Arrays.asList(issue.getNotes())));
  }

  public IssueNoteData convertTextToNote(String text){
    IssueNoteData note = new IssueNoteData();
    note.setText(text);
    return note;
  }

  public ArrayList<String> getNotesText(ArrayList<IssueNoteData> notes) {
    ArrayList<String> text = new ArrayList<>();
    for (IssueNoteData note : notes){
      text.add(note.getText());
    }
    return text;
  }

  public boolean isNotFixed(int id) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    IssueData issue = mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(id));
    ObjectRef[] statuses = mc.mc_enum_status(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    String status = issue.getStatus().getName();
    if (status.equals(statuses[statuses.length - 1].getName())){
      return false;
    } else { return true; }
  }
}
