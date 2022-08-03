package com.upa.administracion.Controller;


import com.upa.administracion.IService.ILogService;
import com.upa.administracion.IService.IUsuarioService;
import com.upa.administracion.Model.Log;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class LogController {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    
    @Autowired
    private ILogService logService;
    
    @Autowired
    private IUsuarioService usuarioServ;
    
    @GetMapping ("logs")
    public ResponseEntity<List<Log>> get(){
        List<Log> logList = logService.getLog();
        return new ResponseEntity<List<Log>>(logList, HttpStatus.OK);
    }
    
    
    @GetMapping ("logs/usuario/{idUsuario}")
    public ResponseEntity<List<Log>> getByUsuario(@PathVariable Long idUsuario){
        List<Log> logList = logService.getLogUsuario(idUsuario);
        return new ResponseEntity<List<Log>>(logList, HttpStatus.OK);
    }  
    
    @GetMapping ("logs/tipoLog/{idTipoEvento}")
    public ResponseEntity<List<Log>> getByTipoLog(@PathVariable Long idTipoLog){
        List<Log> logList = logService.getLogTipoLog(idTipoLog);
        return new ResponseEntity<List<Log>>(logList, HttpStatus.OK);
    }   
    
    @GetMapping ("logs/filter/{idUsuario}/{idTipoEvento}")
    public ResponseEntity<List<Log>> getByUsuarioAndTipoLog(@PathVariable Long idUsuario,
                                                        @PathVariable Long idTipoLog){
        List<Log> logList = logService.getByUsuarioAndTipoLog(idTipoLog, idUsuario);
        return new ResponseEntity<List<Log>>(logList, HttpStatus.OK);
    } 
    
    
    @PostMapping ("logs/save/{idUsuario}/{idTipoLog}")
    public ResponseEntity<Log> saveLogUsuario(@PathVariable(value = "idUsuario") Long idUsuario,
                                                    @PathVariable(value = "idTipoLog") Long idTipoLog,
                                                    @RequestBody Log log) {
        Log logTemp = new Log(
                log.getDescripcion(),
                log.getDate_time());
        logTemp = logService.saveLogId(idUsuario, idTipoLog, logTemp);
        return new ResponseEntity<Log>(logTemp, HttpStatus.OK);
    }
}
