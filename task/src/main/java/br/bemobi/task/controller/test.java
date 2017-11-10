package br.bemobi.task.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

public class test {

	public static void main(String[] args){
		String stringURL = "http://www.guj.com.br";
        String resposta = "";
        try {
        	
        	java.awt.Desktop.getDesktop().browse( new java.net.URI( "www.google.com" ) );

            URL url = new URL(stringURL);
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            
            String inputLine;
            StringBuffer sb = new StringBuffer();
            
            while ((inputLine = in.readLine()) != null) sb.append(inputLine);
            
            resposta = sb.toString();
            
            in.close();
            
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
