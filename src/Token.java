import java.util.ArrayList;
import java.util.List;

public class Token {

    //public String Message;
    //private int UserID
    private List Commands = new ArrayList();

    private List getCommandsFromMessage(string Message){
        Message = Message.replaceAll("[,.\n]", "");
        Message.toLowerCase();
        for(string word : Message.split(" ")){
            if (word.Length > 0)
                Command.add(word);
        }
        return Commands;
    }
}
