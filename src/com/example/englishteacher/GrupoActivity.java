package com.example.englishteacher;

import java.util.ArrayList;
import java.util.List;

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

public class GrupoActivity extends ListActivity {
	
	private List<Grupo> grupos;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		createGrupos();
		
		setContentView(R.layout.grupo_list);
		Log.i("GRUPOACTIVITY","GrupoActivity"+grupos.toString());
		ArrayAdapter<Grupo> adapter= new ArrayAdapter<Grupo>(this.getApplicationContext(),android.R.layout.simple_list_item_1, grupos){

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				View view= super.getView(position, convertView, parent);
				TextView tx= (TextView)view.findViewById(android.R.id.text1);
				tx.setTextColor(Color.BLACK);
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
			g.setClave("TC2005");
			g.setGrupo(i);
			grupos.add(g);
			
		}
	}
}
