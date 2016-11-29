package logic;

import audioUtil.AudioFeedbackFiles;
import audioUtil.AudioOutput;

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

	AudioOutput output = new AudioOutput();
	AudioFeedbackFiles files = new AudioFeedbackFiles();
	// text1 is command expression;
	// text2 is current status of light;
	public String TextToHub(String text1, String text2) {
		String toReturn = "";
		String expression = text1;
		String status = text2;
		switch (expression) {
			case "help":
				output.output(files.help_main);
				break;

			case "how do i change the brightness":
				output.output(files.help_brightness);
				break;
			default:
				output.output(files.invalid_input);
				break;
			case "switch on":
			switch (status) {
			case "LightAlreadyOn":
//				toReturn = "Well, Light is already on";
				break;
			case "Normal":
				output.output(files.response());
				break;
			case "Failure":
//				toReturn = "Sorry, I can not find the light";
				break;
			}
			case "lights on":
			switch (status) {
			case "LightAlreadyOn":
				break;
			case "Normal":
				output.output(files.response());
				break;
			case "Failure":
				break;
			}


			case "lights off":
			switch (status) {
			case "LightAlreadyOff":
//				toReturn = "Hum, the light is not on now";
				break;
			case "Normal":
//				toReturn = "Ok";
				output.output(files.response());
				break;
			case "Failure":
//				toReturn = "Sorry, I can not find the light";
				break;
			}

			case "switch off":
			switch (status) {
			case "LightAlreadyOff":
//				toReturn = "Hum, the light is not on now";
				break;
			case "Normal":
//				toReturn = "Ok";
				output.output(files.response());
				break;
			case "Failure":
//				toReturn = "Sorry, I can not find the light";
				break;
			}


			case "brighter":
			switch (status) {

			// light should be on first
			case "Normal":
//				toReturn = "10% brigter"

				output.output(files.brightness_brighter);
				// light is not on
			case "Failure":
//				toReturn = "Please turn on the light first";
				break;
			case "full brightness":
//				toReturn = "Sorry, I can not go any brigher now";
				break;
			}

			case "bright":
			switch (status) {

			// light should be on first
			case "Normal":
//				toReturn = "10% brigter"

				output.output(files.brightness_bright);
				// light is not on
			case "Failure":
//				toReturn = "Please turn on the light first";
				break;
			case "full brightness":
//				toReturn = "Sorry, I can not go any brigher now";
				break;
			}

			case "brightest":
			switch (status) {

			// light should be on first
			case "Normal":
//				toReturn = "10% brigter"

				output.output(files.brightness_brightest);
				// light is not on
			case "Failure":
//				toReturn = "Please turn on the light first";
				break;
			case "full brightness":
//				toReturn = "Sorry, I can not go any brigher now";
				break;
			}


			case "dimmer":
			switch (status) {
			// light should be on first
			case "Normal":
//				toReturn = "10% dimmer";

				output.output(files.brightness_dimmer);
				// light is not on
			case "Failure":
//				toReturn = "Please turn on the light first";
				break;
			case "full dim":
//				toReturn = "Sorry, I can not go any dimmer now";
				break;
			}


		}



		return toReturn;
	}

	public static void main(String[] args) {

	}

}
