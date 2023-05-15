import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FileTransferImpl extends UnicastRemoteObject implements FileTransfer, Serializable{

    final String PATH = "D:\\JavaVSCode\\practica_envio\\";

    protected FileTransferImpl() throws RemoteException {
        super();
    }

    private void rebuildFile(String fileName, float chunkSize){
        byte [] buffer = new byte[(int) chunkSize*10+1];
        int offset = 0;
        for(int i = 0; i < 10; i++){
            String [] fileNameSeparated = fileName.split("[0-9][.]");
            String newFileName = fileNameSeparated[0] + (i+1) + "." + fileNameSeparated[1];
            try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(newFileName))){
                int bytesRead = 0;

                bytesRead = bis.read(buffer, offset, (int)chunkSize);
                System.out.println("Se termino de leer el fragmento: " + Integer.toString(i+1));
                offset += bytesRead;

            }
            catch(Exception e){

            }
        }

        String [] fileNameSepareted = fileName.split("[0-9][.]");
        String newFileName = fileNameSepareted[0] + "_r." + fileNameSepareted[1];
        try(FileOutputStream fos = new FileOutputStream(newFileName, true)){
            fos.write(buffer);
            System.out.println("Archivo reconstruido");
            fos.close();
        }
        catch(Exception e){
            System.err.println("Error writting file: " + e.getMessage());
        }
    }

    private boolean verifyFileAndProgress(String fileName){
        int cantidadArchivos = 0;
        boolean completed = false;
        for(int i = 0; i < 10; i++){
            String [] fileNameSeparated = fileName.split("[0-9][.]");
            String newFileName = fileNameSeparated[0] + (i+1) + "." + fileNameSeparated[1];
            try(FileInputStream fis = new FileInputStream(newFileName)){
                cantidadArchivos++;
                fis.close();
            }
            catch(Exception e){

            }
        }

        if(cantidadArchivos >= 10){
            completed = true;
        }
        else{
            float porcentaje = 1- cantidadArchivos/10;

            System.out.println("Archivo al " + Float.toString(porcentaje) + "%");
            
        }

        return completed;
    }

    @Override
    public boolean transferFile(byte [] data, String fileName, int id, int chunkSize) throws RemoteException {
        System.out.println("Soy el cliente y he recibido el fragmento" + id + " del archivo " + fileName);
        boolean completed = false;
        String newFileName = PATH + fileName;
        try(FileOutputStream fos = new FileOutputStream(newFileName, true)){
            fos.write(data);
            if(verifyFileAndProgress(newFileName)){
                rebuildFile(newFileName, chunkSize);
                completed = true;
            }
            fos.close();
        }
        catch(Exception e){
            System.err.println("Error writting file: " + e.getMessage());
        }

        return completed;
    }
    
}
