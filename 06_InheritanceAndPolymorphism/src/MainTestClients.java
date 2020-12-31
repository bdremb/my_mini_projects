import clients.Businessman;
import clients.Client;
import clients.Entity;
import clients.Individual;

public class MainTestClients {         // тестирование работы с абстрактными классами

    public static void main(String[] args) {

        System.out.println("\nTest Entity\n"); // тестирование юридического лица
        Client entity = new Entity();
        entity.accauntBalance();
        entity.putMoney(1000);
        entity.accauntBalance();
        entity.withdrawMoney(1000);
        entity.accauntBalance();
        System.out.println();
        entity.withdrawMoney(700);
        entity.accauntBalance();

        System.out.println("\nTest Individual\n"); // тестирование физического лица
        Client entity1 = new Individual();
        entity1.accauntBalance();
        entity1.putMoney(1000);
        entity1.accauntBalance();
        entity1.withdrawMoney(500);
        entity1.accauntBalance();

        System.out.println("\nTest Businessman\n"); // тестирование юридического лица
        Client entity2 = new Businessman();
        entity2.accauntBalance();
        entity2.putMoney(1000);
        entity2.accauntBalance();
        entity2.withdrawMoney(500);
        entity2.accauntBalance();
        entity2.putMoney(-20);

        System.out.println();
        entity2.putMoney(10000);
        entity2.accauntBalance();
        entity2.withdrawMoney(15500);
        entity2.accauntBalance();

        System.out.println("\nAll balance :\n");
        entity.accauntBalance();
        entity1.accauntBalance();
        entity2.accauntBalance();



    }
}
