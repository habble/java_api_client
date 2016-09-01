package it.habble.api.agent.security;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Mapper for Info JSON object. Example:
 * <pre>
  "info":{
       "serial":"222123456789123",
       "version":null,
       "protocol":5,
       "deviceManufacturer":"LGE",
       "deviceModel":"Nexus 5X",
       "deviceOs": "A",
       "idPush":"cakJTrjyDtY:APA91bG_-",
       "versionApp":"3.0.12",
       "versionAppCode":3000012,
       "versionOS":"6.0.1",
       "versionSDK":23
   }
</pre>
 * @author ccastelli
 *
 */
@JsonIgnoreProperties({"valid", "deviceOSName", "isAdmin"})
public class Info {
	public String serial;
	public Integer protocol;
	public String deviceManufacturer, deviceModel, deviceOs;
	public String idPush;
	public String versionApp;
	public Integer versionAppCode;
	public String versionOS, localeOs;
	public Integer versionSDK;
	
	public Boolean isValid() {
		return StringUtils.isNotBlank(this.serial); 
	}
	
	public Info idPush(String idPush) {
		this.idPush = idPush;
		return this;
	}
	
	public Info protocol(Integer protocol) {
		this.protocol = protocol;
		return this;
	}
	
	public Info versionSDK(Integer versionSDK) {
		this.versionSDK = versionSDK;
		return this;
	}
	
	public Info versionAppCode(Integer versionAppCode) {
		this.versionAppCode = versionAppCode;
		return this;
	}
	
	public Info versionOS(String versionOS) {
		this.versionOS = versionOS;
		return this;
	}
	
	public Info versionApp(String versionApp) {
		this.versionApp = versionApp;
		return this;
	}
	
	public Info deviceOs(String deviceOs) {
		this.deviceOs = deviceOs;
		return this;
	}
	
	public Info serial(String serial) {
		this.serial = serial;
		return this;
	}
	
	public Info deviceManufacturer(String deviceManufacturer) {
		this.deviceManufacturer = deviceManufacturer;
		return this;
	}
	
	public Info localeOs(String localeOs) {
		this.localeOs = localeOs;
		return this;
	}
	
	public Info deviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
		return this;
	}
	
	public String getDeviceOSName() {
		if(StringUtils.isNotBlank(this.deviceOs)) {
			switch(this.deviceOs) {
				case "A": return "Android";
				case "B": return "Blackberry";
				case "I": return "iOS";
				case "W": return "Windows";
				case "WP": return "Windows Phone";
				default: return "Unknown: " + this.deviceOs;
			}
		} else {
			return "N/D";
		}
	}
}
