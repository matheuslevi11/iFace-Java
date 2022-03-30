import java.util.Scanner;

public class Profile {
    public String hobbie;
    public int idade;
    public String local;

    public Profile(String hobbie, int idade, String local)
    {
        this.hobbie = hobbie;
        this.idade = idade;
        this.local = local;
    }

    public String[] getProfileInfo()
    {
        String[] atts = {this.hobbie, String.valueOf(this.idade), this.local};
        return atts;
    }

    public static Profile createProfile() {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o seu hobbie:");
        String hobbie = input.next().trim();
        System.out.println("Digite a sua idade:");
        int idade = input.nextInt();
        System.out.println("Digite o seu local:");
        String local = input.next().trim();
        
        Profile p = new Profile(hobbie, idade, local);
        return p;
    }

}