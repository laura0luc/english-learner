package utilities;

import utilities.LoginUtilities;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class LoginTask extends AsyncTask<String, Integer, StringBuffer> {
	
	private static ProgressDialog dialog = null;
	private Context context = null;
	private String tag = null;

	public LoginTask(Context ctx, String tag) {
		this.context = ctx;
		this.tag = tag;
	}

	@Override
	protected void onCancelled() {
		super.onCancelled();
	}

	@Override
	protected void onPostExecute(StringBuffer result) {
		Log.i("dismising","dialog");
		dialog.cancel();
		super.onPostExecute(result);
	}

	@Override
	protected void onPreExecute() {
		dialog = ProgressDialog.show(this.context, tag,
				"In Progress...", true);
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		Log.i("Procesando", values[0].toString());
	}

	@Override
	protected StringBuffer doInBackground(String... logInfo)
			throws IllegalArgumentException {
		StringBuffer result = null;
		LoginUtilities logUtil = new LoginUtilities();
		if (logInfo.length == 2) {
			result = logUtil.getSession(logInfo[0], logInfo[1]);
		} else {
			throw new IllegalArgumentException();
		}
		return result;
	}

}
