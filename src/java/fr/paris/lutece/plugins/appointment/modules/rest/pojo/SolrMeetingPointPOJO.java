package fr.paris.lutece.plugins.appointment.modules.rest.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SolrMeetingPointPOJO {
    public final static String SOLR_FIELD_UID = "uid_form_string";
    public final static String SOLR_FIELD_TITLE = "title";
    public final static String SOLR_FIELD_ADDRESS = "appointmentslot_address_text";
    public final static String SOLR_FIELD_GEOLOC = "appointmentslot_geoloc";

    @JsonProperty(SOLR_FIELD_UID)
    String _strUidFormString;
    @JsonProperty(SOLR_FIELD_TITLE)
    String _strTitle;
    @JsonProperty(SOLR_FIELD_ADDRESS)
    String _strAddressText;
    @JsonProperty(SOLR_FIELD_GEOLOC)
    String _strGeoloc;

    public String getUidFormString() {
        return _strUidFormString;
    }

    public void setUidFormString(String strUidFormString) {
        this._strUidFormString = strUidFormString;
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
