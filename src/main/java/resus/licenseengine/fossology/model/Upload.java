package resus.licenseengine.fossology.model;


import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Upload   {
  
  @Schema(description = "The folder id, where the upload is located")
 /**
   * The folder id, where the upload is located  
  **/
  private Integer folderid = null;
  
  @Schema(description = "The name of the folder where the upload is located")
 /**
   * The name of the folder where the upload is located  
  **/
  private String foldername = null;
  
  @Schema(description = "Upload id of the upload.")
 /**
   * Upload id of the upload.  
  **/
  private Integer id = null;
  
  @Schema(description = "Description of the upload.")
 /**
   * Description of the upload.  
  **/
  private String description = null;
  
  @Schema(description = "Display name of the upload.")
 /**
   * Display name of the upload.  
  **/
  private String uploadname = null;
  
  @Schema(description = "Date, when the file was uploaded.")
 /**
   * Date, when the file was uploaded.  
  **/
  private String uploaddate = null;
  
  @Schema(description = "Filesize in Bytes.")
 /**
   * Filesize in Bytes.  
  **/
  private Integer filesize = null;
  
  @Schema(description = "SHA1 digest of the file")
 /**
   * SHA1 digest of the file  
  **/
  private String filesha1 = null;
 /**
   * The folder id, where the upload is located
   * @return folderid
  **/
  @JsonProperty("folderid")
  public Integer getFolderid() {
    return folderid;
  }

  public void setFolderid(Integer folderid) {
    this.folderid = folderid;
  }

  public Upload folderid(Integer folderid) {
    this.folderid = folderid;
    return this;
  }

 /**
   * The name of the folder where the upload is located
   * @return foldername
  **/
  @JsonProperty("foldername")
  public String getFoldername() {
    return foldername;
  }

  public void setFoldername(String foldername) {
    this.foldername = foldername;
  }

  public Upload foldername(String foldername) {
    this.foldername = foldername;
    return this;
  }

 /**
   * Upload id of the upload.
   * @return id
  **/
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Upload id(Integer id) {
    this.id = id;
    return this;
  }

 /**
   * Description of the upload.
   * @return description
  **/
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Upload description(String description) {
    this.description = description;
    return this;
  }

 /**
   * Display name of the upload.
   * @return uploadname
  **/
  @JsonProperty("uploadname")
  public String getUploadname() {
    return uploadname;
  }

  public void setUploadname(String uploadname) {
    this.uploadname = uploadname;
  }

  public Upload uploadname(String uploadname) {
    this.uploadname = uploadname;
    return this;
  }

 /**
   * Date, when the file was uploaded.
   * @return uploaddate
  **/
  @JsonProperty("uploaddate")
  public String getUploaddate() {
    return uploaddate;
  }

  public void setUploaddate(String uploaddate) {
    this.uploaddate = uploaddate;
  }

  public Upload uploaddate(String uploaddate) {
    this.uploaddate = uploaddate;
    return this;
  }

 /**
   * Filesize in Bytes.
   * @return filesize
  **/
  @JsonProperty("filesize")
  public Integer getFilesize() {
    return filesize;
  }

  public void setFilesize(Integer filesize) {
    this.filesize = filesize;
  }

  public Upload filesize(Integer filesize) {
    this.filesize = filesize;
    return this;
  }

 /**
   * SHA1 digest of the file
   * @return filesha1
  **/
  @JsonProperty("filesha1")
  public String getFilesha1() {
    return filesha1;
  }

  public void setFilesha1(String filesha1) {
    this.filesha1 = filesha1;
  }

  public Upload filesha1(String filesha1) {
    this.filesha1 = filesha1;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Upload {\n");
    
    sb.append("    folderid: ").append(toIndentedString(folderid)).append("\n");
    sb.append("    foldername: ").append(toIndentedString(foldername)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    uploadname: ").append(toIndentedString(uploadname)).append("\n");
    sb.append("    uploaddate: ").append(toIndentedString(uploaddate)).append("\n");
    sb.append("    filesize: ").append(toIndentedString(filesize)).append("\n");
    sb.append("    filesha1: ").append(toIndentedString(filesha1)).append("\n");
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
