package servicios;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import pojo.Grupo;
import android.util.Log;

public class UserJSONParser {
	
	public static String getUser(String jsonString){
		int size=jsonString.length();
		String temp=jsonString.substring(13,size);
		size=temp.length();
		temp=temp.substring(0,size-3);
		return temp;
	}

}
