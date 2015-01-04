package rrdtool;

import java.io.IOException;
import java.util.List;

import org.rrd4j.core.Util;

import pojoStats.ListPortStatistics;
import pojoStats.portStatistics;
import httpclient.HttpCliente;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



public class RRDThread implements Runnable{

	int in = 0;
	int out = 0;
	public void getStatistics(){
		
		//recojo estadisticas
		
		
		getPortStatistics();
		rrdupdateport("opendaylight",in, out);
		System.out.println(in+" "+ out);
		in = in + 3000000;
		out = out +200000;
		
		System.out.println("Recogiendo estadísticas.. De momento no hace nada a la espera de que se levante el servidor que aloja el controller");
		
		
		//devuelve JSON (esto realmente irá en la clase principal de webservice( de momento en pruebas)
		//BBDDrrdtool bbdd2 = new BBDDrrdtool();
		//bbdd2.getJson("opendaylight", "2d");
		
	}
	

	
	
	
	//metodo que retorna stadisticas en un objeto
	
	public ListPortStatistics getPortStatistics(){
		//******************Ho farem quan tinguem connexió amb el controller**//
		
		/*Gson gson = new Gson();
		HttpCliente http = new HttpCliente();
		String json=http.getStatistics();
		
		ListPortStatistics lps = gson.fromJson(json, ListPortStatistics.class);
		
		System.out.println("SOY YO?");
		List <portStatistics> lista= (List<portStatistics>) lps.genListPurged();
		
		for (portStatistics p : lista){
			
			//System.out.println(p.getNode());
		}
		return lps;
		*/
		return null;
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




