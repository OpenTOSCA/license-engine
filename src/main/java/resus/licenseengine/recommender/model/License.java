package resus.licenseengine.recommender.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class License   {
  
  @Schema(required = true, description = "")
  private String id = null;
  
  @Schema(required = true, description = "")
  private String name = null;
  
  @Schema(description = "")
  private String url = null;
  
  @Schema(description = "")
  private LicenseType type = null;
  
  @Schema(description = "")
  private List<Condition> conditions = null;
  
  @Schema(description = "")
  private List<Permission> permissions = null;
  
  @Schema(description = "")
  private List<Limitation> limitations = null;
 /**
   * Get id
   * @return id
  **/
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public License id(String id) {
    this.id = id;
    return this;
  }

 /**
   * Get name
   * @return name
  **/
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public License name(String name) {
    this.name = name;
    return this;
  }

 /**
   * Get url
   * @return url
  **/
  @JsonProperty("url")
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public License url(String url) {
    this.url = url;
    return this;
  }

 /**
   * Get type
   * @return type
  **/
  @JsonProperty("type")
  public LicenseType getType() {
    return type;
  }

  public void setType(LicenseType type) {
    this.type = type;
  }

  public License type(LicenseType type) {
    this.type = type;
    return this;
  }

 /**
   * Get conditions
   * @return conditions
  **/
  @JsonProperty("conditions")
  public List<Condition> getConditions() {
    return conditions;
  }

  public void setConditions(List<Condition> conditions) {
    this.conditions = conditions;
  }

  public License conditions(List<Condition> conditions) {
    this.conditions = conditions;
    return this;
  }

  public License addConditionsItem(Condition conditionsItem) {
    this.conditions.add(conditionsItem);
    return this;
  }

 /**
   * Get permissions
   * @return permissions
  **/
  @JsonProperty("permissions")
  public List<Permission> getPermissions() {
    return permissions;
  }

  public void setPermissions(List<Permission> permissions) {
    this.permissions = permissions;
  }

  public License permissions(List<Permission> permissions) {
    this.permissions = permissions;
    return this;
  }

  public License addPermissionsItem(Permission permissionsItem) {
    this.permissions.add(permissionsItem);
    return this;
  }

 /**
   * Get limitations
   * @return limitations
  **/
  @JsonProperty("limitations")
  public List<Limitation> getLimitations() {
    return limitations;
  }

  public void setLimitations(List<Limitation> limitations) {
    this.limitations = limitations;
  }

  public License limitations(List<Limitation> limitations) {
    this.limitations = limitations;
    return this;
  }

  public License addLimitationsItem(Limitation limitationsItem) {
    this.limitations.add(limitationsItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class License {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    conditions: ").append(toIndentedString(conditions)).append("\n");
    sb.append("    permissions: ").append(toIndentedString(permissions)).append("\n");
    sb.append("    limitations: ").append(toIndentedString(limitations)).append("\n");
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
