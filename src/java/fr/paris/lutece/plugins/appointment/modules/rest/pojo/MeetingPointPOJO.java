package fr.paris.lutece.plugins.appointment.modules.rest.pojo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE, setterVisibility=JsonAutoDetect.Visibility.NONE, creatorVisibility= JsonAutoDetect.Visibility.NONE)
public class MeetingPointPOJO {
    @JsonProperty( "id" )
    private String _strId;
    @JsonProperty( "name" )
    private String _strName;
    @JsonProperty( "longitude" )
    private String _strLongitude;
    @JsonProperty( "latitude" )
    private String _strLatitude;
    @JsonProperty( "public_entry_address" )
    private String _strPublicAdress;
    @JsonProperty( "zip_code" )
    private String _strZipCode;
    @JsonProperty( "city_name" )
    private String _strCityName;
    @JsonProperty( "website" )
    private String _strWebsite;
    @JsonProperty( "city_logo" )
    private String _strCityLogo;

    public MeetingPointPOJO() {
    }

    public MeetingPointPOJO(String strId, String strName, String strLongitude, String strLatitude, String strPublicAdress, String strZipCode, String strCityName, String strWebsite, String strCityLogo) {
        this._strId = strId;
        this._strName = strName;
        this._strLongitude = strLongitude;
        this._strLatitude = strLatitude;
        this._strPublicAdress = strPublicAdress;
        this._strZipCode = strZipCode;
        this._strCityName = strCityName;
        this._strWebsite = strWebsite;
        this._strCityLogo = strCityLogo;
    }

    public String getId() {
        return _strId;
    }

    public void setId(String strId) {
        this._strId = strId;
    }

    public String getName() {
        return _strName;
    }

    public void setName(String strName) {
        this._strName = _strName;
    }

    public String getLongitude() {
        return _strLongitude;
    }

    public void setLongitude(String strLongitude) {
        this._strLongitude = strLongitude;
    }

    public String getLatitude() {
        return _strLatitude;
    }

    public void setLatitude(String strLatitude) {
        this._strLatitude = strLatitude;
    }

    public String getPublicAdress() {
        return _strPublicAdress;
    }

    public void setPublicAdress(String strPublicAdress) {
        this._strPublicAdress = strPublicAdress;
    }

    public String getZipCode() {
        return _strZipCode;
    }

    public void setZipCode(String strZipCode) {
        this._strZipCode = strZipCode;
    }

    public String getCityName() {
        return _strCityName;
    }

    public void setCityName(String strCityName) {
        this._strCityName = strCityName;
    }

    public String getWebsite() {
        return _strWebsite;
    }

    public void setWebsite(String strWebsite) {
        this._strWebsite = strWebsite;
    }

    public String getCityLogo() {
        return _strCityLogo;
    }

    public void setCityLogo(String strCityLogo) {
        this._strCityLogo = strCityLogo;
    }
}
