import java.io.*;
import java.net.InetAddress;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.*;
import static java.rmi.server.RemoteServer.getClientHost;
import java.rmi.server.ServerNotActiveException;
import java.util.concurrent.TimeUnit;
import javax.management.Descriptor;

public class FileTransferClient {
    public static void main(String[] args)
            throws RemoteException, NotBoundException, IOException, InterruptedException {
        // String filename = "破片１.txt";
        // File file = new File(filename);
        // long fileSize = file.length();
        // long chunkSize = fileSize;
        // long chunkSize = fileSize / 10;
        int index = 1;
        String[] clientes = { "localhost", "localhost", "localhost", "localhost", "localhost" };
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        FileTransfer stub = (FileTransfer) registry.lookup("FileTransfer");
        Registry registry2 = LocateRegistry.getRegistry("localhost", 1100);
        FileTransfer stub2 = (FileTransfer) registry.lookup("FileTransfer");

        /*
         * Recognize the different ip addresses that have established a connection to
         * our client that works
         * as a server
         */
        // String clientHost = "";
        // try {
        // clientHost = getClientHost();
        // // if (clientHost.contains(":")) {
        // clientHost = "[" + clientHost + "]";
        // // }
        // } catch (ServerNotActiveException e) {
        // System.out.println(e);
        // }
        // int random_int = (int) Math.floor(Math.random() * (10 - 1 + 1) + 1);
        int[] checker = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int[] empty = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        int suma = 0;
        boolean decision = false, decision_sends = false, decision_for_clients = false;
        InetAddress address = InetAddress.getLocalHost();
        String sHostName;
        sHostName = address.getHostAddress();
        // sHostName = address.getHostName();
        System.out.println(sHostName);
        do {
            int random_client = (int) Math.floor(Math.random() * (5 - 1 + 1) + 1);

            // do {
            // decision_sends = false;
            suma = 0;
            int random_int = (int) Math.floor(Math.random() * (10 - 1 + 1) + 1);
            System.out.println("random " + random_int);
            // for (int i = 0; i < 10; i++) {
            // if (checker[i] == random_int) {
            // checker[i] = 0;
            // }
            // }
            // int suma = 0;
            // for (int i = 0; i < 10; i++) {
            // suma = checker[i];
            // suma = suma + checker[i];

            // }
            // if (suma == 0) {
            // decision = true;
            // }
            // System.out.println("suma " + suma);

            System.out.println("//////////////////////////////////////////////////////////////");
            for (int i = 0; i < 10; i++) {
                System.out.println(checker[i]);
            }
            System.out.println("//////////////////////////////////////////////////////////////");
            for (int i = 0; i < 10; i++) {
                if (checker[i] == random_int) {
                    decision_sends = true;
                }
            }
            for (int i = 0; i < 10; i++) {
                if (checker[i] == random_int) {
                    checker[i] = 0;
                }
            }
            if (decision_sends == true) {
                String filename = "破片" + random_int + ".mp3";
                File file = new File(filename);
                long fileSize = file.length();
                long chunkSize = fileSize;
                try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
                    byte[] buffer = new byte[(int) chunkSize];
                    int bytesRead = 0;
                    while ((bytesRead = bis.read(buffer)) != -1) {
                        stub.transferFile(buffer, file.getName());
                    }
                }
            }

            TimeUnit.SECONDS.sleep(2);

            for (int i = 0; i < 9; i++) {
                suma = checker[i] + suma;

            }
            if (suma == 0) {
                decision = true;
            }
            System.out.println("suma " + suma);

            // index++;
        } while (decision != true);

        for (int i = 0; i < 9; i++) {
            suma = checker[i] + suma;

        }
        if (suma == 0) {
            decision = true;
        }
        System.out.println("suma " + suma);

        // } while (decision_for_clients != true);
        // while (index != 11);
    }
}
