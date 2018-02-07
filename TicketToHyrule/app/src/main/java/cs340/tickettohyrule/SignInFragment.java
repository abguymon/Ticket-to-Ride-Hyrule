package cs340.tickettohyrule;

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

import cs340.tickettohyrule.Presenters.SignInPresenter;

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

        loginButton = (ImageButton) view.findViewById(R.id.login_button);
        registerButton = (ImageButton) view.findViewById(R.id.register_button);

        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);

        loginButton.setEnabled(false);
        registerButton.setEnabled(false);

        host = (EditText) view.findViewById(R.id.host_text);
        host.addTextChangedListener(textWatcher);

        port = (EditText) view.findViewById(R.id.port_text);
        port.addTextChangedListener(textWatcher);

        username = (EditText) view.findViewById(R.id.username_text);
        username.addTextChangedListener(textWatcher);

        password = (EditText) view.findViewById(R.id.password_text);
        password.addTextChangedListener(textWatcher);

        return view;
    }

    //handle the clicking of register and login buttons
    @Override
    public void onClick(View v) {
        SignInPresenter signInPresenter = new SignInPresenter();
        switch (v.getId()) {
            case R.id.register_button:
                Toast.makeText(getActivity(), "register called", Toast.LENGTH_SHORT).show();
                if(signInPresenter.register() == "") {
                    ((SignInActivity) getActivity()).moveToLobby();
                }
                //RegisterAsync registerAsync = new RegisterAsync();
                //registerAsync.execute();
                break;
            case R.id.login_button:
                Toast.makeText(getActivity(), "login called", Toast.LENGTH_SHORT).show();
                if(signInPresenter.login() == "") {
                    ((SignInActivity) getActivity()).moveToLobby();
                }
                //LoginAsync loginAsync = new LoginAsync();
                //loginAsync.execute();
                break;
        }
    }

    //login with the server
    class LoginAsync extends AsyncTask<Void, Void, String> {
        //private LoginRequest loginRequest = new LoginRequest(username.getText().toString(),password.getText().toString());
        String serverHost = host.getText().toString();
        String serverPort = port.getText().toString();
        @Override
        protected String doInBackground(Void... v) {
            try {
                //loginData.setUsername(username.getText().toString());
                //loginData.setPassword(password.getText().toString());
                //loginData.setServerHost(serverHost.getText().toString());
                //loginData.setServerPort(serverPort.getText().toString());

                //LoginResponse loginResponse = serverProxy.login(loginRequest,host,port);

                //setUser(loginResponse.getPersonID());
                //serverProxy.clear(host,port);
                //return loginResponse.getAuthToken();
                return null;//temp
                //Toast.makeText(getActivity(),"Login Successful",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(getActivity(),"Login Failed",Toast.LENGTH_SHORT).show();
                System.out.print(e.getMessage());
                e.printStackTrace();
                return "error";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //DataAsync dataAsync = new DataAsync();
            //dataAsync.execute(s);
        }
    }
}
