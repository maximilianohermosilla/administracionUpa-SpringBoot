package com.upa.administracion.Controller;

import com.upa.administracion.DTO.EventoDTO;
import com.upa.administracion.IService.IEventoService;
import com.upa.administracion.IService.ILogService;
import com.upa.administracion.IService.ITipoEventoService;
import com.upa.administracion.IService.IUsuarioService;
import com.upa.administracion.Model.Evento;
import com.upa.administracion.Model.Log;
import com.upa.administracion.Model.TipoEvento;
import com.upa.administracion.Model.Usuario;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class EventoController {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    @Autowired
    private ILogService logService; 
    
    @Autowired
    private IEventoService eventoServ;
    @Autowired
    private IUsuarioService usuarioServ;
    @Autowired
    private ITipoEventoService tipoEventoServ;
    
    @GetMapping ("evento")
    public ResponseEntity<List<Evento>> get(){
        List<Evento> eventoList = eventoServ.getEvento();
        return new ResponseEntity<List<Evento>>(eventoList, HttpStatus.OK);
    }
    
    @GetMapping ("evento/usuario/{idUsuario}")
    public ResponseEntity<List<Evento>> getByUsuario(@PathVariable Long idUsuario){
        List<Evento> eventoList = eventoServ.getEventoUsuario(idUsuario);
        return new ResponseEntity<List<Evento>>(eventoList, HttpStatus.OK);
    }  
    
    @GetMapping ("evento/tipoEvento/{idTipoEvento}")
    public ResponseEntity<List<Evento>> getByTipoEvento(@PathVariable Long idTipoEvento){
        List<Evento> eventoList = eventoServ.getEventoTipoEvento(idTipoEvento);
        return new ResponseEntity<List<Evento>>(eventoList, HttpStatus.OK);
    }   
    
    @GetMapping ("evento/filter/{idUsuario}/{idTipoEvento}")
    public ResponseEntity<List<Evento>> getByTipoEvento(@PathVariable Long idUsuario,
                                                        @PathVariable Long idTipoEvento){
        List<Evento> eventoList = eventoServ.getByUsuarioAndTipoEvento(idTipoEvento, idUsuario);
        return new ResponseEntity<List<Evento>>(eventoList, HttpStatus.OK);
    } 
    
    @PostMapping ("evento/save/{idUsuario}/{idTipoEvento}")
    public ResponseEntity<Evento> saveEventoUsuario(@PathVariable(value = "idUsuario") Long idUsuario,
                                                    @PathVariable(value = "idTipoEvento") Long idTipoEvento,
                                                    @RequestBody Evento evento) {
        Evento eventoTemp = new Evento(
                evento.getTitle(),
                evento.getStart(),
                evento.getEnd(),
                evento.getDescription(),
                evento.getColor(),
                evento.getBackgroundColor(),
                evento.getBorderColor(),
                evento.getEditable(),
                evento.getEnabled(),
                evento.getTipoEvento(),
                evento.getUsuario(),      
                false);    
        eventoTemp = eventoServ.saveEventoId(idUsuario, idTipoEvento, eventoTemp);
        
        //INSERTO EVENTO
        LocalDateTime now = LocalDateTime.now(); 
        Log logTemp = new Log("Nuevo evento: " + evento.getTipoEvento().getDescripcion() + ". Dia: " + evento.getStart() + "-" + evento.getEnd() +  ". Usuario: " + evento.getUsuario().getUser(),  dtf.format(now));
        logService.saveLogId(evento.getUsuario().getId(),new Long(5),logTemp);
        
        return new ResponseEntity<Evento>(eventoTemp, HttpStatus.OK);
    }
    
    @PutMapping ("evento/update/{id}")
    public ResponseEntity<Evento> edit(@PathVariable Long id,
                                     @RequestBody Evento evento){
        Evento eventoTemp = eventoServ.findEvento(id);
        eventoTemp.setTitle(evento.getTitle());
        eventoTemp.setStart(evento.getStart());
        eventoTemp.setEnd(evento.getEnd());
        eventoTemp.setColor(evento.getColor());
        eventoTemp.setBackgroundColor(evento.getBackgroundColor());
        eventoTemp.setBorderColor(evento.getBorderColor());
        eventoTemp.setEditable(evento.getEditable());
        eventoTemp.setEnabled(evento.getEnabled());
        eventoTemp.setTipoEvento(evento.getTipoEvento());
        eventoTemp.setUsuario(evento.getUsuario());        
        eventoTemp.setNewEvent(false);
        
        eventoServ.saveEventoId(evento.getUsuario().getId(), evento.getTipoEvento().getId(), eventoTemp);
        
        //INSERTO EVENTO
        LocalDateTime now = LocalDateTime.now(); 
        Log logTemp = new Log("Evento " + id +  " actualizado. Datos anteriores: " + eventoTemp.getTipoEvento().getDescripcion() + ". Dia: " + eventoTemp.getStart() + "-" + eventoTemp.getEnd() +  ". Usuario: " + eventoTemp.getUsuario().getUser(),  dtf.format(now));
        logService.saveLogId(evento.getUsuario().getId(),new Long(5),logTemp);
        
        return new ResponseEntity<Evento>(eventoTemp, HttpStatus.OK);        
    }
    
    @PutMapping ("evento/enable/{id}")
    public ResponseEntity<Evento> enable(@PathVariable Long id){
        Evento eventoTemp = eventoServ.findEvento(id);       
        eventoTemp.setEnabled(true);     
        eventoTemp.setNewEvent(false);
        
        eventoServ.saveEventoId(eventoTemp.getUsuario().getId(), eventoTemp.getTipoEvento().getId(), eventoTemp);
        
        //INSERTO EVENTO
        LocalDateTime now = LocalDateTime.now(); 
        Log logTemp = new Log("Evento " + id +  " aprobado. Tipo: " + eventoTemp.getTipoEvento().getDescripcion() + ". Dia: " + eventoTemp.getStart() + "-" + eventoTemp.getEnd() +  ". Usuario: " + eventoTemp.getUsuario().getUser(),  dtf.format(now));
        logService.saveLogId(eventoTemp.getUsuario().getId(),new Long(5),logTemp);
        
        return new ResponseEntity<Evento>(eventoTemp, HttpStatus.OK);        
    }
    
    @DeleteMapping ("evento/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        
        Evento eventoTemp = eventoServ.findEvento(id);
        
        //INSERTO EVENTO
        LocalDateTime now = LocalDateTime.now(); 
        Log logTemp = new Log("Evento " + id +  " eliminado: " + eventoTemp.getTipoEvento().getDescripcion() + ". Dia: " + eventoTemp.getStart() + "-" + eventoTemp.getEnd() +  ". Usuario: " + eventoTemp.getUsuario().getUser(),  dtf.format(now));
        logService.saveLogId(eventoTemp.getUsuario().getId(),new Long(5),logTemp);
        eventoServ.deleteEvento(id);
        return new ResponseEntity<String>("Evento is deleted successfully.!", HttpStatus.OK);
    }
}
