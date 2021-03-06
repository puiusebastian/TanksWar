package test;

import java.io.StringReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import webservices.Users; //to use class Users

public class ClientTest {
	public static void main(String[] args) {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		
		

		WebTarget target = client.target(getBaseURI());
		
		String accArray="{\"username\":\"ilie\",\"password\":\"yoki\"}"; // {"username":"wanted username","password":"wanted password"}  
		JsonObject jsonObject=Json.createReader(new StringReader(accArray)).readObject();
		//check if user "tank" with password "tank" exists
		System.out.println(target.path("api").path("checkuser").request(MediaType.TEXT_PLAIN)
				.post(Entity.entity(jsonObject,MediaType.APPLICATION_JSON),Boolean.class));
		
		
		/*System.out.println(target.path("api").path("ssw").path("getUsers").request().accept(MediaType.APPLICATION_JSON).get(String.class));
		System.out.println(target.path("api/ssw/getTanks").request().accept(MediaType.APPLICATION_JSON).get(String.class));*/
		//save list
		//List<Users> users=new ArrayList<Users>();
		
		JsonArray users;
		users=target.path("api").path("ssw").path("getUsers").request().accept(MediaType.APPLICATION_JSON).get(JsonArray.class);
		for(JsonValue user:users)
		{
			JsonObject js=(JsonObject) user;
			System.out.println(user);
		}
		
		
		//test insert user
		/*JsonObject userObject=Json.createReader(new StringReader("{\"username\":\"player\",\"password\":\"player\",\"name\":\"player\",\"email\":\"player@yahoo.com\",\"age\":22}")).readObject();
		if(target.path("api/ssw/insertuser").request(MediaType.TEXT_PLAIN).post(Entity.entity(userObject, MediaType.APPLICATION_JSON),Boolean.class)){
			System.out.println("User was inserted!");
		}
		else {
			System.out.println("User wasn't inserted!");
		}*/
		
		//test delete, id is autoincrement so after a delete it won't be the same as the last time
		/*if(target.path("api/ssw/delete").path("5").request(MediaType.TEXT_PLAIN).accept(MediaType.APPLICATION_JSON).delete(Boolean.class)) {
			System.out.println("User was deleted!");
		}else {
			System.out.println("User wasn't deleted!");
		}*/
		
		//test update user
		/*JsonObject userUpdateObject=Json.createReader(new StringReader("{\"username\":\"player\",\"password\":\"player\",\"name\":\"Lucifer\",\"email\":\"player@yahoo.com\",\"age\":22}")).readObject();
		if(target.path("api/ssw/update/14").request(MediaType.TEXT_PLAIN).put(Entity.entity(userUpdateObject, MediaType.APPLICATION_JSON),Boolean.class)){
			System.out.println("User was updated!");
		}
		else {
			System.out.println("User wasn't updated!");
		}*/
		
		//test insert in tank_picked
		/*JsonObject tpObject=Json.createReader(new StringReader("{\"user\":\"seby_boss\",\"tank\":1}")).readObject();
		if(target.path("api/ssw/inserttankpicked").request(MediaType.TEXT_PLAIN).post(Entity.entity(tpObject, MediaType.APPLICATION_JSON),Boolean.class)){
			System.out.println("Line in tank_picked was inserted!");
		}
		else {
			System.out.println("Line in tank_picked wasn't inserted!");
		}*/
		
		//print table tank_picked GET
		//System.out.println(target.path("api/ssw/gettankpicked").request().accept(MediaType.APPLICATION_JSON).get(JsonArray.class));
		
		//delete in tank_picked DELETE
		/*if(target.path("api/ssw/deletetankpicked").path("seby_boss").request(MediaType.TEXT_PLAIN).accept(MediaType.APPLICATION_JSON).delete(Boolean.class)) {
			System.out.println("Line in tank_picked was deleted!");
		}else {
			System.out.println("Line in tank_picked wasn't deleted!");
		}*/
		
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8888/").build();
	}
}