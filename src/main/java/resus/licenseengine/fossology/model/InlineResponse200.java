package resus.licenseengine.fossology.model;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InlineResponse200   {
  
  @Schema(description = "Current API version as per documentation")
 /**
   * Current API version as per documentation  
  **/
  private String version = null;
  
  @Schema(description = "")
  private List<String> security = null;
 /**
   * Current API version as per documentation
   * @return version
  **/
  @JsonProperty("version")
  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public InlineResponse200 version(String version) {
    this.version = version;
    return this;
  }

 /**
   * Get security
   * @return security
  **/
  @JsonProperty("security")
  public List<String> getSecurity() {
    return security;
  }

  public void setSecurity(List<String> security) {
    this.security = security;
  }

  public InlineResponse200 security(List<String> security) {
    this.security = security;
    return this;
  }

  public InlineResponse200 addSecurityItem(String securityItem) {
    this.security.add(securityItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse200 {\n");
    
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    security: ").append(toIndentedString(security)).append("\n");
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
