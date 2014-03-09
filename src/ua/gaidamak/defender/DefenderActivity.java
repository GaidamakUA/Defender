package ua.gaidamak.defender;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;

public class DefenderActivity extends Activity
{
    String PASSWORD_EDIT = "edit";
    String PASSWORD = "password";
    String LOGIN = "login";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
	mEmailField = (EditText) findViewById(R.id.login);
	mPassField = (EditText) findViewById(R.id.password);
    }

    public void onLogin(View v) {
	mEmail = mEmailField.getText().toString();
	mPass = mPassField.getText().toString();
	
	if (mEmail.equals(LOGIN)) {
	    if (mPass.equals(PASSWORD) || mPass.equals(PASSWORD_EDIT)) {
		Intent intent = new Intent(this, SecretFileActivity.class);
		intent.putExtra("ua.gaidamak.defender.IsEditable", mPass.equals(PASSWORD_EDIT));
		startActivity(intent);
	    }
	}

	mEmailField.setText("");
	mPassField.setText("");	
    }

    String mEmail;
    String mPass;

    EditText mEmailField;
    EditText mPassField;
}
