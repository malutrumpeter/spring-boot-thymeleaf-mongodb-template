package models;

public class ArduinoPin {

	private String type;
	private String value;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getData() {
		return value;
	}

	public void setData(String data) {
		this.value = data;
	}

	@Override
	public String toString() {
		return "ArduinoPin [type=" + type + ", value=" + value + "]";
	}

}
