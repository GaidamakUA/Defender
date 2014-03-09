package ua.gaidamak.defender;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class SecretFileActivity extends Activity
{
    String SECRET_FILE_NAME = "secret_file.txt";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secret_file);
	Intent intent = getIntent();
	boolean isEditable = intent.getBooleanExtra("ua.gaidamak.defender.IsEditable", false);
	mStatusBar = (TextView) findViewById(R.id.status_bar);
	if (isEditable) {
	    mSecretFileField = (EditText) findViewById(R.id.secret_file_field);
	    mSecretFileField.setVisibility(View.VISIBLE);
	    mSecretFileField.setText(readFromFile());
	    findViewById(R.id.save_button).setVisibility(View.VISIBLE);
	} else {
	    mSecretFileText = (TextView) findViewById(R.id.secret_file_text);
	    mSecretFileText.setText(readFromFile());
	    mSecretFileText.setVisibility(View.VISIBLE);
	}
    }

    public void onSave(View v) {
	writeToFile(mSecretFileField.getText().toString());
	mStatusBar.setText("Secret file has been written");
    }

    private void writeToFile(String data) {
	try {
	    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(SECRET_FILE_NAME, Context.MODE_PRIVATE));
	    outputStreamWriter.write(data);
	    outputStreamWriter.close();
	}
	catch (IOException e) {
	    Log.e("Exception", "File write failed: " + e.toString());
	} 
    }

    private String readFromFile() {

	String ret = "EOF";

	try {
	    InputStream inputStream = openFileInput(SECRET_FILE_NAME);

	    if ( inputStream != null ) {
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String receiveString = "";
		StringBuilder stringBuilder = new StringBuilder();

		while ( (receiveString = bufferedReader.readLine()) != null ) {
		    stringBuilder.append(receiveString);
		}

		inputStream.close();
		ret = stringBuilder.toString();
	    }
	}
	catch (FileNotFoundException e) {
	    Log.e("login activity", "File not found: " + e.toString());
	} catch (IOException e) {
	    Log.e("login activity", "Can not read file: " + e.toString());
	}

	return ret;
    }

    EditText mSecretFileField;
    TextView mSecretFileText;
    TextView mStatusBar;
}
