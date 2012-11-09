package workers;

import java.io.IOException;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Sender;

public class GCMWorker {
    public static final String API_KEY = "AIzaSyCHJ1mqb3pyPYi2nO81YiO98ZQQ5vKc6Yc";

    public static void sendMessage(String deviceId, String messageString, String command, String matchId) {
	try {

	    Sender sender = new Sender(API_KEY);
	    Message message = new Message.Builder().collapseKey("1").timeToLive(3).delayWhileIdle(true).addData("message", messageString)
		    .addData("command", command).addData("matchId", matchId).build();
	    sender.send(message, deviceId, 5);

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
