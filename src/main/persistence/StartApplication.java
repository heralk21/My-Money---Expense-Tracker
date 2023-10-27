package persistence;

public class StartApplication {
    public static void main(String[] args) {
        String path = "/data/testWriterEmptyExpenseList.json";
        JsonWriter writer = new JsonWriter(path);

    }
}
