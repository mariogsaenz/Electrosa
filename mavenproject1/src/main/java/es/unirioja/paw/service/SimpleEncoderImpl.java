package es.unirioja.paw.service;

import java.security.MessageDigest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SimpleEncoderImpl implements SimpleEncoder {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String sha(String value) {
        return encodeValue(value, "SHA");
    }

    private String encodeValue(String value, String algorithm) {
        byte[] arrayOfByte1 = value.getBytes();
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance(algorithm);
        } catch (Exception exception) {
            logger.error("Error getting MessageDigest instance", exception);
            return value;
        }
        messageDigest.reset();
        messageDigest.update(arrayOfByte1);
        byte[] arrayOfByte2 = messageDigest.digest();
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b = 0; b < arrayOfByte2.length; b++) {
            if ((arrayOfByte2[b] & 0xFF) < 16) {
                stringBuffer.append("0");
            }
            stringBuffer.append(Long.toString((arrayOfByte2[b] & 0xFF), 16));
        }
        return stringBuffer.toString();
    }

}
