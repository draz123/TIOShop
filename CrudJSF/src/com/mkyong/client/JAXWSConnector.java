package com.mkyong.client;
 
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import com.mkyong.ws.HelloWorld;

public class JAXWSConnector {
 
	public String managePayment(String data) throws Exception {
 
		URL url = new URL("http://localhost:6006/ws/hello?wsdl");
 
        //1st argument service URI, refer to wsdl document above
		//2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://ws.mkyong.com/", "HelloWorldImplService");
 
        Service service = Service.create(url, qname);
 
        HelloWorld hello = service.getPort(HelloWorld.class);
 
        return hello.getHelloWorldAsString(data);
 
    }
 
}