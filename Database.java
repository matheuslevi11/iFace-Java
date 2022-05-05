import java.util.ArrayList;

public class Database {

    public static ArrayList<String> mainMenuOptions()
    {
        ArrayList<String> array = new ArrayList<String>();
        array.add("Criar outro usuário");
        array.add("Fazer logout");
        array.add("Criar/Entrar em uma comunidade");
        array.add("Editar Perfil");
        array.add("Adicionar Amigo");
        array.add("Ver solicitações de amizade");
        array.add("Ver lista de amigos");
        array.add("Enviar uma mensagem");
        array.add("Ver caixa de mensagens");
        array.add("Ir para o menu da comunidade");
        array.add("Pesquisar usuário");
        array.add("Visualizar Feed de Notícias");
        array.add("Apagar meu usuário do iFace");
        return array;
    }

    public static ArrayList<String> logoutOptions()
    {
        ArrayList<String> array = new ArrayList<String>();
        array.add("Criar um novo usuário");
        array.add("Fazer login");
        return array;
    }

    public static ArrayList<String> messageOptions()
    {
        ArrayList<String> array = new ArrayList<String>();
        array.add("Enviar uma mensagem para o usuário");
        array.add("Enviar uma mensagem para uma comunidade");
        array.add("Enviar uma mensagem para o feed de Notícias");
        return array;
    }

    public static ArrayList<String> feedOptions()
    {
        ArrayList<String> array = new ArrayList<String>();
        array.add("Publicar para amigos");
        array.add("Publicar para todos");
        return array;
    }
    
    public static ArrayList<String> editOptions(String type)
    {
        ArrayList<String> array = new ArrayList<String>();
        if (type.equalsIgnoreCase("User")) {
            array.add("Apelido");
            array.add("Login");
            array.add("Senha");
            array.add("Editar Perfil");
        }
        else if (type.equalsIgnoreCase("Profile"))
        {
            array.add("Hobbie");
            array.add("Idade");
            array.add("Local");
        }
        return array;
    }

    public static ArrayList<String> communityOptions(boolean owner)
    {
        ArrayList<String> array = new ArrayList<String>();
        array.add("Ver mensagens da comunidade");
        if (owner)
        {
            array.add("Gerenciar comunidade");
        }
        return array;
    }
}
