package resus.licenseengine.fossology.model;


import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Analysis   {
  
  @Schema(description = "Should bucket analysis be run on this upload")
 /**
   * Should bucket analysis be run on this upload  
  **/
  private Boolean bucket = null;
  
  @Schema(description = "Should Copyright/Email/URL/Author Analysis be run on this upload.")
 /**
   * Should Copyright/Email/URL/Author Analysis be run on this upload.  
  **/
  private Boolean copyrightEmailAuthor = null;
  
  @Schema(description = "Should ECC Analysis be run on this upload.")
 /**
   * Should ECC Analysis be run on this upload.  
  **/
  private Boolean ecc = null;
  
  @Schema(description = "Should keyword Analysis be run on this upload.")
 /**
   * Should keyword Analysis be run on this upload.  
  **/
  private Boolean keyword = null;
  
  @Schema(description = "Should MIME Analysis be run on this upload.")
 /**
   * Should MIME Analysis be run on this upload.  
  **/
  private Boolean mime = null;
  
  @Schema(description = "Should Monk Analysis be run on this upload.")
 /**
   * Should Monk Analysis be run on this upload.  
  **/
  private Boolean monk = null;
  
  @Schema(description = "Should Nomos Analysis be run on this upload.")
 /**
   * Should Nomos Analysis be run on this upload.  
  **/
  private Boolean nomos = null;
  
  @Schema(description = "Should OJO Analysis be run on this upload.")
 /**
   * Should OJO Analysis be run on this upload.  
  **/
  private Boolean ojo = null;
  
  @Schema(description = "Should Package Analysis be run on this upload.")
 /**
   * Should Package Analysis be run on this upload.  
  **/
  private Boolean _package = null;
 /**
   * Should bucket analysis be run on this upload
   * @return bucket
  **/
  @JsonProperty("bucket")
  public Boolean isBucket() {
    return bucket;
  }

  public void setBucket(Boolean bucket) {
    this.bucket = bucket;
  }

  public Analysis bucket(Boolean bucket) {
    this.bucket = bucket;
    return this;
  }

 /**
   * Should Copyright/Email/URL/Author Analysis be run on this upload.
   * @return copyrightEmailAuthor
  **/
  @JsonProperty("copyright_email_author")
  public Boolean isCopyrightEmailAuthor() {
    return copyrightEmailAuthor;
  }

  public void setCopyrightEmailAuthor(Boolean copyrightEmailAuthor) {
    this.copyrightEmailAuthor = copyrightEmailAuthor;
  }

  public Analysis copyrightEmailAuthor(Boolean copyrightEmailAuthor) {
    this.copyrightEmailAuthor = copyrightEmailAuthor;
    return this;
  }

 /**
   * Should ECC Analysis be run on this upload.
   * @return ecc
  **/
  @JsonProperty("ecc")
  public Boolean isEcc() {
    return ecc;
  }

  public void setEcc(Boolean ecc) {
    this.ecc = ecc;
  }

  public Analysis ecc(Boolean ecc) {
    this.ecc = ecc;
    return this;
  }

 /**
   * Should keyword Analysis be run on this upload.
   * @return keyword
  **/
  @JsonProperty("keyword")
  public Boolean isKeyword() {
    return keyword;
  }

  public void setKeyword(Boolean keyword) {
    this.keyword = keyword;
  }

  public Analysis keyword(Boolean keyword) {
    this.keyword = keyword;
    return this;
  }

 /**
   * Should MIME Analysis be run on this upload.
   * @return mime
  **/
  @JsonProperty("mime")
  public Boolean isMime() {
    return mime;
  }

  public void setMime(Boolean mime) {
    this.mime = mime;
  }

  public Analysis mime(Boolean mime) {
    this.mime = mime;
    return this;
  }

 /**
   * Should Monk Analysis be run on this upload.
   * @return monk
  **/
  @JsonProperty("monk")
  public Boolean isMonk() {
    return monk;
  }

  public void setMonk(Boolean monk) {
    this.monk = monk;
  }

  public Analysis monk(Boolean monk) {
    this.monk = monk;
    return this;
  }

 /**
   * Should Nomos Analysis be run on this upload.
   * @return nomos
  **/
  @JsonProperty("nomos")
  public Boolean isNomos() {
    return nomos;
  }

  public void setNomos(Boolean nomos) {
    this.nomos = nomos;
  }

  public Analysis nomos(Boolean nomos) {
    this.nomos = nomos;
    return this;
  }

 /**
   * Should OJO Analysis be run on this upload.
   * @return ojo
  **/
  @JsonProperty("ojo")
  public Boolean isOjo() {
    return ojo;
  }

  public void setOjo(Boolean ojo) {
    this.ojo = ojo;
  }

  public Analysis ojo(Boolean ojo) {
    this.ojo = ojo;
    return this;
  }

 /**
   * Should Package Analysis be run on this upload.
   * @return _package
  **/
  @JsonProperty("package")
  public Boolean isPackage() {
    return _package;
  }

  public void setPackage(Boolean _package) {
    this._package = _package;
  }

  public Analysis _package(Boolean _package) {
    this._package = _package;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Analysis {\n");
    
    sb.append("    bucket: ").append(toIndentedString(bucket)).append("\n");
    sb.append("    copyrightEmailAuthor: ").append(toIndentedString(copyrightEmailAuthor)).append("\n");
    sb.append("    ecc: ").append(toIndentedString(ecc)).append("\n");
    sb.append("    keyword: ").append(toIndentedString(keyword)).append("\n");
    sb.append("    mime: ").append(toIndentedString(mime)).append("\n");
    sb.append("    monk: ").append(toIndentedString(monk)).append("\n");
    sb.append("    nomos: ").append(toIndentedString(nomos)).append("\n");
    sb.append("    ojo: ").append(toIndentedString(ojo)).append("\n");
    sb.append("    _package: ").append(toIndentedString(_package)).append("\n");
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
