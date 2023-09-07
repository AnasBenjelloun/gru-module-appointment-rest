package fr.paris.lutece.plugins.appointment.modules.rest.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SolrAppointmentSlotPOJO {

    public final static String SOLR_FIELD_UID = "uid_form_string";
    public final static String SOLR_FIELD_DATE = "date";
    public final static String SOLR_FIELD_URL = "url";
    public final static String SOLR_FIELD_DAY_OPEN = "day_open_string";
    public final static String SOLR_FIELD_ENABLED = "enabled_string";
    public final static String SOLR_FIELD_APPOINTMENT_ACTIVE = "appointment_active_string";
    public final static String SOLR_FIELD_NB_CONSECUTIVES_SLOTS = "nb_consecutives_slots_long";
    public final static String SOLR_FIELD_MAX_CONSECUTIVES_SLOTS = "max_consecutives_slots_long";


    @JsonProperty(SOLR_FIELD_UID)
    String _strUidFormString;
    @JsonProperty(SOLR_FIELD_DATE)
    String _strDate;
    @JsonProperty(SOLR_FIELD_URL)
    String _strUrl;
    @JsonProperty(SOLR_FIELD_DAY_OPEN)
    String _strDayOpen;
    @JsonProperty(SOLR_FIELD_ENABLED)
    String _strEnabled;
    @JsonProperty(SOLR_FIELD_APPOINTMENT_ACTIVE)
    String _strAppointmentActive;
    @JsonProperty(SOLR_FIELD_NB_CONSECUTIVES_SLOTS)
    Long _lNbConsecutivesSlots;
    @JsonProperty(SOLR_FIELD_MAX_CONSECUTIVES_SLOTS)
    Long _lMaxConsecutivesSlots;

    public SolrAppointmentSlotPOJO() {
    }

    public String getUidFormString() {
        return _strUidFormString;
    }

    public void setUidFormString(String uidFormString) {
        _strUidFormString = uidFormString;
    }

    public String getDate() {
        return _strDate;
    }

    public void setDate(String date) {
        _strDate = date;
    }

    public String getUrl() {
        return _strUrl;
    }

    public void setUrl(String url) {
        _strUrl = url;
    }

    public String getDayOpen() {
        return _strDayOpen;
    }

    public void setDayOpen(String strDayOpen) {
        this._strDayOpen = strDayOpen;
    }

    public String getEnabled() {
        return _strEnabled;
    }

    public void setEnabled(String strEnabled) {
        this._strEnabled = strEnabled;
    }

    public String getAppointmentActive() {
        return _strAppointmentActive;
    }

    public void setAppointmentActive(String strAppointmentActive) {
        this._strAppointmentActive = strAppointmentActive;
    }

    public Long getNbConsecutivesSlots() {
        return _lNbConsecutivesSlots;
    }

    public void setNbConsecutivesSlots(Long lNbConsecutivesSlots) {
        this._lNbConsecutivesSlots = lNbConsecutivesSlots;
    }

    public Long get_lMaxConsecutivesSlots() {
        return _lMaxConsecutivesSlots;
    }

    public void setMaxConsecutivesSlots(Long lMaxConsecutivesSlots) {
        this._lMaxConsecutivesSlots = lMaxConsecutivesSlots;
    }
}
