package resus.licenseengine.fossology.model;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

public class User   {
  
  @Schema(required = true, description = "ID of the user")
 /**
   * ID of the user  
  **/
  private Integer id = null;
  
  @Schema(required = true, description = "Unique username")
 /**
   * Unique username  
  **/
  private String name = null;
  
  @Schema(required = true, description = "Description of the user")
 /**
   * Description of the user  
  **/
  private String description = null;
  
  @Schema(description = "Email of the user, needs to be unique and is required")
 /**
   * Email of the user, needs to be unique and is required  
  **/
  private String email = null;
  public enum AccessLevelEnum {
    NONE("none"),
    READ_ONLY("read_only"),
    READ_WRITE("read_write"),
    ADMIN("admin");

    private String value;

    AccessLevelEnum(String value) {
      this.value = value;
    }
    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
    @JsonCreator
    public static AccessLevelEnum fromValue(String text) {
      for (AccessLevelEnum b : AccessLevelEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }  
  @Schema(description = "")
  private AccessLevelEnum accessLevel = null;
  
  @Schema(description = "root folder id of the user")
 /**
   * root folder id of the user  
  **/
  private BigDecimal rootFolderId = null;
  
  @Schema(description = "enable email notification when upload scan completes")
 /**
   * enable email notification when upload scan completes  
  **/
  private Boolean emailNotification = null;
  
  @Schema(description = "")
  private Analysis agents = null;
 /**
   * ID of the user
   * @return id
  **/
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public User id(Integer id) {
    this.id = id;
    return this;
  }

 /**
   * Unique username
   * @return name
  **/
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User name(String name) {
    this.name = name;
    return this;
  }

 /**
   * Description of the user
   * @return description
  **/
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public User description(String description) {
    this.description = description;
    return this;
  }

 /**
   * Email of the user, needs to be unique and is required
   * @return email
  **/
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public User email(String email) {
    this.email = email;
    return this;
  }

 /**
   * Get accessLevel
   * @return accessLevel
  **/
  @JsonProperty("accessLevel")
  public String getAccessLevel() {
    if (accessLevel == null) {
      return null;
    }
    return accessLevel.getValue();
  }

  public void setAccessLevel(AccessLevelEnum accessLevel) {
    this.accessLevel = accessLevel;
  }

  public User accessLevel(AccessLevelEnum accessLevel) {
    this.accessLevel = accessLevel;
    return this;
  }

 /**
   * root folder id of the user
   * @return rootFolderId
  **/
  @JsonProperty("rootFolderId")
  public BigDecimal getRootFolderId() {
    return rootFolderId;
  }

  public void setRootFolderId(BigDecimal rootFolderId) {
    this.rootFolderId = rootFolderId;
  }

  public User rootFolderId(BigDecimal rootFolderId) {
    this.rootFolderId = rootFolderId;
    return this;
  }

 /**
   * enable email notification when upload scan completes
   * @return emailNotification
  **/
  @JsonProperty("emailNotification")
  public Boolean isEmailNotification() {
    return emailNotification;
  }

  public void setEmailNotification(Boolean emailNotification) {
    this.emailNotification = emailNotification;
  }

  public User emailNotification(Boolean emailNotification) {
    this.emailNotification = emailNotification;
    return this;
  }

 /**
   * Get agents
   * @return agents
  **/
  @JsonProperty("agents")
  public Analysis getAgents() {
    return agents;
  }

  public void setAgents(Analysis agents) {
    this.agents = agents;
  }

  public User agents(Analysis agents) {
    this.agents = agents;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    accessLevel: ").append(toIndentedString(accessLevel)).append("\n");
    sb.append("    rootFolderId: ").append(toIndentedString(rootFolderId)).append("\n");
    sb.append("    emailNotification: ").append(toIndentedString(emailNotification)).append("\n");
    sb.append("    agents: ").append(toIndentedString(agents)).append("\n");
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
