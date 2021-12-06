package botLogic;

import botLogic.commands.Command;
import botLogic.commands.CommandListener;
import botLogic.commands.adviseCommand.AdviseCommand;
import botLogic.userData.UserId;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import tokenizer.Token;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BotLogic {
    public HashMap<String, Date> lastUserMessages = new HashMap<String, Date>();
    public Date lastCheckTime;
    public Date checkPeriod = new Date(2000);
    private final Map<String, Method> commands;
    private final CommandListener commandListener;
    private final TelegramBot bot;

    public BotLogic(TelegramBot bot) {
        this.bot = bot;
        lastCheckTime = new Date();
        commandListener = new CommandListener();
        commands = new HashMap<>();
        registerCommands();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    checkForUpdates();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public String handle(Token token) {
        String commandName = token.command();
        String[] arguments = token.arguments();
        Method methodForCommand = commands.get(commandName);
        if (methodForCommand == null)
            return String.format("Неизвестная команда %s", commandName);
        Command command = methodForCommand.getAnnotation(Command.class);
        if (arguments.length >= command.minArgs() && arguments.length <= command.maxArgs())
            return getResultOfCommandExecution(token, methodForCommand);
        return String.format(
                "Команда %s принимает %d - %d аргументов",
                command.name(),
                command.minArgs(),
                command.maxArgs());
    }

    private void checkForUpdates() throws Exception {
        while (true)
        {
            if(new Date().getTime() - lastCheckTime.getTime() >= checkPeriod.getTime())
            {
                for(var user : lastUserMessages.keySet())
                {
                    if(new Date().getTime() - lastUserMessages.get(user).getTime() >= checkPeriod.getTime())
                    {
                        bot.execute(new SendMessage(user, AdviseCommand.advise()));
                    }
                }
                lastCheckTime = new Date();
            }
        }
    }

    private String getResultOfCommandExecution(Token token, Method methodForCommand) {
        try {
            UserId.setIdOfCurrentUser(token.userId());
            return (String) methodForCommand.invoke(commandListener, new Object[]{token.arguments()});
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void registerCommands() {
        for (Method method : commandListener.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                Command cmd = method.getAnnotation(Command.class);
                commands.put(cmd.name(), method);
            }
        }
    }
}