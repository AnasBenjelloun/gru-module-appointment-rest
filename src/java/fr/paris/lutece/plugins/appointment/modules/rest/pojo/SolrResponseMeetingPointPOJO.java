package fr.paris.lutece.plugins.appointment.modules.rest.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SolrResponseMeetingPointPOJO {

    @JsonProperty("grouped")
    private Grouped grouped;

    public Grouped getGrouped() {
        return grouped;
    }

    public void setGrouped(Grouped grouped) {
        this.grouped = grouped;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Grouped {

        @JsonProperty("uid_form_string")
        private GroupedByUidForm groupedByUidForm;

        public GroupedByUidForm getGroupedByUidForm() {
            return groupedByUidForm;
        }

        public void setGroupedByUidForm(GroupedByUidForm groupedByUidForm) {
            this.groupedByUidForm = groupedByUidForm;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GroupedByUidForm {

        @JsonProperty("groups")
        private List<Group> groups;

        public List<Group> getGroups() {
            return groups;
        }

        public void setGroups(List<Group> groups) {
            this.groups = groups;
        }

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Group {

        @JsonProperty("groupValue")
        private String groupValue;
        @JsonProperty("doclist")
        private DocList docList;

        public String getGroupValue() {
            return groupValue;
        }

        public void setGroupValue(String groupValue) {
            this.groupValue = groupValue;
        }

        public DocList getDocList() {
            return docList;
        }

        public void setDocList(DocList docList) {
            this.docList = docList;
        }

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DocList {

        @JsonProperty("docs")
        private List<SolrMeetingPointPOJO> docs;

        public List<SolrMeetingPointPOJO> getDocs() {
            return docs;
        }

        public void setDocs(List<SolrMeetingPointPOJO> docs) {
            this.docs = docs;
        }
    }
}
