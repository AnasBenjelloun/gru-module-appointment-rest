package fr.paris.lutece.plugins.appointment.modules.rest.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SolrMeetingPointPOJO {
    public final static String SOLR_FIELD_UID = "uid";
    public final static String SOLR_FIELD_TITLE = "title";
    public final static String SOLR_FIELD_ADDRESS = "appointment_address_text";
    public final static String SOLR_FIELD_GEOLOC = "appointment_geoloc";
    public static final String SOLR_FIELD_TYPE = "type";
    public static final String SOLR_FIELD_TYPE_APPOINTMENT = "appointment";

    @JsonProperty(SOLR_FIELD_UID)
    String _strUid;
    @JsonProperty(SOLR_FIELD_TITLE)
    String _strTitle;
    @JsonProperty(SOLR_FIELD_ADDRESS)
    String _strAddressText;
    @JsonProperty(SOLR_FIELD_GEOLOC)
    String _strGeoloc;

    public String getUid() {
        return _strUid;
    }

    public void setUid(String strUid) {
        this._strUid = strUid;
    }

    public String getTitle() {
        return _strTitle;
    }

    public void setTitle(String strTitle) {
        this._strTitle = strTitle;
    }

    public String getAddressText() {
        return _strAddressText;
    }

    public void setAddressText(String strAddressText) {
        this._strAddressText = strAddressText;
    }

    public String getGeoloc() {
        return _strGeoloc;
    }

    public void setGeoloc(String strGeoloc) {
        this._strGeoloc = strGeoloc;
    }
}
