package com.upa.administracion.Controller;

import com.upa.administracion.DTO.EventoDTO;
import com.upa.administracion.IService.IEventoService;
import com.upa.administracion.IService.ITipoEventoService;
import com.upa.administracion.IService.IUsuarioService;
import com.upa.administracion.Model.Evento;
import com.upa.administracion.Model.TipoEvento;
import com.upa.administracion.Model.Usuario;
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
    
    @GetMapping ("eventoDTO")
    public ResponseEntity<List<EventoDTO>> getDTO() {
        List<Evento> eventos = eventoServ.getEvento();
        List<EventoDTO> eventosDTO = new ArrayList<EventoDTO>();
        TipoEvento tipoEvento;
        Usuario usuario;
        for (int i=0;i<eventos.size();i++) {
            if(eventos.get(i).getUsuario()!=null){
            usuario = usuarioServ.findUsuario(eventos.get(i).getUsuario().getId());
        }
        else{
            usuario = new Usuario();
        }
        if(eventos.get(i).getTipoEvento()!=null){
            tipoEvento = tipoEventoServ.findById(eventos.get(i).getUsuario().getId());
        }  
        else{
            tipoEvento = new TipoEvento();
        }
        EventoDTO eventoDTO = new EventoDTO(eventos.get(i).getId(),
                                            eventos.get(i).getTitle(), 
                                            eventos.get(i).getStart(), 
                                            eventos.get(i).getEnd(), 
                                            eventos.get(i).getDescription(), 
                                            eventos.get(i).getColor(), 
                                            eventos.get(i).getBackgroundColor(), 
                                            eventos.get(i).getBorderColor(),
                                            eventos.get(i).getEditable(),
                                            eventos.get(i).getEnabled(),
                                            eventos.get(i).getNewEvent(),
                                            tipoEvento,
                                            usuario
                                            );
        eventosDTO.add(eventoDTO);
        }
        
        
        return new ResponseEntity<List<EventoDTO>>(eventosDTO, HttpStatus.OK);
    }
    
    /*@GetMapping ("evento/{idUsuario}")
    public ResponseEntity<List<Evento>> get(@PathVariable Long idUsuario){
        List<Evento> eventoList = eventoServ.getEventoUsuario(idUsuario);
        return new ResponseEntity<List<Evento>>(eventoList, HttpStatus.OK);
    }*/
    
    /*@GetMapping ("evento/{idUsuario}/{idTipoEvento}")
    public ResponseEntity<List<Evento>> get(@PathVariable Long idTipoEvento, @PathVariable Long idUsuario){
        List<Evento> eventoList = eventoServ.getByUsuarioAndTipoEvento(idTipoEvento, idUsuario);
        return new ResponseEntity<List<Evento>>(eventoList, HttpStatus.OK);
    }*/
    
    /*@PostMapping ("evento/save")
    public ResponseEntity<Evento> save(@RequestBody Evento evento, Long idTipoEvento, Long idUsuario) {
        Evento eventoTemp = eventoServ.saveEventoId(idUsuario, idTipoEvento, evento);
		return new ResponseEntity<Evento>(eventoTemp, HttpStatus.OK);*/
        /*TipoEvento tipo = tipoEventoServ.findById(idTipoEvento);
        Usuario usuarioTemp = usuarioServ.findUsuario(idUsuario);
        Evento eventoTemp = new Evento();
        eventoTemp.setTitle(evento.getTitle());
        eventoTemp.setDescription(evento.getDescription());
        eventoTemp.setStart(evento.getStart());
        eventoTemp.setEnd(evento.getEnd());
        eventoTemp.setColor(evento.getColor());
        eventoTemp.setBackgroundColor(evento.getBackgroundColor());
        eventoTemp.setBorderColor(evento.getBorderColor());
        eventoTemp.setEditable(evento.getEditable());
        eventoTemp.setEnabled(evento.getEnabled());
        eventoTemp.setTipoEvento(tipo);
        eventoTemp.setUsuario(usuarioTemp);        
        eventoTemp.setNewEvent(false);
        
        eventoServ.saveEvento(eventoTemp);
        return new ResponseEntity<Evento>(eventoTemp, HttpStatus.OK); */
    //}
    
    @PostMapping ("evento/saveEvento/{idUsuario}/{idTipoEvento}")
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
        return new ResponseEntity<Evento>(eventoTemp, HttpStatus.OK);
    }
    
    @PutMapping ("evento/{id}")
    public ResponseEntity<Evento> edit(@PathVariable Long id,
                                     @RequestBody EventoDTO evento){
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
        return new ResponseEntity<Evento>(eventoTemp, HttpStatus.OK);        
    }
    
    @DeleteMapping ("evento/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        eventoServ.deleteEvento(id);
        return new ResponseEntity<String>("Evento is deleted successfully.!", HttpStatus.OK);
    }
}
