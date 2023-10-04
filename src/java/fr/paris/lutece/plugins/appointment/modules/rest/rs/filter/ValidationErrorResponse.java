package fr.paris.lutece.plugins.appointment.modules.rest.rs.filter;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse {
    private List<Detail> detail = new ArrayList<>();

    public List<Detail> getDetail() {
        return detail;
    }

    public void setDetail(List<Detail> detail) {
        this.detail = detail;
    }

    public void addDetail(Detail d) {
        detail.add(d);
    }

    static class Detail {
        private List<Object> loc = new ArrayList<>();
        private String msg;
        private String type;

        public Detail(List<Object> loc, String msg, String type) {
            this.loc = loc;
            this.msg = msg;
            this.type = type;
        }

        public Detail(Object loc, String msg, String type) {
            this.loc.add(loc);
            this.msg = msg;
            this.type = type;
        }

        public List<Object> getLoc() {
            return loc;
        }

        public void setLoc(List<Object> loc) {
            this.loc = loc;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
