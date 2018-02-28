package cs340.tickettohyrule;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import cs340.tickettohyrule.PhaseOnePresenters.SignInPresenter;

/**
 * Created by eholm on 2/6/2018.
 */

public class SignInFragment extends Fragment implements View.OnClickListener {
    private ImageButton loginButton;
    private ImageButton registerButton;
    private EditText host;
    private EditText port;
    private EditText username;
    private EditText password;

    //watch the text fields for user input
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //Toast.makeText(getActivity(),"changed " + charSequence.toString(),Toast.LENGTH_SHORT).show();
            TestFields();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    // test if input has been given by user
    private void TestFields()
    {
        if(host.length() > 0 && port.length() > 0 &&
                username.length() > 0 && password.length() > 0)
        {
            loginButton.setEnabled(true);
            registerButton.setEnabled(true);
        }
        else
        {
            loginButton.setEnabled(false);
            registerButton.setEnabled(false);
        }
    }

    //create view of login fragment and set up variables
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        Typeface zeldaFont;

        zeldaFont = Typeface.createFromAsset(getActivity().getAssets(),"fonts/HyliaSerifBeta-Regular.otf");

        loginButton = (ImageButton) view.findViewById(R.id.login_button);
        registerButton = (ImageButton) view.findViewById(R.id.register_button);

        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);

        loginButton.setEnabled(false);
        registerButton.setEnabled(false);

        host = (EditText) view.findViewById(R.id.host_text);
        host.addTextChangedListener(textWatcher);
        host.setTypeface(zeldaFont);

        port = (EditText) view.findViewById(R.id.port_text);
        port.addTextChangedListener(textWatcher);
        port.setTypeface(zeldaFont);

        username = (EditText) view.findViewById(R.id.username_text);
        username.addTextChangedListener(textWatcher);
        username.setTypeface(zeldaFont);

        password = (EditText) view.findViewById(R.id.password_text);
        password.addTextChangedListener(textWatcher);
        password.setTypeface(zeldaFont);

        return view;
    }

    //handle the clicking of register and login buttons
    @Override
    public void onClick(View v) {
        SignInPresenter signInPresenter = new SignInPresenter();
        switch (v.getId()) {
            case R.id.register_button:
                Toast.makeText(getActivity(), "register called", Toast.LENGTH_SHORT).show();
                RegisterTask registerTask = new RegisterTask();
                registerTask.execute();
                break;
            case R.id.login_button:
                Toast.makeText(getActivity(), "login called", Toast.LENGTH_SHORT).show();
                LoginTask loginTask = new LoginTask();
                loginTask.execute();
                break;
        }
    }
    private class LoginTask extends AsyncTask<Void, Void, String>{
        SignInPresenter signInPresenter = new SignInPresenter();
        @Override
        protected String doInBackground(Void... params){
            String message = signInPresenter.login(username.getText().toString(), password.getText().toString(),
                    host.getText().toString(), port.getText().toString());
            return message;
        }
        @Override protected void onPostExecute(String message){
            super.onPostExecute(message);
            if(message.equals("")){
                ((SignInActivity) getActivity()).moveToLobby();
            }
            else{
                Toast.makeText(getActivity(), (String) message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class RegisterTask extends AsyncTask<Void, Void, String>{
        SignInPresenter signInPresenter = new SignInPresenter();
        @Override
        protected String doInBackground(Void... params){
            String message = signInPresenter.register(username.getText().toString(), password.getText().toString(),
                    host.getText().toString(), port.getText().toString());
            return message;
        }
        @Override protected void onPostExecute(String message){
            super.onPostExecute(message);
            if(message.equals("")){
                ((SignInActivity) getActivity()).moveToLobby();
            }
            else{
                Toast.makeText(getActivity(), (String) message, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
