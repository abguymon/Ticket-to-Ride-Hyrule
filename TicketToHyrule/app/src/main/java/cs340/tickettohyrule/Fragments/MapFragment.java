package cs340.tickettohyrule.Fragments;

import android.graphics.Typeface;
import android.media.MediaPlayer;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs240.lib.Model.ClientFacade;
import cs240.lib.Model.ModelFacade;
import cs240.lib.Model.colors.TrainCardColor;
import cs240.lib.Model.gameParts.City;
import cs240.lib.Model.gameParts.CityPair;
import cs240.lib.Model.gameParts.Route;
import cs240.lib.Model.states.TurnEnded;
import cs340.tickettohyrule.GameActivity;
import cs340.tickettohyrule.PhaseTwoPresenters.MapPresenter;
import cs340.tickettohyrule.R;

import static cs240.lib.Model.colors.TrainCardColor.GREY;

/**
 * Created by eholm on 3/5/2018.
 */

public class MapFragment extends Fragment implements View.OnClickListener{
    RecyclerView colorRecycler;
    Adapter colorAdapter;
    Typeface zeldaFont;
    boolean mapMade = false;
    RouteButtonListener routeButtonListener = new RouteButtonListener();
    private Map<Integer,Route> routeMap = new HashMap<>();
    private Map<Integer,ImageButton> buttonMap = new HashMap<>();
    private ArrayList<Route> routeList = new ArrayList<>();
    //private MediaPlayer navi = MediaPlayer.create(getActivity(), R.raw.navi);

    ImageButton chatButton;
    ImageButton infoButton;
    ImageButton historyButton;
    ImageButton turnButton;

