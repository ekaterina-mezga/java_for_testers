package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contacts count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    save(contacts, new File(file));
  }

  private void save(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts){
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstName(), contact.getLastName(), contact.getAddress(),
              contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(),
              contact.getEmail(), contact.getEmail2(), contact.getEmail3(), contact.getGroup(),
              contact.getPhoto()));
    }
    writer.close();
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    File photo = new File("src/test/resources/icon.png ");
    for (int i=0; i< count; i++){
      contacts.add(new ContactData().withFirstName(String.format("Sherlock %s", i)).withLastName(String.format("Holmes %s", i))
              .withAddress(String.format("London Backer str. 221b-%s", i)).withHomePhone(String.format("222 22 2%s", i))
              .withMobilePhone(String.format("333 33 3%s", i)).withWorkPhone(String.format("444 44 4%s", i))
              .withEmail(String.format("sherlock%s@test.test", i)).withEmail2(String.format("sherlock2%s@test.test", i))
              .withEmail3(String.format("sherlock3%s@test.test", i)).withGroup(String.format("group %s", i)).withPhoto(photo));
    }
    return contacts;
  }
}
