package com.upa.administracion.Controller;

import com.upa.administracion.IService.IEventoService;
import com.upa.administracion.Model.Evento;
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
    
    @GetMapping ("evento")
    public ResponseEntity<List<Evento>> get(){
        List<Evento> eventoList = eventoServ.getEvento();
        return new ResponseEntity<List<Evento>>(eventoList, HttpStatus.OK);
    }
    
    @PostMapping ("evento/save")
    public ResponseEntity<Evento> save(@RequestBody Evento evento) {
        Evento eventoTemp = eventoServ.saveEvento(evento);
        return new ResponseEntity<Evento>(eventoTemp, HttpStatus.OK);
    }
    
    @PostMapping ("evento/saveEvento/")
    public ResponseEntity<Evento> saveEventoUsuario(@RequestParam Long idUsuario,
                                                    @RequestParam Long idTipoEvento,
                                                    @RequestBody Evento evento) {
		Evento eventoTemp = eventoServ.saveEventoId(idUsuario, idTipoEvento, evento);
		return new ResponseEntity<Evento>(eventoTemp, HttpStatus.OK);
    }
    
    @PutMapping ("evento/{id}")
    public ResponseEntity<Evento> edit(@PathVariable Long id,
                                     @RequestBody Evento tipoEvento){
        Evento eventoTemp = eventoServ.findEvento(id);
        eventoTemp.setTitle(tipoEvento.getTitle());
        eventoTemp.setStart(tipoEvento.getStart());
        eventoTemp.setEnd(tipoEvento.getEnd());
        eventoTemp.setColor(tipoEvento.getColor());
        eventoTemp.setBackgroundColor(tipoEvento.getBackgroundColor());
        eventoTemp.setBorderColor(tipoEvento.getBorderColor());
        eventoTemp.setEditable(tipoEvento.getEditable());
        eventoTemp.setTipoEvento(tipoEvento.getTipoEvento());
        eventoTemp.setUsuario(tipoEvento.getUsuario());        
        
        eventoServ.saveEvento(eventoTemp);
        return new ResponseEntity<Evento>(eventoTemp, HttpStatus.OK);        
    }
    
    @DeleteMapping ("evento/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        eventoServ.deleteEvento(id);
        return new ResponseEntity<String>("Evento is deleted successfully.!", HttpStatus.OK);
    }
}
