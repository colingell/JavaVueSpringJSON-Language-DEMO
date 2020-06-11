package com.utilitylocker.interview.controllers.subfolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;
import java.net.URL;
import java.net.HttpURLConnection;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.utilitylocker.interview.jsonConnect;




@Controller
public class MainController {

	protected String json = "";
	protected String data = "";


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        // this attribute will be available in the view index.html as a thymeleaf variable
        //model.addAttribute("products", "TEST");
        
	
		try
		{
			URL url = new URL("http://gellispatz.xyz/utilityLocker/mobile.json");
			//Parse URL into HttpURLConnection in order to open the connection in order to get the JSON data
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			//Set the request to GET or POST as per the requirements
			conn.setRequestMethod("GET");
			//Use the connect method to create the connection bridge
			conn.connect();
			//Get the response status of the Rest API
			int responsecode = conn.getResponseCode();
			//System.out.println("Response code is: " +responsecode);
			
			//Iterating condition to if response code is not 200 then throw a runtime exception
			//else continue the actual process of getting the JSON data
			if(responsecode != 200)
				throw new RuntimeException("HttpResponseCode: " +responsecode);
			else
			{
				//Scanner functionality will read the JSON data from the stream
				Scanner sc = new Scanner(url.openStream());
				while(sc.hasNext())
				{
					json+=sc.nextLine();
				}
				sc.close();
				//System.out.println("\nJSON Response in String format"); 
				//System.out.println(json);
				//Close the stream when reading the data has been finished
				ObjectMapper mapper = new ObjectMapper();
				
  					//objectMapper.readValue(new URL(url));
				model.addAttribute("products", mapper.writeValueAsString(json));
				//model.addAttribute("products", json);
				//model.addAttribute("products", mapper.readValue(new URL(url));

				
			}

				
		}
		
			
			
		
		catch(Exception e)
		{
			e.printStackTrace();
		}


        return "index";
 
 }

 
}
