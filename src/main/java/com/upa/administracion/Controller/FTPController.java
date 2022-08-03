
package com.upa.administracion.Controller;

import com.upa.administracion.IService.ILogService;
import com.upa.administracion.Model.Log;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin("*")
@RestController
public class FTPController {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    
    @Autowired
    private ILogService logService;       
    
    FTPClient ftpClient;    
    
    @PostMapping("/ftp-upload/{idUsuario}") public void uploadFile(@RequestParam(name = "file", required = true) MultipartFile multipartFile,
                                                        @PathVariable Long idUsuario)
    throws Exception{

        //Map<String, Object> response = new HashMap<>();

        final String host = ("ftp.amh-web.com");
        final String user = ("u373460528.adminUpa10");
        final String password = ("App_4320*3");

        ftpClient = new FTPSClient();
        ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));

        if(multipartFile == null || multipartFile.isEmpty()) {
            throw new Exception("Elige un archivo válido");
        }

        try {
            ftpClient.connect(host, 21);
            int connectionReply = ftpClient.getReplyCode();
            if(!FTPReply.isPositiveCompletion(connectionReply)) {
                System.out.println("No se ha podido conectar. El servidor FTP respondió con el código " + connectionReply);
            }

            boolean loginOk = ftpClient.login(user, password);

            if(loginOk) {
                System.out.println("Login correcto");
            }else {
                System.out.println("No se ha podido hacer login en el servidor FTP");
            }
            
            ftpClient.enterLocalPassiveMode();

            File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+idUsuario.toString());
            System.out.println(multipartFile.getOriginalFilename());
            System.out.println(convFile.getName());            
            multipartFile.transferTo(convFile);
            
            System.out.println(convFile.getName());
            uploadFileToFTP(convFile, host , "/");
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();  
            
            LocalDateTime now = LocalDateTime.now(); 
            Log logTemp = new Log("El usuario " + idUsuario + " ha subido el archivo: " + convFile.getAbsolutePath(),  dtf.format(now));
            logService.saveLogId(idUsuario,new Long(6),logTemp);

        } catch(IOException e) {

            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();

        } finally {           
            
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }       
    }
    
    public void uploadFileToFTP(File file, String ftpHostDir , String serverFilename) throws Exception{
        try {
            InputStream input = new FileInputStream(file);
            //this.ftpClient.storeFile(ftpHostDir + serverFilename, input);
            this.ftpClient.storeFile(file.getName(), input);
        } catch (IOException e) {
            System.out.println("No se pudo subir el archivo al servidor.");            
            //throw new FTPErrors(errorMessage);
        }
    }
    
    //FTP DOWNLOAD
    @GetMapping("/ftp-download")
    public ResponseEntity<byte[]> downoadFile()
    throws Exception{
        //Map<String, Object> response = new HashMap<>();
        String finalPath="";
        final String host = ("ftp.amh-web.com");
        final String user = ("u373460528.adminUpa10");
        final String password = ("App_4320*3");
        ftpClient = new FTPSClient();
        ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        
        try {
            ftpClient.connect(host, 21);
            int connectionReply = ftpClient.getReplyCode();
            if(!FTPReply.isPositiveCompletion(connectionReply)) {
                System.out.println("No se ha podido conectar. El servidor FTP respondió con el código " + connectionReply);
            }
            boolean loginOk = ftpClient.login(user, password);
            if(loginOk) {
                System.out.println("Login correcto");
            }else {
                System.out.println("No se ha podido hacer login en el servidor FTP");
            }            
            File tempDirectory = new File(System.getProperty("java.io.tmpdir"), "files");            
            //String urlFile = "https://administracion-upa-10.herokuapp.com/files/" + tempDirectory;
            if(tempDirectory.exists()){
                System.out.println(System.getProperty("java.io.tmpdir"));
            }else{
                tempDirectory.mkdirs();
            }

            File file = new File(tempDirectory.getAbsolutePath()+"/abcde.jpg");
            if(!file.exists()){
                file.createNewFile();
            }
            finalPath= new File(tempDirectory.getAbsolutePath()+"/something.jpg").getAbsolutePath();

            System.out.println(finalPath);    
            
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            
            ftpClient.enterLocalPassiveMode();
            /*InputStream is = downloadFileFromFTP("/unidosxlaunaj.jpg", finalPath);
            byte[] targetArray = new byte[is.available()];
            return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "unidosxlaunaj.jpg" + "\"")
            .body(targetArray);*/
            //downloadFileFromFTP("/unidosxlaunaj.jpg", finalPath);            
        } catch(IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            /*try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }  
        OutputStream outputStream2 = new BufferedOutputStream(new FileOutputStream(finalPath));
        InputStream is = downloadFileFromFTP("/unidosxlaunaj.jpg", finalPath);        
        byte[] bytes = IOUtils.toByteArray(is);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "unidosxlaunaj.jpg" + "\"")
            .body(bytes);
    }
    
    
    public InputStream downloadFileFromFTP(String ftpRelativePath, String copytoPath) throws Exception {
        FileOutputStream fos;
        InputStream inputStream;
        try {
            fos = new FileOutputStream(copytoPath);
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo obtener la referencia a la carpeta relativa donde guardar, verifique la ruta y los permisos.");
            System.out.println(e.getMessage());
        }
        try {
            fos = new FileOutputStream(copytoPath);
            this.ftpClient.retrieveFile(ftpRelativePath, fos);
            
        } catch (IOException e) {
            System.out.println("No se pudo descargar el archivo.");
        }
        inputStream = this.ftpClient.retrieveFileStream(ftpRelativePath);
        return inputStream;
    }  
 
    
    /**
     * Method for release the FTP connection.
     * @throws FTPErrors Error if unplugged process failed.
     */
    
    public void disconnectFTP() throws Exception {
        if (this.ftpClient.isConnected()) {
            try {
                this.ftpClient.logout();
                this.ftpClient.disconnect();
            } catch (IOException f) {
                System.out.println("Ha ocurrido un error al realizar la desconexión del servidor FTP");
               //throw new FTPErrors( new ErrorMessage(-8, "Ha ocurrido un error al realizar la desconexión del servidor FTP"));
            }
        }
    }
}
