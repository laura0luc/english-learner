package servicios;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class ParseUrl {
	public static String jsonString(   String url   ){		
		Log.i("ParseURL: ","url: "+url);
		StringBuilder bufferParaRespuesta = new StringBuilder(); 
		HttpClient  cliente  = new DefaultHttpClient(); 
		HttpGet httpMetodoGet = new HttpGet( url ); 
		try {
			HttpResponse respuesta  = cliente.execute( httpMetodoGet ); 
					      
			StatusLine lineaDeEstado = respuesta.getStatusLine(   ); 			      
			int codigoDeEstado = lineaDeEstado.getStatusCode(   ); 			      
			if (codigoDeEstado == 200) {			    	  
				HttpEntity entidad = respuesta.getEntity(   ); 				        
				InputStream contenido = entidad.getContent();				        
				BufferedReader lector = new BufferedReader(new InputStreamReader(contenido ) ); 					        
				String linea = "";

				while (  (  linea = lector.readLine()) != null ) { 
					bufferParaRespuesta.append(linea);
				}

			} else {
				Log.e("Error de consulta ", "Error de estado: "  +  codigoDeEstado );
			}
		} catch (ClientProtocolException e) {
			Log.e("Exception ", e.toString(    ) );
		} catch (IOException e) {
			Log.e("Exception ", e.toString(    ) );
		}

		return bufferParaRespuesta.toString();
	}

}
