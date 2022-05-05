import java.util.Scanner;
import java.util.ArrayList;

public class Feed {
    public ArrayList<Message> msgs = new ArrayList<Message>();

    public void newMessage(User sender, ArrayList<User> users)
    {
        Scanner input = new Scanner(System.in);
        Message m = Message.sendMessage(sender.nickname);
        ArrayList<String> options = Database.feedOptions();
        Graphics.printOptions(options, "Como deseja publicar?");
        int restricted = input.nextInt();
        for (User u : users) {
            if (restricted != 1) {
                u.feed.msgs.add(m);
            }
            else if (u.friends.contains(sender) || u == sender)
                u.feed.msgs.add(m);
        }
        Graphics.success("Sua mensagem foi enviada ao feed!");
    }

    public void showFeed()
    {
        if (msgs.size() > 0)
        {
            Graphics.printMessages(msgs, "Mensagens do feed");
        }
        else
            System.out.println("O feed está vazio!");
    }
}