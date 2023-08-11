package fr.paris.lutece.plugins.appointment.modules.rest.pojo;

import java.time.LocalDate;
import java.util.List;

public class AppointmentSlotsSearchPOJO {
    private List<String> _appointmentIds;
    private LocalDate _startDate;
    private LocalDate _endDate;
    private String _strReason;
    private Integer _nDocumentNumber;

    public AppointmentSlotsSearchPOJO() {
    }

    public AppointmentSlotsSearchPOJO(List<String> _appointmentIds, LocalDate _startDate, LocalDate _endDate) {
        this._appointmentIds = _appointmentIds;
        this._startDate = _startDate;
        this._endDate = _endDate;
    }

    public AppointmentSlotsSearchPOJO(List<String> _appointmentIds, LocalDate _startDate, LocalDate _endDate, String _strReason, Integer _nDocumentNumber) {
        this._appointmentIds = _appointmentIds;
        this._startDate = _startDate;
        this._endDate = _endDate;
        this._strReason = _strReason;
        this._nDocumentNumber = _nDocumentNumber;
    }

    public List<String> getAppointmentIds() {
        return _appointmentIds;
    }

    public void setAppointmentIds(List<String> appointmentIds) {
        this._appointmentIds = appointmentIds;
    }

    public LocalDate getStartDate() {
        return _startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this._startDate = startDate;
    }

    public LocalDate getEndDate() {
        return _endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this._endDate = endDate;
    }

    public String getReason() {
        return _strReason;
    }

    public void setReason(String reason) {
        this._strReason = reason;
    }

    public Integer getDocumentNumber() {
        return _nDocumentNumber;
    }

    public void setDocumentNumber(Integer documentNumber) {
        this._nDocumentNumber = documentNumber;
    }
}
