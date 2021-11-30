package resus.licenseengine.fossology.model;


import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Folder   {
  
  @Schema(description = "Id of the folder.")
 /**
   * Id of the folder.  
  **/
  private Integer id = null;
  
  @Schema(description = "Name of the folder.")
 /**
   * Name of the folder.  
  **/
  private String name = null;
  
  @Schema(description = "Description of the folder.")
 /**
   * Description of the folder.  
  **/
  private String description = null;
  
  @Schema(description = "Id of the parent folder (if any, null otherwise).")
 /**
   * Id of the parent folder (if any, null otherwise).  
  **/
  private Integer parent = null;
 /**
   * Id of the folder.
   * @return id
  **/
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Folder id(Integer id) {
    this.id = id;
    return this;
  }

 /**
   * Name of the folder.
   * @return name
  **/
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Folder name(String name) {
    this.name = name;
    return this;
  }

 /**
   * Description of the folder.
   * @return description
  **/
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Folder description(String description) {
    this.description = description;
    return this;
  }

 /**
   * Id of the parent folder (if any, null otherwise).
   * @return parent
  **/
  @JsonProperty("parent")
  public Integer getParent() {
    return parent;
  }

  public void setParent(Integer parent) {
    this.parent = parent;
  }

  public Folder parent(Integer parent) {
    this.parent = parent;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Folder {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    parent: ").append(toIndentedString(parent)).append("\n");
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
