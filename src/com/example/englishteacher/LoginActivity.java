package com.example.englishteacher;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import utilities.LoginTask;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
	}
	
	public void login(View v){
		Log.i("LOGIN","Etrando al login");
		String user= ((EditText)this.findViewById(R.id.editText1)).toString().trim();
		String pass=((EditText)this.findViewById(R.id.editText2)).toString().trim();
		LoginTask t = new LoginTask(this, "login"); 
		t.execute(user, pass);
		StringBuffer session= null;

		try {
			session = t.get(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(session == null){
			Toast.makeText(getApplicationContext(), "No hay sesi—n", Toast.LENGTH_LONG).show();

		}else{
			Intent next=new Intent(LoginActivity.this, GrupoActivity.class);
			startActivity(next);
		}




	}
	

}
