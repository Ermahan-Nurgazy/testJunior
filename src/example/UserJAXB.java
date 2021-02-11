package example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class UserJAXB {
    public void marshall(){
        try {
            User user = new User(1,"Ermahan","Nurgazy", 137810512,"ermahan.nurgazy","Alma1212");
            JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            marshaller.marshal(user,System.out);
            marshaller.marshal(user, new File("src\\data\\User.xml"));
        }catch (Exception e){
            System.out.println(""+ e.getMessage());
        }
    }
    public void unmarshall(){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            User user = (User) unmarshaller.unmarshal(new File("src\\data\\User.xml"));
            System.out.println("User information:");
            System.out.println("ID: " + user.getId());
            System.out.println("Username: " + user.getUserName());
            System.out.println("Lastname: " + user.getLastName());
        }catch (Exception e){
            System.out.println(""+ e.getMessage());
        }
    }
}
