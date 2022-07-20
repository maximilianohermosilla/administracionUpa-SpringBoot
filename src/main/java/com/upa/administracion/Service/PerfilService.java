package com.upa.administracion.Service;

import com.upa.administracion.IService.IPerfilService;
import com.upa.administracion.Model.Perfil;
import com.upa.administracion.Repository.PerfilRepository;
import com.upa.administracion.enums.PerfilNombre;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PerfilService implements IPerfilService{

    @Autowired
    private PerfilRepository perfilRepo;
    
    @Override
    public List<Perfil> getPerfil() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Perfil savePerfil(Perfil persona) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deletePerfil(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Perfil findPerfil(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Optional<Perfil> getByPerfilNombre(PerfilNombre perfilNombre){
        return perfilRepo.findByPerfilNombre(perfilNombre);
    }
    
}
