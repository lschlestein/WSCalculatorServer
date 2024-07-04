import com.webservices.service.CalculatorImpl;
import jakarta.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args) {
        CalculatorImpl calculator = new CalculatorImpl();
        Endpoint.publish("http://localhost:8085/service/calculator", calculator);
        System.out.println("Web Service successfully published");
    }
}
