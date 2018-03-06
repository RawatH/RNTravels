package rn.travels.in.rntravels.models;

import org.json.JSONObject;

import java.io.Serializable;

import rn.travels.in.rntravels.util.Appconst;

/**
 * Created by demo on 20/02/18.
 */

public class TicketVO implements Serializable {
    private String clientName;
    private String flightDetail;
    private String ticketNumber;
    private String airlineName;
    private String endorsments;
    private String status;

    public TicketVO(JSONObject json) {
        this.clientName = json.optString(Appconst.JKey.NAME);
        this.flightDetail = json.optString(Appconst.JKey.FLIGHT_DETAIL);
        this.ticketNumber = json.optString(Appconst.JKey.TICKET_NUM);
        this.airlineName = json.optString(Appconst.JKey.AIRLINE);
        this.endorsments = json.optString(Appconst.JKey.ENDORSEMENT);
        this.status = json.optString(Appconst.JKey.STATUS);
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getFlightDetail() {
        return flightDetail;
    }

    public void setFlightDetail(String flightDetail) {
        this.flightDetail = flightDetail;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getEndorsments() {
        return endorsments;
    }

    public void setEndorsments(String endorsments) {
        this.endorsments = endorsments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
