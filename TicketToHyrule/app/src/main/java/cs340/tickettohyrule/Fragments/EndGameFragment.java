package cs340.tickettohyrule.Fragments;

import android.graphics.Typeface;
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

import java.util.List;

import cs240.lib.Model.ClientFacade;
import cs240.lib.Model.gameParts.Player;
import cs340.tickettohyrule.GameActivity;
import cs340.tickettohyrule.PhaseTwoPresenters.EndGamePresenter;
import cs340.tickettohyrule.R;

/**
 * Created by eholm on 3/20/2018.
 */

public class EndGameFragment extends Fragment implements View.OnClickListener{

    private EndGamePresenter endGamePresenter = new EndGamePresenter();
    private ImageButton returnButton;
    private RecyclerView playerRecycler;
    private Adapter playerAdapter;
    private Typeface zeldaFont;
    private TextView titleText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_end_game, container, false);

        zeldaFont = Typeface.createFromAsset(getActivity().getAssets(),"fonts/HyliaSerifBeta-Regular.otf");

        titleText = (TextView) view.findViewById(R.id.end_title);
        titleText.setTypeface(zeldaFont);

        playerRecycler = (RecyclerView) view.findViewById(R.id.player_recyler);
        playerRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        returnButton = (ImageButton) view.findViewById(R.id.return_button);
        returnButton.setOnClickListener(this);

        updateUI();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ClientFacade.getInstance().deleteObserver(endGamePresenter);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(), "return called", Toast.LENGTH_SHORT).show();
    }

    private void updateUI()
    {
        List<Player> playerList = getPlayerInfo();
        playerAdapter = new Adapter(playerList);
        playerRecycler.setAdapter(playerAdapter);
    }

    private List<Player> getPlayerInfo() {
        return endGamePresenter.getPlayers();
    }

    private class Holder extends RecyclerView.ViewHolder {

        private TextView playerRank;
        private TextView playerName;
        private TextView playerRScore;
        private TextView playerDScore;
        private TextView playerBScore;
        private TextView playerNScore;
        private TextView playerTScore;

        public Holder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.game_over_list,parent,false));

            playerRank = (TextView) itemView.findViewById(R.id.player_rank);
            playerName = (TextView) itemView.findViewById(R.id.player_name);
            playerRScore = (TextView) itemView.findViewById(R.id.route_points);
            playerDScore = (TextView) itemView.findViewById(R.id.dest_points);
            playerBScore = (TextView) itemView.findViewById(R.id.bonus_points);
            playerNScore = (TextView) itemView.findViewById(R.id.neg_points);
            playerTScore = (TextView) itemView.findViewById(R.id.tot_points);
        }
        //bind object to recycler
        public void bind(Player player)
        {
            playerRank.setText("You are Great!");
            playerRank.setTypeface(zeldaFont);
            playerName.setText(player.getPlayerName());
            playerName.setTypeface(zeldaFont);
            playerRScore.setText("Route Points: " + player.getRoutePoints());
            playerRScore.setTypeface(zeldaFont);
            playerDScore.setText("Destination Points: " + player.getPositiveDestinationPoints());
            playerDScore.setTypeface(zeldaFont);
            playerBScore.setText("Bonus Points: " + player.getBonusPoints());
            playerBScore.setTypeface(zeldaFont);
            playerNScore.setText("Negative Points: " + player.getNegativeDestinationPoints());
            playerNScore.setTypeface(zeldaFont);
            playerTScore.setText("Total Points: " + player.getScore());
            playerTScore.setTypeface(zeldaFont);
        }
    }

    //adapter for recycler
    private class Adapter extends RecyclerView.Adapter<Holder>
    {
        private List<Player> mPlayers;

        public Adapter(List<Player> players){
            mPlayers = players;
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new Holder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            holder.bind(mPlayers.get(position));
        }

        @Override
        public int getItemCount() {
            return mPlayers.size();
        }
    }
}
