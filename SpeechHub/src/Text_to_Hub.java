import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//import com.fasterxml.jackson.core.JsonGenerationException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;


public class Text_to_Hub {
	
	public String TextToHub (String text) {
		String toReturn = null;
//		String getText = text;
//		JSONObject jObject  = new JSONObject(getText); // json
//		JSONObject data = jObject.getJSONObject("data"); // get data object
//		String projectname = data.getString("name"); // get the name from data.
		
//		ObjectMapper mapper = new ObjectMapper();
		
		
		
		return toReturn;
	}
	public String HelpMenu (String text) {
		String toReturn = null;
		String expression = text;
		switch (expression) {
		case "What can you do?" : 
			toReturn = "I am a simple voice interface for your to control"
					+ "the Philips hue light. You can use your voice to turn on or off"
					+ "the light. ALso you can adjust the brightness and color of the light";
			System.out.println(toReturn);
			
		case "How do I change the brightness?" :
			toReturn = "You could say brighter or dimmer to change the brightness incrementally"
					+ " or you can set the brightness by percentage, by saying set the brightness to "
					+ " 50%. You can also use brightness or dimmer to see the limitation of the"
					+ " brightness";
		}
		return toReturn;
	}
	

	public static void main(String[] args) {
		

	}

}
