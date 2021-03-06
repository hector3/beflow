package rrdtool;

import httpclient.HttpCliente;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.glassfish.jersey.archetypes.jersey.quickstart.webapp.CRUD;
import org.glassfish.jersey.archetypes.jersey.quickstart.webapp.Node;
import org.rrd4j.ConsolFun;
import org.rrd4j.DsType;
import org.rrd4j.core.RrdDb;
import org.rrd4j.core.RrdDef;
import org.rrd4j.core.Sample;
import org.rrd4j.core.Util;
import org.rrd4j.graph.RrdGraph;
import org.rrd4j.graph.RrdGraphDef;

import com.google.gson.Gson;

import wsPojoStats.ListWsStats;
import wsPojoStats.PortSwitch;
import wsPojoStats.WsObjectStats;
import wsobjects.CompanyWS;
import wspolicies.Consum;

public class BBDDrrdtool {

	//para cear las bbdd's de rrdtool, actualizarlas y devolver valores e json
	

/**************************************Definiciones de BBDD************/
	
	//RDD Switch **** DE MOMENTO NO
	public void createswitchBBDD( String name_sw, long date) throws IOException{//date = Util.getTime
		
		
		RrdDef rrdDef = new RrdDef("/var/www/html/beflow/rrdtool/"+name_sw+".rrd");//creo un fichero rrd, con el nombre de switch
        rrdDef.setStep(300);//Le indico que los datos seran act cada 5 min
        rrdDef.setStartTime(date);//En que momento empieza a actualizar datos
        
        //variables que se guardaran en el switch
        rrdDef.addDatasource("xxx", DsType.DERIVE, 600, 0, Double.NaN);
        //derive toma el valor antiguo hace la diferencia con el actual, y lo promedia con el tiempo pasado
       
        //como guardo los datos (consolidación)
        
        rrdDef.addArchive(ConsolFun.AVERAGE, 0.5, 1, 120);//guardamos 12 steps de 5min (1hora) en average - datos leidos
        rrdDef.addArchive(ConsolFun.AVERAGE, 0.5, 120, 24);// estadistadisticas de 1 dia marcado por horas
        rrdDef.addArchive(ConsolFun.AVERAGE, 0.5, 2880,30);//estadisticas de 1 mes marcado por dias
        
        rrdDef.addArchive(ConsolFun.MAX, 0.5, 1, 120);//consolida el maximo valor
        rrdDef.addArchive(ConsolFun.MAX, 0.5, 120, 24);
        rrdDef.addArchive(ConsolFun.MAX, 0.5, 2880,30);
        
        rrdDef.addArchive(ConsolFun.MIN, 0.5, 1, 120);//consolida el min valor
        rrdDef.addArchive(ConsolFun.MIN, 0.5, 120, 24);
        rrdDef.addArchive(ConsolFun.MIN, 0.5, 2880,30);
        
        RrdDb rrdDb = new RrdDb(rrdDef); //creo la bbdd con lo que he definido
        rrdDb.close();
		
	}
	
	
	//RDD Puertos
	
