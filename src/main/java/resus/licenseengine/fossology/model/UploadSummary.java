package resus.licenseengine.fossology.model;


import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

public class UploadSummary   {
  
  @Schema(description = "Upload id of the upload.")
 /**
   * Upload id of the upload.  
  **/
  private Integer id = null;
  
  @Schema(description = "Display name of the upload.")
 /**
   * Display name of the upload.  
  **/
  private String uploadName = null;
  
  @Schema(description = "Main license selected on the upload.")
 /**
   * Main license selected on the upload.  
  **/
  private String mainLicense = null;
  
  @Schema(description = "No. of unique licenses found.")
 /**
   * No. of unique licenses found.  
  **/
  private Integer uniqueLicenses = null;
  
  @Schema(description = "Total no. of licenses found.")
 /**
   * Total no. of licenses found.  
  **/
  private Integer totalLicenses = null;
  
  @Schema(description = "Unique licenses concluded.")
 /**
   * Unique licenses concluded.  
  **/
  private Integer uniqueConcludedLicenses = null;
  
  @Schema(description = "Total concluded licenses.")
 /**
   * Total concluded licenses.  
  **/
  private Integer totalConcludedLicenses = null;
  
  @Schema(description = "Files without clearing decisions.")
 /**
   * Files without clearing decisions.  
  **/
  private Integer filesToBeCleared = null;
  
  @Schema(description = "Files with clearing decisions.")
 /**
   * Files with clearing decisions.  
  **/
  private Integer filesCleared = null;
  public enum ClearingStatusEnum {
    OPEN("Open"),
    INPROGRESS("InProgress"),
    CLOSED("Closed"),
    REJECTED("Rejected");

    private String value;

