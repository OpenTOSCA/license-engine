package resus.licenseengine.fossology.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;

public class TokenRequest   {
  
  @Schema(required = true, description = "Username of the login user.")
 /**
   * Username of the login user.  
  **/
  private String username = null;
  
  @Schema(required = true, description = "Password of the user trying to login.")
 /**
   * Password of the user trying to login.  
  **/
  private String password = null;
  
  @Schema(required = true, description = "Friendly name of the token")
 /**
   * Friendly name of the token  
  **/
  private String tokenName = null;
  public enum TokenScopeEnum {
    READ("read"),
    WRITE("write");

    private String value;

    TokenScopeEnum(String value) {
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
    public static TokenScopeEnum fromValue(String text) {
      for (TokenScopeEnum b : TokenScopeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }  
  @Schema(required = true, description = "The scope of the token.")
 /**
   * The scope of the token.  
  **/
  private TokenScopeEnum tokenScope = null;
  
  @Schema(required = true, description = "Date when the token must expire (default max 30 days).")
 /**
   * Date when the token must expire (default max 30 days).  
  **/
  private String tokenExpire = null;
 /**
   * Username of the login user.
   * @return username
  **/
  @JsonProperty("username")
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public TokenRequest username(String username) {
    this.username = username;
    return this;
  }

 /**
   * Password of the user trying to login.
   * @return password
  **/
  @JsonProperty("password")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public TokenRequest password(String password) {
    this.password = password;
    return this;
  }

 /**
   * Friendly name of the token
   * @return tokenName
  **/
  @JsonProperty("token_name")
  public String getTokenName() {
    return tokenName;
  }

  public void setTokenName(String tokenName) {
    this.tokenName = tokenName;
  }

  public TokenRequest tokenName(String tokenName) {
    this.tokenName = tokenName;
    return this;
  }

 /**
   * The scope of the token.
   * @return tokenScope
  **/
  @JsonProperty("token_scope")
  public String getTokenScope() {
    if (tokenScope == null) {
      return null;
    }
    return tokenScope.getValue();
  }

  public void setTokenScope(TokenScopeEnum tokenScope) {
    this.tokenScope = tokenScope;
  }

  public TokenRequest tokenScope(TokenScopeEnum tokenScope) {
    this.tokenScope = tokenScope;
    return this;
  }

 /**
   * Date when the token must expire (default max 30 days).
   * @return tokenExpire
  **/
  @JsonProperty("token_expire")
  public String getTokenExpire() {
    return tokenExpire;
  }

  public void setTokenExpire(String tokenExpire) {
    this.tokenExpire = tokenExpire;
  }

  public TokenRequest tokenExpire(String tokenExpire) {
    this.tokenExpire = tokenExpire;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TokenRequest {\n");
    
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    tokenName: ").append(toIndentedString(tokenName)).append("\n");
    sb.append("    tokenScope: ").append(toIndentedString(tokenScope)).append("\n");
    sb.append("    tokenExpire: ").append(toIndentedString(tokenExpire)).append("\n");
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
