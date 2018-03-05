package cs340.tickettohyrule.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cs240.lib.Model.ClientFacade;
import cs340.tickettohyrule.PhaseTwoPresenters.GameChatPresenter;
import cs340.tickettohyrule.R;

/**
 * Created by eholm on 2/25/2018.
 */

public class GameChatFragment extends Fragment {
    private GameChatPresenter gameChatPresenter = new GameChatPresenter();
    private EditText chatField;
    private ImageButton sendButton;
    private RecyclerView chatRecycler;
    private Adapter chatAdapter;

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
        View view = inflater.inflate(R.layout.fragment_prestart, container, false);

        chatRecycler = (RecyclerView) view.findViewById(R.id.chat_recycler);

        gameChatPresenter.setView(this);
        ClientFacade.getInstance().addObserver(gameChatPresenter);

        Typeface zeldaFont;

        zeldaFont = Typeface.createFromAsset(getActivity().getAssets(),"fonts/HyliaSerifBeta-Regular.otf");

        chatField = (EditText) view.findViewById(R.id.my_chat_text);
        chatField.addTextChangedListener(textWatcher);
        chatField.setTypeface(zeldaFont);

        sendButton = (ImageButton) view.findViewById(R.id.send_chat_button);
        sendButton.setEnabled(false);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameChatPresenter.sendMessage(chatField.getText().toString());
            }
        });

        return view;
    }

    public void toast(String s){
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    //update list ui information
    private void updateUI()
    {
        List<String> chatList = getChat();
        chatAdapter = new Adapter(chatList);
        chatRecycler.setAdapter(chatAdapter);
    }

    private List<String> getChat() {
        return null;
    }

    private class Holder extends RecyclerView.ViewHolder {

        private TextView chatText;

        public Holder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.log_text_list,parent,false));

            chatText = (TextView) itemView.findViewById(R.id.log_text);
        }
    }

    //adapter for recycler
    private class Adapter extends RecyclerView.Adapter<Holder>
    {
        private List<String> mChat;

        public Adapter(List<String> chat){
            mChat = chat;
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new Holder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
        }

        @Override
        public int getItemCount() {
            return mChat.size();
        }
    }
}
