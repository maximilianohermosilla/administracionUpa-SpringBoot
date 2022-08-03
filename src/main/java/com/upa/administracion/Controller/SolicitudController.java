package com.upa.administracion.Controller;


import com.upa.administracion.IService.IEventoService;
import com.upa.administracion.IService.ILogService;
import com.upa.administracion.IService.ISolicitudService;
import com.upa.administracion.IService.IUsuarioService;
import com.upa.administracion.Model.Evento;
import com.upa.administracion.Model.Log;
import com.upa.administracion.Model.Solicitud;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    
    @Autowired
    private ISolicitudService solicitudService;
    
    @Autowired
    private IUsuarioService usuarioServ;
    
    @Autowired
    private IEventoService eventoService;
    
    @Autowired
    private ILogService logService; 
    
    @GetMapping ("solicitud")
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
    
    @PostMapping ("solicitud/save/{idUsuario}/{idEvento}")
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
        
        //INSERTO EVENTO
        LocalDateTime now = LocalDateTime.now(); 
        Log logTemp = new Log("Solicitud de " + solicitudTemp.getTipoEvento().getDescripcion() + " ingresada por el usuario "+ solicitudTemp.getUsuario().getUser() + " para el dìa " + solicitudTemp.getEvento().getStart(),  dtf.format(now));
        logService.saveLogId(idUsuario,new Long(3),logTemp);

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
    
    @PostMapping ("solicitud/save/{idUsuario}")
    public ResponseEntity<Solicitud> saveSolicitudUsuarioEvento(@PathVariable(value = "idUsuario") Long idUsuario,
                                                                @RequestBody Solicitud solicitud, Evento evento) {
        Evento eventoTemp = eventoService.saveEvento(evento);
        Solicitud solicitudTemp = solicitudService.saveSolicitud(solicitud);
        
        //INSERTO EVENTO
        LocalDateTime now = LocalDateTime.now(); 
        Log logTemp = new Log("Solicitud de " + solicitudTemp.getTipoEvento().getDescripcion() + " ingresada por el usuario "+ solicitudTemp.getUsuario().getUser() + " para el dìa " + solicitudTemp.getEvento().getStart(),  dtf.format(now));
        logService.saveLogId(idUsuario,new Long(3),logTemp);

        return new ResponseEntity<Solicitud>(solicitudTemp, HttpStatus.OK);
    }
    
    
    @PutMapping ("solicitud/update/{id}")
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
    
    @PutMapping ("solicitud/enable/{id}")
    public ResponseEntity<Solicitud> enable(@PathVariable Long id,
                                     @RequestBody Solicitud solicitud){
        Solicitud solicitudTemp = solicitudService.findSolicitud(id);    
        LocalDateTime now = LocalDateTime.now(); 
        solicitudTemp.setAprobado(solicitud.getAprobado());
        solicitudTemp.setDate_time_aprobado(dtf.format(now));
        
        //INSERTO EVENTO
        Log logTemp = new Log("Solicitud " + id +  " aprobada. Evento: " + solicitudTemp.getTipoEvento().getDescripcion() + " ingresado por el usuario "+ solicitudTemp.getUsuario().getUser() + " para el dìa " + solicitudTemp.getEvento().getStart(),  dtf.format(now));
        logService.saveLogId(solicitud.getUsuario().getId() ,new Long(3), logTemp);
        
        solicitudService.saveSolicitud(solicitudTemp);
        
        return new ResponseEntity<Solicitud>(solicitudTemp, HttpStatus.OK);        
    }
    
    
}