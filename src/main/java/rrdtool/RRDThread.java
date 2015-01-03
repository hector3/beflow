package rrdtool;

import java.util.List;

import pojoStats.ListPortStatistics;
import pojoStats.portStatistics;
import httpclient.HttpCliente;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



public class RRDThread implements Runnable{

	public void getStatistics(){
		
		//recojo estadisticas
		
		
		
		
		System.out.println("Thread");
	}
	
	
	//metodo que retorna stadisticas en un objeto
	
	public ListPortStatistics getPortStatistics(){
		
		Gson gson = new Gson();
		HttpCliente http = new HttpCliente();
		String json=http.getStatistics();
		
		ListPortStatistics lps = gson.fromJson(json, ListPortStatistics.class);
		
		System.out.println("SOY YO?");
		List <portStatistics> lista= (List<portStatistics>) lps.genListPurged();
		
		for (portStatistics p : lista){
			//System.out.println(p.getNode());
		}
		return lps;
	}
	//el thread se ejecutara cada 30 segundos
	public void run() {
	
		while (true){
			try {

				getStatistics();//recojo estadisticas
				Thread.sleep(5000);//milisegundos
				
			} catch (InterruptedException e) {
				// Se ha interrumpido el thread
				
			}
		}
			
	}
}




