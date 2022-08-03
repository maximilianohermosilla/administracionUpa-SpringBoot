package com.upa.administracion.Controller;

import com.upa.administracion.IService.ILogService;
import com.upa.administracion.IService.IUsuarioService;
import com.upa.administracion.Model.Email;
import com.upa.administracion.Model.Log;
import com.upa.administracion.Model.Usuario;
import com.upa.administracion.Service.EmailSenderService;
import com.upa.administracion.Service.SendMailService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class SendMailController {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
     
    @Autowired
    private SendMailService sendMailService;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private ILogService logService;
    @Autowired
    private IUsuarioService usuarioServ;
    
    
    @GetMapping("/")
    public String index(){
        return "send_mail_view";
    }
    
    @PostMapping("/sendMail")
    public String sendMail(@RequestParam("name") String name, @RequestParam("mail") String mail,
                            @RequestParam("subject") String subject, @RequestParam("body") String body){
        
        String message = body + "\n\nDatos de contacto: " + "\nNombre: " + name + "\nE-Mail: " + mail;
        sendMailService.sendMail("administracion.upa10@amh-web.com", "maximiliano_hermosilla@hotmail.com", subject, message);
        
        return "send_mail_view";
    }
    
    @PostMapping("welcome-email")    
    public void sendHtmlMessageTest() throws MessagingException {
        Email email = new Email();
        email.setTo("maximiliano_hermosilla@hotmail.com");
        email.setFrom("administracion.upa10@amh-web.com");
        email.setSubject("Bienvenido a Administracion Upa 10");
        email.setTemplate("welcome-email.html");
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", "Maximiliano Hermosilla");
        properties.put("subscriptionDate", LocalDate.now().toString());
        properties.put("technologies", Arrays.asList("ahermosilla", "CLAVE_DEFAULT"));
        email.setProperties(properties);
        emailSenderService.sendHtmlMessage(email);
        //Assertions.assertDoesNotThrow(() -> emailSenderService.sendHtmlMessage(email));
    }
    
    @PostMapping("welcome/{idUsuario}")    
    public void sendHtmlMessage(@PathVariable("idUsuario") Long idUsuario) throws MessagingException {
        Usuario usuarioTemp = usuarioServ.findUsuario(idUsuario);
        Email email = new Email();
        email.setTo(usuarioTemp.getEmail());
        email.setFrom("support@amh-web.com");
        email.setSubject("Bienvenido a Administracion Upa 10");
        email.setTemplate("welcome.html");
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", usuarioTemp.getName()+" "+usuarioTemp.getLastName());
        properties.put("subscriptionDate", LocalDate.now().toString());
        properties.put("technologies", Arrays.asList(usuarioTemp.getUser(), "CLAVE_DEFAULT"));
        email.setProperties(properties);
        emailSenderService.sendHtmlMessage(email);
        LocalDateTime now = LocalDateTime.now(); 
        Log logTemp = new Log("Se ha restablecido la contraseña del usuario: "+ usuarioTemp.getUser(),  dtf.format(now));
        logService.saveLogId(idUsuario,new Long(6),logTemp);
        //Assertions.assertDoesNotThrow(() -> emailSenderService.sendHtmlMessage(email));
    }
}
