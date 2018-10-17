package Interface;

public class GlobalAttributes {
	//Attributes
	private double[] windowSize = {1440, 810};
	private String hostID = "";
	private String port = "";
	
	//Methods
	public double getWindowWidth() {
		System.out.println(windowSize[0]);
		return this.windowSize[0];
	}
	
	public void setWindowWidth(double windowWidth) {
		this.windowSize[0] = windowWidth;
	}
	
	public double getWindowHeight() {
		return this.windowSize[1];
	}
	
	public void setWindowHeight(double windowHeight) {
		this.windowSize[1] = windowHeight;
	}

	public double[] getWindowSize() {
		return this.windowSize;
	}

	public void setWindowSize(double[] windowSize) {
		this.windowSize = windowSize;
	}
	
	public String getHostID() {
		return hostID;
	}

	public void setHostID(String hostID) {
		this.hostID = hostID;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

}
