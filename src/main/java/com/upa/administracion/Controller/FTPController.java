
package com.upa.administracion.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin("*")
@RestController
public class FTPController {
    
    FTPClient ftpClient;
    
    @PostMapping("/ftp-upload") public void uploadFile(@RequestParam(name = "file", required = true) MultipartFile multipartFile)
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
            //ftpClient.connect(host);

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

            /*FTPFile[] files = ftpClient.listFiles();

            for(FTPFile file : files) {
                System.out.println(file.getName());
            }*/
            File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+multipartFile.getOriginalFilename());
            System.out.println(multipartFile.getOriginalFilename());
            System.out.println(convFile.getName());
            //File convFile = new File("src/main/resources/targetFile.tmp");
            
            multipartFile.transferTo(convFile);
            
            System.out.println(convFile.getName());
            uploadFileToFTP(convFile, host , "/");
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();          

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
    /**
     * Method for download files from FTP.
     * @param ftpRelativePath Relative path of file to download into FTP server.
     * @param copytoPath Path to copy the file in download process.
     * @throws FTPErrors Set of errors associated with download process.
     */
    
    @GetMapping("/ftp-download")
    public ResponseEntity<String> downoadFile()
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
            //ftpClient.connect(host);

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
            
            File tempDirectory = new File(new File(System.getProperty("java.io.tmpdir")), "files");            
            
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
            
            downloadFileFromFTP("/unidosxlaunaj.jpg", finalPath);
            
            ftpClient.enterLocalPassiveMode();
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
        return new ResponseEntity<String>(finalPath, HttpStatus.OK);
    }
    public void downloadFileFromFTP(String ftpRelativePath, String copytoPath) throws Exception {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(copytoPath);
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo obtener la referencia a la carpeta relativa donde guardar, verifique la ruta y los permisos.");
            System.out.println(e.getMessage());
            //logger.error(errorMessage.toString());
            //throw new FTPErrors(errorMessage);
        }
        try {
            fos = new FileOutputStream(copytoPath);
            this.ftpClient.retrieveFile(ftpRelativePath, fos);
        } catch (IOException e) {
            System.out.println("No se pudo descargar el archivo.");
            //logger.error(errorMessage.toString());
            //throw new FTPErrors(errorMessage);
        }
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
