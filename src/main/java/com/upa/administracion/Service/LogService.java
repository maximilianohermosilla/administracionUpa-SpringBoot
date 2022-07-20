package com.upa.administracion.Service;

import com.upa.administracion.IService.ILogService;
import com.upa.administracion.Model.Log;
import com.upa.administracion.Model.TipoLog;
import com.upa.administracion.Model.Usuario;
import com.upa.administracion.Repository.LogRepository;
import com.upa.administracion.Repository.TipoLogRepository;
import com.upa.administracion.Repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService implements ILogService{

    @Autowired
    private LogRepository logRepo;
    
    @Autowired
    private TipoLogRepository tipoLogRepo;
    
    @Autowired
    private UsuarioRepository usuarioRepo;
    
    @Override
    public List<Log> getLog() {
        List<Log> listaLog = logRepo.findAll();
        return listaLog;
    }

    @Override
    public List<Log> getLogUsuario(Long idUsuario) {
        List<Log> listaLog = logRepo.findByUsuario(idUsuario);
        return listaLog;
    }

    @Override
    public List<Log> getLogTipoLog(Long idTipoLog) {
        List<Log> listaLog = logRepo.findByTipoLog(idTipoLog);
        return listaLog;
    }

    @Override
    public List<Log> getByUsuarioAndTipoLog(Long idTipoLog, Long idUsuario) {
        List<Log> listaLog = logRepo.findByUsuarioAndTipoLog(idTipoLog, idUsuario);
        return listaLog;
    }

    @Override
    public Log saveLog(Log log) {
        logRepo.save(log);
        return log;
    }

    @Override
    public Log saveLogId(Long idUsuario, Long idTipoLog, Log log) {
        Usuario usuario = usuarioRepo.findById(idUsuario).orElse(null);
        TipoLog tipoLog = tipoLogRepo.findById(idTipoLog).orElse(null);
        log.setUsuario(usuario);       
        log.setTipoLog(tipoLog); 
        
        logRepo.save(log);
        return log;
    }

    @Override
    public void deleteLog(Long id) {
        logRepo.deleteById(id);
    }

    @Override
    public Log findLog(Long id) {
        Log logTemp = logRepo.findById(id).orElse(null);
        return logTemp;
    }
    
}
