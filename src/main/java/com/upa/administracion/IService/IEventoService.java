package com.upa.administracion.IService;

import com.upa.administracion.Model.Evento;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface IEventoService {
    public List<Evento> getEvento();
    public List<Evento> getEventoUsuario(Long idUsuario);
    public List<Evento> getEventoTipoEvento(Long idTipoEvento);
    public List<Evento> getByUsuarioAndTipoEvento(Long idTipoEvento, Long idUsuario);
    public Evento saveEvento(Evento evento);
    public Evento saveEventoId(Long idUsuario, Long idTipoEvento, Evento evento);
    public void deleteEvento(Long id);
    public Evento findEvento(Long id);
}
