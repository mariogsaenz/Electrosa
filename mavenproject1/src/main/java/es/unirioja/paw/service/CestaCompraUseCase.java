package es.unirioja.paw.service;

import es.unirioja.paw.service.data.AddToCartRequest;
import es.unirioja.paw.service.data.AddToCartResponse;
import es.unirioja.paw.service.data.SaveCartRequest;
import es.unirioja.paw.service.data.SaveCartResponse;
import java.util.Optional;

public interface CestaCompraUseCase {

    public Optional<AddToCartResponse> add(AddToCartRequest r);

}
