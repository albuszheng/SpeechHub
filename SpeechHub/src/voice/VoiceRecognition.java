package voice;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.UUID;

/**
 * Created by albus on 11/28/2016.
 */
public class VoiceRecognition {
    private String ACCESS_TOKEN;
    private String uuid;

    private AccessToken token = new AccessToken();

    public VoiceRecognition(){
        ACCESS_TOKEN = token.getAccessToken();

        uuid = UUID.randomUUID().toString();
    }

    private String recognitionRequest(Object audioStream) throws Exception {
        String url = "https://speech.platform.bing.com/recognize";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        StringBuilder result = new StringBuilder();

        con.setRequestMethod("POST");
//        con.setRequestProperty("scenarios", "smd");
//        con.setRequestProperty("appid", "D4D52672-91D7-4C74-8AD8-42B1D98141A5");
//        con.setRequestProperty("format", "json");
//        con.setRequestProperty("locale","en-US");
//        con.setRequestProperty("version","3.0");
//        con.setRequestProperty("device.os","Windows OS");
//        con.setRequestProperty("instanceid",uuid);
//        con.setRequestProperty("requestid",uuid);
        con.setRequestProperty("Authorization","Bearer "+ACCESS_TOKEN);
        con.setRequestProperty("Content-type","audio/wav; codec=\"audio/pcm\"; samplerate=16000");

        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes("scenarios=smd&appid=D4D52672-91D7-4C74-8AD8-42B1D98141A5&locale=en-US&device.os=Windows OS&version=3.0&format=json&instanceid="+uuid+"requestid="+uuid);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println(responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    public String getRecognitionResult(Object audioStream) throws Exception{
        return recognitionRequest(audioStream);
    }

}
