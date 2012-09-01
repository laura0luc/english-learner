package com.example.englishteacher;

import java.util.ArrayList;
import java.util.List;

import pojo.Assesment;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import pojo.Assesment;

public class AssesmentAdapter extends ArrayAdapter<Assesment> {

	private List<Assesment> assesments;
	private Context context;
	private Assesment assesment;
	
	public AssesmentAdapter(Context context, 
			int textViewResourceId, ArrayList<Assesment> objects){
		super(context, textViewResourceId, objects);
		this.assesments=objects;
		this.context=context;
		assesment=new Assesment();
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View fila=convertView;
		if(fila==null){
			LayoutInflater inflate = (LayoutInflater)
					context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			fila=inflate.inflate(R.layout.assesment_row, parent, false);
		}
		final Assesment assesment=assesments.get(position);
		ImageView im=(ImageView)fila.findViewById(R.id.imageView1);
		int id=fila.getResources().getIdentifier("codigo","drawable", context.getPackageName());
		im.setImageResource(id);
		
		TextView nombre = (TextView)fila.findViewById(R.id.textView1);
		nombre.setText("Android-Quiz1");
		
		TextView activo=(TextView)fila.findViewById(R.id.textView2);
		activo.setText("activo");
		

		Button boton=(Button)fila.findViewById(R.id.button2);
		boton.setOnClickListener( new Button.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent i=  new Intent(context, MainActivity.class);
				context.startActivity(i);
				
			}				
		} );
		Button boton2=(Button)fila.findViewById(R.id.button1);
		boton.setOnClickListener( new Button.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent i=  new Intent(context, EstadisticasActivity.class);
				context.startActivity(i);
				
			}				
		} );		
		
		
		return fila;
	}
	
	

}
