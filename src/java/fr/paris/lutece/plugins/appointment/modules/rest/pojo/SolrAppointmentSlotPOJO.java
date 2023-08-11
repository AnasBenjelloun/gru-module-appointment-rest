package fr.paris.lutece.plugins.appointment.modules.rest.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SolrAppointmentSlotPOJO {

    public final static String SOLR_FIELD_UID = "uid_form_string";
    public final static String SOLR_FIELD_DATE = "date";
    public final static String SOLR_FIELD_URL = "url";


    @JsonProperty(SOLR_FIELD_UID)
    String _strUidFormString;
    @JsonProperty(SOLR_FIELD_DATE)
    String _strDate;
    @JsonProperty(SOLR_FIELD_DATE)
    String _strUrl;

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
}
