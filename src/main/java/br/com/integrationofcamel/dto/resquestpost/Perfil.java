
package br.com.integrationofcamel.dto.resquestpost;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "languages",
    "frameworks",
    "location"
})
public class Perfil implements Serializable
{

    @JsonProperty("languages")
    private List<String> languages = null;
    @JsonProperty("frameworks")
    private List<String> frameworks = null;
    @JsonProperty("location")
    private String location;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -3037624924718247381L;

    @JsonProperty("languages")
    public List<String> getLanguages() {
        return languages;
    }

    @JsonProperty("languages")
    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    @JsonProperty("frameworks")
    public List<String> getFrameworks() {
        return frameworks;
    }

    @JsonProperty("frameworks")
    public void setFrameworks(List<String> frameworks) {
        this.frameworks = frameworks;
    }

    @JsonProperty("location")
    public String getlocation() {
        return location;
    }

    @JsonProperty("location")
    public void setlocation(String location) {
        this.location = location;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("languages", languages).append("frameworks", frameworks).append("location", location).append("additionalProperties", additionalProperties).toString();
    }

}
