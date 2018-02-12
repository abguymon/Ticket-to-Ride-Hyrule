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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import cs240.lib.Model.ModelFacade;
import cs340.tickettohyrule.Presenters.CreateGamePresenter;

/**
 * Created by eholm on 2/6/2018.
 */

public class CreateGameFragment extends Fragment implements View.OnClickListener {
    private ImageButton createButton;
    private EditText gameName;
    private TextView title;
    private TextView playerNumText;
    private Spinner numPlayers;
    private InGameSingleton inGameSingleton = InGameSingleton.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_game, container, false);

        Typeface zeldaFont;

        zeldaFont = Typeface.createFromAsset(getActivity().getAssets(),"fonts/HyliaSerifBeta-Regular.otf");

        createButton = (ImageButton) view.findViewById(R.id.create_game_button);
        createButton.setOnClickListener(this);
        createButton.setEnabled(false);

        gameName = (EditText) view.findViewById(R.id.game_name_text);
        gameName.addTextChangedListener(textWatcher);
        gameName.setTypeface(zeldaFont);

        title = (TextView) view.findViewById(R.id.create_game_title);
        title.setTypeface(zeldaFont);

        playerNumText = (TextView) view.findViewById(R.id.num_players_text) ;
        playerNumText.setTypeface(zeldaFont);

        numPlayers = (Spinner) view.findViewById(R.id.num_players_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.num_players_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        numPlayers.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_game_button:
//                Toast.makeText(getActivity(), "create called, num players: " + numPlayers.getSelectedItem(),
//                        Toast.LENGTH_SHORT).show();
//                String createMessage = createGamePresenter.createGame(currentUser.getUserName(),
//                        gameName.getText().toString(),
//                        Integer.parseInt(numPlayers.getSelectedItem().toString()));
//                if(createMessage.equals(""))
//                {
//                    ((SignInActivity) getActivity()).moveToLobby();
//                }
//                else
//                {
//                    Toast.makeText(getActivity(),  createMessage, Toast.LENGTH_SHORT).show();
//                }

                CreateAsync createAsync = new CreateAsync();
                createAsync.execute();
                break;
        }
    }

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
        if(gameName.length() > 0)
        {
            createButton.setEnabled(true);
        }
        else
        {
            createButton.setEnabled(false);
        }
    }


    private class CreateAsync extends AsyncTask<Void, Void, String> {
        CreateGamePresenter createGamePresenter = new CreateGamePresenter();
        ModelFacade modelFacade = CurrentUserSingleton.getInstance().getModelFacade();
        @Override
        protected String doInBackground(Void... params){
            String message = createGamePresenter.createGame(modelFacade.getCurrentUser().getUsername(),
                    gameName.getText().toString(),
                    Integer.parseInt(numPlayers.getSelectedItem().toString()));
            //System.out.println("user: " + modelFacade.getCurrentUser().getUsername() + " game: " + gameName.getText());
            return message;
        }
        @Override protected void onPostExecute(String message){
            super.onPostExecute(message);
            if(message.equals("")){
                inGameSingleton.setInGame(true);
                inGameSingleton.setGameImIn(gameName.getText().toString());
                ((SignInActivity) getActivity()).moveToLobby();
            }
            else{
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
