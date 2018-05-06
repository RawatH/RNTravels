package rn.travels.in.rntravels.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by demo on 06/05/18.
 */

public class ItineraryVO {
    private String numOfDays;
    private String title;
    ArrayList<DayVO> daysList;

    public ItineraryVO(JSONObject json) {
        numOfDays = json.optString("no_of_days");
        title = json.optString("title");
        daysList = populateDaysList(json.optJSONArray("days"));

    }

    private ArrayList<DayVO> populateDaysList(JSONArray daysArr) {
        ArrayList<DayVO> list = new ArrayList<>();

        for (int i = 0; i < daysArr.length(); i++) {
            try {
                list.add(new DayVO(daysArr.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    public String getNumOfDays() {
        return numOfDays;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<DayVO> getDaysList() {
        return daysList;
    }
}
