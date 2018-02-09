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

import cs240.lib.Model.Game;
import cs240.lib.Model.ModelFacade;
import cs340.tickettohyrule.Presenters.GameLobbyPresenter;

/**
 * Created by eholm on 2/6/2018.
 */

public class GameLobbyFragment extends Fragment implements View.OnClickListener  {
    private ImageButton joinButton;
    private ImageButton leaveButton;
    private ImageButton createButton;
    private RecyclerView gameListRecycler;
    private Adapter gameAdapter;
    private String currentGame;
    private boolean inGame;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_lobby, container, false);

        inGame = false;
        currentGame = "";

        joinButton = (ImageButton) view.findViewById(R.id.join_game_button);
        leaveButton = (ImageButton) view.findViewById(R.id.leave_game_button);
        createButton = (ImageButton) view.findViewById(R.id.create_button);

        joinButton.setOnClickListener(this);
        leaveButton.setOnClickListener(this);
        createButton.setOnClickListener(this);

        joinButton.setEnabled(false);
        leaveButton.setEnabled(false);
        createButton.setEnabled(true);

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
        ModelFacade modelFacade = ModelFacade.getInstance();
        //List<Game> gamesToAdd = new ArrayList<Game>();

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
                    if(!inGame)
                    {
                        currentGame = recyclerGame.getGameName();
                        joinButton.setEnabled(true);
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
//                String joinMessage = gameLobbyPresenter.joinGame(currentUser.getUserName(),
//                        currentUser.getPassword());
//                if(joinMessage.equals(""))
//                {
                    JoinAsync joinAsync = new JoinAsync();
                    joinAsync.execute();
//                }
//                else
//                {
//                    Toast.makeText(getActivity(), joinMessage, Toast.LENGTH_SHORT).show();
//                }
                //RegisterAsync registerAsync = new RegisterAsync();
                //registerAsync.execute();
                break;
            case R.id.leave_game_button:
                Toast.makeText(getActivity(), "leave called", Toast.LENGTH_SHORT).show();
                String leaveMessage = gameLobbyPresenter.leaveGame(currentUser.getUserName(),
                        currentUser.getPassword());
                if(leaveMessage.equals(""))
                {
                    inGame = false;
                }
                else
                {
                    Toast.makeText(getActivity(), leaveMessage, Toast.LENGTH_SHORT).show();
                }

                //LoginAsync loginAsync = new LoginAsync();
                //loginAsync.execute();
                break;
            case R.id.create_button:
                Toast.makeText(getActivity(), "create called", Toast.LENGTH_SHORT).show();
                ((SignInActivity) getActivity()).moveToCreate();

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
                inGame = true;
                Toast.makeText(getActivity(), "Successfully joined game", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
