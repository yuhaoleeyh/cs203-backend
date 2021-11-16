package csd.cs203project.security.cognito;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(
        prefix = "com.kb.jwt.aws"
)
public class JwtConfiguration {
    private String userPoolId;
    private String identityPoolId;
    private String jwkUrl;
    private String region = "ap-southeast-1";
    private String userNameField = "cognito:username";
    private int connectionTimeout = 2000;
    private int readTimeout = 2000;
    private String httpHeader = "Authorization";

    public JwtConfiguration() {
    }

    
    /** 
     * @return String
     */
    public String getJwkUrl() {
        return this.jwkUrl != null && !this.jwkUrl.isEmpty() ? this.jwkUrl : String.format("https://cognito-idp.%s.amazonaws.com/%s/.well-known/jwks.json", this.region, this.userPoolId);
    }

    
    /** 
     * @return String
     */
    public String getCognitoIdentityPoolUrl() {
        return String.format("https://cognito-idp.%s.amazonaws.com/%s", this.region, this.userPoolId);
    }

    
    /** 
     * @return String
     */
    public String getUserPoolId() {
        return userPoolId;
    }

    
    /** 
     * @param userPoolId
     */
    public void setUserPoolId(String userPoolId) {
        this.userPoolId = userPoolId;
    }

    
    /** 
     * @return String
     */
    public String getIdentityPoolId() {
        return identityPoolId;
    }

    
    /** 
     * @param identityPoolId
     */
    public void setIdentityPoolId(String identityPoolId) {
        this.identityPoolId = identityPoolId;
    }

    
    /** 
     * @param jwkUrl
     */
    public void setJwkUrl(String jwkUrl) {
        this.jwkUrl = jwkUrl;
    }

    
    /** 
     * @return String
     */
    public String getRegion() {
        return region;
    }

    
    /** 
     * @param region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    
    /** 
     * @return String
     */
    public String getUserNameField() {
        return userNameField;
    }

    
    /** 
     * @param userNameField
     */
    public void setUserNameField(String userNameField) {
        this.userNameField = userNameField;
    }

    
    /** 
     * @return int
     */
    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    
    /** 
     * @param connectionTimeout
     */
    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    
    /** 
     * @return int
     */
    public int getReadTimeout() {
        return readTimeout;
    }

    
    /** 
     * @param readTimeout
     */
    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    
    /** 
     * @return String
     */
    public String getHttpHeader() {
        return httpHeader;
    }

    
    /** 
     * @param httpHeader
     */
    public void setHttpHeader(String httpHeader) {
        this.httpHeader = httpHeader;
    }
}
