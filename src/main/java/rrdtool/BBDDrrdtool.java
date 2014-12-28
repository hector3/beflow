package rrdtool;

import java.io.IOException;

import org.rrd4j.ConsolFun;
import org.rrd4j.DsType;
import org.rrd4j.core.RrdDb;
import org.rrd4j.core.RrdDef;
import org.rrd4j.core.Sample;

public class BBDDrrdtool {

	//para cear las bbdd's de rrdtool, actualizarlas y devolver valores e json
	

/**************************************Definiciones de BBDD************/
	
	//RDD Switch
	public void createswitchBBDD( String name_sw, long date) throws IOException{//date = Util.getTime
		
		
		RrdDef rrdDef = new RrdDef("./"+name_sw+".rrd");//creo un fichero rrd, con el nombre de switch
        rrdDef.setStep(300);//Le indico que los datos seran act cada 5 min
        rrdDef.setStartTime(date);//En que momento empieza a actualizar datos
        
        //variables que se guardaran en el switch
        rrdDef.addDatasource("xxx", DsType.DERIVE, 600, 0, Double.NaN);
        //derive toma el valor antiguo hace la diferencia con el actual, y lo promedia con el tiempo pasado
       
        //como guardo los datos (consolidación)
        
        rrdDef.addArchive(ConsolFun.AVERAGE, 0.5, 1, 12);//guardamos 12 steps de 5min (1hora) en average - datos leidos
        rrdDef.addArchive(ConsolFun.AVERAGE, 0.5, 12, 24);// estadistadisticas de 1 dia marcado por horas
        rrdDef.addArchive(ConsolFun.AVERAGE, 0.5, 288,30);//estadisticas de 1 mes marcado por dias
        
        rrdDef.addArchive(ConsolFun.MAX, 0.5, 1, 12);//consolida el maximo valor
        rrdDef.addArchive(ConsolFun.MAX, 0.5, 12, 24);
        rrdDef.addArchive(ConsolFun.MAX, 0.5, 288,30);
        
        rrdDef.addArchive(ConsolFun.MIN, 0.5, 1, 12);//consolida el min valor
        rrdDef.addArchive(ConsolFun.MIN, 0.5, 12, 24);
        rrdDef.addArchive(ConsolFun.MIN, 0.5, 288,30);
        
        RrdDb rrdDb = new RrdDb(rrdDef); //creo la bbdd con lo que he definido
        rrdDb.close();
		
	}
	
	
	//RDD Puertos buenos
	public void createportBBDD( String name_port, long date) throws IOException{//date = Util.getTime
		
		
		RrdDef rrdDef = new RrdDef("./"+name_port+".rrd");//creo un fichero rrd, con el nombre de switch
        rrdDef.setStep(300);//Le indico que los datos seran act cada 5 min
        rrdDef.setStartTime(date);//En que momento empieza a actualizar datos
        
        //variables que se guardaran en el switch
        rrdDef.addDatasource("xxx", DsType.DERIVE, 600, 0, Double.NaN);
        //derive toma el valor antiguo hace la diferencia con el actual, y lo promedia con el tiempo pasado
       
        //como guardo los datos (consolidación)
        
        rrdDef.addArchive(ConsolFun.AVERAGE, 0.5, 1, 12);//guardamos 12 steps de 5min (1hora) en average - datos leidos
        rrdDef.addArchive(ConsolFun.AVERAGE, 0.5, 12, 24);// estadistadisticas de 1 dia marcado por horas
        rrdDef.addArchive(ConsolFun.AVERAGE, 0.5, 288,30);//estadisticas de 1 mes marcado por dias
        
        rrdDef.addArchive(ConsolFun.MAX, 0.5, 1, 12);//consolida el maximo valor
        rrdDef.addArchive(ConsolFun.MAX, 0.5, 12, 24);
        rrdDef.addArchive(ConsolFun.MAX, 0.5, 288,30);
        
        rrdDef.addArchive(ConsolFun.MIN, 0.5, 1, 12);//consolida el min valor
        rrdDef.addArchive(ConsolFun.MIN, 0.5, 12, 24);
        rrdDef.addArchive(ConsolFun.MIN, 0.5, 288,30);
        
        RrdDb rrdDb = new RrdDb(rrdDef); //creo la bbdd con lo que he definido
        rrdDb.close();
		
	}
	
	//RDD flujos
		public void createflowBBDD( String name_flow, long date) throws IOException{//date = Util.getTime
			
			//coment
			RrdDef rrdDef = new RrdDef("./"+name_flow+".rrd");//creo un fichero rrd, con el nombre de switch
	        rrdDef.setStep(300);//Le indico que los datos seran act cada 5 min
	        rrdDef.setStartTime(date);//En que momento empieza a actualizar datos
	        
	        //variables que se guardaran en el switch
	        rrdDef.addDatasource("xxx", DsType.DERIVE, 600, 0, Double.NaN);
	        //derive toma el valor antiguo hace la diferencia con el actual, y lo promedia con el tiempo pasado
	       
	        //como guardo los datos (consolidación)
	        
	        rrdDef.addArchive(ConsolFun.AVERAGE, 0.5, 1, 12);//guardamos 12 steps de 5min (1hora) en average - datos leidos
	        rrdDef.addArchive(ConsolFun.AVERAGE, 0.5, 12, 24);// estadistadisticas de 1 dia marcado por horas
	        rrdDef.addArchive(ConsolFun.AVERAGE, 0.5, 288,30);//estadisticas de 1 mes marcado por dias
	        
	        rrdDef.addArchive(ConsolFun.MAX, 0.5, 1, 12);//consolida el maximo valor
	        rrdDef.addArchive(ConsolFun.MAX, 0.5, 12, 24);
	        rrdDef.addArchive(ConsolFun.MAX, 0.5, 288,30);
	        
	        rrdDef.addArchive(ConsolFun.MIN, 0.5, 1, 12);//consolida el min valor
	        rrdDef.addArchive(ConsolFun.MIN, 0.5, 12, 24);
	        rrdDef.addArchive(ConsolFun.MIN, 0.5, 288,30);
	        
	        RrdDb rrdDb = new RrdDb(rrdDef); //creo la bbdd con lo que he definido
	        rrdDb.close();
			
		}
		
		
		
		
		/**************************************Actualización BBDD*************/
		
		
		//Actualización Switch
		
		public void updateSWstatistic(String name_sw, double data) throws IOException{
			
			RrdDb rrdDb2 = new RrdDb("./"+name_sw+".rrd");
	        Sample sample = rrdDb2.createSample();
	        sample.setValue("xxx", data);
			rrdDb2.close();
		}
		
		//Actualización PORT
		public void updatePORTstatistic(String name_sw, double data) throws IOException{
			
			RrdDb rrdDb2 = new RrdDb("./"+name_sw+".rrd");
	        Sample sample = rrdDb2.createSample();
	        sample.setValue("xxx", data);
			rrdDb2.close();
		}
		
		//Actualización FLOW
				
		public void updateFLOWstatistic(String name_sw, double data) throws IOException{
					
		RrdDb rrdDb2 = new RrdDb("./"+name_sw+".rrd");
		Sample sample = rrdDb2.createSample();
		sample.setValue("xxx", data);
		rrdDb2.close();
		}
		
	
}