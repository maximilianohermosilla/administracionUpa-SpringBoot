package com.upa.administracion.Service;

import com.upa.administracion.IService.ITipoEventoService;
import com.upa.administracion.Model.TipoEvento;
import com.upa.administracion.Model.Usuario;
import com.upa.administracion.Repository.TipoEventoRepository;
import com.upa.administracion.Repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoEventoService implements ITipoEventoService{
    
    @Autowired
    private TipoEventoRepository tipoEventoRepo;
    
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Override
    public List<TipoEvento> getTipoEvento() {
        List<TipoEvento> listaTipoEvento = tipoEventoRepo.findAll();
        return listaTipoEvento;
    }

    @Override
    public List<TipoEvento> getTipoEventoUsuario(Long idUsuario) {
        List<TipoEvento> listaTipoEvento = tipoEventoRepo.findByUsuario(idUsuario);
        return listaTipoEvento;
    }

    @Override
    public TipoEvento saveTipoEvento(TipoEvento tipoEvento) {
        tipoEventoRepo.save(tipoEvento);
        return tipoEvento;
    }

    @Override
    public TipoEvento saveTipoEventoId(Long idUsuario, TipoEvento tipoEvento) {
        Usuario usuario = usuarioRepo.findById(idUsuario).orElse(null);;
        //tipoEvento.setUsuario(usuario);       
        
        tipoEventoRepo.save(tipoEvento);
        return tipoEvento;
    }

    @Override
    public void deleteTipoEvento(Long id) {
        tipoEventoRepo.deleteById(id);
    }

    @Override
    public TipoEvento findTipoEvento(Long id) {
        TipoEvento tipoEventoTemp = tipoEventoRepo.findById(id).orElse(null);
        return tipoEventoTemp;
    }
    
    
    
    
    
}
