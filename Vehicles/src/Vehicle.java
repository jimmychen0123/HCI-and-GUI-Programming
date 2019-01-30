
abstract class Vehicle { //abstract class can not be instantiated
	
	protected String make = "not set";
	protected String model = "not set";
	protected String colour = "not set";
	protected String regNum = "not set";
	
	protected String chassisNum;
	protected String testCert = "";

	public Vehicle (String rn) {
		regNum = rn;
	}//constructor
	

	public Vehicle() {
		
			
	}//Default constructor
	
	//Vehicle getter methods
	protected String getMake() {return make;}
	protected String getModel() {return model;}
	protected String getColour() {return colour;}
	protected String getChassisNum() {return chassisNum;}
	protected String getReg() {return regNum;}
	protected String getTestCertificate() {return testCert;}
	
	//Vehicle setter methods
	protected void setMake(String m) {make = m;}
	protected void setModel(String m) {model = m;}
	protected void setColour(String c) {colour = c;}
	protected void setChassisNum(String chass) {chassisNum = chass;}
	protected void setReg(String r) {regNum = r;}
	protected void setTestCertificate(String cert) {testCert = cert;}
	
	
	
	
	

}//class
