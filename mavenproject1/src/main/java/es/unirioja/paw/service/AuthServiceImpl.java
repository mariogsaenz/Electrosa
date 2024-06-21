package es.unirioja.paw.service;

import es.unirioja.paw.jpa.ClienteEntity;
import es.unirioja.paw.jpa.UsuarioEntity;
import es.unirioja.paw.repository.ClienteRepository;
import es.unirioja.paw.repository.UsuarioRepository;
import java.security.MessageDigest;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

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

    @Override
    public ClienteEntity authenticate(String username, String password) {
        UsuarioEntity usuarioExample = new UsuarioEntity();
        usuarioExample.setUsername(username);
        String encodedPassword = encodeValue(password, "SHA");
        usuarioExample.setPassword(encodedPassword);
        ClienteEntity clienteExample = new ClienteEntity();
        clienteExample.setUsername(username);

        Optional<UsuarioEntity> usuario = usuarioRepository.findOne(Example.of(usuarioExample));
        Optional<ClienteEntity> cliente = clienteRepository.findOne(Example.of(clienteExample));
        if (usuario.isPresent() && cliente.isPresent()) {
            logger.info("Auth success");
            return cliente.get();
        }

        logger.warn("Auth failed: username={}", username);
        return null;
    }

}
