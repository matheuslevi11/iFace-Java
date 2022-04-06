import java.util.Scanner;
import java.util.ArrayList;

public class Feed {
    public ArrayList<Message> msgs = new ArrayList<Message>();

    public void newMessage(String sender)
    {
        Message m = Message.sendMessage(sender);
        this.msgs.add(m);
        System.out.println("Sua mensagem foi enviada!");
    }

    public void showFeed(User u, boolean onlyFriends)
    {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
        ArrayList<String> friends = new ArrayList<String>();
        for (User i : u.friends) {
            friends.add(i.nickname);
        }
        if (msgs.size() > 0)
        {
            System.out.println("Mensagens do feed:\n");
            for (Message m : msgs) {
                if (onlyFriends) {
                    if (friends.contains(m.getSender()) || m.getSender() == u.nickname)
                    {
                        System.out.println(m.getDate() + " " + m.getSender() + ": " + m.getContent());
                    }
                }
                else
                    System.out.println(m.getDate() + " " + m.getSender() + ": " + m.getContent());
            }
        }
        else
            System.out.println("O feed está vazio!");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}