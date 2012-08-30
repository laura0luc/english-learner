package com.example.englishteacher;

import java.util.ArrayList;

import pojo.Assesment;
import android.content.Context;
import android.widget.ArrayAdapter;

public class AssesmentAdapter extends ArrayAdapter<Assesment> {

	public AssesmentAdapter(Context context, 
			int textViewResourceId, ArrayList<Assesment> objects){
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
	}

}
