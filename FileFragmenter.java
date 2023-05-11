import java.io.*;
// import FileTransferClient.*;

public class FileFragmenter /* extends FileTransferServer */ {
    public static void main(String[] args) throws IOException {
        // String filename = "archivotransferir.txt";
        // FileTransferClient newo = new FileTransferClient();
        String filename = "NecesitoDecirte.mp3";
        File file = new File(filename);
        long fileSize = file.length();
        long chunkSize = fileSize / 10;

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            byte[] buffer = new byte[(int) chunkSize];
            int bytesRead = 0;
            int index = 1;
            while ((bytesRead = bis.read(buffer)) != -1) {

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                String filemane = "chunk "/* + index */;
                baos.write(buffer, 0, bytesRead);
                byte[] chunk = baos.toByteArray();
                // FileUtils.writeByteArrayToFile(new File("pathname"), chunk);
                try (FileOutputStream fos = new FileOutputStream(
                        "C:\\Users\\biosh\\Documents\\noveno_sem\\practicas_sistemas\\practica_9\\practicas-SD-2022\\rmi fragmentador archivos cliente\\破片"
                                + index + ".mp3")) {
                    fos.write(chunk);
                    // fos.close(); There is no more need for this line since you had created the
                    // instance of "fos" inside the try. And this will automatically close the
                    // OutputStream
                }
                // newo.AndIgonnaKeepOnLovingYou(chunk, filemane);
                // Aquí se puede llamar un método remoto para transferir cada fragmento
                System.out.println("第　" + index + "　の破片");

                // utilizando RMI Java.
                index++;
            }

        }
    }

}