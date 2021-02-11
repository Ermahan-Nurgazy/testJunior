package example;
import com.sun.xml.ws.spi.ProviderImpl;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class HelloWorld {

  public static void main(String[] argv) {
    UserJAXB userJAXB = new UserJAXB();
    userJAXB.marshall();
    userJAXB.unmarshall();
    Object implementor = new UserServiceImpl();
    String address = "http://localhost:9000/UserService";
    ProviderImpl provider = new ProviderImpl();
    provider.createAndPublishEndpoint(address,implementor);

//    Endpoint.publish(address, implementor);
  }
}
