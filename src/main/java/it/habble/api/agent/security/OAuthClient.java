package it.habble.api.agent.security;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.databind.ObjectMapper;


public class OAuthClient implements Serializable, RowMapper<OAuthClient> {

	private static final long serialVersionUID = 7947088654676658009L;
	private String clientId;
	private String resourceIds;
	private String clientSecret;
	private String scope;
	private String authorizedGrantTypes;
	private String webServerRedirectUri;
	private String authorities;
	private Integer accessTokenValidity;
	private Integer refreshTokenValidity;
	private String additionalInformation;
	private String autoapprove;
	private String schema;

	public String getClientId() {
		return clientId;
	}

	public OAuthClient setClientId(String clientId) {
		this.clientId = clientId;
		return this;
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public OAuthClient setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
		return this;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public OAuthClient setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
		return this;
	}

	public String getScope() {
		return scope;
	}

	public OAuthClient setScope(String scope) {
		this.scope = scope;
		return this;
	}

	public String getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}

	public OAuthClient setAuthorizedGrantTypes(String authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
		return this;
	}

	public String getWebServerRedirectUri() {
		return webServerRedirectUri;
	}

	public OAuthClient setWebServerRedirectUri(String webServerRedirectUri) {
		this.webServerRedirectUri = webServerRedirectUri;
		return this;
	}

	public String getAuthorities() {
		return authorities;
	}

	public OAuthClient setAuthorities(String authorities) {
		this.authorities = authorities;
		return this;
	}

	public Integer getAccessTokenValidity() {
		return accessTokenValidity;
	}

	public OAuthClient setAccessTokenValidity(Integer accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
		return this;
	}

	public Integer getRefreshTokenValidity() {
		return refreshTokenValidity;
	}

	public OAuthClient setRefreshTokenValidity(Integer refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
		return this;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}
	
	/**
	 * Deserialize JSON additional info for this client
	 * @return
	 */
	public AdditionalInformation getInformations() {
		try {
			if(StringUtils.isBlank(this.additionalInformation))
				return null;
			
			return new ObjectMapper().readValue(this.additionalInformation, AdditionalInformation.class);
		} catch (Exception e) {
			return null;
		} 
	}

	public OAuthClient setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
		return this;
	}
	
	public OAuthClient setAdditionalInformation(AdditionalInformation additionalInformation) {
		if(additionalInformation != null) {
			try {
				this.additionalInformation = new ObjectMapper().writeValueAsString(additionalInformation);
				return this;
			} catch (Exception e) {
				return null;
			}
		}
		
		return this;
	}

	public String getAutoapprove() {
		return autoapprove;
	}

	public OAuthClient setAutoapprove(String autoapprove) {
		this.autoapprove = autoapprove;
		return this;
	}

	@Override
	public OAuthClient mapRow(ResultSet rs, int rowNum) throws SQLException {
		OAuthClient client = new OAuthClient()
				.setClientId(rs.getString("client_id"))
				.setResourceIds(rs.getString("resource_ids"))
				.setScope(rs.getString("scope"))
				.setAuthorizedGrantTypes(rs.getString("authorized_grant_types"))
				.setWebServerRedirectUri(rs.getString("web_server_redirect_uri"))
				.setAuthorities(rs.getString("authorities"))
				.setAccessTokenValidity(rs.getInt("access_token_validity"))
				.setRefreshTokenValidity(rs.getInt("refresh_token_validity"))
				.setAdditionalInformation(rs.getString("additional_information"))
				.setAutoapprove(rs.getString("autoapprove"));
		return client;
	}
	
	/**
	 * @return the schema
	 */
	public String getSchema() {
		return this.schema;
	}

	/**
	 * @param schema the schema to set
	 */
	public void setSchema(String schema) {
		this.schema = schema;
	}
}
