package servicios;

import java.util.ArrayList;

import org.json.JSONObject;
import org.json.JSONArray;

import android.util.Log;

import pojo.Grupo;

public class GruposJSONParser {
	
	public static ArrayList<Grupo> getGrupos(String jsonString){
		ArrayList<Grupo> objs= new ArrayList<Grupo>();
		try{
			JSONObject obj= new JSONObject(jsonString);
			JSONArray array= obj.getJSONArray("root");
			for (int i=0; i<array.length(); i++){
				JSONObject ob= array.getJSONObject(i);
				Grupo g= new Grupo();
				g.setNombre(ob.getString("title"));
				g.setId(ob.getString("site_id"));
				objs.add(g);
			}
			
		}catch(Exception e){
			Log.e("EXCEPCION", e.toString());
		}
		return objs;
	}
	
	public static boolean getStatus(String jsonString){
		boolean status=true;
		try{
			JSONObject obj=new JSONObject(jsonString);
			int stat=obj.getInt("successful");
			status=stat==1?true:false;	
		}catch(Exception e){
			Log.e("EXCEPCION", e.toString());
		}
		
		return status;
		
	}

}
