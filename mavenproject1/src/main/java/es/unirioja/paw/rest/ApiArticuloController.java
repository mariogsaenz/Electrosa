package es.unirioja.paw.rest;

import com.github.javafaker.Faker;
import es.unirioja.paw.jpa.ArticuloEntity;
import es.unirioja.paw.jpa.Oferta3X2Entity;
import es.unirioja.paw.repository.ArticuloRepository;
import es.unirioja.paw.repository.OfertaRepository;
import es.unirioja.paw.rest.data.OfertaArticuloRestEntity;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/articulo")
public class ApiArticuloController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Faker faker = new Faker(Locale.of("es"));
    
    @Autowired
    private OfertaRepository ofertaRepository;
    
    @Autowired
    private ArticuloRepository articuloRepository;
    
    @GetMapping("/{articuloId:.+}/vendido/hoy")
    @ResponseBody
    public Integer vendidoHoy(Model model, @PathVariable("articuloId") String articuloId) {

        int hoy = getHoy();
        return hoy;
        
    }
    
    @GetMapping("/{articuloId:.+}/oferta")
    @ResponseBody
    public OfertaArticuloRestEntity infoOferta(Model model, @PathVariable("articuloId") String articuloId) {
        Optional<ArticuloEntity> articulo = articuloRepository.findById(articuloId);
	if (articulo.isEmpty()) {
		logger.warn("Articulo {}: no encontrado", articuloId);
		return null;
	}
        logger.warn("El id del articulo es: {}", articuloId);
	Optional<Oferta3X2Entity> entity = ofertaRepository.findById(articuloId); // Está fallando en este método
	if (entity.isEmpty()) {
		logger.warn("Articulo {}: no tiene oferta", articuloId); 
		return null;
	}

	return buildOferta(articulo.get(), entity.get());
        
    }
    
    public static int getHoy() {
        LocalDate hoy = LocalDate.now();
        return hoy.getDayOfMonth();
    }
    
    private OfertaArticuloRestEntity buildOferta(ArticuloEntity articulo, Oferta3X2Entity oferta3x2) {
	OfertaArticuloRestEntity r = new OfertaArticuloRestEntity();
	r.setCodigoArticulo(oferta3x2.getCodigoArticulo());
	LocalDateTime letsCelebrateDay = LocalDateTime.of(LocalDate.of(2024, 6, 7),LocalTime.of(14, 30));
	Duration duration = Duration.between(LocalDateTime.now(), letsCelebrateDay);
	r.setVigenciaDias(duration.toHours() / 24);
	r.setVigenciaHoras(duration.toHours() % 24);
	r.setVigenciaMinutos(duration.toMinutes() - duration.toHours() * 60);
	r.setVigenciaSegundos(duration.getSeconds() - duration.toMinutes() * 60);
	r.setPrecioOriginal(articulo.getPvp().floatValue());
	r.setCantidadComprada(oferta3x2.getaComprar());
	r.setCantidadPagada(oferta3x2.getaPagar());
	r.setPrecioOfertado(oferta3x2.getaPagar() * articulo.getPvp().floatValue() / oferta3x2.getaComprar());

	return r;
    }   
}
    

