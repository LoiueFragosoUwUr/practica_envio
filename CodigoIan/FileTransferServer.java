import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class FileTransferServer {
    public static void main(String [] args) throws RemoteException{

        try{
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("FileTransfer", new FileTransferImpl());
            System.out.println("Server ready");

        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        
    }
}
