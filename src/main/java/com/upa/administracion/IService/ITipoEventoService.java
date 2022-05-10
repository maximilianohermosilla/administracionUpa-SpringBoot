package com.upa.administracion.IService;

import com.upa.administracion.Model.TipoEvento;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface ITipoEventoService {
    public List<TipoEvento> getTipoEvento();
    public List<TipoEvento> getTipoEventoUsuario(Long idUsuario);
    public TipoEvento saveTipoEvento(TipoEvento tipoEvento);
    public TipoEvento saveTipoEventoId(Long idUsuario, TipoEvento tipoEvento);
    public void deleteTipoEvento(Long id);
    public TipoEvento findById(Long idTipoEvento);
}
