package pojoCapab;

public class properties {

	supportedFlowActions supportedFlowActions;
	macAddress macAddress;
	public properties(pojoCapab.supportedFlowActions supportedFlowActions,
			pojoCapab.macAddress macAddress) {

		this.supportedFlowActions = supportedFlowActions;
		this.macAddress = macAddress;
	}
	public supportedFlowActions getSupportedFlowActions() {
		return supportedFlowActions;
	}
	public void setSupportedFlowActions(supportedFlowActions supportedFlowActions) {
		this.supportedFlowActions = supportedFlowActions;
	}
	public macAddress getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(macAddress macAddress) {
		this.macAddress = macAddress;
	}
	
	

	
	
	
}
