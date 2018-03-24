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

import java.util.HashMap;
import java.util.List;

import cs240.lib.Model.ClientFacade;
import cs240.lib.Model.gameParts.City;
import cs240.lib.Model.gameParts.CityPair;
import cs340.tickettohyrule.GameActivity;
import cs340.tickettohyrule.PhaseTwoPresenters.MapPresenter;
import cs340.tickettohyrule.R;

/**
 * Created by eholm on 3/5/2018.
 */

public class MapFragment extends Fragment implements View.OnClickListener{
    HashMap<Integer, CityPair> mapToCityPair = new HashMap<>();
    RecyclerView colorRecycler;
    Adapter colorAdapter;
    Typeface zeldaFont;
    ImageButton chatButton;
    ImageButton infoButton;
    ImageButton historyButton;
    ImageButton testButton;
    ImageButton bKakarikoLonLon;
    ImageButton bHyruleCLonLon;
    ImageButton bGoronVLonLonOne;
    ImageButton bGoronVLonLonTwo;
    ImageButton bLonLonLordJW;
    ImageButton bLonLonLordJG;

    MapPresenter mapPresenter = new MapPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        ClientFacade.getInstance().deleteObserver(mapPresenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        zeldaFont = Typeface.createFromAsset(getActivity().getAssets(),"fonts/HyliaSerifBeta-Regular.otf");

        mapPresenter.setView(this);
        ClientFacade.getInstance().addObserver(mapPresenter);

        colorRecycler = (RecyclerView) view.findViewById(R.id.color_recycler);
        colorRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        bKakarikoLonLon = (ImageButton) view.findViewById(R.id.b_kak_lon);
        bHyruleCLonLon = (ImageButton) view.findViewById(R.id.b_hyr_lon);
        bGoronVLonLonOne = (ImageButton) view.findViewById(R.id.b_gor_lon);
        bGoronVLonLonTwo = (ImageButton) view.findViewById(R.id.b_gor_lon_two);
        bLonLonLordJW = (ImageButton) view.findViewById(R.id.b_lon_lord_white);
        bLonLonLordJG = (ImageButton) view.findViewById(R.id.b_lon_lord_green);

        chatButton = (ImageButton) view.findViewById(R.id.chat_button);
        infoButton = (ImageButton) view.findViewById(R.id.info_button);
        historyButton = (ImageButton) view.findViewById(R.id.history_button);
        testButton = (ImageButton) view.findViewById(R.id.test_button);

        chatButton.setOnClickListener(this);
        infoButton.setOnClickListener(this);
        historyButton.setOnClickListener(this);
        testButton.setOnClickListener(this);

        updateUI();

        return view;
    }


    public void updateUI(){
        if(mapPresenter.isClaimed()) {
            //bKakarikoLonLon.setBackground(getActivity().getDrawable(R.drawable.green_circle));
        }

        List<String> historyList = getColors();
        colorAdapter = new Adapter(historyList);
        colorRecycler.setAdapter(colorAdapter);
    }

    private List<String> getColors() {
        return mapPresenter.getColors();
    }

    private class Holder extends RecyclerView.ViewHolder {

        private TextView colorText;

        public Holder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.log_text_list,parent,false));

            colorText = (TextView) itemView.findViewById(R.id.log_text);
        }
        //bind object to recycler
        public void bind(String colorText)
        {
            this.colorText.setText(colorText);
            this.colorText.setTypeface(zeldaFont);
        }
    }

    private class Adapter extends RecyclerView.Adapter<Holder>
    {
        private List<String> mColors;

        public Adapter(List<String> colors){
            mColors = colors;
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new Holder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            holder.bind(mColors.get(position));
        }

        @Override
        public int getItemCount() {
            return mColors.size();
        }
    }

    public void toast(String s){
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    public class RouteButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            mapPresenter.claimRoute(mapToCityPair.get(v.getId()));
        }
    }

    //handle the clicking of join,leave, and create buttons
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.info_button:
                Toast.makeText(getActivity(), "info called", Toast.LENGTH_SHORT).show();
                ((GameActivity) getActivity()).moveToInfo();
                break;
            case R.id.chat_button:
                Toast.makeText(getActivity(), "chat called", Toast.LENGTH_SHORT).show();
                ((GameActivity) getActivity()).moveToChat();
                break;
            case R.id.history_button:
                Toast.makeText(getActivity(), "history called", Toast.LENGTH_SHORT).show();
                ((GameActivity) getActivity()).moveToHistory();
                break;
        }
    }

    //IS THIS THE BEST WAY TO DO IT?
    public void createMap(){
        City outsetIsland = new City("Outset Island");
        City lordJabuJabu = new City("Lord Jabu Jabu");
        City marineResearchLab = new City("Marine Research Lab");
        City dekuPalace = new City("Deku Palace");
        City goronCity = new City("Goron City");
        City templeOfLight = new City("Temple of Light");
        City gerudoFortress = new City("Gerudo Fortress");
        City templeOfTime = new City("Temple of Time");
        City zorasHall = new City("Zora's Hall");
        City snowpeakRuins = new City("Snowpeak Ruins");
        City hatenoVillage = new City("Hateno Village");
        City clockTown = new City("Clock Town");
        City dragonRoostIsland = new City("Dragon Roost Island");
        City ordonVillage = new City("Ordon Village");
        City forestTemple = new City("Forest Temple");
        City ritoVillage = new City("Rito Village");
        City lurelinVillage = new City("Lurelin Village");
        City fireTemple = new City("Fire Temple");
        City spiritTemple = new City("Spirit Temple");
        City waterTemple = new City("Water Temple");
        City lostWoods = new City("Lost Woods");
        City tarreyTown = new City("Tarrey Town");
        City cityInTheSky = new City("City in the Sky");
        City deathMountain = new City("Death Mountain");
        City zorasDomain = new City("Zora's Domain");
        City lakeHylia = new City("Lake Hylia");
        City iceCavern = new City("Ice Cavern");
        City hyruleCastle = new City("Hyrule Castle");
        City tingleIsland = new City("Tingle Island");
        City shadowTemple = new City("Shadow Temple");
        City greatDekuTree = new City("Great Deku Tree");
        City tingle = new City("Tingle");
        City kakarikoVillage = new City("Kakariko Village");
        City hiddenVillage = new City("Hidden Village");
        City goronVillage = new City("Goron Village");
        City lonLonRanch = new City("Lon Lon Ranch");
        mapToCityPair.put(R.id.b_kak_lon, new CityPair(kakarikoVillage, lonLonRanch));
    }


}
