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

import java.util.ArrayList;
import java.util.List;

import cs240.lib.Model.ClientFacade;
import cs240.lib.Model.cards.DestinationCard;
import cs240.lib.Model.cards.TrainCard;
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
    private ImageButton tCardOne;
    private ImageButton tCardTwo;
    private ImageButton tCardThree;
    private ImageButton tCardFour;
    private ImageButton tCardFive;
    private TextView numTDeck;
    private TextView numDDeck;
    private TextView numLoco;
    private TextView numRed;
    private TextView numGreen;
    private TextView numOrange;
    private TextView numBlue;
    private TextView numBlack;
    private TextView numYellow;
    private TextView numPink;
    private TextView numWhite;
    private RecyclerView playerRecycler;
    private RecyclerView dCardRecycler;
    private Adapter dCardAdapter;
    private HorizontalAdapter playerAdapter;
    private Typeface zeldaFont;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_info, container, false);

        zeldaFont = Typeface.createFromAsset(getActivity().getAssets(),"fonts/HyliaSerifBeta-Regular.otf");

        gameInfoPresenter.setView(this);
        ClientFacade.getInstance().addObserver(gameInfoPresenter);

        trainCardDeck = (ImageButton) view.findViewById(R.id.train_deck);
        destinationCardDeck = (ImageButton) view.findViewById(R.id.destination_deck);

        tCardOne = (ImageButton) view.findViewById(R.id.tCardOne);
        tCardTwo = (ImageButton) view.findViewById(R.id.tCardTwo);
        tCardThree = (ImageButton) view.findViewById(R.id.tCardThree);
        tCardFour = (ImageButton) view.findViewById(R.id.tCardFour);
        tCardFive = (ImageButton) view.findViewById(R.id.tCardFive);

        numTDeck = (TextView) view.findViewById(R.id.num_t_deck);
        numTDeck.setTypeface(zeldaFont);
        numDDeck = (TextView) view.findViewById(R.id.num_d_deck);
        numDDeck.setTypeface(zeldaFont);
        numLoco = (TextView) view.findViewById(R.id.locoTCtext);
        numLoco.setTypeface(zeldaFont);
        numRed = (TextView) view.findViewById(R.id.redTCtext);
        numRed.setTypeface(zeldaFont);
        numGreen = (TextView) view.findViewById(R.id.greenTCtext);
        numGreen.setTypeface(zeldaFont);
        numOrange = (TextView) view.findViewById(R.id.orangeTCtext);
        numOrange.setTypeface(zeldaFont);
        numBlue = (TextView) view.findViewById(R.id.blueTCtext);
        numBlue.setTypeface(zeldaFont);
        numBlack = (TextView) view.findViewById(R.id.blackTCtext);
        numBlack.setTypeface(zeldaFont);
        numYellow = (TextView) view.findViewById(R.id.yellowTCtext);
        numYellow.setTypeface(zeldaFont);
        numPink = (TextView) view.findViewById(R.id.pinkTCtext);
        numPink.setTypeface(zeldaFont);
        numWhite = (TextView) view.findViewById(R.id.whiteTCtext);
        numWhite.setTypeface(zeldaFont);

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
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void updateUI()
    {
        setFaceUpTrainCards();
        setPlayerCards();
        numDDeck.setText("num destination cards: " + gameInfoPresenter.getnumCardsInDDeck());
        numTDeck.setText("num train cards: " + gameInfoPresenter.getNumCardsInTDeck());

        List<DestinationCard> dCardList = getDCards();
        List<Player> players = getPlayers();
        playerAdapter = new HorizontalAdapter(players);
        dCardAdapter = new Adapter(dCardList);
        playerRecycler.setAdapter(playerAdapter);
        dCardRecycler.setAdapter(dCardAdapter);
    }

    private void setPlayerCards()
    {
        ArrayList<TrainCard> playerTC = gameInfoPresenter.getPlayerTCards();
        int loco = 0;
        int red = 0;
        int green = 0;
        int orange = 0;
        int blue = 0;
        int black = 0;
        int yellow = 0;
        int pink = 0;
        int white = 0;
        for(TrainCard t :playerTC)
        {
            switch (t.getColor().toString())
            {
                case "GREEN":
                    green++;
                    break;
                case "RED":
                    red++;
                    break;
                case "BLACK":
                    black++;
                    break;
                case "YELLOW":
                    yellow++;
                    break;
                case "PINK":
                    pink++;
                    break;
                case "ORANGE":
                    orange++;
                    break;
                case "WHITE":
                    white++;
                    break;
                case "BLUE":
                    blue++;
                    break;
                case "WILD":
                    loco++;
                    break;
                default:
                    loco++;
                    break;
            }
        }
        numLoco.setText("number of locomotives: " + loco);
        numRed.setText("number of red cards: " + red);
        numGreen.setText("number of green cards: " + green);
        numOrange.setText("number of orange cards: " + orange);
        numBlue.setText("number of blue cards: " + blue);
        numBlack.setText("number of black cards: " + black);
        numYellow.setText("number of yellow cards: " + yellow);
        numPink.setText("number of pink cards: " + pink);
        numWhite.setText("number of white cards: " + white);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setFaceUpTrainCards()
    {
        TrainCard[] faceUpCards = gameInfoPresenter.getFaceUpTrainCards();
        for (int i = 0; i < 5; i++)
        {
            ImageView currentTrainCard;
            switch (i)
            {
                case 0:
                    currentTrainCard = tCardOne;
                    break;
                case 1:
                    currentTrainCard = tCardTwo;
                    break;
                case 2:
                    currentTrainCard = tCardThree;
                    break;
                case 3:
                    currentTrainCard = tCardFour;
                    break;
                case 4:
                    currentTrainCard = tCardFive;
                    break;
                default:
                    currentTrainCard = tCardOne;
                    break;
            }
            switch (faceUpCards[i].getColor().toString())
            {
                case "GREEN":
                    currentTrainCard.setBackground(getActivity().getDrawable(R.drawable.greentc));
                    break;
                case "RED":
                    currentTrainCard.setBackground(getActivity().getDrawable(R.drawable.redtc));
                    break;
                case "BLACK":
                    currentTrainCard.setBackground(getActivity().getDrawable(R.drawable.blacktc));
                    break;
                case "YELLOW":
                    currentTrainCard.setBackground(getActivity().getDrawable(R.drawable.yellowtc));
                    break;
                case "PINK":
                    currentTrainCard.setBackground(getActivity().getDrawable(R.drawable.pinktc));
                    break;
                case "ORANGE":
                    currentTrainCard.setBackground(getActivity().getDrawable(R.drawable.orangetc));
                    break;
                case "WHITE":
                    currentTrainCard.setBackground(getActivity().getDrawable(R.drawable.whitetc));
                    break;
                case "BLUE":
                    currentTrainCard.setBackground(getActivity().getDrawable(R.drawable.bluetc));
                    break;
                case "WILD":
                    currentTrainCard.setBackground(getActivity().getDrawable(R.drawable.locotc));
                    break;
                default:
                    currentTrainCard.setBackground(getActivity().getDrawable(R.drawable.locotc));
                    break;
            }
            //tCardOne = faceUpCards[0].getColor().toString();

        }
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
            dCardText.setTypeface(zeldaFont);
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
                txtView.setTypeface(zeldaFont);
                imgView = (ImageView) view.findViewById(R.id.player_image);
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
                    "\n" + mPlayer.getTrainsRemaining() + " trains" +
                    "\n" + "points: " + mPlayer.getScore() + "\nnum train cards: " +
                    mPlayer.getTrainCards().size() + "\nnum dest cards: " +
                    mPlayer.getDestinationCards().size());
            switch(playerList.get(position).getPlayerNum())
            {
                case 1:
                    holder.imgView.setImageDrawable(getActivity().getDrawable(R.drawable.link));
                    break;
                case 2:
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
