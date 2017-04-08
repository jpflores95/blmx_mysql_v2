package net.tutorial.utilities;


import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class EnvVariables {
	
	boolean hasVcap = false;
	JSONObject vcap = null;
	
	public EnvVariables() {
		String VCAP_SERVICES = System.getenv("VCAP_SERVICES");
				
		if (VCAP_SERVICES != null) {
			this.hasVcap = true;
			JSONParser parser = new JSONParser();
			Object obj;
			
			try {
				obj = parser.parse(VCAP_SERVICES);
				vcap =  (JSONObject) obj;
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public Map<String, String> getCredentials(String serviceName) {
		
		Map<String, String> creds = new HashMap<String, String>();
		
		if (this.hasVcap) {
			JSONArray serviceConfig = (JSONArray) vcap.get(serviceName);
	        JSONObject serviceInstance = (JSONObject) serviceConfig.get(0);
	        JSONObject serviceCreds = (JSONObject) serviceInstance.get("credentials");
			
			//creds.put("jdbcUrl", serviceCreds.get("jdbcUrl").toString());
				if (serviceName.equals("text_to_speech")){
				creds.put("username", serviceCreds.get("username").toString());
				creds.put("password",  serviceCreds.get("password").toString());
			}
				else if(serviceName.equals("cleardb")){
				creds.put("jdbcUrl", serviceCreds.get("jdbcUrl").toString());
			}
			
			
		} else {
			
		if(serviceName.equals("cleardb")){
			creds.put("jdbcUrl", "jdbc:mysql://us-cdbr-iron-east-04.cleardb.net/ad_a26915a035fc832?user=b035db40527917&password=61e95ee1"); // Put username here if you are testing in local
		}
		else if(serviceName.equals("text_to_speech")){
			creds.put("username","");
			creds.put("password","");
		}
		


		}
		
		return creds;
	}

}
