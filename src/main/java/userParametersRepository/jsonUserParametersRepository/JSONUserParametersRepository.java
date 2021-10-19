package userParametersRepository.jsonUserParametersRepository;

import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import userParametersRepository.UserParametersRepository;
import userParametersRepository.UserParameters;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;


// todo: многие моменты захардкожены, будет больно хранить еще одну таблицу
public class JSONUserParametersRepository implements UserParametersRepository {
    private final File database;
    private final JsonObject databaseData;

    public JSONUserParametersRepository() {
        this.database = new File(
                "src/main/java/userParametersRepository/jsonUserParametersRepository/database.json");
        if (!this.database.exists()) {
            try {
                if (!this.database.createNewFile())
                    System.exit(3);
                uploadDatabase(new JsonObject());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.databaseData = downloadDatabase();
    }

    @Override
    public void save(String userId, UserParameters parameters) {
        this.databaseData.put(userId, Parser.parseUserParametersToJsonObject(parameters));
        uploadDatabase(this.databaseData);
    }

    @Override
    public UserParameters get(String userId) {
        JsonObject userParametersAsJson = (JsonObject) this.databaseData.get(userId);
        return userParametersAsJson == null
                ? null
                : Parser.parseJsonObjectToUserParameters(userParametersAsJson);
    }

    private JsonObject downloadDatabase() {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(this.database.getPath()));
            JsonObject data = (JsonObject) Jsoner.deserialize(reader);
            reader.close();
            return data;
        } catch (IOException | JsonException e) {
            e.printStackTrace();
            System.exit(3);
        }
        return null;
    }

    private void uploadDatabase(JsonObject data) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(this.database.getPath()));
            Jsoner.serialize(data, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(3);
        }
    }
}
