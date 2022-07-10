package by.ivanshka.readerWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderWriterJson<T> {
    public void write(String filePath, T object) throws IOException {
        FileWriter writer = new FileWriter(filePath);

        Gson serializer = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        String json = serializer.toJson(object);

        writer.write(json);
        writer.close();
    }

    public T read(String filePath, Class<T> expectedType) throws IOException {
        FileReader reader = new FileReader(filePath);

        Gson serializer = new Gson();

        return serializer.fromJson(reader, expectedType);
    }
}
