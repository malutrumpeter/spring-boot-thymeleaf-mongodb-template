package controller;

import java.util.ArrayList;
import java.util.Map;

import models.ArduinoPin;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.mongodb.util.JSON;

@Controller
public class MainController {

	private boolean LedState = false;

	private String http = "http://";
	private String ipAddress = "192.168.1.212";
	private String httpAddress = http + ipAddress;
	private String command = "";
	private int digitalPinUp = 2;
	private int digitalPinDown = 3;
	private int digitalPinStop = 4;
	private int on = 1;
	private int off = 0;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showHome(Map<String, Object> model) {

		boolean isDeviceConnected = checkDeviceConnection(model);

		if (isDeviceConnected == false) {
			return "home";
		}

		boolean currentLedState = checkLedState();

		if (currentLedState == true) {
			model.put("ledButtonText", "PIN 13 OFF");
		} else {
			model.put("ledButtonText", "PIN 13 ON");
		}

		return "home";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/arduino/ledToggle", method = RequestMethod.GET)
	public String arduinoLedToggle(Map<String, Object> model) {

		String arduinoReponseString = "";
		String URL = "";

		arduinoReponseString = toggleLed(model);

		System.out.println(arduinoReponseString);

		/*
		 * JSONParser jParser = new JSONParser(); try { JSONObject jObject =
		 * (JSONObject) jParser .parse(arduinoReponseString); String type =
		 * (String) jObject.get("type"); long value = (long)
		 * jObject.get("value"); } catch (ParseException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		return "home";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/blind/up", method = RequestMethod.GET)
	public String blindMoveUp(Map<String, Object> model) {

		boolean isDeviceConnected = checkDeviceConnection(model);
		if (isDeviceConnected == false) {
			return "home";
		}

		String command = "/arduino/digital/" + digitalPinUp + "/" + on;

		String arduinoReponseString = sendCommandToDevice(command);

		System.out.println(arduinoReponseString);

		command = "/arduino/digital/" + digitalPinUp + "/" + off;

		arduinoReponseString = sendCommandToDevice(command);

		System.out.println(arduinoReponseString);

		/*
		 * JSONParser jParser = new JSONParser(); try { JSONObject jObject =
		 * (JSONObject) jParser .parse(arduinoReponseString); String type =
		 * (String) jObject.get("type"); long value = (long)
		 * jObject.get("value"); } catch (ParseException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		return "home";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/blind/down", method = RequestMethod.GET)
	public String blindMoveDown(Map<String, Object> model) {

		boolean isDeviceConnected = checkDeviceConnection(model);
		if (isDeviceConnected == false) {
			return "home";
		}

		String arduinoReponseString = sendCommandToDevice("/arduino/digital/"
				+ digitalPinDown + "/" + on);

		System.out.println(arduinoReponseString);

		arduinoReponseString = sendCommandToDevice("/arduino/digital/"
				+ digitalPinDown + "/" + off);

		System.out.println(arduinoReponseString);

		/*
		 * JSONParser jParser = new JSONParser(); try { JSONObject jObject =
		 * (JSONObject) jParser .parse(arduinoReponseString); String type =
		 * (String) jObject.get("type"); long value = (long)
		 * jObject.get("value"); } catch (ParseException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		return "home";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/blind/stop", method = RequestMethod.GET)
	public String blindMoveStop(Map<String, Object> model) {

		boolean isDeviceConnected = checkDeviceConnection(model);
		if (isDeviceConnected == false) {
			return "home";
		}

		String arduinoReponseString = sendCommandToDevice("/arduino/digital/"
				+ digitalPinStop + "/" + on);

		System.out.println(arduinoReponseString);

		arduinoReponseString = sendCommandToDevice("/arduino/digital/"
				+ digitalPinStop + "/" + off);

		System.out.println(arduinoReponseString);

		JSONParser jParser = new JSONParser();

		return "home";
	}

	private String sendCommandToDevice(String command) {
		RestTemplate restTemplate = new RestTemplate();
		String URL = httpAddress + command;

		System.out.println(URL);
		String arduinoReponseString = "";
		try {
			arduinoReponseString = restTemplate.getForObject(URL, String.class);
		} catch (RestClientException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return arduinoReponseString;
	}

	private String toggleLed(Map<String, Object> model) {
		RestTemplate restTemplate = new RestTemplate();
		String arduinoReponseString;

		boolean curentLedState = checkLedState();
		String URL;
		if (curentLedState == true) {
			curentLedState = false;
			// URL = httpAddress + "/arduino/digital/13/0";
			arduinoReponseString = sendCommandToDevice("/arduino/digital/13/0");
			model.put("ledButtonText", "PIN 13 ON");
		} else {
			curentLedState = true;
			// URL = httpAddress + "/arduino/digital/13/1";
			arduinoReponseString = sendCommandToDevice("/arduino/digital/13/1");
			model.put("ledButtonText", "PIN 13 OFF");
		}
		// arduinoReponseString = restTemplate.getForObject(URL, String.class);
		return arduinoReponseString;
	}

	private boolean checkLedState() {
		String arduinoReponseString = sendCommandToDevice("/arduino/digital/13/");

		// long value = 0;
		boolean ledState = false;
		String[] responseArray = arduinoReponseString.split(" ");
		String value = responseArray[4];
		System.out.println("value: " + value);
		value = value.trim();
		if (value.equals("0")) {
			ledState = false;
		}

		if (value.equals("1")) {
			ledState = true;
		}

		/*
		 * JSONParser jParser = new JSONParser(); try { JSONObject jObject =
		 * (JSONObject) jParser .parse(arduinoReponseString); String type =
		 * (String) jObject.get("type"); value = (long) jObject.get("value"); if
		 * (value == 1) { ledState = true; } else ledState = false; } catch
		 * (ParseException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		return ledState;
	}

	private boolean checkDeviceConnection(Map<String, Object> model) {
		RestTemplate restTemplate = new RestTemplate();
		String URL = httpAddress + "/arduino/digital/13";
		String arduinoReponseString = "";
		try {
			arduinoReponseString = restTemplate.getForObject(URL, String.class);
			return true;
		} catch (RestClientException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			model.put("errorMessage", "device not connected");
			return false;
		}
	}

}
