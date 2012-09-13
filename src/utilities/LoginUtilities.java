package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;

import android.util.Log;


public class LoginUtilities {
	public StringBuffer getSession(String userName, String password) {
		 StringBuffer session= null;
		try {
			userName = URLEncoder.encode(userName, "UTF-8");
			password = URLEncoder.encode(password, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			Log.i("UnsupportedEncodingException: ", e.getMessage());
		}
		InputStream inputServer = this
				.getClass()
				.getClassLoader()
				.getResourceAsStream(
						"properties/server.properties");
		InputStream inputServices = this
				.getClass()
				.getClassLoader()
				.getResourceAsStream(
						"properties/services.properties");

		Properties properties = new Properties();
		String url = null;
		try {
			properties.load(inputServer);
			url = "http://" + properties.getProperty("domain") + ":"
					+ properties.getProperty("port");
			properties.load(inputServices);
			url += properties.getProperty("sessionNew");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Log.i("URL: ", url);
		session = ConnectionUtil
				.postLogin(url, userName, password);
		if(session != null){
			Log.i("Session en Login Utils",session.toString());
		}
		return session;

	}
	
	public boolean deleteSession(String session){
		boolean result = false;
		try {
			session = URLEncoder.encode(session, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			Log.i("UnsupportedEncodingException: ", e.getMessage());
		}
		InputStream inputServer = this
				.getClass()
				.getClassLoader()
				.getResourceAsStream(
						"properties/server.properties");
		InputStream inputServices = this
				.getClass()
				.getClassLoader()
				.getResourceAsStream(
						"properties/services.properties");

		Properties properties = new Properties();
		String url = null;
		try {
			properties.load(inputServer);
			url = "http://" + properties.getProperty("domain") + ":"
					+ properties.getProperty("port");
			properties.load(inputServices);
			url += properties.getProperty("session");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Log.i("URL: ", url);
		result = ConnectionUtil
				.deleteSession(url+"/"+session+".json");
		if(session != null){
			Log.i("Session en Login Utils",session.toString());
		}
		return result;
	}


}
