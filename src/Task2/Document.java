package Task2;

import java.util.ArrayList;
import java.util.List;

public class Document {
    private List<String> docNumbers;
    private List<String> phoneNumbers;
    private List<String> eMails;

    public Document() {
        this.docNumbers = new ArrayList<>();
        this.phoneNumbers = new ArrayList<>();
        this.eMails = new ArrayList<>();
    }

    public Document(List<String> docNumbers, List<String> phoneNumbers, List<String> eMails) {
        this.docNumbers = docNumbers;
        this.phoneNumbers = phoneNumbers;
        this.eMails = eMails;
    }

    public void addDocNumbers(String value) {
        docNumbers.add(value);
    }

    public void addPhoneNumbers(String value) {
        phoneNumbers.add(value);
    }

    public void addeMails(String value) {
        eMails.add(value);
    }

    public int getDocsSize() {
        return docNumbers.size();
    }

    @Override
    public String toString() {
        return String.format("The Document(s): %s\nPhone number(s): %s\nE-mail(s): %s\n", docNumbers, phoneNumbers, eMails);
    }

}
