package resus.licenseengine.fossology.model;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchResults   {
  
  @Schema(description = "")
  private Upload upload = null;
  
  @Schema(description = "Upload tree ID")
 /**
   * Upload tree ID  
  **/
  private Integer uploadTreeId = null;
  
  @Schema(description = "Filename of the treeItem")
 /**
   * Filename of the treeItem  
  **/
  private String filename = null;
 /**
   * Get upload
   * @return upload
  **/
  @JsonProperty("upload")
  public Upload getUpload() {
    return upload;
  }

  public void setUpload(Upload upload) {
    this.upload = upload;
  }

  public SearchResults upload(Upload upload) {
    this.upload = upload;
    return this;
  }

 /**
   * Upload tree ID
   * @return uploadTreeId
  **/
  @JsonProperty("uploadTreeId")
  public Integer getUploadTreeId() {
    return uploadTreeId;
  }

  public void setUploadTreeId(Integer uploadTreeId) {
    this.uploadTreeId = uploadTreeId;
  }

  public SearchResults uploadTreeId(Integer uploadTreeId) {
    this.uploadTreeId = uploadTreeId;
    return this;
  }

 /**
   * Filename of the treeItem
   * @return filename
  **/
  @JsonProperty("filename")
  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public SearchResults filename(String filename) {
    this.filename = filename;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SearchResults {\n");
    
    sb.append("    upload: ").append(toIndentedString(upload)).append("\n");
    sb.append("    uploadTreeId: ").append(toIndentedString(uploadTreeId)).append("\n");
    sb.append("    filename: ").append(toIndentedString(filename)).append("\n");
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
