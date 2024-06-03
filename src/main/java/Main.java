import com.academia.webservices.servico.CalculatorImpl;
import jakarta.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args) {
        CalculatorImpl calculator = new CalculatorImpl();
        Endpoint.publish("http://localhost:8085/servico/calculator", calculator);
        System.out.println("Serviço publicado com sucesso");
    }
}
