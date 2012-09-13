package utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

public class ConnectionUtil {
	public static boolean delete(String session){
		
		return false;
	}

	public static StringBuffer postLogin(String stringUrl, String username,
			String password) {
		StringBuffer result = null;
		HttpClient httpClient = new DefaultHttpClient(); 
		HttpPost httpPost = new HttpPost(stringUrl); 

		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs
					.add(new BasicNameValuePair("_username", username));
			nameValuePairs.add(new BasicNameValuePair("_password", password));
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpClient.execute(httpPost);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			Log.i("Estatus: ", ""+statusCode);
			if (statusCode == 201) {
				result = new StringBuffer(); 
				HttpEntity entidad = response.getEntity(); 

				InputStream contenido = entidad.getContent(); 
				BufferedReader lector = new BufferedReader(
						new InputStreamReader(contenido)); 
				String linea = null;
				while ((linea = lector.readLine()) != null) { 
					result.append(linea);
				}
			}

		} catch (ClientProtocolException e) {
			Log.i("ClientProtocolException: ", e.getMessage());
		} catch (IOException e) {
			Log.i("IOException: ", e.getMessage());
		}
		if(result != null){
			Log.i("Session: ", result.toString());			
		}

		return result;

	}

	public static boolean deleteSession(String url) {
		boolean result = false;
		HttpClient httpClient = new DefaultHttpClient(); 
		HttpDelete httpDelete = new HttpDelete(url); 

		try {
			HttpResponse response = httpClient.execute(httpDelete);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			Log.i("Estatus: ", ""+statusCode);
			if (statusCode == 204) {
				result = true;
			}
		} catch (ClientProtocolException e) {
			Log.i("ClientProtocolException: ", e.getMessage());
		} catch (IOException e) {
			Log.i("IOException: ", e.getMessage());
		}

		return result;
	}
}
