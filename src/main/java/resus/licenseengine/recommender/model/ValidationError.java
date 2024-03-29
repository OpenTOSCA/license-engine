package resus.licenseengine.recommender.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class ValidationError   {
  
  @Schema(required = true, description = "")
  private List<String> loc = new ArrayList<String>();
  
  @Schema(required = true, description = "")
  private String msg = null;
  
  @Schema(required = true, description = "")
  private String type = null;
 /**
   * Get loc
   * @return loc
  **/
  @JsonProperty("loc")
  public List<String> getLoc() {
    return loc;
  }

  public void setLoc(List<String> loc) {
    this.loc = loc;
  }

  public ValidationError loc(List<String> loc) {
    this.loc = loc;
    return this;
  }

  public ValidationError addLocItem(String locItem) {
    this.loc.add(locItem);
    return this;
  }

 /**
   * Get msg
   * @return msg
  **/
  @JsonProperty("msg")
  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public ValidationError msg(String msg) {
    this.msg = msg;
    return this;
  }

 /**
   * Get type
   * @return type
  **/
  @JsonProperty("type")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public ValidationError type(String type) {
    this.type = type;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ValidationError {\n");
    
    sb.append("    loc: ").append(toIndentedString(loc)).append("\n");
    sb.append("    msg: ").append(toIndentedString(msg)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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
