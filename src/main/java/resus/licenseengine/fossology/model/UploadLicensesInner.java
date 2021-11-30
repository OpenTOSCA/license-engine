package resus.licenseengine.fossology.model;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UploadLicensesInner   {
  
  @Schema(example = "path/to/LICENSE", description = "Relative file path")
 /**
   * Relative file path  
  **/
  private String filePath = null;
  
  @Schema(example = "[\"MIT\",\"No_license_found\"]", description = "Short names of the found licenses")
 /**
   * Short names of the found licenses  
  **/
  private List<String> agentFindings = null;
  
  @Schema(example = "[\"MIT\",\"No_license_found\"]", description = "License decided by user")
 /**
   * License decided by user  
  **/
  private List<String> conclusions = null;
 /**
   * Relative file path
   * @return filePath
  **/
  @JsonProperty("filePath")
  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public UploadLicensesInner filePath(String filePath) {
    this.filePath = filePath;
    return this;
  }

 /**
   * Short names of the found licenses
   * @return agentFindings
  **/
  @JsonProperty("agentFindings")
  public List<String> getAgentFindings() {
    return agentFindings;
  }

  public void setAgentFindings(List<String> agentFindings) {
    this.agentFindings = agentFindings;
  }

  public UploadLicensesInner agentFindings(List<String> agentFindings) {
    this.agentFindings = agentFindings;
    return this;
  }

  public UploadLicensesInner addAgentFindingsItem(String agentFindingsItem) {
    this.agentFindings.add(agentFindingsItem);
    return this;
  }

 /**
   * License decided by user
   * @return conclusions
  **/
  @JsonProperty("conclusions")
  public List<String> getConclusions() {
    return conclusions;
  }

  public void setConclusions(List<String> conclusions) {
    this.conclusions = conclusions;
  }

  public UploadLicensesInner conclusions(List<String> conclusions) {
    this.conclusions = conclusions;
    return this;
  }

  public UploadLicensesInner addConclusionsItem(String conclusionsItem) {
    this.conclusions.add(conclusionsItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UploadLicensesInner {\n");
    
    sb.append("    filePath: ").append(toIndentedString(filePath)).append("\n");
    sb.append("    agentFindings: ").append(toIndentedString(agentFindings)).append("\n");
    sb.append("    conclusions: ").append(toIndentedString(conclusions)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private static String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
