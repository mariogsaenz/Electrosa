package es.unirioja.paw.service;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import es.unirioja.paw.exception.RepositoryException;
import es.unirioja.paw.jpa.ClienteEntity;
import es.unirioja.paw.jpa.UsuarioEntity;
import es.unirioja.paw.repository.ClienteRepository;
import es.unirioja.paw.repository.UsuarioRepository;
import es.unirioja.paw.service.data.GenderGuess;
import es.unirioja.paw.service.data.MailConfig;
import es.unirioja.paw.service.data.MailRequest;
import es.unirioja.paw.service.data.MailResponse;
import es.unirioja.paw.service.data.RegistroClienteRequest;
import es.unirioja.paw.service.data.RegistroClienteResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Optional;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

@Service
public class RegistroClienteUseCaseImpl implements RegistroClienteUseCase {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private UsuarioRepository usuarioRepository;

    private ClienteRepository clienteRepository;

    private SimpleEncoder simpleEncoder;

    private Mailer mailer;

    private final Faker faker = new Faker(Locale.of("es"));

    @Autowired
    public RegistroClienteUseCaseImpl(UsuarioRepository usuarioRepository, ClienteRepository clienteRepository, SimpleEncoder simpleEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.clienteRepository = clienteRepository;
        this.simpleEncoder = simpleEncoder;
        this.mailer = new JavaxMailer(buildMailConfig());;
    }

    private MailConfig buildMailConfig() {
        final String filename = "mail.properties";
        InputStream propertiesAsInputStream = this.getClass().getResourceAsStream(String.format("/%s", filename));

        Properties prop = new Properties();
        try {
            prop.load(propertiesAsInputStream);
        } catch (IOException ex) {
            logger.error("Error loading properties", ex);
        }
        MailConfig config = new MailConfig(prop);
        return config;
    }

    @Override
    @Transactional
    public RegistroClienteResponse registrar(RegistroClienteRequest request) {
        // 1. Guardar en BD
        UsuarioEntity u = saveUsuario(request);
        ClienteEntity c = saveCliente(request);
        // 2. Mail de bievenida
        sendMailBienvenida(c);
        // 3. Datos adicionales
        c = guessGender(c);
        c = updateAvatar(c);
        c = initLoyaltyPoints(c);

        return new RegistroClienteResponse(c, u);
    }

    private UsuarioEntity saveUsuario(RegistroClienteRequest request) {
        logger.info("Registrando usuario: {}", request.payload.getUsername());
        UsuarioEntity u = new UsuarioEntity();
        u.setUsername(request.payload.getUsername());
        u.setPassword(simpleEncoder.sha(request.payload.getPassword1()));
        logger.info("Guardando usuario {}", u.getUsername());
        Optional<UsuarioEntity> usuarioBD = usuarioRepository.findById(u.getUsername());
        if (usuarioBD.isPresent()) {
            throw new RepositoryException(String.format("Usuario %s: ya existe", u.getUsername()));
        }
        u = usuarioRepository.save(u);
        logger.info("Usuario guardado: {}", u.getUsername());

        return u;
    }

    private ClienteEntity saveCliente(RegistroClienteRequest request) {
        logger.info("Guardando cliente: {}", request.payload.getUsername());
        ClienteEntity c = new ClienteEntity();
        // Datos reales
        c.setEmail(request.payload.getEmail());
        c.setUsername(request.payload.getUsername());

        // Datos mockeados
        Address address = faker.address();
        c.setCalle(address.streetAddress());
        c.setCiudad(address.cityName());
        c.setProvincia(address.state());
        c.setCif(String.format("A%s", faker.number().digits(8)));
        c.setCp(address.zipCode());
        c.setTfno(faker.phoneNumber().phoneNumber());
        c.setNombre(faker.name().firstName());
        c = clienteRepository.save(c);
        return c;
    }

    private String guessGender(String nombre) {
        RestClient restClient = RestClient.create();

        GenderGuess body = restClient.get()
                .uri(String.format("https://api.genderize.io?name=%s", nombre))
                .retrieve()
                .body(GenderGuess.class);
        String result = body.getGender();
        logger.info("Nombre {}: genero={} (probabilidad={})", nombre, result, body.getProbability());
        return result;
    }

    private ClienteEntity guessGender(ClienteEntity c) {
        String gender = guessGender(c.getNombre());
        c.setSexo(gender);
        return clienteRepository.save(c);
    }

    private void sendMailBienvenida(ClienteEntity c) {
        logger.info("Enviando mail para {}", c.getEmail());
        MailRequest request = new MailRequest(
                c.getEmail(),
                "Bienvenida a Electrosa",
                String.format("Le damos la bienvenida a Electrosa, %s", c.getNombre())
                + "Es un placer para nosotros tenerle como cliente."
        );
        MailResponse response = mailer.send(request);
        logger.info("Mail para {} enviado {}", request.recipient, response.isSuccess());
    }

    private ClienteEntity updateAvatar(ClienteEntity c) {
        logger.info("Gravatar para email {}", c.getEmail());
        String hash = MD5Util.md5Hex(c.getEmail());
        c.setGravatarHashId(hash);
        return clienteRepository.save(c);
    }

    private ClienteEntity initLoyaltyPoints(ClienteEntity c) {
        c.setPuntosFidelidad(faker.number().numberBetween(100, 500));
        logger.info("Programa de fidelidad para cliente {}: {} puntos", c.getCodigo(), c.getPuntosFidelidad());
        return clienteRepository.save(c);
    }

}
