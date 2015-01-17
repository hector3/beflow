package rrdtool;

import java.io.IOException;
import java.util.List;

import org.rrd4j.core.Util;

import pojoStats.ListPortStatistics;
import pojoStats.portStatistics;
import wsPojoStats.ListPorts;
import wsPojoStats.ListWsStats;
import wsPojoStats.PortSwitch;
import wsPojoStats.WsObjectStats;
import wsobjects.CredWS;
import httpclient.HttpCliente;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



public class RRDThread implements Runnable{

	
	HttpCliente httpcliente= new HttpCliente();
	Gson gson = new Gson();
	String objetoEnJson;
	
	public void getStatistics(){
		
		//recojo estadisticas
		
		
		getPortStatistics();
		
			
		System.out.println("Recogiendo estadísticas.. De momento no hace nada a la espera de que se levante el servidor que aloja el controller");
		
		
		
			
	
		
		
		//fin de código para recuperar los valores	
		
		
		//devuelve JSON (esto realmente irá en la clase principal de webservice( de momento en pruebas)
		//BBDDrrdtool bbdd2 = new BBDDrrdtool();
		//bbdd2.getJson("opendaylight", "2d");
		
	}
	

	
	
	
	//metodo que retorna stadisticas en un objeto
	
	public void getPortStatistics(){
		
		//código para recuperar los valores
		objetoEnJson=httpcliente.getStatsPurged();
		
		//System.out.println(objetoEnJson);
		
		final ListWsStats listWsObjectStats = gson.fromJson(objetoEnJson, ListWsStats.class);
				
		for (WsObjectStats lws : listWsObjectStats.getListWsObjectStats()){
			
			//System.out.println("MAC: "+lws.getMac());
			
			for(PortSwitch ps: lws.getListPorts()){
				
				//System.out.println("Puerto: "+ps.getPortId());
				
				String name_bbdd= lws.getMac()+"_"+ps.getPortId();//MAC_numpuerto (nombre bbdd)
				
				System.out.println("Paquetes recibidos: "+ps.getReceivePackets());
				System.out.println("Paquetes enviados: "+ps.getTransmitPackets());
				rrdupdateport(name_bbdd,ps.getReceivePackets(), ps.getTransmitPackets());		
			}
		}
		
	}
	
	//metodo actualiza la bbdd
	
	public void rrdupdateport(String name, double received, double transmitted){
		
		BBDDrrdtool bbdd = new BBDDrrdtool();
		
		if(!bbdd.fileExist(name)){
			
			System.out.println("bbdd no existe crearla y actualizarla");
			
			//creo bbdd
			try {
				bbdd.createportBBDD(name, Util.getTime());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//actualizo bbdd
			try {
				bbdd.updatePORTstatistic(name, received, transmitted);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else{
			System.out.println("bbdd existe actualizarla");
			try {
				bbdd.updatePORTstatistic(name, received, transmitted);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	//el thread se ejecutara cada 30 segundos
	public void run() {
	
		while (true){
			try {

				getStatistics();//recojo estadisticas
				Thread.sleep(30000);//milisegundos
				System.out.println("Estoy dentro del run");
				
			} catch (InterruptedException e) {
				// Se ha interrumpido el thread
				System.out.println("Thread interrumpido. Excepción: "+e.getMessage());
			}
		}
			
	}
}




