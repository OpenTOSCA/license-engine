package resus.licenseengine.fossology.model;

import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
  * To create an upload from a URL
 **/
@Schema(description="To create an upload from a URL")
public class UrlUpload  implements OneOfbody  {
  
  @Schema(required = true, description = "URL for file/folder to be uploaded")
 /**
   * URL for file/folder to be uploaded  
  **/
  private String url = null;
  
  @Schema(description = "Viewable name for this file or directory")
 /**
   * Viewable name for this file or directory  
  **/
  private String name = null;
  
  @Schema(description = "Comma-separated lists of file name suffixes or patterns to accpet ")
 /**
   * Comma-separated lists of file name suffixes or patterns to accpet   
  **/
  private String accept = null;
  
  @Schema(description = "Comma-separated lists of file name suffixes or patterns to reject ")
 /**
   * Comma-separated lists of file name suffixes or patterns to reject   
  **/
  private String reject = null;
  
  @Schema(description = "Maximum recursion depth for folders (0 for infinite)")
 /**
   * Maximum recursion depth for folders (0 for infinite)  
  **/
  private Integer maxRecursionDepth = null;
 /**
   * URL for file/folder to be uploaded
   * @return url
  **/
  @JsonProperty("url")
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public UrlUpload url(String url) {
    this.url = url;
    return this;
  }

 /**
   * Viewable name for this file or directory
   * @return name
  **/
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UrlUpload name(String name) {
    this.name = name;
    return this;
  }

 /**
   * Comma-separated lists of file name suffixes or patterns to accpet 
   * @return accept
  **/
  @JsonProperty("accept")
  public String getAccept() {
    return accept;
  }

  public void setAccept(String accept) {
    this.accept = accept;
  }

  public UrlUpload accept(String accept) {
    this.accept = accept;
    return this;
  }

 /**
   * Comma-separated lists of file name suffixes or patterns to reject 
   * @return reject
  **/
  @JsonProperty("reject")
  public String getReject() {
    return reject;
  }

  public void setReject(String reject) {
    this.reject = reject;
  }

  public UrlUpload reject(String reject) {
    this.reject = reject;
    return this;
  }

 /**
   * Maximum recursion depth for folders (0 for infinite)
   * @return maxRecursionDepth
  **/
  @JsonProperty("maxRecursionDepth")
  public Integer getMaxRecursionDepth() {
    return maxRecursionDepth;
  }

  public void setMaxRecursionDepth(Integer maxRecursionDepth) {
    this.maxRecursionDepth = maxRecursionDepth;
  }

  public UrlUpload maxRecursionDepth(Integer maxRecursionDepth) {
    this.maxRecursionDepth = maxRecursionDepth;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UrlUpload {\n");
    
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    accept: ").append(toIndentedString(accept)).append("\n");
    sb.append("    reject: ").append(toIndentedString(reject)).append("\n");
    sb.append("    maxRecursionDepth: ").append(toIndentedString(maxRecursionDepth)).append("\n");
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
