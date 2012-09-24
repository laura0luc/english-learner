package com.example.englishteacher;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import pojo.Grupo;
import servicios.GruposJSONParser;
import servicios.ParseUrl;
import servicios.UserJSONParser;

public class GrupoActivity extends ListActivity {
	
	private ArrayList<Grupo> grupos;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//createGrupos();
		String user=this.getIntent().getExtras().get("username")+"";
		String urlUser="http://escolares.ccm.itesm.mx/~mobile/sakai/users.php?name="+user;
		String userId=UserJSONParser.getUser(ParseUrl.jsonString(urlUser));
		String urlSites="http://escolares.ccm.itesm.mx/~mobile/sakai/sites.php?user="+userId;
		
		Log.i("GRUPOACTIVITY","urlSites "+urlSites);
		grupos=GruposJSONParser.getGrupos(ParseUrl.jsonString(urlSites));
		setContentView(R.layout.grupo_list);
		Log.i("GRUPOACTIVITY","GrupoActivity"+grupos.toString());
		ArrayAdapter<Grupo> adapter= new ArrayAdapter<Grupo>(this.getApplicationContext(),android.R.layout.simple_list_item_1, grupos){

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				View view= super.getView(position, convertView, parent);
				TextView tx= (TextView)view.findViewById(android.R.id.text1);
				tx.setTextColor(Color.WHITE);
				return view;
			}
			
		};
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Log.i("GRUPOACTIVITY","position: "+position);
		Intent i= new Intent(GrupoActivity.this,AssestmentActivity.class);
		startActivity(i);
		
	}
	
	public void createGrupos(){
		grupos= new ArrayList<Grupo>();
		for (int i=0; i<5; i++){
			Grupo g= new Grupo();
			g.setNombre("Android");
			grupos.add(g);
			
		}
	}
}