    ClearingStatusEnum(String value) {
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
    public static ClearingStatusEnum fromValue(String text) {
      for (ClearingStatusEnum b : ClearingStatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }  
  @Schema(description = "Upload is clearing status.")
 /**
   * Upload is clearing status.  
  **/
  private ClearingStatusEnum clearingStatus = null;
  
  @Schema(description = "No. of copyrights found in the upload.")
 /**
   * No. of copyrights found in the upload.  
  **/
  private Integer copyrightCount = null;
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

  public UploadSummary id(Integer id) {
    this.id = id;
    return this;
  }

 /**
   * Display name of the upload.
   * @return uploadName
  **/
  @JsonProperty("uploadName")
  public String getUploadName() {
    return uploadName;
  }

  public void setUploadName(String uploadName) {
    this.uploadName = uploadName;
  }

  public UploadSummary uploadName(String uploadName) {
    this.uploadName = uploadName;
    return this;
  }

 /**
   * Main license selected on the upload.
   * @return mainLicense
  **/
  @JsonProperty("mainLicense")
  public String getMainLicense() {
    return mainLicense;
  }

  public void setMainLicense(String mainLicense) {
    this.mainLicense = mainLicense;
  }

  public UploadSummary mainLicense(String mainLicense) {
    this.mainLicense = mainLicense;
    return this;
  }

 /**
   * No. of unique licenses found.
   * @return uniqueLicenses
  **/
  @JsonProperty("uniqueLicenses")
  public Integer getUniqueLicenses() {
    return uniqueLicenses;
  }

  public void setUniqueLicenses(Integer uniqueLicenses) {
    this.uniqueLicenses = uniqueLicenses;
  }

  public UploadSummary uniqueLicenses(Integer uniqueLicenses) {
    this.uniqueLicenses = uniqueLicenses;
    return this;
  }

 /**
   * Total no. of licenses found.
   * @return totalLicenses
  **/
  @JsonProperty("totalLicenses")
  public Integer getTotalLicenses() {
    return totalLicenses;
  }

  public void setTotalLicenses(Integer totalLicenses) {
    this.totalLicenses = totalLicenses;
  }

  public UploadSummary totalLicenses(Integer totalLicenses) {
    this.totalLicenses = totalLicenses;
    return this;
  }

 /**
   * Unique licenses concluded.
   * @return uniqueConcludedLicenses
  **/
  @JsonProperty("uniqueConcludedLicenses")
  public Integer getUniqueConcludedLicenses() {
    return uniqueConcludedLicenses;
  }

  public void setUniqueConcludedLicenses(Integer uniqueConcludedLicenses) {
    this.uniqueConcludedLicenses = uniqueConcludedLicenses;
  }

  public UploadSummary uniqueConcludedLicenses(Integer uniqueConcludedLicenses) {
    this.uniqueConcludedLicenses = uniqueConcludedLicenses;
    return this;
  }

 /**
   * Total concluded licenses.
   * @return totalConcludedLicenses
  **/
  @JsonProperty("totalConcludedLicenses")
  public Integer getTotalConcludedLicenses() {
    return totalConcludedLicenses;
  }

  public void setTotalConcludedLicenses(Integer totalConcludedLicenses) {
    this.totalConcludedLicenses = totalConcludedLicenses;
  }

  public UploadSummary totalConcludedLicenses(Integer totalConcludedLicenses) {
    this.totalConcludedLicenses = totalConcludedLicenses;
    return this;
  }

 /**
   * Files without clearing decisions.
   * @return filesToBeCleared
  **/
  @JsonProperty("filesToBeCleared")
  public Integer getFilesToBeCleared() {
    return filesToBeCleared;
  }

  public void setFilesToBeCleared(Integer filesToBeCleared) {
    this.filesToBeCleared = filesToBeCleared;
  }

  public UploadSummary filesToBeCleared(Integer filesToBeCleared) {
    this.filesToBeCleared = filesToBeCleared;
    return this;
  }

 /**
   * Files with clearing decisions.
   * @return filesCleared
  **/
  @JsonProperty("filesCleared")
  public Integer getFilesCleared() {
    return filesCleared;
  }

  public void setFilesCleared(Integer filesCleared) {
    this.filesCleared = filesCleared;
  }

  public UploadSummary filesCleared(Integer filesCleared) {
    this.filesCleared = filesCleared;
    return this;
  }

 /**
   * Upload is clearing status.
   * @return clearingStatus
  **/
  @JsonProperty("clearingStatus")
  public String getClearingStatus() {
    if (clearingStatus == null) {
      return null;
    }
    return clearingStatus.getValue();
  }

  public void setClearingStatus(ClearingStatusEnum clearingStatus) {
    this.clearingStatus = clearingStatus;
  }

  public UploadSummary clearingStatus(ClearingStatusEnum clearingStatus) {
    this.clearingStatus = clearingStatus;
    return this;
  }

 /**
   * No. of copyrights found in the upload.
   * @return copyrightCount
  **/
  @JsonProperty("copyrightCount")
  public Integer getCopyrightCount() {
    return copyrightCount;
  }

  public void setCopyrightCount(Integer copyrightCount) {
    this.copyrightCount = copyrightCount;
  }

  public UploadSummary copyrightCount(Integer copyrightCount) {
    this.copyrightCount = copyrightCount;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UploadSummary {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    uploadName: ").append(toIndentedString(uploadName)).append("\n");
    sb.append("    mainLicense: ").append(toIndentedString(mainLicense)).append("\n");
    sb.append("    uniqueLicenses: ").append(toIndentedString(uniqueLicenses)).append("\n");
    sb.append("    totalLicenses: ").append(toIndentedString(totalLicenses)).append("\n");
    sb.append("    uniqueConcludedLicenses: ").append(toIndentedString(uniqueConcludedLicenses)).append("\n");
    sb.append("    totalConcludedLicenses: ").append(toIndentedString(totalConcludedLicenses)).append("\n");
    sb.append("    filesToBeCleared: ").append(toIndentedString(filesToBeCleared)).append("\n");
    sb.append("    filesCleared: ").append(toIndentedString(filesCleared)).append("\n");
    sb.append("    clearingStatus: ").append(toIndentedString(clearingStatus)).append("\n");
    sb.append("    copyrightCount: ").append(toIndentedString(copyrightCount)).append("\n");
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