    ImageButton bKakarikoLonLon;
    ImageButton bHyruleCLonLon;
    ImageButton bGoronVLonLonOne;
    ImageButton bGoronVLonLonTwo;
    ImageButton bLonLonLordJW;
    ImageButton bLonLonLordJG;
    ImageButton bKakVTemLG;
    ImageButton bKakVTemLW;
    ImageButton bKakVTarT;
    ImageButton bCitSKakV;
    ImageButton bCitSLorJ;
    ImageButton bHatVLorJO;
    ImageButton bHatVJorJB;
    ImageButton bCitSHatV;
    ImageButton bHatVIceCOne;
    ImageButton bHatVIceCTwo;
    ImageButton bCitSIceC;
    ImageButton bIceCOrdV;
    ImageButton bGorCIceC;
    ImageButton bFirTGorC;
    ImageButton bGorCShaT;
    ImageButton bGorVHidVOne;
    ImageButton bGorVHidVTwo;
    ImageButton bGorVHyrC;
    ImageButton bHidVTemT;
    ImageButton bDraIHidV;
    ImageButton bDraIMarL;
    ImageButton bDraISpiT;
    ImageButton bDraIGerF;
    ImageButton bGerFHidVOne;
    ImageButton bGerFHidVTwo;
    ImageButton bGerFSpiT;
    ImageButton bHyrCTemL;
    ImageButton bHyrCTemT;
    ImageButton bSpiTMarL;
    ImageButton bSpiTTemTY;
    ImageButton bSpiTTemTO;
    ImageButton bLosWSpiT;
    ImageButton bCloTMarL;
    ImageButton bMarLRitVOne;
    ImageButton bMarLRitVTwo;
    ImageButton bCloTRitV;
    ImageButton bLosWRitVOne;
    ImageButton bLosWRitVTwo;
    ImageButton bRitVZorD;
    ImageButton bLosWTemLB;
    ImageButton bLosWTemLP;
    ImageButton bLosWTarT;
    ImageButton bGreTLosW;
    ImageButton bDeaMLosWB;
    ImageButton bDeaMLosWO;
    ImageButton bCitSTarTB;
    ImageButton bCitSTarTO;
    ImageButton bOrdVTarT;
    ImageButton bDekPTarTOne;
    ImageButton bDekPTarTTwo;
    ImageButton bDekPOrdV;
    ImageButton bDekPShaT;
    ImageButton bDekPTing;
    ImageButton bGreTTing;
    ImageButton bLurVTingR;
    ImageButton bLurVTingY;
    ImageButton bLakHTing;
    ImageButton bDeaMGreTOne;
    ImageButton bDeaMGreTTwo;
    ImageButton bDeaMZorD;
    ImageButton bCloTDeaM;
    ImageButton bCloTZorD;
    ImageButton bFirTShaT;
    ImageButton bFirTTinI;
    ImageButton bFirTSnoR;
    ImageButton bSnoRTinIOne;
    ImageButton bSnoRTinITwo;
    ImageButton bLurVShaT;
    ImageButton bShaTTinI;
    ImageButton bTinIZorHOne;
    ImageButton bTinIZorHTwo;
    ImageButton bLakHLurV;
    ImageButton bLurVZorH;
    ImageButton bWatTZorHP;
    ImageButton bWatTZorHG;
    ImageButton bLakHWatTO;
    ImageButton bLakHWatTW;
    ImageButton bForTLakH;
    ImageButton bOutIWatT;
    ImageButton bForTOutI;
    ImageButton bCloTForT;
    ImageButton bCloTOutI;

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
        bKakVTemLG = (ImageButton) view.findViewById(R.id.b_kakv_teml_gre);
        bKakVTemLW = (ImageButton) view.findViewById(R.id.b_kakv_teml_whi);
        bKakVTarT = (ImageButton) view.findViewById(R.id.b_kakv_tart);
        bCitSKakV = (ImageButton) view.findViewById(R.id.b_cits_kakv);
        bCitSLorJ = (ImageButton) view.findViewById(R.id.b_cits_lorj);
        bHatVLorJO = (ImageButton) view.findViewById(R.id.b_hatv_lorj_ora);
        bHatVJorJB = (ImageButton) view.findViewById(R.id.b_hatv_lorj_bla);
        bCitSHatV = (ImageButton) view.findViewById(R.id.b_cits_hatv);
        bHatVIceCOne = (ImageButton) view.findViewById(R.id.b_hatv_icec);
        bHatVIceCTwo = (ImageButton) view.findViewById(R.id.b_hatv_icec_two);
        bCitSIceC = (ImageButton) view.findViewById(R.id.b_cits_icec);
        bIceCOrdV = (ImageButton) view.findViewById(R.id.b_icec_ordv);
        bGorCIceC = (ImageButton) view.findViewById(R.id.b_gorc_icec);
        bFirTGorC = (ImageButton) view.findViewById(R.id.b_gorc_firt);
        bGorCShaT = (ImageButton) view.findViewById(R.id.b_gorc_shat);
        bGorVHidVOne = (ImageButton) view.findViewById(R.id.b_gorv_hidv_one);
        bGorVHidVTwo = (ImageButton) view.findViewById(R.id.b_gorv_hidv_two);
        bGorVHyrC = (ImageButton) view.findViewById(R.id.b_gorv_hyrc);
        bHidVTemT = (ImageButton) view.findViewById(R.id.b_hidv_temt);
        bDraIHidV = (ImageButton) view.findViewById(R.id.b_drai_hidv);
        bDraIMarL = (ImageButton) view.findViewById(R.id.b_drai_marl);
        bDraISpiT = (ImageButton) view.findViewById(R.id.b_drai_spit);
        bDraIGerF = (ImageButton) view.findViewById(R.id.b_drai_gerf);
        bGerFHidVOne = (ImageButton) view.findViewById(R.id.b_hidv_gerf_one);
        bGerFHidVTwo = (ImageButton) view.findViewById(R.id.b_gerf_hidv_two);
        bGerFSpiT = (ImageButton) view.findViewById(R.id.b_gerf_spit);
        bHyrCTemL = (ImageButton) view.findViewById(R.id.b_hyrc_teml);
        bHyrCTemT = (ImageButton) view.findViewById(R.id.b_hyrc_temt);
        bSpiTMarL = (ImageButton) view.findViewById(R.id.b_marl_spit);
        bSpiTTemTY = (ImageButton) view.findViewById(R.id.b_spit_temt_yel);
        bSpiTTemTO = (ImageButton) view.findViewById(R.id.b_spit_temt_ora);
        bLosWSpiT = (ImageButton) view.findViewById(R.id.b_losw_spit);
        bCloTMarL = (ImageButton) view.findViewById(R.id.b_clot_marl);
        bMarLRitVOne = (ImageButton) view.findViewById(R.id.b_marl_ritv_one);
        bMarLRitVTwo = (ImageButton) view.findViewById(R.id.b_marl_ritv_two);
        bCloTRitV = (ImageButton) view.findViewById(R.id.b_clot_ritv);
        bLosWRitVOne = (ImageButton) view.findViewById(R.id.b_losw_ritv_one);
        bLosWRitVTwo = (ImageButton) view.findViewById(R.id.b_losw_ritv_two);
        bRitVZorD = (ImageButton) view.findViewById(R.id.b_ritv_zord);
        bLosWTemLB = (ImageButton) view.findViewById(R.id.b_losw_teml_blu);
        bLosWTemLP = (ImageButton) view.findViewById(R.id.b_losw_teml_pin);
        bLosWTarT = (ImageButton) view.findViewById(R.id.b_losw_tart);
        bGreTLosW = (ImageButton) view.findViewById(R.id.b_gret_losw);
        bDeaMLosWB = (ImageButton) view.findViewById(R.id.b_deam_losw_bla);
        bDeaMLosWO = (ImageButton) view.findViewById(R.id.b_deam_losw_ora);
        bCitSTarTB = (ImageButton) view.findViewById(R.id.b_cits_tart_bla);
        bCitSTarTO = (ImageButton) view.findViewById(R.id.b_cits_tart_ora);
        bOrdVTarT = (ImageButton) view.findViewById(R.id.b_ordv_tart);
        bDekPTarTOne = (ImageButton) view.findViewById(R.id.b_dekp_tart_one);
        bDekPTarTTwo = (ImageButton) view.findViewById(R.id.b_dekp_tart_two);
        bDekPOrdV = (ImageButton) view.findViewById(R.id.b_dekp_ordv);
        bDekPShaT = (ImageButton) view.findViewById(R.id.b_dekp_shat);
        bDekPTing = (ImageButton) view.findViewById(R.id.b_dekp_ting);
        bGreTTing = (ImageButton) view.findViewById(R.id.b_gret_ting);
        bLurVTingR = (ImageButton) view.findViewById(R.id.b_lurv_ting_red);
        bLurVTingY = (ImageButton) view.findViewById(R.id.b_lurv_ting_yel);
        bLakHTing = (ImageButton) view.findViewById(R.id.b_lakh_ting);
        bDeaMGreTOne = (ImageButton) view.findViewById(R.id.b_deam_gret_one);
        bDeaMGreTTwo = (ImageButton) view.findViewById(R.id.b_deam_gret_two);
        bDeaMZorD = (ImageButton) view.findViewById(R.id.b_deam_zord);
        bCloTDeaM = (ImageButton) view.findViewById(R.id.b_clot_deam);
        bCloTZorD = (ImageButton) view.findViewById(R.id.b_clot_zord);
        bFirTShaT = (ImageButton) view.findViewById(R.id.b_firt_shat);
        bFirTTinI = (ImageButton) view.findViewById(R.id.b_firt_tini);
        bFirTSnoR = (ImageButton) view.findViewById(R.id.b_firt_snor);
        bSnoRTinIOne = (ImageButton) view.findViewById(R.id.b_snor_tini_one);
        bSnoRTinITwo = (ImageButton) view.findViewById(R.id.b_snor_tini_two);
        bLurVShaT = (ImageButton) view.findViewById(R.id.b_lurv_shat);
        bShaTTinI = (ImageButton) view.findViewById(R.id.b_shat_tini);
        bTinIZorHOne = (ImageButton) view.findViewById(R.id.b_tini_zorh_one);
        bTinIZorHTwo = (ImageButton) view.findViewById(R.id.b_tini_zorh_two);
        bLakHLurV = (ImageButton) view.findViewById(R.id.b_lakh_lurv);
        bLurVZorH = (ImageButton) view.findViewById(R.id.b_lurv_zorh);
        bWatTZorHP = (ImageButton) view.findViewById(R.id.b_watt_zorh_pin);
        bWatTZorHG = (ImageButton) view.findViewById(R.id.b_watt_zorh_gre);
        bLakHWatTO = (ImageButton) view.findViewById(R.id.b_lakh_watt_ora);
        bLakHWatTW = (ImageButton) view.findViewById(R.id.b_lakh_watt_whi);
        bForTLakH = (ImageButton) view.findViewById(R.id.b_fort_lakh);
        bOutIWatT = (ImageButton) view.findViewById(R.id.b_outi_watt);
        bForTOutI = (ImageButton) view.findViewById(R.id.b_fort_outi);
        bCloTForT = (ImageButton) view.findViewById(R.id.b_clot_fort);
        bCloTOutI = (ImageButton) view.findViewById(R.id.b_clot_outi);

