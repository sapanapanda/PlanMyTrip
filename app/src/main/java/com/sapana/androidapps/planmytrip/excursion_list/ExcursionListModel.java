package com.sapana.androidapps.planmytrip.excursion_list;

import com.sapana.androidapps.planmytrip.R;
import com.sapana.androidapps.planmytrip.excursions_details.ExcursionListContract;
import com.sapana.androidapps.planmytrip.model.Excursion;

import java.util.ArrayList;
import java.util.List;

public class ExcursionListModel implements ExcursionListContract.Model {
    String[] names={"Berlin","London","Majoraca","Paris"};
    int[] drawables ={R.drawable.berlin,R.drawable.london,R.drawable.majoraca,R.drawable.paris};
    int[] details = {R.string.berlin_details,R.string.london_details,R.string.majoraca_details,R.string.paris_details};
    String [] price ={"500","300","400","800"};
    @Override
    public void getExcursionList(OnFinishedListener onFinishedListener) {
       List<Excursion> excursionList = new ArrayList<>();
        for(int i =0;i<4;i++){
            Excursion excursion = new Excursion(names[i],drawables[i],details[i],price[i]);
            excursionList.add(excursion);

        }
        onFinishedListener.onFinished(excursionList);

    }
}
