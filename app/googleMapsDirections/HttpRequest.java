package googleMapsDirections;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.codehaus.jackson.JsonNode;

import play.libs.Json;

public class HttpRequest {

    public HttpRequest(String URI) {
    }

    public JsonNode getBody(String targetURL) {
	URL url;
	HttpURLConnection conn;
	BufferedReader rd;
	String line;
	String result = "";
	try {
	    url = new URL(targetURL);
	    conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    while ((line = rd.readLine()) != null) {
		result += line;
	    }
	    rd.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	Json json = new Json();
	
	return json.parse(result);
	
    }

    public String getResult() {
	return null;
    }
}
