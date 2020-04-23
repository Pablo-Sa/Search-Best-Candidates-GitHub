
package br.com.integrationofcamel.dto.finalcandidates;

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
    "finalcandidates"
})
public class FinalCandidatesEvaluated implements Serializable
{

    @JsonProperty("finalcandidates")
    private List<Finalcandidate> finalcandidates = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -3376300071995157832L;

    @JsonProperty("finalcandidates")
    public List<Finalcandidate> getFinalcandidates() {
        return finalcandidates;
    }

    @JsonProperty("finalcandidates")
    public void setFinalcandidates(List<Finalcandidate> finalcandidates) {
        this.finalcandidates = finalcandidates;
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
        return new ToStringBuilder(this).append("finalcandidates", finalcandidates).append("additionalProperties", additionalProperties).toString();
    }

}
