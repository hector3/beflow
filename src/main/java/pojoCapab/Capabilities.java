package pojoCapab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Capabilities {
	
	public List <nodeProperties> nodeProperties;
	
	public Capabilities(){
		nodeProperties = new ArrayList <nodeProperties>();
	}
	
	
	public wsPojoCap.Capabilities genListPurged(){
		wsPojoCap.Capabilities cap;
		List <wsPojoCap.nodecap> listcap = new ArrayList <wsPojoCap.nodecap>();
		for(nodeProperties np : nodeProperties){
			String mac = np.getNode().getId();
			String capab = np.getProperties().getSupportedFlowActions().getValue();
			String cadenaSinExtremos = capab.substring(1, capab.length()-1).replaceAll("\\s","");
			List<String> listCap = new ArrayList<String>(Arrays.asList(cadenaSinExtremos.split(",")));
			
			listcap.add(new wsPojoCap.nodecap(mac, listCap));
		}
		cap = new wsPojoCap.Capabilities(listcap);
		
		return cap;
	}

	public List <String> genListPurged2(String macRequest){
		wsPojoCap.Capabilities cap;
		//List <wsPojoCap.nodecap> listcap = new ArrayList <wsPojoCap.nodecap>();
		List <String> listCap = null;
		for(nodeProperties np : nodeProperties){
			String mac = np.getProperties().getMacAddress().getValue();
			if(macRequest.contains(mac)){
				String capab = np.getProperties().getSupportedFlowActions().getValue();
				String cadenaSinExtremos = capab.substring(1, capab.length()-1).replaceAll("\\s","");
				listCap = new ArrayList<String>(Arrays.asList(cadenaSinExtremos.split(",")));
				break;
			}
			
			
			
			//listcap.add(new wsPojoCap.nodecap(mac, listCap));
		}
		//cap = new wsPojoCap.Capabilities(listcap);
		
		return listCap;
	}
	

	
}
