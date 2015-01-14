package httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.ws.rs.core.Response;

import net.iharder.Base64;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;

import flowController.Flow;


public class HttpCliente {

	String user="admin";
	String passwd="admin";
	HttpGet httpget;
	HttpPut httpput;
	HttpDelete httpdel;
	String encoding;
	String url = "http://147.83.118.254:8080/controller/nb/v2/";
	//http://147.83.118.254:8080/controller/nb/v2/statistics/default/port
	HttpClient httpclient = HttpClientBuilder.create().build();
	String result;
	String urlLocal="http://localhost:8000/jersey-quickstart-webapp/beflow/myresource/";
	
	public HttpCliente() {
		encoding = Base64.encodeBytes ((user + ":" + passwd).getBytes());
		
		
	}
	
	public String getNodes(){
		String url = "http://147.83.118.254:8080/controller/nb/v2/topology/default";
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
		String urlGetStats = "http://147.83.118.254:8080/controller/nb/v2/statistics/default/port";
		httpget = new HttpGet(urlGetStats);
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
		String urlLocalStats="http://localhost:8080/jersey-quickstart-webapp/beflow/myresource/getStatsController";
		httpget = new HttpGet(urlLocalStats);
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
		
	public String addflowController(String idNode, String name, Flow flow) throws UnsupportedEncodingException{
		Gson gson = new Gson();
		String flowEnJson = gson.toJson(flow);
		StringEntity entidad = new StringEntity(flowEnJson);
		String urlController="http://147.83.118.254:8080/controller/nb/v2/flowprogrammer/default/node/OF/"+idNode+"/staticFlow/"+name;
		httpput = new HttpPut(urlController);
		httpput.setHeader("Authorization", "Basic " + encoding);
		httpput.setHeader("Content-Type","application/json");
		httpput.setEntity(entidad);
		System.out.println("executing request " + httpput.getRequestLine());
		
		HttpResponse response = null;
		try {
			response = httpclient.execute(httpput);
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

	public String getFlows(){
		String urlGetFlows = "http://147.83.118.254:8080/controller/nb/v2/flowprogrammer/default";
		httpget = new HttpGet(urlGetFlows);
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
	
	public String getNodesController(){
		String urlGetNodes = "http://147.83.118.254:8080/controller/nb/v2/switchmanager/default/nodes";
		httpget = new HttpGet(urlGetNodes);
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

	public StatusLine delFlow(String mac, String name){
		String urlDelFlow = "http://147.83.118.254:8080/controller/nb/v2/flowprogrammer/default/node/OF/"+mac+"/staticFlow/"+name;
		System.out.println(urlDelFlow);
		httpdel = new HttpDelete(urlDelFlow);
		httpdel.setHeader("Authorization", "Basic " + encoding);
		System.out.println("executing request " + httpdel.getRequestLine());
		HttpResponse response = null;
		try {
			response = httpclient.execute(httpdel);
			System.out.println(response.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		//HttpEntity entity = response.getEntity();
		//System.out.println(entity.toString());
		try {
			result = getResult(response).toString();
			
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		return response.getStatusLine();
		
	}
	
	
}
