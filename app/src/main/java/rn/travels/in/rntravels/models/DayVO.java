package rn.travels.in.rntravels.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by demo on 16/02/18.
 */

public class DayVO implements Serializable{

    private String title;
    private String date;
    private ArrayList<String> daySnippetList;
    private String photo;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<String> getDaySnippetList() {
        return daySnippetList;
    }

    public void setDaySnippetList(ArrayList<String> daySnippetList) {
        this.daySnippetList = daySnippetList;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "DayVO{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", daySnippetList=" + daySnippetList +
                ", photo='" + photo + '\'' +
                '}';
    }
}
