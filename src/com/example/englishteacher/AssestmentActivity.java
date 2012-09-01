package com.example.englishteacher;
import java.util.ArrayList;
import java.util.List;

import pojo.Assesment;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class AssestmentActivity extends ListActivity {
	private ArrayList<Assesment> assesments;
	private AssesmentAdapter adapter;
	private Assesment assesment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.assesment_list);
		Log.i("ASSESMENT ACTIVITY","assesment pintado");
		assesments= new ArrayList<Assesment>();
		Assesment ass= new Assesment();
		ass.setActivo(true);
		ass.setGrupo("TC2005");
		assesments.add(ass);
		adapter=new AssesmentAdapter(this,R.layout.assesment_row, assesments);
		setListAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		//Toast.makeText(getApplicationContext(), "position: "+position, Toast.LENGTH_LONG).show();
	}
}
