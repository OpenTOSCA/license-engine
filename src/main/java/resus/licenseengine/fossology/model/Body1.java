package resus.licenseengine.fossology.model;

import java.io.File;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Body1   {
  
  @Schema(description = "")
  private File fileInput = null;
 /**
   * Get fileInput
   * @return fileInput
  **/
  @JsonProperty("fileInput")
  public File getFileInput() {
    return fileInput;
  }

  public void setFileInput(File fileInput) {
    this.fileInput = fileInput;
  }

  public Body1 fileInput(File fileInput) {
    this.fileInput = fileInput;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Body1 {\n");
    
    sb.append("    fileInput: ").append(toIndentedString(fileInput)).append("\n");
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
