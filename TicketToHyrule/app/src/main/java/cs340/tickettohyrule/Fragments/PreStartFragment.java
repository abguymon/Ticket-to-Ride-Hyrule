package cs340.tickettohyrule.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import cs240.lib.Model.ClientFacade;
import cs340.tickettohyrule.PhaseTwoPresenters.PreStartPresenter;
import cs340.tickettohyrule.R;

/**
 * Created by eholm on 2/25/2018.
 */

public class PreStartFragment extends Fragment implements View.OnClickListener{
    private PreStartPresenter preStartPresenter = new PreStartPresenter();
    private CheckBox checkBoxOne;
    private CheckBox checkBoxTwo;
    private CheckBox checkBoxThree;
    private ImageButton submitButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prestart, container, false);

        preStartPresenter.setView(this);
        ClientFacade.getInstance().addObserver(preStartPresenter);

        checkBoxOne = (CheckBox) view.findViewById(R.id.checkBox2);
        checkBoxTwo = (CheckBox) view.findViewById(R.id.checkBox2);
        checkBoxThree = (CheckBox) view.findViewById(R.id.checkBox3);
        submitButton = (ImageButton) view.findViewById(R.id.imageButton5);
        submitButton.setOnClickListener(this);

        return view;
    }
    public void onCheckboxClicked(View v){
        boolean checked = ((CheckBox) v).isChecked();
        switch (v.getId()){
            case R.id.checkBox2:
                if(checked) {
                    checkBoxOne.setChecked(false);
                    preStartPresenter.getRemovedDestinationCards().add(preStartPresenter.getDestinationCards().get(0));
                }
                else {
                    checkBoxOne.setChecked(true);
                    preStartPresenter.getRemovedDestinationCards().remove(preStartPresenter.getDestinationCards().get(0));
                }
                break;
            case R.id.checkBox2:
                if(checked) {
                    checkBoxTwo.setChecked(false);
                    preStartPresenter.getRemovedDestinationCards().add(preStartPresenter.getDestinationCards().get(0));
                }
                else {
                    checkBoxTwo.setChecked(true);
                    preStartPresenter.getRemovedDestinationCards().remove(preStartPresenter.getDestinationCards().get(0));
                }
                break;
            case R.id.checkBox3:
                if(checked) {
                    checkBoxThree.setChecked(false);
                    preStartPresenter.getRemovedDestinationCards().add(preStartPresenter.getDestinationCards().get(0));
                }
                else {
                    checkBoxThree.setChecked(true);
                    preStartPresenter.getRemovedDestinationCards().remove(preStartPresenter.getDestinationCards().get(0));
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
