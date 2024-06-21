/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.unirioja.paw.service;

import es.unirioja.paw.jpa.ArticuloEntity;
import es.unirioja.paw.jpa.LineaCestaCompraEntity;
import es.unirioja.paw.repository.ArticuloRepository;
import es.unirioja.paw.repository.CestaCompraRepository;
import es.unirioja.paw.repository.ClienteRepository;
import es.unirioja.paw.repository.UsuarioRepository;
import es.unirioja.paw.service.data.AddToCartRequest;
import es.unirioja.paw.service.data.AddToCartResponse;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CestaCompraUseCaseImpl implements CestaCompraUseCase{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    private CestaCompraRepository cestaRepository;
    
    @Override
    public Optional<AddToCartResponse> add(AddToCartRequest r) {
        
        Optional<LineaCestaCompraEntity> lineaArticulo = r.cesta.findLineaArticulo(r.codigoArticulo);
        
        LineaCestaCompraEntity linea = new LineaCestaCompraEntity();
        if (!lineaArticulo.isEmpty()) {
            linea = lineaArticulo.get();
            linea.setCantidad(linea.getCantidad() + 1);
        }
        else{
            Optional<ArticuloEntity> articuloOpt = articuloRepository.findById(r.codigoArticulo);
            if(articuloOpt.isPresent()){
                logger.info("Articulo {}: encontrado", r.codigoArticulo);
                linea.setArticulo(articuloOpt.get());
                linea.setCantidad(1);
                linea.setPrecio(articuloOpt.get().getPvp());
                linea.setCesta(r.cesta);
                r.cesta.getLineas().add(linea);
            }
            else{
                logger.info("Articulo {}: no encontrado", r.codigoArticulo);
            }   
        }
        r.cesta = cestaRepository.save(r.cesta);
        return Optional.of(new AddToCartResponse(r.cesta,linea));
    }  
    
}
