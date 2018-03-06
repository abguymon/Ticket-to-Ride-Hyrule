package cs340.tickettohyrule.Fragments;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cs240.lib.Model.ClientFacade;
import cs240.lib.Model.cards.DestinationCard;
import cs240.lib.Model.gameParts.Player;
import cs340.tickettohyrule.PhaseTwoPresenters.GameInfoPresenter;
import cs340.tickettohyrule.R;

/**
 * Created by eholm on 2/25/2018.
 */

public class GameInfoFragment extends Fragment {
    private GameInfoPresenter gameInfoPresenter = new GameInfoPresenter();
    private ImageButton trainCardDeck;
    private ImageButton destinationCardDeck;
    private RecyclerView playerRecycler;
    private RecyclerView dCardRecycler;
    private Adapter dCardAdapter;
    private HorizontalAdapter playerAdapter;
    private Typeface zeldaFont;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_info, container, false);

        gameInfoPresenter.setView(this);
        ClientFacade.getInstance().addObserver(gameInfoPresenter);

        trainCardDeck = (ImageButton) view.findViewById(R.id.train_deck);
        destinationCardDeck = (ImageButton) view.findViewById(R.id.destination_deck);

        dCardRecycler = (RecyclerView) view.findViewById(R.id.destination_card_recycler);
        dCardRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        playerRecycler = (RecyclerView) view.findViewById(R.id.player_info_recycler);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        playerRecycler.setLayoutManager(horizontalLayoutManager);

        updateUI();

        return view;
    }

    //update list ui information
    public void updateUI()
    {
        List<DestinationCard> dCardList = getDCards();
        List<Player> players = getPlayers();
        playerAdapter = new HorizontalAdapter(players);
        dCardAdapter = new Adapter(dCardList);
        playerRecycler.setAdapter(playerAdapter);
        dCardRecycler.setAdapter(dCardAdapter);
    }

    private List<DestinationCard> getDCards() {
        return gameInfoPresenter.getDestinationCards();
    }
    private List<Player> getPlayers() {return gameInfoPresenter.getPlayers();}

    private class Holder extends RecyclerView.ViewHolder {

        private TextView dCardText;
        private DestinationCard mDestinationCard;

        public Holder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.log_text_list,parent,false));

            dCardText = (TextView) itemView.findViewById(R.id.log_text);
        }

        //bind object to recycler
        public void bind(DestinationCard destinationCard)
        {
            this.mDestinationCard = destinationCard;
            dCardText.setText(mDestinationCard.toString());
            dCardText.setTypeface(zeldaFont);
        }
    }

    //adapter for recycler
    private class Adapter extends RecyclerView.Adapter<Holder>
    {
        private List<DestinationCard> mDCards;

        public Adapter(List<DestinationCard> dCards){
            mDCards = dCards;
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new Holder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            holder.bind(mDCards.get(position));
        }

        @Override
        public int getItemCount() {
            return mDCards.size();
        }
    }

    public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {

        private List<Player> playerList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView txtView;
            public ImageView imgView;

            public MyViewHolder(View view) {
                super(view);
                txtView = (TextView) view.findViewById(R.id.playerInfo);
                imgView = (ImageView) view.findViewById(R.id.playerImage);
            }
        }

        public HorizontalAdapter(List<Player> playerList) {
            this.playerList= playerList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.player_image_list, parent, false);

            return new MyViewHolder(itemView);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            Player mPlayer = playerList.get(position);
            holder.txtView.setText(mPlayer.getPlayerName() + "\n" + mPlayer.getColor() +
                    "\n" + mPlayer.getTrainsRemaining() + " trains");
            switch(playerList.get(position).getPlayerNum())
            {
                case 0:
                    holder.imgView.setImageDrawable(getActivity().getDrawable(R.drawable.link));
                    break;
                case 1:
                    holder.imgView.setImageDrawable(getActivity().getDrawable(R.drawable.goron));
                    break;
                case 3:
                    holder.imgView.setImageDrawable(getActivity().getDrawable(R.drawable.zelda));
                    break;
                case 4:
                    holder.imgView.setImageDrawable(getActivity().getDrawable(R.drawable.bird_person));
                    break;
                case 5:
                    holder.imgView.setImageDrawable(getActivity().getDrawable(R.drawable.zora));
                    break;
                default:
                    holder.imgView.setImageDrawable(getActivity().getDrawable(R.drawable.gerudo));
            }
        }

        @Override
        public int getItemCount() {
            return playerList.size();
        }
    }

}
