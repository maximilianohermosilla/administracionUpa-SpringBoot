package com.upa.administracion.IService;

import com.upa.administracion.Model.Perfil;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface IPerfilService {
    public List<Perfil> getPerfil();
    public Perfil savePerfil(Perfil persona);
    public void deletePerfil(Long id);
    public Perfil findPerfil(Long id);
}