	public void createportBBDD( String name_port, long date) throws IOException{
		
		
		RrdDef rrdDef = new RrdDef("/var/www/html/beflow/rrdtool/"+name_port+".rrd");//creo un fichero rrd, con el nombre de switch
        rrdDef.setStep(30);//Le indico que los datos seran act cada 30 seg
        rrdDef.setStartTime(date);//En que momento empieza a actualizar datos
        
        //variables que se guardaran del puerto
        rrdDef.addDatasource("IN", DsType.DERIVE, 600, 0, Double.NaN);
        rrdDef.addDatasource("OUT", DsType.DERIVE, 600, 0, Double.NaN);
        
        
        rrdDef.addDatasource("DROP IN", DsType.DERIVE, 600, 0, Double.NaN);
        rrdDef.addDatasource("DROP OUT", DsType.DERIVE, 600, 0, Double.NaN);
        
        rrdDef.addDatasource("ERROR IN", DsType.DERIVE, 600, 0, Double.NaN);
        rrdDef.addDatasource("ERROR OUT", DsType.DERIVE, 600, 0, Double.NaN);
        //derive toma el valor antiguo hace la diferencia con el actual, y lo promedia con el tiempo pasado
       
        //como guardo los datos (consolidación)
        
        rrdDef.addArchive(ConsolFun.AVERAGE, 0.5, 1, 120);//guardamos 120 steps de 30 seg (1hora) en average - datos leidos
        rrdDef.addArchive(ConsolFun.AVERAGE, 0.5, 120, 24);// estadistadisticas de 1 dia marcado por horas
        rrdDef.addArchive(ConsolFun.AVERAGE, 0.5, 2880,30);//estadisticas de 1 mes marcado por dias
        
        rrdDef.addArchive(ConsolFun.MAX, 0.5, 1, 120);//consolida el maximo valor
        rrdDef.addArchive(ConsolFun.MAX, 0.5, 120, 24);
        rrdDef.addArchive(ConsolFun.MAX, 0.5, 2880,30);
        
        rrdDef.addArchive(ConsolFun.MIN, 0.5, 1, 120);//consolida el min valor
        rrdDef.addArchive(ConsolFun.MIN, 0.5, 120, 24);
        rrdDef.addArchive(ConsolFun.MIN, 0.5, 2880,30);
        
        rrdDef.addArchive(ConsolFun.LAST, 0.5, 1, 120);//consolida el último valor
        rrdDef.addArchive(ConsolFun.LAST, 0.5, 120, 24);
        rrdDef.addArchive(ConsolFun.LAST, 0.5, 2880,30);
        
        RrdDb rrdDb = new RrdDb(rrdDef); //creo la bbdd con lo que he definido
        rrdDb.close();
		
	}
	
	//RDD flujos
		public void createflowBBDD( String name_flow, long date) throws IOException{//date = Util.getTime
			
			//coment
			RrdDef rrdDef = new RrdDef("/var/www/html/beflow/rrdtool"+name_flow+".rrd");//creo un fichero rrd, con el nombre de switch
	        rrdDef.setStep(300);//Le indico que los datos seran act cada 5 min
	        rrdDef.setStartTime(date);//En que momento empieza a actualizar datos
	        
	        //variables que se guardaran en el switch
	        rrdDef.addDatasource("xxx", DsType.DERIVE, 600, 0, Double.NaN);
	        //derive toma el valor antiguo hace la diferencia con el actual, y lo promedia con el tiempo pasado
	       
	        //como guardo los datos (consolidación)
	        
	        rrdDef.addArchive(ConsolFun.AVERAGE, 0.5, 1, 120);//guardamos 12 steps de 5min (1hora) en average - datos leidos
	        rrdDef.addArchive(ConsolFun.AVERAGE, 0.5, 120, 24);// estadistadisticas de 1 dia marcado por horas
	        rrdDef.addArchive(ConsolFun.AVERAGE, 0.5, 2880,30);//estadisticas de 1 mes marcado por dias
	        
	        rrdDef.addArchive(ConsolFun.MAX, 0.5, 1, 120);//consolida el maximo valor
	        rrdDef.addArchive(ConsolFun.MAX, 0.5, 120, 24);
	        rrdDef.addArchive(ConsolFun.MAX, 0.5, 2880,30);
	        
	        rrdDef.addArchive(ConsolFun.MIN, 0.5, 1, 120);//consolida el min valor
	        rrdDef.addArchive(ConsolFun.MIN, 0.5, 120, 24);
	        rrdDef.addArchive(ConsolFun.MIN, 0.5, 2880,30);
	        
	        rrdDef.addArchive(ConsolFun.LAST, 0.5, 1, 120);//consolida el min valor
	        rrdDef.addArchive(ConsolFun.LAST, 0.5, 120, 24);
	        rrdDef.addArchive(ConsolFun.LAST, 0.5, 2880,30);
	        
	        RrdDb rrdDb = new RrdDb(rrdDef); //creo la bbdd con lo que he definido
	        rrdDb.close();
			
		}
		
		
		
		
		/**************************************Actualización BBDD*************/
		
		
		//Actualización Switch
		
