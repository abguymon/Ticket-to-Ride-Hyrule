package cs340.tickettohyrule.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import cs240.lib.Model.ClientFacade;
import cs340.tickettohyrule.GameActivity;
import cs340.tickettohyrule.PhaseTwoPresenters.PreStartPresenter;
import cs340.tickettohyrule.R;

/**
 * Created by eholm on 2/25/2018.
 */

public class PreStartFragment extends Fragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener{
    private PreStartPresenter preStartPresenter = new PreStartPresenter();
    private CheckBox checkBoxOne;
    private CheckBox checkBoxTwo;
    private CheckBox checkBoxThree;
    private ImageButton submitButton;
    private TextView cardOne;
    private TextView cardTwo;
    private TextView cardThree;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prestart, container, false);

        preStartPresenter.setView(this);
        ClientFacade.getInstance().addObserver(preStartPresenter);

        checkBoxOne = (CheckBox) view.findViewById(R.id.checkBox1);
        checkBoxOne.setOnCheckedChangeListener(this);
        checkBoxTwo = (CheckBox) view.findViewById(R.id.checkBox2);
        checkBoxTwo.setOnCheckedChangeListener(this);
        checkBoxThree = (CheckBox) view.findViewById(R.id.checkBox3);
        checkBoxThree.setOnCheckedChangeListener(this);
        cardOne = (TextView) view.findViewById(R.id.dCardOne);
        cardTwo = (TextView) view.findViewById(R.id.dCardOne2);
        cardThree = (TextView) view.findViewById(R.id.dCardOne3);
        submitButton = (ImageButton) view.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);
        setText();
        return view;
    }
    public void setText(){
        cardOne.setText(preStartPresenter.getDestinationCards().get(0).toString());
        cardTwo.setText(preStartPresenter.getDestinationCards().get(1).toString());
        cardThree.setText(preStartPresenter.getDestinationCards().get(2).toString());
    }
    @Override
    public void onCheckedChanged(CompoundButton button, boolean isChecked){
        switch (button.getId()){
            case R.id.checkBox1:
                if(!isChecked) {
                    preStartPresenter.getRemovedDestinationCards().add(preStartPresenter.getDestinationCards().get(0));
                }
                else {
                    preStartPresenter.getRemovedDestinationCards().remove(preStartPresenter.getDestinationCards().get(0));
                }
                break;
            case R.id.checkBox2:
                if(!isChecked) {
                    preStartPresenter.getRemovedDestinationCards().add(preStartPresenter.getDestinationCards().get(1));
                }
                else {
                    preStartPresenter.getRemovedDestinationCards().remove(preStartPresenter.getDestinationCards().get(1));
                }
                break;
            case R.id.checkBox3:
                if(!isChecked) {
                    preStartPresenter.getRemovedDestinationCards().add(preStartPresenter.getDestinationCards().get(2));
                }
                else {
                    preStartPresenter.getRemovedDestinationCards().remove(preStartPresenter.getDestinationCards().get(2));
                }
                break;
        }
    }
    @Override
    public void onClick(View v){
        preStartPresenter.submit();
    }

    public void toast(String s){
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }
}
