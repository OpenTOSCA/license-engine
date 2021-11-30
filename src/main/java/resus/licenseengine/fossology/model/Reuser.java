package resus.licenseengine.fossology.model;


import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Reuser   {
  
  @Schema(description = "The UploadID to reuse.")
 /**
   * The UploadID to reuse.  
  **/
  private Integer reuseUpload = null;
  
  @Schema(required = true, description = "The group of the reused upload")
 /**
   * The group of the reused upload  
  **/
  private Integer reuseGroup = null;
  
  @Schema(description = "Scanners matches if all Nomos findings are within the Monk findings.")
 /**
   * Scanners matches if all Nomos findings are within the Monk findings.  
  **/
  private Boolean reuseMain = null;
  
  @Schema(description = "Bulk phrases from reused packages.")
 /**
   * Bulk phrases from reused packages.  
  **/
  private Boolean reuseEnhanced = null;
 /**
   * The UploadID to reuse.
   * @return reuseUpload
  **/
  @JsonProperty("reuse_upload")
  public Integer getReuseUpload() {
    return reuseUpload;
  }

  public void setReuseUpload(Integer reuseUpload) {
    this.reuseUpload = reuseUpload;
  }

  public Reuser reuseUpload(Integer reuseUpload) {
    this.reuseUpload = reuseUpload;
    return this;
  }

 /**
   * The group of the reused upload
   * @return reuseGroup
  **/
  @JsonProperty("reuse_group")
  public Integer getReuseGroup() {
    return reuseGroup;
  }

  public void setReuseGroup(Integer reuseGroup) {
    this.reuseGroup = reuseGroup;
  }

  public Reuser reuseGroup(Integer reuseGroup) {
    this.reuseGroup = reuseGroup;
    return this;
  }

 /**
   * Scanners matches if all Nomos findings are within the Monk findings.
   * @return reuseMain
  **/
  @JsonProperty("reuse_main")
  public Boolean isReuseMain() {
    return reuseMain;
  }

  public void setReuseMain(Boolean reuseMain) {
    this.reuseMain = reuseMain;
  }

  public Reuser reuseMain(Boolean reuseMain) {
    this.reuseMain = reuseMain;
    return this;
  }

 /**
   * Bulk phrases from reused packages.
   * @return reuseEnhanced
  **/
  @JsonProperty("reuse_enhanced")
  public Boolean isReuseEnhanced() {
    return reuseEnhanced;
  }

  public void setReuseEnhanced(Boolean reuseEnhanced) {
    this.reuseEnhanced = reuseEnhanced;
  }

  public Reuser reuseEnhanced(Boolean reuseEnhanced) {
    this.reuseEnhanced = reuseEnhanced;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Reuser {\n");
    
    sb.append("    reuseUpload: ").append(toIndentedString(reuseUpload)).append("\n");
    sb.append("    reuseGroup: ").append(toIndentedString(reuseGroup)).append("\n");
    sb.append("    reuseMain: ").append(toIndentedString(reuseMain)).append("\n");
    sb.append("    reuseEnhanced: ").append(toIndentedString(reuseEnhanced)).append("\n");
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