		public void updateSWstatistic(String name_sw, double data) throws IOException{
			
			RrdDb rrdDb2 = new RrdDb("/var/www/html/beflow/rrdtool"+name_sw+".rrd");
	        Sample sample = rrdDb2.createSample();
	        sample.setValue("xxx", data);
			rrdDb2.close();
		}
		
		//Actualización PORT
		public void updatePORTstatistic(String name_port, double receiveBytes, double transmitBytes,double receiveDrops, double transmitDrops, double receiveErrors,double transmitErrors) throws IOException{
			
			RrdDb rrdDb2 = new RrdDb("/var/www/html/beflow/rrdtool/"+name_port+".rrd");
			//no se puede actualizar bbdd en un momento ya actualizado
			
			if (Util.getTime() == rrdDb2.getLastUpdateTime() ){
				System.out.println(" no se puede actualizar datos, ya actualizada");
			}
			
			else{
				
				receiveBytes = receiveBytes*8; // bits
				transmitBytes=  transmitBytes*8;
				
		        Sample sample = rrdDb2.createSample();
		        sample.setTime(Util.getTime());
		        
		        sample.setValue("IN", receiveBytes);
		        sample.setValue("OUT", transmitBytes);
		     
		        sample.setValue("DROP IN", receiveDrops);
		        sample.setValue("DROP OUT", transmitDrops);
		        
		        //System.out.println("bytes"+ receiveBytes);
		       // System.out.println("Errors"+receiveErrors);
		        
		        sample.setValue("ERROR IN", receiveErrors);
		        sample.setValue("ERROR OUT", transmitErrors);

		        sample.update();
				rrdDb2.close();
			}
		}
		
		//Actualización FLOW
				
		public void updateFLOWstatistic(String name_sw, double data) throws IOException{
					
		RrdDb rrdDb2 = new RrdDb("/var/www/html/beflow/rrdtool/"+name_sw+".rrd");
		Sample sample = rrdDb2.createSample();
		sample.setValue("xxx", data);
		rrdDb2.close();
		}
		
		
		/********************* comprovación de si existe BBDD*********/
		
		public boolean fileExist (String name){
		
			File rrdFile=new File("/var/www/html/beflow/rrdtool/"+name+".rrd");
			if (rrdFile.exists()) {
			  return true;	
		    }
			else{
			  return false;
			}
		}
		
		
		/********************* devuelve datos en json *********/
		
