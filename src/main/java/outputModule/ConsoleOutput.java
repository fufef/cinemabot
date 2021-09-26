package outputModule;

public class ConsoleOutput implements OutputModule {

    @Override
    public void sendMessage(String message, String userId) {
        for (int i = 0; i < 20; i++)
            System.out.print('-');
        System.out.printf("\nuserID:\n\t%s\nMessage:\n\t%s%n", userId, message);
        for (int i = 0; i < 20; i++)
            System.out.print('-');
        System.out.println('\n');
    }
}
