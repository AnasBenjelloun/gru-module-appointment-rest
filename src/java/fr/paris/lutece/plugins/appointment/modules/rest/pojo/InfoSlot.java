package fr.paris.lutece.plugins.appointment.modules.rest.pojo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE, setterVisibility=JsonAutoDetect.Visibility.NONE, creatorVisibility= JsonAutoDetect.Visibility.NONE)
public class InfoSlot {
    @JsonProperty( "datetime" )
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm'Z'")
    private LocalDateTime _dateTime;
    @JsonProperty( "callback_url" )
    private String _strCallbackUrl;


    public InfoSlot() {
    }

    public InfoSlot(LocalDateTime dateTime, String strCallbackUrl) {
        this._dateTime = dateTime;
        this._strCallbackUrl = strCallbackUrl;
    }

    public LocalDateTime getDateTime() {
        return _dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this._dateTime = dateTime;
    }

    public String getCallbackUrl() {
        return _strCallbackUrl;
    }

    public void setCallbackUrl(String CallbackUrl) {
        this._strCallbackUrl = CallbackUrl;
    }
}
