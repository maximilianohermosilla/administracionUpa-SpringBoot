package com.upa.administracion.IService;

import com.upa.administracion.Model.Solicitud;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public interface ISolicitudService {
    public List<Solicitud> getSolicitud();
    public List<Solicitud> getSolicitudUsuario(Long idUsuario);
    public List<Solicitud> getSolicitudTipoEvento(Long idTipoEvento);
    public List<Solicitud> getByUsuarioAndTipoEvento(Long idTipoEvento, Long idUsuario);
    public List<Solicitud> getGuardiasUsuarioAndTipoEvento(Long idUsuario);
    public List<Solicitud> getSolicitudesUsuarioAndTipoEvento(Long idUsuario);
    public Solicitud saveSolicitud(Solicitud solicitud);
    public Solicitud saveSolicitudId(Long idUsuario, Long idTipoEvento, Solicitud solicitud);
    public void deleteSolicitud(Long id);
    public Solicitud findSolicitud(Long id);
    
}
