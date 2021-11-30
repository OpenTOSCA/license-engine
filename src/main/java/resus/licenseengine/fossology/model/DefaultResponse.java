package resus.licenseengine.fossology.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class DefaultResponse   {
  
  @Schema(description = "")
  private String header = null;
 /**
   * Get header
   * @return header
  **/
  @JsonProperty("header")
  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public DefaultResponse header(String header) {
    this.header = header;
    return this;
  }

  
  @Schema(description = "")
  private String authorization = null;
 /**
   * Get Authorization
   * @return Authorization
  **/
  @JsonProperty("Authorization")
  public String getAuthorization() {
    return authorization;
  }

  public void setAuthorization(String authorization) {
    this.authorization = authorization;
  }

  public DefaultResponse authorization(String authorization) {
    this.authorization = authorization;
    return this;
  }
  

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse299 {\n");
    
    sb.append("    header: ").append(toIndentedString(header)).append("\n");
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
