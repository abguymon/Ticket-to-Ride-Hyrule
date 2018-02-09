package cs340.tickettohyrule;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import cs240.lib.Model.Game;
import cs240.lib.Model.ModelFacade;
import cs340.tickettohyrule.Presenters.GameLobbyPresenter;

/**
 * Created by eholm on 2/6/2018.
 */

public class GameLobbyFragment extends Fragment implements View.OnClickListener, Observer {
    private ImageButton joinButton;
    private ImageButton leaveButton;
    private ImageButton createButton;
    private RecyclerView gameListRecycler;
    private Adapter gameAdapter;
    private String currentGame;
    private ModelFacade modelFacade;

    private InGameSingleton inGameSingleton = InGameSingleton.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_lobby, container, false);
        modelFacade = ModelFacade.getInstance();
        modelFacade.addObserver(this);

        currentGame = "";

        joinButton = (ImageButton) view.findViewById(R.id.join_game_button);
        leaveButton = (ImageButton) view.findViewById(R.id.leave_game_button);
        createButton = (ImageButton) view.findViewById(R.id.create_button);

        joinButton.setOnClickListener(this);
        leaveButton.setOnClickListener(this);
        createButton.setOnClickListener(this);

        joinButton.setEnabled(false);
        leaveButton.setEnabled(false);
        if(inGameSingleton.isInGame()) {
            createButton.setEnabled(false);
        }
        else
        {
            createButton.setEnabled(true);
        }

        gameListRecycler = (RecyclerView) view.findViewById(R.id.game_lobby_recycler);
        gameListRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    //update list ui information
    private void updateUI()
    {
        List<Game> gameList = getGames();
        gameAdapter = new Adapter(gameList);
        gameListRecycler.setAdapter(gameAdapter);
    }

    private List<Game> getGames() {
        return modelFacade.getGames();
    }

    private class Holder extends RecyclerView.ViewHolder {

        private TextView gameName;
        private TextView numPlayers;
        private Game recyclerGame;

        public Holder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.game_list,parent,false));

            gameName = (TextView) itemView.findViewById(R.id.game_name);
            numPlayers = (TextView) itemView.findViewById(R.id.num_players);
        }
        //bind object to recycler
        public void bind(Game game)
        {
            recyclerGame = game;
            gameName.setText(recyclerGame.getGameName());
            numPlayers.setText(recyclerGame.getPlayersJoined() + "/" +recyclerGame.getMaxPlayers());
            gameName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), recyclerGame.getGameName() + " selected",
                            Toast.LENGTH_SHORT).show();
                    currentGame = recyclerGame.getGameName();
                    if(!inGameSingleton.isInGame())
                    {
                        joinButton.setEnabled(true);
                    }
                    else
                    {
                        leaveButton.setEnabled(true);
                    }

                }
            });
        }
    }

    //handle the clicking of join,leave, and create buttons
    @Override
    public void onClick(View v) {
        GameLobbyPresenter gameLobbyPresenter = new GameLobbyPresenter();
        CurrentUserSingleton currentUser = CurrentUserSingleton.getInstance();
        switch (v.getId()) {
            case R.id.join_game_button:
                Toast.makeText(getActivity(), "join called", Toast.LENGTH_SHORT).show();
                    JoinAsync joinAsync = new JoinAsync();
                    joinAsync.execute();
                break;
            case R.id.leave_game_button:
                Toast.makeText(getActivity(), "leave called", Toast.LENGTH_SHORT).show();
                LeaveAsync leaveAsync = new LeaveAsync();
                leaveAsync.execute();
                break;
            case R.id.create_button:
                Toast.makeText(getActivity(), "create called", Toast.LENGTH_SHORT).show();
                ((SignInActivity) getActivity()).moveToCreate();
                //inGame = true;
                break;
        }
    }

    //adapter for recycler
    private class Adapter extends RecyclerView.Adapter<Holder>
    {
        private List<Game> mGames;

        public Adapter(List<Game> games){
            mGames = games;
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new Holder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            Game game = mGames.get(position);
            holder.bind(game);
        }

        @Override
        public int getItemCount() {
            return mGames.size();
        }
    }

    private class JoinAsync extends AsyncTask<Void, Void, String> {
        GameLobbyPresenter gameLobbyPresenter = new GameLobbyPresenter();
        ModelFacade modelFacade = ModelFacade.getInstance();
        @Override
        protected String doInBackground(Void... params){
            String message = gameLobbyPresenter.joinGame(modelFacade.getCurrentUser().getUsername(), currentGame);
            return message;
        }
        @Override protected void onPostExecute(String message){
            super.onPostExecute(message);
            if(message.equals("")){
                inGameSingleton.setInGame(true);
                Toast.makeText(getActivity(), "Successfully joined game", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class LeaveAsync extends AsyncTask<Void, Void, String> {
        GameLobbyPresenter gameLobbyPresenter = new GameLobbyPresenter();
        ModelFacade modelFacade = ModelFacade.getInstance();
        @Override
        protected String doInBackground(Void... params){
            String message = gameLobbyPresenter.leaveGame(modelFacade.getCurrentUser().getUsername(), currentGame);
            return message;
        }
        @Override protected void onPostExecute(String message){
            super.onPostExecute(message);
            if(message.equals("")){
                inGameSingleton.setInGame(false);
                Toast.makeText(getActivity(), "Successfully left game", Toast.LENGTH_SHORT).show();
                createButton.setEnabled(true);
                currentGame = "";
            }
            else{
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void update (Observable observable, Object o){
        ModelFacade facade = (ModelFacade) observable;
        //UPDATE ALL THE INFO FROM HERE
        getActivity().runOnUiThread(new Runnable(){
            @Override
            public void run(){
                updateUI();
            }
        });
    }

}