        bKakarikoLonLon.setOnClickListener(routeButtonListener);
        bHyruleCLonLon.setOnClickListener(routeButtonListener);
        bGoronVLonLonOne.setOnClickListener(routeButtonListener);
        bGoronVLonLonTwo.setOnClickListener(routeButtonListener);
        bLonLonLordJW.setOnClickListener(routeButtonListener);
        bLonLonLordJG.setOnClickListener(routeButtonListener);
        bKakVTemLG.setOnClickListener(routeButtonListener);
        bKakVTemLW.setOnClickListener(routeButtonListener);
        bKakVTarT.setOnClickListener(routeButtonListener);
        bCitSKakV.setOnClickListener(routeButtonListener);
        bCitSLorJ.setOnClickListener(routeButtonListener);
        bHatVLorJO.setOnClickListener(routeButtonListener);
        bHatVJorJB.setOnClickListener(routeButtonListener);
        bCitSHatV.setOnClickListener(routeButtonListener);
        bHatVIceCOne.setOnClickListener(routeButtonListener);
        bHatVIceCTwo.setOnClickListener(routeButtonListener);
        bCitSIceC.setOnClickListener(routeButtonListener);
        bIceCOrdV.setOnClickListener(routeButtonListener);
        bGorCIceC.setOnClickListener(routeButtonListener);
        bFirTGorC.setOnClickListener(routeButtonListener);
        bGorCShaT.setOnClickListener(routeButtonListener);
        bGorVHidVOne.setOnClickListener(routeButtonListener);
        bGorVHidVTwo.setOnClickListener(routeButtonListener);
        bGorVHyrC.setOnClickListener(routeButtonListener);
        bHidVTemT.setOnClickListener(routeButtonListener);
        bDraIHidV.setOnClickListener(routeButtonListener);
        bDraIMarL.setOnClickListener(routeButtonListener);
        bDraISpiT.setOnClickListener(routeButtonListener);
        bDraIGerF.setOnClickListener(routeButtonListener);
        bGerFHidVOne.setOnClickListener(routeButtonListener);
        bGerFHidVTwo.setOnClickListener(routeButtonListener);
        bGerFSpiT.setOnClickListener(routeButtonListener);
        bHyrCTemL.setOnClickListener(routeButtonListener);
        bHyrCTemT.setOnClickListener(routeButtonListener);
        bSpiTMarL.setOnClickListener(routeButtonListener);
        bSpiTTemTY.setOnClickListener(routeButtonListener);
        bSpiTTemTO.setOnClickListener(routeButtonListener);
        bLosWSpiT.setOnClickListener(routeButtonListener);
        bCloTMarL.setOnClickListener(routeButtonListener);
        bMarLRitVOne.setOnClickListener(routeButtonListener);
        bMarLRitVTwo.setOnClickListener(routeButtonListener);
        bCloTRitV.setOnClickListener(routeButtonListener);
        bLosWRitVOne.setOnClickListener(routeButtonListener);
        bLosWRitVTwo.setOnClickListener(routeButtonListener);
        bRitVZorD.setOnClickListener(routeButtonListener);
        bLosWTemLB.setOnClickListener(routeButtonListener);
        bLosWTemLP.setOnClickListener(routeButtonListener);
        bLosWTarT.setOnClickListener(routeButtonListener);
        bGreTLosW.setOnClickListener(routeButtonListener);
        bDeaMLosWB.setOnClickListener(routeButtonListener);
        bDeaMLosWO.setOnClickListener(routeButtonListener);
        bCitSTarTB.setOnClickListener(routeButtonListener);
        bCitSTarTO.setOnClickListener(routeButtonListener);
        bOrdVTarT.setOnClickListener(routeButtonListener);
        bDekPTarTOne.setOnClickListener(routeButtonListener);
        bDekPTarTTwo.setOnClickListener(routeButtonListener);
        bDekPOrdV.setOnClickListener(routeButtonListener);
        bDekPShaT.setOnClickListener(routeButtonListener);
        bDekPTing.setOnClickListener(routeButtonListener);
        bGreTTing.setOnClickListener(routeButtonListener);
        bLurVTingR.setOnClickListener(routeButtonListener);
        bLurVTingY.setOnClickListener(routeButtonListener);
        bLakHTing.setOnClickListener(routeButtonListener);
        bDeaMGreTOne.setOnClickListener(routeButtonListener);
        bDeaMGreTTwo.setOnClickListener(routeButtonListener);
        bDeaMZorD.setOnClickListener(routeButtonListener);
        bCloTDeaM.setOnClickListener(routeButtonListener);
        bCloTZorD.setOnClickListener(routeButtonListener);
        bFirTShaT.setOnClickListener(routeButtonListener);
        bFirTTinI.setOnClickListener(routeButtonListener);
        bFirTSnoR.setOnClickListener(routeButtonListener);
        bSnoRTinIOne.setOnClickListener(routeButtonListener);
        bSnoRTinITwo.setOnClickListener(routeButtonListener);
        bLurVShaT.setOnClickListener(routeButtonListener);
        bShaTTinI.setOnClickListener(routeButtonListener);
        bTinIZorHOne.setOnClickListener(routeButtonListener);
        bTinIZorHTwo.setOnClickListener(routeButtonListener);
        bLakHLurV.setOnClickListener(routeButtonListener);
        bLurVZorH.setOnClickListener(routeButtonListener);
        bWatTZorHP.setOnClickListener(routeButtonListener);
        bWatTZorHG.setOnClickListener(routeButtonListener);
        bLakHWatTO.setOnClickListener(routeButtonListener);
        bLakHWatTW.setOnClickListener(routeButtonListener);
        bForTLakH.setOnClickListener(routeButtonListener);
        bOutIWatT.setOnClickListener(routeButtonListener);
        bForTOutI.setOnClickListener(routeButtonListener);
        bCloTForT.setOnClickListener(routeButtonListener);
        bCloTOutI.setOnClickListener(routeButtonListener);

