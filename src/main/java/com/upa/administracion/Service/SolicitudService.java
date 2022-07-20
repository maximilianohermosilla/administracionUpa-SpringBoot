package com.upa.administracion.Service;

import com.upa.administracion.IService.ISolicitudService;
import com.upa.administracion.Model.Solicitud;
import com.upa.administracion.Model.TipoEvento;
import com.upa.administracion.Model.Usuario;
import com.upa.administracion.Repository.SolicitudRepository;
import com.upa.administracion.Repository.TipoEventoRepository;
import com.upa.administracion.Repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolicitudService implements ISolicitudService{

    @Autowired
    private SolicitudRepository solicitudRepo;
    
    @Autowired
    private TipoEventoRepository tipoEventoRepo;
    
    @Autowired
    private UsuarioRepository usuarioRepo;
    
    @Override
    public List<Solicitud> getSolicitud() {
        List<Solicitud> solicitudList = solicitudRepo.findAll();
        return solicitudList;
    }

    @Override
    public List<Solicitud> getSolicitudUsuario(Long idUsuario) {
        List<Solicitud> solicitudList = solicitudRepo.findByUsuario(idUsuario);
        return solicitudList;
    }

    @Override
    public List<Solicitud> getSolicitudTipoEvento(Long idTipoEvento) {
        List<Solicitud> solicitudList = solicitudRepo.findByTipoEvento(idTipoEvento);
        return solicitudList;
    }

    @Override
    public List<Solicitud> getByUsuarioAndTipoEvento(Long idTipoEvento, Long idUsuario) {
        List<Solicitud> solicitudList = solicitudRepo.findByUsuarioAndTipoEvento(idTipoEvento, idUsuario);
        return solicitudList;
    }
    
    @Override
    public List<Solicitud> getGuardiasUsuarioAndTipoEvento(Long idUsuario) {
        List<Solicitud> solicitudList = solicitudRepo.findGuardiasUsuarioAndTipoEvento(idUsuario);
        return solicitudList;
    }
    
    @Override
    public List<Solicitud> getSolicitudesUsuarioAndTipoEvento(Long idUsuario) {
        List<Solicitud> solicitudList = solicitudRepo.findSolicitudesUsuarioAndTipoEvento(idUsuario);
        return solicitudList;
    }

    @Override
    public Solicitud saveSolicitud(Solicitud solicitud) {
        solicitudRepo.save(solicitud);
        return solicitud;
    }

    @Override
    public Solicitud saveSolicitudId(Long idUsuario, Long idTipoEvento, Solicitud solicitud) {
        Usuario usuario = usuarioRepo.findById(idUsuario).orElse(null);
        TipoEvento tipoEvento = tipoEventoRepo.findById(idTipoEvento).orElse(null);
        solicitud.setUsuario(usuario);       
        solicitud.setTipoEvento(tipoEvento); 
        
        solicitudRepo.save(solicitud);
        return solicitud;
    }

    @Override
    public void deleteSolicitud(Long id) {
        solicitudRepo.deleteById(id);
    }

    @Override
    public Solicitud findSolicitud(Long id) {
        Solicitud solicitudTemp = solicitudRepo.findById(id).orElse(null);
        return solicitudTemp;
    }
    
}
