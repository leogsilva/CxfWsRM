package br.com.devmedia.helloworldservice;

import java.net.MalformedURLException;
import java.util.Calendar;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.frontend.ClientProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class HelloWorldServiceTest {

	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	Bus bus;

	public HelloService getHelloServiceRef() throws MalformedURLException {
        ClientProxyFactoryBean factory = new ClientProxyFactoryBean();
        factory.setServiceClass(HelloService.class);
        factory.setAddress("http://localhost:9292/hello");
        factory.getFeatures().add(new LoggingFeature());
        factory.setBus(bus);
        
        HelloService helloService = factory.create(HelloService.class);
        Client client = ClientProxy.getClient(helloService);
        HTTPConduit http = (HTTPConduit) client.getConduit();
        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
        httpClientPolicy.setDecoupledEndpoint("http://localhost:9292/decoupled_endpoint");
        http.setClient(httpClientPolicy);
		return helloService;
	}

	
	@Test
	public void testNormal() throws Exception {
		HelloService hello = getHelloServiceRef();
		PersonInfo info = new PersonInfo();
		info.setName("John Doe");
//		info = hello.digaOla(info);
		Assert.assertEquals("John Doe", info.getName());
		try {
			System.out.println("Inicio: " + Calendar.getInstance().getTime());
			info.setSleep(false);
			hello.digaAdeus(info);
			info.setSleep(true);
			hello.digaAdeus(info);
			System.out.println("Fim: " + Calendar.getInstance().getTime());
		} catch (Throwable t) {
			System.out.println("Fim: " + Calendar.getInstance().getTime());
			t.printStackTrace();
		}
		Thread.sleep(5000);
	}


}