        chatButton = (ImageButton) view.findViewById(R.id.chat_button);
        infoButton = (ImageButton) view.findViewById(R.id.info_button);
        historyButton = (ImageButton) view.findViewById(R.id.history_button);
        turnButton = (ImageButton) view.findViewById(R.id.test_button);

        chatButton.setOnClickListener(this);
        infoButton.setOnClickListener(this);
        historyButton.setOnClickListener(this);
        turnButton.setOnClickListener(this);

        buttonMap.clear();
        createButtonMap();
        if(!mapMade)
        {
            createRouteMap();
            mapMade = true;
        }
        updateUI();

        return view;
    }



    private void getRouteList()
    {
        this.routeList = mapPresenter.getRoutes();
    }


    public void updateUI(){
        if(mapPresenter.isGameOver())
        {
            ((GameActivity) getActivity()).moveToEnd();
        }
        updateMap();

    }

    public void updateMap()
    {
        getRouteList();
        updateRouteMap();
        for (Map.Entry<Integer, Route> entry : routeMap.entrySet()) {
            ImageButton imageButton = buttonMap.get(entry.getKey());
            if (!entry.getValue().getOwner().equals(""))
            {
                switch (entry.getValue().getOwnerColor()) {
                    case GREEN:
                        imageButton.setBackground(getActivity().getDrawable(R.drawable.green_circle));
                        break;
                    case BLUE:
                        imageButton.setBackground(getActivity().getDrawable(R.drawable.blue_circle));
                        break;
                    case PINK:
                        imageButton.setBackground(getActivity().getDrawable(R.drawable.pink_circle));
                        break;
                    case RED:
                        imageButton.setBackground(getActivity().getDrawable(R.drawable.red_circle));
                        break;
                    case YELLOW:
                        imageButton.setBackground(getActivity().getDrawable(R.drawable.yellow_circle));
                    break;
                    case GRAY:
                        imageButton.setBackground(getActivity().getDrawable(R.drawable.black_circle));
                        break;
                        default:
                            break;
                }
            }
            else
            {
                imageButton.setBackground(getActivity().getDrawable(R.drawable.black_circle));
            }

            //System.out.println(entry.getKey() + "/" + entry.getValue());
        }
    }

    private void updateRouteMap()
    {
        for(Route r: routeList)
        {
            for(Map.Entry<Integer, Route> entry : routeMap.entrySet())
            {
                if (r.getCity1Name().equals(entry.getValue().getCity1Name()) && r.getCity2Name().equals(entry.getValue().getCity2Name()))
                {
                    entry.getValue().setOwner(r.getOwner());
                    entry.getValue().setOwnerColor(r.getOwnerColor());
                }
            }
        }
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
        public void bind(final String colorText)
        {
            this.colorText.setText(colorText);
            this.colorText.setTypeface(zeldaFont);

            this.colorText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mapPresenter.claimGreyRoute(colorText.toString());
                }
            });
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
            Route myRoute = routeMap.get(v.getId());
            mapPresenter.selectRoute(myRoute);
            if (myRoute.getColor() != GREY) {
                if (mapPresenter.claimRoute()) {
                    routeMap.get(v.getId()).setOwner(mapPresenter.getCurrentPlayer().getPlayerName());
                } else {
                    Toast.makeText(getContext(), "Not Enough Cards!", Toast.LENGTH_SHORT).show();
                }
            } else {
                List<String> colorList = getColors();
                colorAdapter = new Adapter(colorList);
                colorRecycler.setAdapter(colorAdapter);
            }
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
            case R.id.test_button:
                //navi.start();
                ModelFacade modelFacade = mapPresenter.getModelFacade(); //variable for break point evaluation
                String playerName = "";
                for(int i = 0; i < modelFacade.getGameData().getPlayerArray().size(); i++){
                    if(!(modelFacade.getGameData().getPlayerArray().get(i).getState() instanceof TurnEnded)) {
                        playerName = modelFacade.getGameData().getPlayerArray().get(i).getPlayerName();
                        break;
                    }
                }
                Toast.makeText(getActivity(), playerName + "'s Turn", Toast.LENGTH_SHORT).show();
        }
    }

    //IS THIS THE BEST WAY TO DO IT?
    //No
    public void createRouteMap(){
        routeMap.put(bKakarikoLonLon.getId(),createRoute("Kakariko Village", "Lon Lon Ranch",3, GREY));
        routeMap.put(bGoronVLonLonOne.getId(),createRoute("Goron Village", "Lon Lon Ranch", 2, GREY));
        routeMap.put(bGoronVLonLonTwo.getId(),createRoute("Goron Village", "Lon Lon Ranch", 2, GREY));
        routeMap.put(bHyruleCLonLon.getId(),createRoute("Hyrule Castle", "Lon Lon Ranch", 3, TrainCardColor.YELLOW));
        routeMap.put(bLonLonLordJW.getId(),createRoute("Lon Lon Ranch", "Lord Jabu Jabu", 2, TrainCardColor.WHITE));
        routeMap.put(bLonLonLordJG.getId(),createRoute("Lon Lon Ranch", "Lord Jabu Jabu", 2, TrainCardColor.GREEN));
        routeMap.put(bKakVTemLG.getId(),createRoute("Kakariko Village", "Temple of Light", 2 , TrainCardColor.GREEN));
        routeMap.put(bKakVTemLW.getId(),createRoute("Kakariko Village", "Temple of Light", 2 , TrainCardColor.WHITE));
        routeMap.put(bKakVTarT.getId(),createRoute("Kakariko Village", "Tarrey Town", 4, TrainCardColor.BLUE));
        routeMap.put(bCitSKakV.getId(),createRoute("City in the Sky", "Kakariko Village", 4, TrainCardColor.WHITE));
        routeMap.put(bCitSLorJ.getId(),createRoute("City in the Sky", "Lord Jabu Jabu", 2, GREY));
        routeMap.put(bHatVLorJO.getId(),createRoute("Hateno Village", "Lord Jabu Jabu", 2, TrainCardColor.ORANGE));
        routeMap.put(bHatVJorJB.getId(),createRoute("Hateno Village", "Lord Jabu Jabu", 2, TrainCardColor.BLACK));
        routeMap.put(bCitSHatV.getId(),createRoute("City in the Sky", "Hateno Village", 2, GREY));
        routeMap.put(bHatVIceCOne.getId(),createRoute("Hateno Village", "Ice Cavern", 2, GREY));
        routeMap.put(bHatVIceCTwo.getId(),createRoute("Hateno Village", "Ice Cavern", 2, GREY));
        routeMap.put(bCitSIceC.getId(),createRoute("City in the Sky", "Ice Cavern", 3, TrainCardColor.BLUE));
        routeMap.put(bIceCOrdV.getId(),createRoute("Ice Cavern", "Ordon Village", 3, TrainCardColor.RED));
        routeMap.put(bGorCIceC.getId(),createRoute("Goron City", "Ice Cavern", 5, TrainCardColor.BLACK));
        routeMap.put(bFirTGorC.getId(),createRoute("Fire Temple", "Goron City", 6, TrainCardColor.WHITE));
        routeMap.put(bGorCShaT.getId(),createRoute("Goron City", "Shadow Temple", 4, TrainCardColor.BLUE));
        routeMap.put(bGorVHidVOne.getId(),createRoute("Goron Village", "Hidden Village", 2, GREY));
        routeMap.put(bGorVHidVTwo.getId(),createRoute("Goron Village", "Hidden Village", 2, GREY));
        routeMap.put(bGorVHyrC.getId(),createRoute("Goron Village", "Hyrule Castle", 2, TrainCardColor.RED));
        routeMap.put(bHidVTemT.getId(),createRoute("Hidden Village", "Temple of Time", 2, GREY));
        routeMap.put(bDraIHidV.getId(),createRoute("Dragon Roost Island", "Hidden Village", 4, TrainCardColor.PINK));
        routeMap.put(bDraIMarL.getId(),createRoute("Dragon Roost Island", "Marine Research Laboratory", 2, GREY));
        routeMap.put(bDraISpiT.getId(),createRoute("Dragon Roost Island", "Spirit Temple", 6, TrainCardColor.RED));
        routeMap.put(bDraIGerF.getId(),createRoute("Dragon Roost Island", "Gerudo Fortress", 5, TrainCardColor.BLUE));
        routeMap.put(bGerFHidVOne.getId(),createRoute("Gerudo Fortress", "Hidden Village", 2, GREY));
        routeMap.put(bGerFHidVTwo.getId(),createRoute("Gerudo Fortress", "Hidden Village", 2, GREY));
        routeMap.put(bGerFSpiT.getId(),createRoute("Gerudo Fortress", "Spirit Temple", 1, GREY));
        routeMap.put(bHyrCTemL.getId(),createRoute("Hyrule Castle", "Temple of Light", 3, TrainCardColor.BLACK));
        routeMap.put(bHyrCTemT.getId(),createRoute("Hyrule Castle", "Temple of Time", 3, TrainCardColor.WHITE));
        routeMap.put(bSpiTMarL.getId(),createRoute("Spirit Temple", "Marine Research Laboratory", 2, GREY));
        routeMap.put(bSpiTTemTY.getId(),createRoute("Spirit Temple", "Temple of Time", 4, TrainCardColor.YELLOW));
        routeMap.put(bSpiTTemTO.getId(),createRoute("Spirit Temple", "Temple of Time", 4, TrainCardColor.ORANGE));
        routeMap.put(bLosWSpiT.getId(),createRoute("Lost Woods", "Spirit Temple", 3, TrainCardColor.GREEN));
        routeMap.put(bCloTMarL.getId(),createRoute("Clock Town", "Marine Research Laboratory", 6, TrainCardColor.GREEN));
        routeMap.put(bMarLRitVOne.getId(),createRoute("Marine Research Laboratory", "Rito Village", 2, GREY));
        routeMap.put(bMarLRitVTwo.getId(),createRoute("Marine Research Laboratory", "Rito Village", 2, GREY));
        routeMap.put(bCloTRitV.getId(),createRoute("Clock Town", "Rito Village", 4, TrainCardColor.RED));
        routeMap.put(bLosWRitVOne.getId(),createRoute("Lost Woods", "Rito Village", 2, GREY));
        routeMap.put(bLosWRitVTwo.getId(),createRoute("Lost Woods", "Rito Village", 2, GREY));
        routeMap.put(bRitVZorD.getId(),createRoute("Rito Village", "Zora's Domain", 3, TrainCardColor.BLUE));
        routeMap.put(bLosWTemLB.getId(),createRoute("Lost Woods", "Temple of Light", 2, TrainCardColor.BLUE));
        routeMap.put(bLosWTemLP.getId(),createRoute("Lost Woods", "Temple of Light", 2, TrainCardColor.PINK));
        routeMap.put(bLosWTarT.getId(),createRoute("Lost Woods", "Tarrey Town", 4, TrainCardColor.YELLOW));
        routeMap.put(bGreTLosW.getId(),createRoute("Great Deku Tree", "Lost Woods", 4, TrainCardColor.YELLOW));
        routeMap.put(bDeaMLosWB.getId(),createRoute("Death Mountain", "Lost Woods", 3, TrainCardColor.BLACK));
        routeMap.put(bDeaMLosWO.getId(),createRoute("Death Moutain", "Lost Woods", 3, TrainCardColor.ORANGE));
        routeMap.put(bCitSTarTB.getId(),createRoute("City in the Sky", "Tarrey Town", 3, TrainCardColor.BLACK));
        routeMap.put(bCitSTarTO.getId(),createRoute("City in the Sky", "Tarrey Town", 3, TrainCardColor.ORANGE));
        routeMap.put(bOrdVTarT.getId(),createRoute("Ordon Village", "Tarrey Town", 2, GREY));
        routeMap.put(bDekPTarTOne.getId(),createRoute("Deku Palace", "Tarrey Town", 2, GREY));
        routeMap.put(bDekPTarTTwo.getId(),createRoute("Deku Palace", "Tarrey Town", 2, GREY));
        routeMap.put(bDekPOrdV.getId(),createRoute("Deku Palace", "Ordon Village", 6, TrainCardColor.PINK));
        routeMap.put(bDekPShaT.getId(),createRoute("Deku Palace", "Shadow Temple", 6, TrainCardColor.ORANGE));
        routeMap.put(bDekPTing.getId(),createRoute("Deku Palace", "Tingle", 1, GREY));
        routeMap.put(bGreTTing.getId(),createRoute("Great Deku Tree", "Tingle", 3, TrainCardColor.GREEN));
        routeMap.put(bLurVTingR.getId(),createRoute("Lurelin Village", "Tingle", 3, TrainCardColor.RED));
        routeMap.put(bLurVTingY.getId(),createRoute("Lurelin Village", "Tingle", 3, TrainCardColor.YELLOW));
        routeMap.put(bLakHTing.getId(),createRoute("Lake Hylia", "Tingle", 4, TrainCardColor.PINK));
        routeMap.put(bDeaMGreTOne.getId(),createRoute("Death Mountain", "Great Deku Tree", 1, GREY));
        routeMap.put(bDeaMGreTTwo.getId(),createRoute("Death Mountain", "Great Deku Tree", 1, GREY));
        routeMap.put(bDeaMZorD.getId(),createRoute("Death Mountain", "Zora's Domain", 3, GREY));
        routeMap.put(bCloTDeaM.getId(),createRoute("Clock Town", "Death Mountain", 5, TrainCardColor.WHITE));
        routeMap.put(bCloTZorD.getId(),createRoute("Clock Town", "Zora's Domain", 3, GREY));
        routeMap.put(bFirTShaT.getId(),createRoute("Fire Temple", "Shadow Temple", 3, TrainCardColor.BLACK));
        routeMap.put(bFirTTinI.getId(),createRoute("Fire Temple", "Tingle Island", 4, TrainCardColor.RED));
        routeMap.put(bFirTSnoR.getId(),createRoute("Fire Temple", "Snowpeak Ruins", 3, TrainCardColor.GREEN));
        routeMap.put(bSnoRTinIOne.getId(),createRoute("Snowpeak Ruins", "Tingle Island", 1, GREY));
        routeMap.put(bSnoRTinITwo.getId(),createRoute("Snowpeak Ruins", "Tingle Island", 1, GREY));
        routeMap.put(bLurVShaT.getId(),createRoute("Lurelin Village", "Shadow Temple", 3, TrainCardColor.PINK));
        routeMap.put(bShaTTinI.getId(),createRoute("Shadow Temple", "Tingle Island", 6, TrainCardColor.YELLOW));
        routeMap.put(bTinIZorHOne.getId(),createRoute("Tingle Island", "Zora's Hall", 1, GREY));
        routeMap.put(bTinIZorHTwo.getId(),createRoute("Tingle Island", "Zora's Hall", 1, GREY));
        routeMap.put(bLakHLurV.getId(),createRoute("Lake Hylia", "Lurelin Village", 3, TrainCardColor.ORANGE));
        routeMap.put(bLurVZorH.getId(),createRoute("Lurelin Village", "Zora's Hall", 6, TrainCardColor.BLUE));
        routeMap.put(bWatTZorHP.getId(),createRoute("Water Temple", "Zora's Hall", 3, TrainCardColor.PINK));
        routeMap.put(bWatTZorHG.getId(),createRoute("Water Temple", "Zora's Hall", 3, TrainCardColor.GREEN));
        routeMap.put(bLakHWatTO.getId(),createRoute("Lake Hylia", "Water Temple", 5, TrainCardColor.ORANGE));
        routeMap.put(bLakHWatTW.getId(),createRoute("Lake Hylia", "Water Temple", 5, TrainCardColor.WHITE));
        routeMap.put(bForTLakH.getId(),createRoute("Forest Temple", "Lake Hylia", 3, GREY));
        routeMap.put(bOutIWatT.getId(),createRoute("Outset Island", "Water Temple", 3, TrainCardColor.PINK));
        routeMap.put(bForTOutI.getId(),createRoute("Forest Temple", "Outset Island", 3, TrainCardColor.RED));
        routeMap.put(bCloTForT.getId(),createRoute("Clock Town", "Forest Temple", 3, TrainCardColor.YELLOW));
        routeMap.put(bCloTOutI.getId(),createRoute("Clock Town", "Outset Island", 6, TrainCardColor.BLACK));
    }

    public void createButtonMap(){

        buttonMap.put(bKakarikoLonLon.getId(),bKakarikoLonLon);
        buttonMap.put(bGoronVLonLonOne.getId(),bGoronVLonLonOne);
        buttonMap.put(bGoronVLonLonTwo.getId(),bGoronVLonLonTwo);
        buttonMap.put(bHyruleCLonLon.getId(),bHyruleCLonLon);
        buttonMap.put(bLonLonLordJW.getId(),bLonLonLordJW);
        buttonMap.put(bLonLonLordJG.getId(),bLonLonLordJG);
        buttonMap.put(bKakVTemLG.getId(),bKakVTemLG);
        buttonMap.put(bKakVTemLW.getId(),bKakVTemLW);
        buttonMap.put(bKakVTarT.getId(),bKakVTarT);
        buttonMap.put(bCitSKakV.getId(),bCitSKakV);
        buttonMap.put(bCitSLorJ.getId(),bCitSLorJ);
        buttonMap.put(bHatVLorJO.getId(),bHatVLorJO);
        buttonMap.put(bHatVJorJB.getId(),bHatVJorJB);
        buttonMap.put(bCitSHatV.getId(),bCitSHatV);
        buttonMap.put(bHatVIceCOne.getId(),bHatVIceCOne);
        buttonMap.put(bHatVIceCTwo.getId(),bHatVIceCTwo);
        buttonMap.put(bCitSIceC.getId(),bCitSIceC);
        buttonMap.put(bIceCOrdV.getId(),bIceCOrdV);
        buttonMap.put(bGorCIceC.getId(),bGorCIceC);
        buttonMap.put(bFirTGorC.getId(),bFirTGorC);
        buttonMap.put(bGorCShaT.getId(),bGorCShaT);
        buttonMap.put(bGorVHidVOne.getId(),bGorVHidVOne);
        buttonMap.put(bGorVHidVTwo.getId(),bGorVHidVTwo);
        buttonMap.put(bGorVHyrC.getId(),bGorVHyrC);
        buttonMap.put(bHidVTemT.getId(),bHidVTemT);
        buttonMap.put(bDraIHidV.getId(),bDraIHidV);
        buttonMap.put(bDraIMarL.getId(),bDraIMarL);
        buttonMap.put(bDraISpiT.getId(),bDraISpiT);
        buttonMap.put(bDraIGerF.getId(),bDraIGerF);
        buttonMap.put(bGerFHidVOne.getId(),bGerFHidVOne);
        buttonMap.put(bGerFHidVTwo.getId(),bGerFHidVTwo);
        buttonMap.put(bGerFSpiT.getId(),bGerFSpiT);
        buttonMap.put(bHyrCTemL.getId(),bHyrCTemL);
        buttonMap.put(bHyrCTemT.getId(),bHyrCTemT);
        buttonMap.put(bSpiTMarL.getId(),bSpiTMarL);
        buttonMap.put(bSpiTTemTY.getId(),bSpiTTemTY);
        buttonMap.put(bSpiTTemTO.getId(),bSpiTTemTO);
        buttonMap.put(bLosWSpiT.getId(),bLosWSpiT);
        buttonMap.put(bCloTMarL.getId(),bCloTMarL);
        buttonMap.put(bMarLRitVOne.getId(),bMarLRitVOne);
        buttonMap.put(bMarLRitVTwo.getId(),bMarLRitVTwo);
        buttonMap.put(bCloTRitV.getId(),bCloTRitV);
        buttonMap.put(bLosWRitVOne.getId(),bLosWRitVOne);
        buttonMap.put(bLosWRitVTwo.getId(),bLosWRitVTwo);
        buttonMap.put(bRitVZorD.getId(),bRitVZorD);
        buttonMap.put(bLosWTemLB.getId(),bLosWTemLB);
        buttonMap.put(bLosWTemLP.getId(),bLosWTemLP);
        buttonMap.put(bLosWTarT.getId(),bLosWTarT);
        buttonMap.put(bGreTLosW.getId(),bGreTLosW);
        buttonMap.put(bDeaMLosWB.getId(),bDeaMLosWB);
        buttonMap.put(bDeaMLosWO.getId(),bDeaMLosWO);
        buttonMap.put(bCitSTarTB.getId(),bCitSTarTB);
        buttonMap.put(bCitSTarTO.getId(),bCitSTarTO);
        buttonMap.put(bOrdVTarT.getId(),bOrdVTarT);
        buttonMap.put(bDekPTarTOne.getId(),bDekPTarTOne);
        buttonMap.put(bDekPTarTTwo.getId(),bDekPTarTTwo);
        buttonMap.put(bDekPOrdV.getId(),bDekPOrdV);
        buttonMap.put(bDekPShaT.getId(),bDekPShaT);
        buttonMap.put(bDekPTing.getId(),bDekPTing);
        buttonMap.put(bGreTTing.getId(),bGreTTing);
        buttonMap.put(bLurVTingR.getId(),bLurVTingR);
        buttonMap.put(bLurVTingY.getId(),bLurVTingY);
        buttonMap.put(bLakHTing.getId(),bLakHTing);
        buttonMap.put(bDeaMGreTOne.getId(),bDeaMGreTOne);
        buttonMap.put(bDeaMGreTTwo.getId(),bDeaMGreTTwo);
        buttonMap.put(bDeaMZorD.getId(),bDeaMZorD);
        buttonMap.put(bCloTDeaM.getId(),bCloTDeaM);
        buttonMap.put(bCloTZorD.getId(),bCloTZorD);
        buttonMap.put(bFirTShaT.getId(),bFirTShaT);
        buttonMap.put(bFirTTinI.getId(),bFirTTinI);
        buttonMap.put(bFirTSnoR.getId(),bFirTSnoR);
        buttonMap.put(bSnoRTinIOne.getId(),bSnoRTinIOne);
        buttonMap.put(bSnoRTinITwo.getId(),bSnoRTinITwo);
        buttonMap.put(bLurVShaT.getId(),bLurVShaT);
        buttonMap.put(bShaTTinI.getId(),bShaTTinI);
        buttonMap.put(bTinIZorHOne.getId(),bTinIZorHOne);
        buttonMap.put(bTinIZorHTwo.getId(),bTinIZorHTwo);
        buttonMap.put(bLakHLurV.getId(),bLakHLurV);
        buttonMap.put(bLurVZorH.getId(),bLurVZorH);
        buttonMap.put(bWatTZorHP.getId(),bWatTZorHP);
        buttonMap.put(bWatTZorHG.getId(),bWatTZorHG);
        buttonMap.put(bLakHWatTO.getId(),bLakHWatTO);
        buttonMap.put(bLakHWatTW.getId(),bLakHWatTW);
        buttonMap.put(bForTLakH.getId(),bForTLakH);
        buttonMap.put(bOutIWatT.getId(),bOutIWatT);
        buttonMap.put(bForTOutI.getId(),bForTOutI);
        buttonMap.put(bCloTForT.getId(),bCloTForT);
        buttonMap.put(bCloTOutI.getId(),bCloTOutI);
    }

    public Route createRoute(String city1_name, String city2_name, int length, TrainCardColor color) {
        City city1 = new City(city1_name);
        City city2 = new City(city2_name);
        CityPair cities = new CityPair(city1, city2);
        return new Route(cities, length, color);
    }
}
