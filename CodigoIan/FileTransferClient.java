import java.io.*;
import java.net.InetAddress;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.TimeUnit;

public class FileTransferClient {
    public static void main(String[] args)
            throws RemoteException, NotBoundException, IOException, InterruptedException {
        // String filename = "破片１.txt";
        // File file = new File(filename);
        // long fileSize = file.length();
        // long chunkSize = fileSize;
        // long chunkSize = fileSize / 10;
        // InetAddress address = InetAddress.getLocalHost();

        // String sHostName;
        // sHostName = address.getHostAddress();
        // sHostName = address.getHostName();
        // System.out.println("IP " + sHostName);

        int index = 1; // my own my mother's Ian's 他の その他の他 ^^
        String[] clientes = { "26.65.22.16", "26.144.62.14", "26.17.27.132", "localhost", "26.65.22.16" };
        // String[] clientes = { "26.65.22.16", "localhost", "26.144.62.14",
        // "localhost", "localhost" };
        // Registry registry = LocateRegistry.getRegistry(clientes[], 1099);
        // System.out.println("Current IP address " + address.getHostAddress());
        // Ian's Ip address 26.144.62.14
        // mother's Ip address 26.65.22.16

        // FileTransfer stub = (FileTransfer) registry.lookup("FileTransfer");
        // Registry registry2 = LocateRegistry.getRegistry("localhost", 1099);
        // FileTransfer stub2 = (FileTransfer) registry.lookup("FileTransfer");

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
        int suma = 0, checker_for_clients = 0, random_client;
        String checker_for_clients2 = "";
        boolean decision = false, decision_sends = false, decision_for_clients = false;
        // InetAddress address = InetAddress.getLocalHost();
        // String sHostName;
        // sHostName = address.getHostAddress();
        // sHostName = address.getHostName();
        // System.out.println("IP " + sHostName);
        do {
            checker_for_clients = 0;
            random_client = (int) Math.floor(Math.random() * 5 + 0); // 0 a 4
            System.out.println("random client " + random_client);
            for (int j = 0; j < 5; j++) {
                System.out.println(clientes[j]);
            }
            System.out.println(" Elegido " + clientes[random_client]);
            if (clientes[random_client] != " ") {
                System.out.println("empieza envio");
                // 一 二 三 四 五
                // { "localhost", "26.65.22.19", "26.144.62.14", "localhost", "localhost" };
                Registry registry = LocateRegistry.getRegistry(clientes[random_client], 1099);

                FileTransfer stub = (FileTransfer) registry.lookup("FileTransfer");
                do {
                    decision_sends = false;
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
                                boolean completado = stub.transferFile(buffer, file.getName(), random_int,
                                        (int) chunkSize);
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
                for (int j = 0; j < 9; j++) {
                    checker[j] = j + 1;
                }

                for (int i = 0; i < 9; i++) {
                    suma = checker[i] + suma;

                }
                if (suma == 0) {
                    decision = true;
                }
                System.out.println("suma " + suma);

                clientes[random_client] = " ";
                // for (int i = 0; i < 5; i++) {
                // if (clientes[i] == random_client) {
                // checker[i] = 0;
                // }
                // }
                for (int i = 0; i < 5; i++) {
                    checker_for_clients2 = clientes[i] + checker_for_clients2;
                    System.out.println(clientes);
                }

                if (checker_for_clients2 == " ") {
                    decision_for_clients = true;
                }
                System.out.println("suma clients " + checker_for_clients);
            }

        } while (decision_for_clients != true);
        // while (index != 11);
    }
}
