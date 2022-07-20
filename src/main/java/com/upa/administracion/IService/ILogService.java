package com.upa.administracion.IService;

import com.upa.administracion.Model.Log;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface ILogService {
    public List<Log> getLog();
    public List<Log> getLogUsuario(Long idUsuario);
    public List<Log> getLogTipoLog(Long idTipoLog);
    public List<Log> getByUsuarioAndTipoLog(Long idTipoLog, Long idUsuario);
    public Log saveLog(Log log);
    public Log saveLogId(Long idUsuario, Long idTipoLog, Log log);
    public void deleteLog(Long id);
    public Log findLog(Long id);
}
