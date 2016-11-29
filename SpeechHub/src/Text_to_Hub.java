import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//import com.fasterxml.jackson.core.JsonGenerationException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;

//import org.json.JSONObject;
public class Text_to_Hub {

	// text1 is command expression;
	// text2 is current status of light;
	public String TextToHub(String text1, String text2) {
		String toReturn = null;
		String expression = text1;
		String status = text2;
		switch (expression) {
		case "LightsOn":
			switch (status) {
			case "LightAlreadyOn":
				toReturn = "Well, Light is already on";
				break;
			case "Normal":
				toReturn = "Ok";
				break;
			case "Failure":
				toReturn = "Sorry, I can not find the light";
				break;
			}

		case "LightsOff":
			switch (status) {
			case "LightAlreadyOff":
				toReturn = "Hum, the light is not on now";
				break;
			case "Normal":
				toReturn = "Ok";
				break;
			case "Failure":
				toReturn = "Sorry, I can not find the light";
				break;
			}

		case "brighter":
			switch (status) {

			// light should be on first
			case "LightAlreadyOn":
				toReturn = "10% brigter";

				// light is not on
			case "Failure":
				toReturn = "Please turn on the light first";
				break;
			case "full brightness":
				toReturn = "Sorry, I can not go any brigher now";
				break;
			}

		case "dimmer":
			switch (status) {
			// light should be on first
			case "LightAlreadyOn":
				toReturn = "10% dimmer";

				// light is not on
			case "Failure":
				toReturn = "Please turn on the light first";
				break;
			case "full dim":
				toReturn = "Sorry, I can not go any dimmer now";
				break;
			}

		}
		// String getText = text;
		// JSONObject jObject = new JSONObject(getText); // json
		// JSONObject data = jObject.getJSONObject("data"); // get data object
		// String projectname = data.getString("name"); // get the name from
		// data.

		// ObjectMapper mapper = new ObjectMapper();

		return toReturn;
	}

	public String HelpMenu(String text) {
		String toReturn = null;
		String expression = text;
		switch (expression) {
		case "What can you do?":
			toReturn = "I am a simple voice interface for your to control"
					+ "the Philips hue light. You can use your voice to turn on or off"
					+ "the light. ALso you can adjust the brightness and color of the light";
			System.out.println(toReturn);

		case "How do I change the brightness?":
			toReturn = "You could say brighter or dimmer to change the brightness incrementally"
					+ " or you can set the brightness by percentage, by saying set the brightness to "
					+ " 50%. You can also use brightness or dimmer to see the limitation of the" + " brightness";
			System.out.println(toReturn);
		}
		return toReturn;
	}
	
	public String stringToJson (String input) {
		String toReturn = null;
		
		return toReturn;
	}

	public static void main(String[] args) {

	}

}
