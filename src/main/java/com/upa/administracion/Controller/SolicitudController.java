package com.upa.administracion.Controller;


import com.upa.administracion.IService.ISolicitudService;
import com.upa.administracion.IService.IUsuarioService;
import com.upa.administracion.Model.Evento;
import com.upa.administracion.Model.Solicitud;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class SolicitudController {
    
    @Autowired
    private ISolicitudService solicitudService;
    
    @Autowired
    private IUsuarioService usuarioServ;
    
    @GetMapping ("solicitudes")
    public ResponseEntity<List<Solicitud>> get(){
        List<Solicitud> solicitudList = solicitudService.getSolicitud();
        return new ResponseEntity<List<Solicitud>>(solicitudList, HttpStatus.OK);
    }
    
    
    @GetMapping ("solicitud/usuario/{idUsuario}")
    public ResponseEntity<List<Solicitud>> getByUsuario(@PathVariable Long idUsuario){
        List<Solicitud> solicitudList = solicitudService.getSolicitudUsuario(idUsuario);
        return new ResponseEntity<List<Solicitud>>(solicitudList, HttpStatus.OK);
    }  
    
    @GetMapping ("solicitud/tipoEvento/{idTipoEvento}")
    public ResponseEntity<List<Solicitud>> getByTipoEvento(@PathVariable Long idTipoEvento){
        List<Solicitud> solicitudList = solicitudService.getSolicitudTipoEvento(idTipoEvento);
        return new ResponseEntity<List<Solicitud>>(solicitudList, HttpStatus.OK);
    }   
    
    @GetMapping ("solicitud/guardias/{idUsuario}")
    public ResponseEntity<List<Solicitud>> getGuardiasUsuarioAndTipoEvento(@PathVariable Long idUsuario){
        List<Solicitud> solicitudList = solicitudService.getGuardiasUsuarioAndTipoEvento(idUsuario);
        return new ResponseEntity<List<Solicitud>>(solicitudList, HttpStatus.OK);
    } 
    
    @GetMapping ("solicitud/solicitudes/{idUsuario}")
    public ResponseEntity<List<Solicitud>> getSolicitudesUsuarioAndTipoEvento(@PathVariable Long idUsuario){
        List<Solicitud> solicitudList = solicitudService.getSolicitudesUsuarioAndTipoEvento(idUsuario);
        return new ResponseEntity<List<Solicitud>>(solicitudList, HttpStatus.OK);
    }     
    
    @PostMapping ("saveSolicitud/{idUsuario}/{idEvento}")
    public ResponseEntity<Solicitud> saveSolicitudUsuario(@PathVariable(value = "idUsuario") Long idUsuario,
                                                    @PathVariable(value = "idTipoEvento") Long idTipoEvento,
                                                    @PathVariable(value = "idEvento") Long idEvento,
                                                    @RequestBody Solicitud solicitud) {
        Solicitud solicitudTemp = new Solicitud(
                solicitud.getDescripcion(),
                solicitud.getDate_time(),
                solicitud.getAprobado(),
                solicitud.getDate_time_aprobado(),
                solicitud.getEvento(),
                solicitud.getUsuario(),
                solicitud.getTipoEvento());
        solicitudTemp = solicitudService.saveSolicitudId(idUsuario, idTipoEvento, solicitudTemp);
        return new ResponseEntity<Solicitud>(solicitudTemp, HttpStatus.OK);
    }
    
    @PostMapping ("saveSolicitud/{idEvento}")
    public ResponseEntity<Solicitud> saveSolicitudEvento(@PathVariable(value = "idEvento") Long idEvento,
                                                    @RequestBody Solicitud solicitud) {
        Solicitud solicitudTemp = new Solicitud(
                solicitud.getDescripcion(),
                solicitud.getDate_time(),
                solicitud.getAprobado(),
                solicitud.getDate_time_aprobado(),
                solicitud.getEvento(),
                solicitud.getUsuario(),
                solicitud.getTipoEvento());
        solicitudTemp = solicitudService.saveSolicitud(solicitudTemp);
        return new ResponseEntity<Solicitud>(solicitudTemp, HttpStatus.OK);
    }
    
    @PutMapping ("solicitudUpdate/{id}")
    public ResponseEntity<Solicitud> edit(@PathVariable Long id,
                                     @RequestBody Solicitud solicitud){
        Solicitud solicitudTemp = solicitudService.findSolicitud(id);
        solicitudTemp.setDescripcion(solicitud.getDescripcion());
        solicitudTemp.setDate_time(solicitud.getDate_time());
        solicitudTemp.setAprobado(solicitud.getAprobado());
        solicitudTemp.setDate_time_aprobado(solicitud.getDate_time_aprobado());
        solicitudTemp.setEvento(solicitud.getEvento());
        solicitudTemp.setUsuario(solicitud.getUsuario());
        solicitudTemp.setTipoEvento(solicitud.getTipoEvento());        
        
        solicitudService.saveSolicitud(solicitudTemp);
        return new ResponseEntity<Solicitud>(solicitudTemp, HttpStatus.OK);        
    }
    
    
}