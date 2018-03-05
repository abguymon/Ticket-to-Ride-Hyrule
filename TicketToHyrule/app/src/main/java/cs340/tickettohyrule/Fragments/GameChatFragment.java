package cs340.tickettohyrule.Fragments;

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

import cs240.lib.Model.ClientFacade;
import cs340.tickettohyrule.PhaseOnePresenters.SignInPresenter;
import cs340.tickettohyrule.PhaseTwoPresenters.GameChatPresenter;
import cs340.tickettohyrule.R;
import cs340.tickettohyrule.SignInActivity;

/**
 * Created by eholm on 2/25/2018.
 */

public class GameChatFragment extends Fragment {
    private GameChatPresenter gameChatPresenter = new GameChatPresenter();
    private EditText chatField;
    private ImageButton sendButton;

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            TestFields();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    // test if input has been given by user
    private void TestFields()
    {
        if(chatField.length() > 0)
        {
            sendButton.setEnabled(true);
        }
        else
        {
            sendButton.setEnabled(false);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_chat, container, false);

        gameChatPresenter.setView(this);
        ClientFacade.getInstance().addObserver(gameChatPresenter);

        Typeface zeldaFont;

        zeldaFont = Typeface.createFromAsset(getActivity().getAssets(),"fonts/HyliaSerifBeta-Regular.otf");

        chatField = (EditText) view.findViewById(R.id.editText);
        chatField.addTextChangedListener(textWatcher);
        chatField.setTypeface(zeldaFont);

        sendButton = (ImageButton) view.findViewById(R.id.imageButton);
        sendButton.setEnabled(false);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameChatPresenter.sendMessage(chatField.getText().toString());
            }
        });

        return view;
    }

    public void updateUI(){
        //SETS THE RECYCLER ADAPTER HERE
    }

    public void toast(String s){
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }
}
