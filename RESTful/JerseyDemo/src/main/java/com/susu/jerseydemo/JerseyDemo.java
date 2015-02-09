package com.susu.jerseydemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.susu.jerseydemo.metadata.Student;

@Path("/students")
public class JerseyDemo {
	
	private static int index = 1;
	private static Map<Integer, Student> studentList = new HashMap<Integer, Student>();
	
	public JerseyDemo() {
		if (studentList.size() == 0) {
			studentList.put(index, new Student(index++, "Frank", "CS"));
			studentList.put(index, new Student(index++, "Jersey", "Math"));
		}
	}

	@GET
	@Path("{studentid}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Student getMetadata(@PathParam("studentid") int studentid) {
		if (studentList.containsKey(studentid))
			return studentList.get(studentid);
		else
			return new Student(0, "Nil", "Nil");
	}

	@GET
	@Path("list")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Student> getAllStudents() {
		List<Student> students = new ArrayList<Student>();
		students.addAll(studentList.values());
		return students;
	}

	@POST
	@Path("add")
	@Produces("text/plain")
	public String addStudent(@FormParam("name") String name,
			@FormParam("dept") String dept) {
		studentList.put(index, new Student(index++, name, dept));
		return String.valueOf(index - 1);
	}

	@DELETE
	@Path("delete/{studentid}")
	@Produces("text/plain")
	public String removeStudent(@PathParam("studentid") int studentid) {
		Student removed = studentList.remove(studentid);
		if (removed == null)
			return "failed!";
		else
			return "true";
	}

	@PUT
	@Path("put")
	@Produces("text/plain")
	public String putStudent(@QueryParam("studentid") int studentid,
			@QueryParam("name") String name, @QueryParam("dept") String dept) {
		if (!studentList.containsKey(studentid))
			return "failed!";
		else
			studentList.put(studentid, new Student(studentid, name, dept));

		return String.valueOf(studentid);
	}
}