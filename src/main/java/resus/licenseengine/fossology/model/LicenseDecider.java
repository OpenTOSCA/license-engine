package resus.licenseengine.fossology.model;


import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LicenseDecider   {
  
  @Schema(description = "Scanners matches if all Nomos findings are within the Monk findings.")
 /**
   * Scanners matches if all Nomos findings are within the Monk findings.  
  **/
  private Boolean nomosMonk = null;
  
  @Schema(description = "Bulk phrases from reused packages.")
 /**
   * Bulk phrases from reused packages.  
  **/
  private Boolean bulkReused = null;
  
  @Schema(description = "New scanner results, i.e., decisions were marked as work in progress if new scanner finds additional licenses.")
 /**
   * New scanner results, i.e., decisions were marked as work in progress if new scanner finds additional licenses.  
  **/
  private Boolean newScanner = null;
  
  @Schema(description = "Scanners matches if Ojo findings are no contradiction with other findings.")
 /**
   * Scanners matches if Ojo findings are no contradiction with other findings.  
  **/
  private Boolean ojoDecider = null;
 /**
   * Scanners matches if all Nomos findings are within the Monk findings.
   * @return nomosMonk
  **/
  @JsonProperty("nomos_monk")
  public Boolean isNomosMonk() {
    return nomosMonk;
  }

  public void setNomosMonk(Boolean nomosMonk) {
    this.nomosMonk = nomosMonk;
  }

  public LicenseDecider nomosMonk(Boolean nomosMonk) {
    this.nomosMonk = nomosMonk;
    return this;
  }

 /**
   * Bulk phrases from reused packages.
   * @return bulkReused
  **/
  @JsonProperty("bulk_reused")
  public Boolean isBulkReused() {
    return bulkReused;
  }

  public void setBulkReused(Boolean bulkReused) {
    this.bulkReused = bulkReused;
  }

  public LicenseDecider bulkReused(Boolean bulkReused) {
    this.bulkReused = bulkReused;
    return this;
  }

 /**
   * New scanner results, i.e., decisions were marked as work in progress if new scanner finds additional licenses.
   * @return newScanner
  **/
  @JsonProperty("new_scanner")
  public Boolean isNewScanner() {
    return newScanner;
  }

  public void setNewScanner(Boolean newScanner) {
    this.newScanner = newScanner;
  }

  public LicenseDecider newScanner(Boolean newScanner) {
    this.newScanner = newScanner;
    return this;
  }

 /**
   * Scanners matches if Ojo findings are no contradiction with other findings.
   * @return ojoDecider
  **/
  @JsonProperty("ojo_decider")
  public Boolean isOjoDecider() {
    return ojoDecider;
  }

  public void setOjoDecider(Boolean ojoDecider) {
    this.ojoDecider = ojoDecider;
  }

  public LicenseDecider ojoDecider(Boolean ojoDecider) {
    this.ojoDecider = ojoDecider;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LicenseDecider {\n");
    
    sb.append("    nomosMonk: ").append(toIndentedString(nomosMonk)).append("\n");
    sb.append("    bulkReused: ").append(toIndentedString(bulkReused)).append("\n");
    sb.append("    newScanner: ").append(toIndentedString(newScanner)).append("\n");
    sb.append("    ojoDecider: ").append(toIndentedString(ojoDecider)).append("\n");
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
