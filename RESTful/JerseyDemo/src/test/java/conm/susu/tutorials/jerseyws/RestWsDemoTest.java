package conm.susu.tutorials.jerseyws;

import junit.framework.TestCase;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import com.susu.jerseydemo.metadata.Student;

public class RestWsDemoTest extends TestCase {

	private String url = "http://localhost:8080/JerseyDemo/test/students";

	public void testDelete() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(url).path("delete/1");
		Response response = webTarget.request().delete();
		int expect = 200;
		int actual = response.getStatus();
		assertEquals(expect, actual);
	}

	public void testPut() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(url).path("/put");
		Student student = new Student(5, "nameTest", "deptTest");
		Response response = webTarget.request().buildPut(Entity.entity(student, MediaType.APPLICATION_XML)).invoke();
		int expect = 200;
		int actual = response.getStatus();
		assertEquals(expect, actual);
	}
}