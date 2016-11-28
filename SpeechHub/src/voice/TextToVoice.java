package voice;

/**
 * Created by albus on 11/28/2016.
 */
public class TextToVoice {
    private String ACCESS_TOKEN;

    private AccessToken token = new AccessToken();

    public TextToVoice(){
        ACCESS_TOKEN = token.getAccessToken();
    }

    public void getConvertionResult(String text){}

    private void convertText(String text){}
}
