import java.util.Scanner;
import java.util.Date;

public class Message {

    private String sender;
    private String content;
    private Date date;
    
    public Message(String sender, String content, Date date)
    {
        this.sender = sender;
        this.content = content;
        this.date = date;
    }

    public static Message sendMessage(String sender)
    {
        Scanner messageReceiver = new Scanner(System.in);
        messageReceiver.useDelimiter("\n");

        System.out.println("Digite sua mensagem: ");
        String content = messageReceiver.next();
        Date d = new Date();
        return new Message(sender, content, d);
    }

    public String getSender()
    {
        return this.sender;
    }

    public String getContent()
    {
        return this.content;
    }

    public String getDate()
    {
        return "[" + this.date + "]";
    }
}
