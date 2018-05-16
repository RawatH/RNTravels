package rn.travels.in.rntravels.models;

import android.support.annotation.NonNull;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import rn.travels.in.rntravels.util.Util;

/**
 * Created by demo on 16/02/18.
 */

public class DayVO implements Serializable ,Comparable {

    private String title;
    private int daySequence;
    private String dayLog;
    private ArrayList<String> daySnippetList;
    private String dayPhoto;

    public DayVO(JSONObject json) {
        this.title = json.optString("title");
        this.daySequence = Integer.parseInt(json.optString("day_seq"));
        this.dayPhoto = json.optString("image");
        this.daySnippetList = Util.getDaySnippet(json.optString("itineary_details"));

    }

    public String getTitle() {
        return "Day "+getDaySequence();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDaySequence() {
        return daySequence;
    }

    public void setDaySequence(int daySequence) {
        this.daySequence = daySequence;
    }

    public String getDayLog() {
        return dayLog;
    }

    public void setDayLog(String dayLog) {
        this.dayLog = dayLog;
    }

    public ArrayList<String> getDaySnippetList() {
        return this.daySnippetList;
    }

    public void setDaySnippetList(ArrayList<String> daySnippetList) {
        this.daySnippetList = daySnippetList;
    }

    public String getDayPhoto() {
        return dayPhoto;
    }

    public void setDayPhoto(String dayPhoto) {
        this.dayPhoto = dayPhoto;
    }

    @Override
    public String toString() {
        return "DayVO{" +
                "title='" + title + '\'' +
                ", daySequence=" + daySequence +
                ", dayLog='" + dayLog + '\'' +
                ", daySnippetList=" + daySnippetList +
                ", dayPhoto='" + dayPhoto + '\'' +
                '}';
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return getDaySequence() - ((DayVO)o).getDaySequence();
    }
}
