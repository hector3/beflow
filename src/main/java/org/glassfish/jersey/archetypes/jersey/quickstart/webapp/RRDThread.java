package org.glassfish.jersey.archetypes.jersey.quickstart.webapp;

public class RRDThread implements Runnable{

	public void getStatistics(){
		System.out.println("Thread");
	}
	
	//el thread se ejecutara cada 30 segundos
	public void run() {
	
		getStatistics();//recojo estadisticas
		try {
			Thread.sleep(5000);//milisegundos	
		} catch (InterruptedException e) {
			// Se ha interrumpido el thread
			return;
		}
		System.out.println("thread interrumpido");	
	}
}




