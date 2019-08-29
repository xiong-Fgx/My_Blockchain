package util;
import java.security.*;
import java.util.Base64;

public class GetKey {
    public static String getStringFromKey(Key key){
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }
}
