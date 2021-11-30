package resus.licenseengine.fossology.model;


import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

public class Job   {
  
  @Schema(description = "ID of the job")
 /**
   * ID of the job  
  **/
  private Integer id = null;
  
  @Schema(description = "Name of the job")
 /**
   * Name of the job  
  **/
  private String name = null;
  
  @Schema(description = "When the job was queued")
 /**
   * When the job was queued  
  **/
  private String queueDate = null;
  
  @Schema(description = "Upload for which the job was scheduled")
 /**
   * Upload for which the job was scheduled  
  **/
  private Integer uploadId = null;
  
  @Schema(description = "User who scheduled the job")
 /**
   * User who scheduled the job  
  **/
  private Integer userId = null;
  
  @Schema(description = "Group under which the job was scheduled")
 /**
   * Group under which the job was scheduled  
  **/
  private Integer groupId = null;
  
  @Schema(description = "ETA of job to finish in seconds")
 /**
   * ETA of job to finish in seconds  
  **/
  private Integer eta = null;
  public enum StatusEnum {
    COMPLETED("Completed"),
    FAILED("Failed"),
    QUEUED("Queued"),
    PROCESSING("Processing");

    private String value;

    StatusEnum(String value) {
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
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }  
  @Schema(description = "Denotes the current status of the job in the queue")
 /**
   * Denotes the current status of the job in the queue  
  **/
  private StatusEnum status = null;
 /**
   * ID of the job
   * @return id
  **/
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Job id(Integer id) {
    this.id = id;
    return this;
  }

 /**
   * Name of the job
   * @return name
  **/
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Job name(String name) {
    this.name = name;
    return this;
  }

 /**
   * When the job was queued
   * @return queueDate
  **/
  @JsonProperty("queueDate")
  public String getQueueDate() {
    return queueDate;
  }

  public void setQueueDate(String queueDate) {
    this.queueDate = queueDate;
  }

  public Job queueDate(String queueDate) {
    this.queueDate = queueDate;
    return this;
  }

 /**
   * Upload for which the job was scheduled
   * @return uploadId
  **/
  @JsonProperty("uploadId")
  public Integer getUploadId() {
    return uploadId;
  }

  public void setUploadId(Integer uploadId) {
    this.uploadId = uploadId;
  }

  public Job uploadId(Integer uploadId) {
    this.uploadId = uploadId;
    return this;
  }

 /**
   * User who scheduled the job
   * @return userId
  **/
  @JsonProperty("userId")
  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Job userId(Integer userId) {
    this.userId = userId;
    return this;
  }

 /**
   * Group under which the job was scheduled
   * @return groupId
  **/
  @JsonProperty("groupId")
  public Integer getGroupId() {
    return groupId;
  }

  public void setGroupId(Integer groupId) {
    this.groupId = groupId;
  }

  public Job groupId(Integer groupId) {
    this.groupId = groupId;
    return this;
  }

 /**
   * ETA of job to finish in seconds
   * @return eta
  **/
  @JsonProperty("eta")
  public Integer getEta() {
    return eta;
  }

  public void setEta(Integer eta) {
    this.eta = eta;
  }

  public Job eta(Integer eta) {
    this.eta = eta;
    return this;
  }

 /**
   * Denotes the current status of the job in the queue
   * @return status
  **/
  @JsonProperty("status")
  public String getStatus() {
    if (status == null) {
      return null;
    }
    return status.getValue();
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public Job status(StatusEnum status) {
    this.status = status;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Job {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    queueDate: ").append(toIndentedString(queueDate)).append("\n");
    sb.append("    uploadId: ").append(toIndentedString(uploadId)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
    sb.append("    eta: ").append(toIndentedString(eta)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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
