package it.habble.api.agent;

import it.habble.api.agent.security.Info;

/**
 * Object wrapper for activation message
 * <pre>
 {"info":{
   "serial":"222123456789123",
   "connection_id":"2015-12-15 10:08:32",
   "version":null,
   "protocol":5,
   "deviceManufacterer":"LGE",
   "deviceModel":"Nexus 5X",
   "idPush":"cakJTrjyDtY:APA91bG_-",
   "versionApp":"3.0.12",
   "versionAppCode":3000012,
   "versionOS":"6.0.1",
   "versionSDK":23
   },
   "data":[
     {"activation_code":"support@habble.it",
     "phone_number":"3481259866",
     "serial":"222123456789123"}
    ]}
  * @author ccastelli
 *
 */
public class Activation {
	public Info info;
	public ActivationData data;
}
