package com.upa.administracion.Service;

import com.upa.administracion.IService.IEventoService;
import com.upa.administracion.Model.Evento;
import com.upa.administracion.Model.TipoEvento;
import com.upa.administracion.Model.Usuario;
import com.upa.administracion.Repository.EventoRepository;
import com.upa.administracion.Repository.TipoEventoRepository;
import com.upa.administracion.Repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoService implements IEventoService{
    
    @Autowired
    private EventoRepository eventoRepo;
    
    @Autowired
    private TipoEventoRepository tipoEventoRepo;
    
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Override
    public List<Evento> getEvento() {
        List<Evento> listaEvento = eventoRepo.findAll();
        return listaEvento;
    }

    @Override
    public List<Evento> getEventoUsuario(Long idUsuario) {
        List<Evento> listaEvento = eventoRepo.findByUsuario(idUsuario);
        return listaEvento;
    }
            
    @Override
    public List<Evento> getByUsuarioAndTipoEvento(Long idTipoEvento, Long idUsuario) {
        List<Evento> listaEvento = eventoRepo.findByUsuarioAndTipoEvento(idTipoEvento, idUsuario);
        return listaEvento;
    }

    @Override
    public Evento saveEvento(Evento evento) {
        eventoRepo.save(evento);
        return evento;
    }

    @Override
    public Evento saveEventoId(Long idUsuario, Long idTipoEvento, Evento evento) {
        Usuario usuario = usuarioRepo.findById(idUsuario).orElse(null);
        TipoEvento tipoEvento = tipoEventoRepo.findById(idTipoEvento).orElse(null);
        evento.setUsuario(usuario);       
        evento.setTipoEvento(tipoEvento); 
        
        eventoRepo.save(evento);
        return evento;
    }

    @Override
    public void deleteEvento(Long id) {
        eventoRepo.deleteById(id);
    }

    @Override
    public Evento findEvento(Long id) {
        Evento eventoTemp = eventoRepo.findById(id).orElse(null);
        return eventoTemp;
    }
    
    
    
}
