package resus.licenseengine.fossology.model;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ScanOptions   {
  
  @Schema(description = "")
  private Analysis analysis = null;
  
  @Schema(description = "")
  private LicenseDecider decider = null;
  
  @Schema(description = "")
  private Reuser reuse = null;
 /**
   * Get analysis
   * @return analysis
  **/
  @JsonProperty("analysis")
  public Analysis getAnalysis() {
    return analysis;
  }

  public void setAnalysis(Analysis analysis) {
    this.analysis = analysis;
  }

  public ScanOptions analysis(Analysis analysis) {
    this.analysis = analysis;
    return this;
  }

 /**
   * Get decider
   * @return decider
  **/
  @JsonProperty("decider")
  public LicenseDecider getDecider() {
    return decider;
  }

  public void setDecider(LicenseDecider decider) {
    this.decider = decider;
  }

  public ScanOptions decider(LicenseDecider decider) {
    this.decider = decider;
    return this;
  }

 /**
   * Get reuse
   * @return reuse
  **/
  @JsonProperty("reuse")
  public Reuser getReuse() {
    return reuse;
  }

  public void setReuse(Reuser reuse) {
    this.reuse = reuse;
  }

  public ScanOptions reuse(Reuser reuse) {
    this.reuse = reuse;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ScanOptions {\n");
    
    sb.append("    analysis: ").append(toIndentedString(analysis)).append("\n");
    sb.append("    decider: ").append(toIndentedString(decider)).append("\n");
    sb.append("    reuse: ").append(toIndentedString(reuse)).append("\n");
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
