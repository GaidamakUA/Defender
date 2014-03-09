package ua.gaidamak.defender;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;

public class RegistrationActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_user);
	mLoginField = (EditText) findViewById(R.id.login);
	mPassField = (EditText) findViewById(R.id.password);
    }

    EditText mLoginField;
    EditText mPassField;
}
