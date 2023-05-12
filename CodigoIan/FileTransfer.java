import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FileTransfer extends Remote{
    public boolean transferFile(byte [] data, String fileName, int id, int chunkSize) throws RemoteException;
}