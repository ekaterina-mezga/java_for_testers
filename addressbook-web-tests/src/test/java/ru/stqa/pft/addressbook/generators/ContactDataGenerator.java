package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  public static void main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);

    List<ContactData> contacts = generateContacts(count);
    save(contacts, file);
  }

  private static void save(List<ContactData> contacts, File file) throws IOException {
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

  private static List<ContactData> generateContacts(int count) {
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
