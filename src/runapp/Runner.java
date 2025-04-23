package runapp;

import bryteforce.BrytForceService;
import crypt.CriptService;
import readAndwrite.FileService;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

public class Runner {
    private String encryptedFile = "ENCRYPTED";
    private String deCryptedFile = "DECRYPTED";
    private String bruteForce = "BRUTE_FORCE";
    private String mode;
    private Path path;
    private int key;

    public void run(String[] args) throws IOException {
        if(args.length<1){
            System.out.println("Enter the correct arguments");
        }
        mode = args[0];
        path = Path.of(args[1]);
        key = Integer.parseInt(args[2]);

        FileService fileService = new FileService();
        CriptService criptFiles = new CriptService();

        StringBuilder text = new StringBuilder(fileService.readFile(path));

        if (mode.equals(encryptedFile)) {
            StringBuilder result = criptFiles.cript(text, key);
            fileService.writeFile(path, result, "ENCRYPTED");
        } else if (mode.equals(deCryptedFile)) {
            StringBuilder result = criptFiles.deCript(text, key);
            fileService.writeFile(path, result, "DECRYPTED");
        } else if (mode.equals(bruteForce)) {
            BrytForceService brute = new BrytForceService();
            int foundKey = brute.brytDecript(text);
            StringBuilder result = criptFiles.deCript(text, foundKey);
            fileService.writeFile(path, result, "BRUTE_FORCE");
        }
        System.out.println("Done!");
    }
}

