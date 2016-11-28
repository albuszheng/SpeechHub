
package voice;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;
import voice.VoiceRecognition;

/**
 * Created by albus on 11/28/2016.
 */
public class TextToVoice {
    private String ACCESS_TOKEN;

    private AccessToken token = new AccessToken();

    public TextToVoice(){
        ACCESS_TOKEN = token.getAccessToken();
    }

    public void getConvertionResult(String text){

    }

    private void convertText(String text){}

    private InputStream stream (String text) throws Exception {
        String accessToken = token.AccessToken();
        String urlString = "https://speech.platform.bing.com/synthesize";
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        String POSTBody = "<speak version='1.0' xml:lang='en-US'><voice xml:lang='en-US' xml:gender='Female' name='Microsoft Server Speech Text to Speech Voice (en-US, ZiraRUS)'>Microsoft Bing Voice Output API</voice></speak>";

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-type", "application/ssml+xml");
        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
        conn.setRequestProperty("X-Search-AppId", "07D3234E49CE426DAA29772419F436CA");
        conn.setRequestProperty("X-Search-ClientID", UUID.randomUUID().toString());
        conn.setRequestProperty("User-Agent", "TTSAndroid");
        conn.setRequestProperty("Accept", "*/*");

        byte[] bytes = POSTBody.getBytes();
        conn.setRequestProperty("content-length", String.valueOf(bytes.length));
        conn.connect();

        DataOutputStream dop = new DataOutputStream(conn.getOutputStream());
        dop.write(bytes);
        dop.flush();
        dop.close();

        InputStream inStream = conn.getInputStream();
        inStream.close();
        conn.disconnect();
        return inStream;
}