		public String getJson(String name_bbdd, String granularidad){
		
			int resta=0;
			resta = getGranularidad(granularidad);
			System.out.println(resta);
			 // initialize RRD options
	       	 long endTime = Util.getTime();//hasta la actualidad
	       	 long startTime = endTime-(resta*60*60L);
	       	 ConsolFun fun = ConsolFun.AVERAGE; //que consolidacion quiero
	       	 
	       	 // create RRD Converter instance
	       	 RRD2Json converter = new RRD2Json();
	
	       	 // generate json
	       	 String json ="";
			
	       	 try {
				json = converter.generateJson(name_bbdd,endTime,startTime, fun);
			} catch (RRD2JsonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       	 System.out.println(json);
       	 
       	 return json;
		}
		
		
		/********************* devuelve grafica 
		 * @throws IOException *********/
		
		public String genGraph (String name_bbdd, String granularidad) throws IOException{
			
			
		
			String titulo ="";
			
			String MAC =name_bbdd.substring(0,23);
			String port_number= name_bbdd.substring(24);
			
			System.out.println(MAC+"      "+port_number);
			titulo = titulo(MAC,port_number);
		
			
			int resta=0;
			resta = getGranularidad(granularidad);//granularidad fija de 24 h
			long endTime = Util.getTime();//hasta la actualidad
			long startTime = endTime-(resta*60L);
			String response="";
			//Definición del grafico
			
			RrdGraphDef graphDef = new RrdGraphDef();
			
			graphDef.setFilename("/var/www/html/beflow/rrdtool/"+titulo+".png");
			graphDef.setImageFormat("png");
			
			
			graphDef.setTitle("Traffic - Node "+titulo);
			graphDef.setVerticalLabel("Bits per second");
			graphDef.setWidth(500);
	    	graphDef.setHeight(150);
	    	//graphDef.setBase(1000);
	    	//graphDef.setAltAutoscaleMax(true);
	    	

	    	graphDef.setTimeSpan(startTime, endTime);
	    	graphDef.datasource("IN", "/var/www/html/beflow/rrdtool/"+name_bbdd+".rrd", "IN", ConsolFun.AVERAGE);
	    	graphDef.datasource("OUT", "/var/www/html/beflow/rrdtool/"+name_bbdd+".rrd", "OUT", ConsolFun.AVERAGE);
	    	//
	    	graphDef.datasource("DROP IN", "/var/www/html/beflow/rrdtool/"+name_bbdd+".rrd", "DROP IN", ConsolFun.AVERAGE);
	    	graphDef.datasource("DROP OUT", "/var/www/html/beflow/rrdtool/"+name_bbdd+".rrd", "DROP OUT", ConsolFun.AVERAGE);
	    	
	    	graphDef.datasource("ERROR IN", "/var/www/html/beflow/rrdtool/"+name_bbdd+".rrd", "ERROR IN", ConsolFun.AVERAGE);
	    	graphDef.datasource("ERROR OUT", "/var/www/html/beflow/rrdtool/"+name_bbdd+".rrd", "ERROR OUT", ConsolFun.AVERAGE);
	    	
	    	
	    	
	    	graphDef.area("IN", Color.GREEN, "Inbound         ");
	    	graphDef.gprint("IN", ConsolFun.LAST, "Current:  %.3f%S");
	    	graphDef.gprint("IN", ConsolFun.AVERAGE, "Average:  %.3f%S");
	    	graphDef.gprint("IN", ConsolFun.MAX, "Maximum:  %.3f%S\\l");
	    	
	    	graphDef.line("OUT", Color.BLUE, "Outbound        ",2);
	    	graphDef.gprint("OUT", ConsolFun.LAST, "Current:  %.3f%S");
	    	graphDef.gprint("OUT", ConsolFun.AVERAGE, "Average:  %.3f%S");
	    	graphDef.gprint("OUT", ConsolFun.MAX, "Maximum:  %.3f%S\\l");
	    	graphDef.comment("\\r");
	    	graphDef.line("DROP IN", Color.RED, "Inbound Drops   ",2);
	    	graphDef.gprint("DROP IN", ConsolFun.LAST, "Current:  %.3f%S");
	    	graphDef.gprint("DROP IN", ConsolFun.AVERAGE, "Average:  %.3f%S");
	    	graphDef.gprint("DROP IN", ConsolFun.MAX, "Maximum:  %.3f%S\\l");

	    	graphDef.line("DROP OUT", Color.ORANGE, "Outbound Drops  ",2);
	    	graphDef.gprint("DROP OUT", ConsolFun.LAST, "Current:  %.3f%S");
	    	graphDef.gprint("DROP OUT", ConsolFun.AVERAGE, "Average:  %.3f%S");
	    	graphDef.gprint("DROP OUT", ConsolFun.MAX, "Maximum:  %.3f%S\\l");
	    	graphDef.comment("\\r");
	    	graphDef.line("ERROR IN", Color.MAGENTA, "Inbound Errors  ",2);
	    	graphDef.gprint("ERROR IN", ConsolFun.LAST, "Current:  %.3f%S");
	    	graphDef.gprint("ERROR IN", ConsolFun.AVERAGE, "Average:  %.3f%S");
	    	graphDef.gprint("ERROR IN", ConsolFun.MAX, "Maximum:  %.3f%S\\l");

	    	graphDef.line("ERROR OUT", Color.PINK, "Outbound Errors ",2);
	    	graphDef.gprint("ERROR OUT", ConsolFun.LAST, "Current:  %.3f%S");
	    	graphDef.gprint("ERROR OUT", ConsolFun.AVERAGE, "Average:  %.3f%S");
	    	graphDef.gprint("ERROR OUT", ConsolFun.MAX, "Maximum:  %.3f%S\\l");
	    	
	    	//%8.2lf %s
	    	graphDef.comment("\\r");
	    	graphDef.comment("Period From "+Util.getDate(startTime)+" To "+Util.getDate(endTime)+"\\l");
	    	graphDef.comment("BEFLOW \\r");
	    	RrdGraph graph;
	    	graph = new RrdGraph(graphDef);
	    	BufferedImage bi = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
	    	graph.render(bi.getGraphics());
		
	    	
	    	//devuelvo link donde se ha generado la grafica
	    	response = "http://147.83.113.109/beflow/rrdtool/"+titulo+".png";
	    	return response;
		}
		
		
		public String titulo(String MAC, String port){
			
			Node node = new Node();
			String name_bbdd="";
			CRUD crud =new CRUD();
			
			try{
				node =crud.read_node2(MAC, port);
				name_bbdd = node.getNode_name();
			} catch (Exception e){
				
				//no hay datos en la bbdd
				name_bbdd= "Switch :"+MAC+" port: "+port;
			}
			
			
			return name_bbdd;
		}
		
		
		
		
		/*******************Policies
		 * @throws IOException **********************/
		
		public Consum getConsum(String node_name) throws IOException{

			String objetoJson ="";
			HttpCliente httpcliente= new HttpCliente();
			Gson gson = new Gson();
			Consum c = null;
			
			objetoJson=httpcliente.getStatsPurged();
			
			//System.out.println(objetoEnJson);
			
			final ListWsStats listWsObjectStats = gson.fromJson(objetoJson, ListWsStats.class);
					
			for (WsObjectStats lws : listWsObjectStats.getListWsObjectStats()){
				
				//System.out.println("MAC: "+lws.getMac());
				
				for(PortSwitch ps: lws.getListPorts()){
					
					//System.out.println("Puerto: "+ps.getPortId());
					String name_bbdd= lws.getMac()+"_"+ps.getPortId();//MAC_numpuerto (nombre bbdd)
					
					
					if(node_name.equals(name_bbdd)){
						System.out.println(name_bbdd);
						System.out.println(node_name);	
						System.out.println("Datos consumidos Entrada: "+ps.getReceiveBytes());
						System.out.println("Datos consumidos Salida: "+ps.getTransmitBytes());		
						c =new Consum(ps.getReceiveBytes(),ps.getTransmitBytes());
						
										
					}
				}
			}
			return c;	
			
		}
/********************* granularidad *********/
		
		public int getGranularidad(String granularidad){
			
			String var =granularidad.substring(0,2);
			String param = granularidad.substring(2);
			String num = "";
			
			//compruebo si contiene el campo &
			
			if(var.substring(1).equals("&")){
				
				num = var.substring(0,1);
			}else num = var;
		
			
			
			System.out.println(num+"   "+ param);
			
			int start =0;
			
			switch (param){
			
			
				case "-":
				start =  Integer.parseInt(num);
				
				break;
				
				
				case "h":
					start =  Integer.parseInt(num)*60;
					break;
					
				case "d":
					start =  Integer.parseInt(num)*24*60;
					break;
					
				case "w":
					start =  Integer.parseInt(num)*7*24*60;
					break;
					
				case "m":
					
					start =  Integer.parseInt(num)*30*24*60;
					break;
				}
				
				return start;
			
		}
		
	
}