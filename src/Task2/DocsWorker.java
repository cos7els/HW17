package Task2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DocsWorker {
    private final Pattern DOC_NUMBER_PATTERN = Pattern.compile("^[0-9]{4}-[a-z]{3}-[0-9]{4}-[a-z]{3}-[0-9][a-z][0-9][a-z]");
    private final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("\\+\\d{3}\\(\\d{2}\\)\\d{7}");
    private final Pattern EMAIL_PATTERN = Pattern.compile("[0-9a-z]{4,12}@[a-z]{2,12}\\.[a-z]{2,3}");
    private Map<String, Document> data = new HashMap<>();

    public void start() {
        String read;
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Enter the path: ");
            read = in.nextLine();
            System.out.print("Enter the count value: ");
            int count = Integer.parseInt(in.nextLine());
            checkFiles(read, count);
        }
    }

    private void checkFiles(String path, int count) {
        int txtCounter = 0;
        File[] files = new File(path).listFiles();
        if (files.length == 0) {
            System.out.println("There is no any files");
        }
        for (int i = 0, j = 0; i < files.length && j < count; i++) {
            if (files[i].isFile() && files[i].getName().endsWith(".txt")) {
                txtCounter++;
                j++;
                readData(files[i]);
            }
        }
        if (txtCounter == 0) {
            System.out.println("There is no '.txt' files");
        }
        printData();
    }

    private void readData(File file) {
        List<String> strings = null;
        try {
            strings = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            System.out.println("Something wrong with read from file");
        }
        Document document = new Document();
        strings.stream().map(DOC_NUMBER_PATTERN::matcher).forEach(x -> x.results().forEach(s -> document.addDocNumbers(s.group())));
        strings.stream().map(PHONE_NUMBER_PATTERN::matcher).forEach(x -> x.results().forEach(s -> document.addPhoneNumbers(s.group())));
        strings.stream().map(EMAIL_PATTERN::matcher).forEach(x -> x.results().forEach(s -> document.addeMails(s.group())));
        if (document.getDocsSize() > 0) {
            data.put(file.getName().replace(".txt", ""), document);
        }
    }

    private void printData() {
        data.forEach((k, v) -> System.out.printf("Key: %s\n%s\n", k, v));
    }

}
