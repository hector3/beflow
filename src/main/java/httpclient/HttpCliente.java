package httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import net.iharder.Base64;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;


public class HttpCliente {

	String user="admin";
	String passwd="admin";
	HttpGet httpget;
	String encoding;
	String url = "http://147.83.118.254:8080/controller/nb/v2/";
	//http://147.83.118.254:8080/controller/nb/v2/statistics/default/port
	HttpClient httpclient = HttpClientBuilder.create().build();
	String result;
	String urlLocal="http://localhost:8080/jersey-quickstart-webapp/beflow/myresource/";
	
	public HttpCliente() {
		encoding = Base64.encodeBytes ((user + ":" + passwd).getBytes());
		
		
	}
	
	public String getNodes(){
		url+="topology/default";
		httpget = new HttpGet(url);
		httpget.setHeader("Authorization", "Basic " + encoding);
		System.out.println("executing request " + httpget.getRequestLine());
		HttpResponse response = null;
		try {
			response = httpclient.execute(httpget);
			System.out.println(response.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpEntity entity = response.getEntity();
		System.out.println(entity.toString());
		try {
			result = getResult(response).toString();
			
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	

	public String getStatistics(){
		url+="statistics/default/port";
		httpget = new HttpGet(url);
		httpget.setHeader("Authorization", "Basic " + encoding);
		System.out.println("executing request " + httpget.getRequestLine());
		HttpResponse response = null;
		try {
			response = httpclient.execute(httpget);
			System.out.println(response.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpEntity entity = response.getEntity();
		System.out.println(entity.toString());
		try {
			result = getResult(response).toString();
			
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}

	private StringBuilder getResult(HttpResponse response) throws IllegalStateException, IOException{
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())),1024);
		String output;
		while ((output=br.readLine()) != null){
			result.append(output);
		}
		
		
		return result;
		
	}	
	
	public String getStatsPurged(){
		urlLocal += "getStatsController";
		httpget = new HttpGet(urlLocal);
		System.out.println("executing request " + httpget.getRequestLine());
		
		HttpResponse response = null;
		try {
			response = httpclient.execute(httpget);
			System.out.println(response.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpEntity entity = response.getEntity();
		System.out.println(entity.toString());
		try {
			result = getResult(response).toString();
			
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
		
	
    
}
