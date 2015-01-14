package wsPojoControllerFlowConfig;

import java.util.ArrayList;
import java.util.List;



public class WsConfigObject {
	public List <flowConfig> flowConfig;

	public WsConfigObject(List<wsPojoControllerFlowConfig.flowConfig> flowConfig) {
		this.flowConfig = flowConfig;
	}

	public List<flowConfig> getListfc() {
		return flowConfig;
	}

	public void setListfc(List<flowConfig> listfc) {
		this.flowConfig = listfc;
	}

	
	
	
}
