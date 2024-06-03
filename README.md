# Web Service
Repositório onde é demonstrado um Web Service Básico com JAX-WS.
Para implementar esse WS (Web Service) as dependencias básicas necessárias são as seguintes:

``` xml
       <dependency>
            <groupId>jakarta.xml.ws</groupId>
            <artifactId>jakarta.xml.ws-api</artifactId>
            <version>3.0.0</version>
        </dependency>

        <dependency>
            <groupId>com.sun.xml.ws</groupId>
            <artifactId>jaxws-rt</artifactId>
            <version>3.0.0</version>
        </dependency>

        <dependency>
            <groupId>com.sun.xml.ws</groupId>
            <artifactId>jaxws-ri</artifactId>
            <version>3.0.0</version>
            <type>pom</type>
        </dependency>
```

Logo em seguida, é necessário criarmos uma interface, que fará que disponibilizará nossos serviços:

(Calculator.java) - Service Endpoint Interface (SEI)
``` java
@WebService
@SOAPBinding(style = Style.RPC)
public interface Calculator {
    @WebMethod
    public double add(double a, double b);
    @WebMethod
    public double sub(double a, double b);
    @WebMethod
    public double div(double a, double b);
    @WebMethod
    public double mult(double a, double b);

}
```
É necessário anotar essa interface com @Webservice.
Já a anotação @SOAPBinding, configura o retorno de nosso serviço. 
Deixo uma fonte para maiores informações:
https://developer.ibm.com/articles/ws-whichwsdl/

Também é necessário anotar cada um de nossos métodos (serviços) que serão disponiblizados posteriormente.


Já na implementação é necessário também utilizar a anotação @WebService, porém, apontando para a nossa interface (Calculator)

@WebService(endpointInterface = "com.academia.webservices.servico.Calculator")

(CalculatorImpl.java) - Service Implementation Bean (SIB)
```java
@WebService(endpointInterface = "com.academia.webservices.servico.Calculator")
public class CalculatorImpl implements Calculator {

	@Override
	public double add(double a, double b) {
		return a+b;
	}
.
.
.
```
Após, devemos rodar nosso WS, em uma classe principal:
Criamos uma instância de nosso CalculatorImpl e em seguida publicamos o serviço:
Nesse caso estamos utilizando a porta 8085.
``` java
public class Main {
    public static void main(String[] args) {
        CalculatorImpl calculator = new CalculatorImpl();
        Endpoint.publish("http://localhost:8085/servico/calculator", calculator);
        System.out.println("Serviço publicado com sucesso");
    }
}
```

Após, empacotar a aplicação (maven clean package), localizar o diretório onde está o .jar criado, e rodar a aplicação.
A mensagem Serviço publicaco com sucesso deverá ser mostrada.
``` shell
C:\Users\Lucas\eclipse-workspace\WSCalculatorServer\target>java -jar WSCalculator-1.0-SNAPSHOT.jar
Serviço publicado com sucesso
```

Para confirmar, acessar no navegador o endereço:
http://localhost:8085/servico/calculator
![image](https://github.com/lschlestein/WSCalculatorServer/assets/103784532/b586eb5d-53a6-4920-b0b0-71498edcb1de)

Se tudo estiver ocorrido como o esperado, o WS estará rodando.
É possível testa-lo com o SoapUI no link:
http://localhost:8085/servico/calculator?wsdl




