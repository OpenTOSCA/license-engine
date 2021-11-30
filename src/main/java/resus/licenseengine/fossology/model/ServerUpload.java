package resus.licenseengine.fossology.model;

import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
  * To create an upload from a server
 **/
@Schema(description="To create an upload from a server")
public class ServerUpload  implements OneOfbody  {
  
  @Schema(required = true, description = "File path to be uploaded (reccursive, support *)")
 /**
   * File path to be uploaded (reccursive, support *)  
  **/
  private String path = null;
  
  @Schema(description = "Viewable name for this file or directory")
 /**
   * Viewable name for this file or directory  
  **/
  private String name = null;
 /**
   * File path to be uploaded (reccursive, support *)
   * @return path
  **/
  @JsonProperty("path")
  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public ServerUpload path(String path) {
    this.path = path;
    return this;
  }

 /**
   * Viewable name for this file or directory
   * @return name
  **/
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ServerUpload name(String name) {
    this.name = name;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServerUpload {\n");
    
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
