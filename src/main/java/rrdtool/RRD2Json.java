package rrdtool;

import java.io.IOException;

import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;

import org.rrd4j.ConsolFun;
import org.rrd4j.core.FetchData;
import org.rrd4j.core.FetchRequest;
import org.rrd4j.core.RrdBackendFactory;
import org.rrd4j.core.RrdDb;
import org.rrd4j.core.Util;

public class RRD2Json {

		
	public String generateJson(String name_bbdd,long endTime, long startTime, ConsolFun fun) throws RRD2JsonException {
		String rrdJson = "";
		try {
			
		RrdDb rrdDb3 = new RrdDb("/var/www/html/beflow/rrdtool/"+name_bbdd+".rrd");
			
       	FetchRequest fetchRequest = rrdDb3.createFetchRequest(fun,startTime,endTime);
       	FetchData fetchData = fetchRequest.fetchData();
       	
       	// generate json output out of the rrd database
     	XMLSerializer xmlSerializer = new XMLSerializer();
     	JSON json = xmlSerializer.read(fetchData.exportXml());
     	rrdJson = json.toString();
       	
     	//System.out.println(fetchData.getValues("speed"));
       	rrdDb3.close();
			
		} catch (IOException e) {
			throw new RRD2JsonException("Failed to generate Json output");
		}
		
		return rrdJson;
	}
	
}