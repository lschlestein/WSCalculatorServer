# Web Service (Server)
## Objetivo do Repositório
Este repositório tem como objetivo demonstrar a criação de um Web Service (WS) básico utilizando JAX-WS. 
Ele fornece um exemplo prático de como implementar um web service, 
desde a configuração das dependências necessárias até a implementação e publicação do serviço.

## O que são Web Services?
Web Services são componentes de software que permitem a comunicação entre diferentes aplicações através da web. Eles utilizam padrões abertos como XML, SOAP, WSDL e UDDI para garantir a interoperabilidade entre diferentes sistemas. 
Existem dois principais tipos de Web Services: SOAP (Simple Object Access Protocol) e REST (Representational State Transfer).

Neste projeto, estamos utilizando SOAP, um protocolo baseado em XML que permite a comunicação entre aplicações através de mensagens HTTP.

### Dependências Utilizadas
Para implementar um Web Service com JAX-WS, são necessárias algumas dependências básicas no projeto Maven. Estas dependências são responsáveis por fornecer as bibliotecas necessárias para a criação e execução dos serviços web. As principais dependências utilizadas são:

- jakarta.xml.ws-api: Fornece a API JAX-WS para criar e consumir Web Services. A versão utilizada é 3.0.0.
- jaxws-rt: Implementação de referência (runtime) de JAX-WS, fornecida pela Sun/Oracle. A versão utilizada é 3.0.0.
- jaxws-ri: Runtime implementation (RI) de JAX-WS, também fornecida pela Sun/Oracle. A versão utilizada é 3.0.0.
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
## Implementação do Web Service

### Interface de Serviço (Service Endpoint Interface - SEI) - (Calculator.java)
A interface do serviço define os métodos que serão disponibilizados pelo Web Service. Ela deve ser anotada com @WebService e os métodos devem ser anotados com @WebMethod.
Já a anotação @SOAPBinding, configura o retorno de nosso serviço. Assim o lado client, poderá acessar os métodos e serviço posteriormente.
Deixo uma fonte para maiores informações:
https://developer.ibm.com/articles/ws-whichwsdl/
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

### Implementação do Serviço (Service Implementation Bean - SIB) - (CalculatorImpl.java)

A implementação do serviço deve implementar a interface definida anteriormente e também deve ser anotada com @WebService, especificando a interface que ela implementa.

@WebService(endpointInterface = "com.webservices.service.Calculator")

```java
@WebService(endpointInterface = "com.webservices.service.Calculator")
public class CalculatorImpl implements Calculator {

	@Override
	public double add(double a, double b) {
		return a+b;
	}
.
.
.
```
### Publicação do Serviço 

Após, devemos rodar nosso Web Service:
Criamos uma instância de nosso CalculatorImpl e em seguida publicamos o serviço:
Nesse caso estamos utilizando a porta 8085.
``` java
public class Main {
    public static void main(String[] args) {
        CalculatorImpl calculator = new CalculatorImpl();
        Endpoint.publish("http://localhost:8085/service/calculator", calculator);
        System.out.println("Web Service successfully published");
    }
}
```
## Empacotamento do jar com Maven

Para empacotar a aplicação é necessário configurar arquivo pom.xml, para copiar as depedências para dentro da pasta /libs conforme segue:
```xml
<build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-dependency-plugin</artifactId>
                <executions>
                    <execution>
                        <id>copy-dependencies</id>
                        <phase>prepare-package</phase>
                        <goals>
                            <goal>copy-dependencies</goal>
                        </goals>
                        <configuration>
                            <outputDirectory>
                                ${project.build.directory}/libs
                            </outputDirectory>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>3.4.1</version>
                <configuration>
                    <archive>
                        <manifest>
                            <addClasspath>true</addClasspath>
                            <classpathPrefix>libs/</classpathPrefix>
                            <mainClass>
                                Main
                            </mainClass>
                        </manifest>
                    </archive>
                </configuration>
            </plugin>
        </plugins>
    </build>
```
### Executando o Web Service
Após, empacotar a aplicação (com `maven clean package`), localizar o diretório onde está o .jar criado, e rodar a aplicação.
A mensagem Serviço publicado com sucesso deverá ser mostrada.
``` shell
C:\Users\Lucas\eclipse-workspace\WSCalculatorServer\target>java -jar WSCalculator-1.0-SNAPSHOT.jar
Web Service successfully published
```
## Teste do Web Service
Para confirmar, acessar no navegador o endereço:
http://localhost:8085/service/calculator
![image](https://github.com/lschlestein/WSCalculatorServer/assets/103784532/b586eb5d-53a6-4920-b0b0-71498edcb1de)

Se tudo estiver ocorrido como o esperado, o WS estará rodando.
Para testar o Web Service, você pode utilizar ferramentas como o SoapUI e acessar o WSDL no seguinte link:
http://localhost:8085/service/calculator?wsdl




