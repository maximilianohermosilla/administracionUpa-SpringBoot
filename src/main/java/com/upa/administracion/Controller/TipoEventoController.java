package com.upa.administracion.Controller;

import com.upa.administracion.IService.ITipoEventoService;
import com.upa.administracion.Model.TipoEvento;
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
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class TipoEventoController {
    
    @Autowired
    private ITipoEventoService tipoEventoServ;
    
    @GetMapping ("tipoevento")
    public ResponseEntity<List<TipoEvento>> get(){
        List<TipoEvento> tipoEventoList = tipoEventoServ.getTipoEvento();
        return new ResponseEntity<List<TipoEvento>>(tipoEventoList, HttpStatus.OK);
    }
    
    @PostMapping ("tipoevento/save")
    public ResponseEntity<TipoEvento> save(@RequestBody TipoEvento tipoEvento) {
        TipoEvento tipoTemp = tipoEventoServ.saveTipoEvento(tipoEvento);
        return new ResponseEntity<TipoEvento>(tipoTemp, HttpStatus.OK);
    }
    
    @PutMapping ("tipoevento/{id}")
    public ResponseEntity<TipoEvento> edit(@PathVariable Long id,
                                     @RequestBody TipoEvento tipoEvento){
        TipoEvento tipoEventoTemp = tipoEventoServ.findById(id);
        tipoEventoTemp.setDescripcion(tipoEvento.getDescripcion());
        tipoEventoTemp.setOneDay(tipoEvento.getOneDay());
        
        tipoEventoServ.saveTipoEvento(tipoEventoTemp);
        return new ResponseEntity<TipoEvento>(tipoEventoTemp, HttpStatus.OK);        
    }
    
    @DeleteMapping ("tipoevento/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        tipoEventoServ.deleteTipoEvento(id);
        return new ResponseEntity<String>("TipoEvento is deleted successfully.!", HttpStatus.OK);
    }
}
