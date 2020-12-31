import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.*;


public class ShopController {

    private static boolean start;

    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        MongoDatabase database = mongoClient.getDatabase("local");

        MongoCollection<Document> collectionShop = database.getCollection("MyShops");
        MongoCollection<Document> collectionItems = database.getCollection("MyItems");

        System.out.println("Список команд: \n ADD-SHOP (name); \n ADD-ITEM (name, price); \n ADD-TO-SHOP (item, shop); \n INFO; \n EXIT ");
        while (!start) {
            System.out.println("---------------------------------------------------------------------------------------------------------------");
            Scanner scanner = new Scanner(System.in);
            String[] string = scanner.nextLine().split(" ");
            String command = string[0].toLowerCase();

            switch (command) {
                case ("add-shop"):
                    if (string.length != 2) {
                        break;
                    }
                    String shop = string[1];
                    if (Optional.ofNullable(collectionShop
                            .find(BsonDocument.parse("{name: \"" + shop + "\"}"))
                            .first())
                            .isPresent()) {
                        System.out.println("THIS SHOP IS PRESENT");
                        break;
                    }

                    collectionShop.insertOne(new Document()
                            .append("name", shop)
                            .append("items", new ArrayList<>()));
                    System.out.println("        >> ADD shop: <" + shop + ">");
                    break;

                case ("add-item"):

                    if (string.length != 3) {
                        break;
                    }
                    String itm = string[1];
                    if (Optional.ofNullable(collectionItems
                            .find(BsonDocument.parse("{name: \"" + itm + "\"}"))
                            .first())
                            .isPresent()) {
                        System.out.println("THIS ITEM IS PRESENT");
                        break;
                    }

                    int price = Integer.parseInt(string[2]);
                    collectionItems.insertOne(new Document()
                            .append("name", itm)
                            .append("price", price));
                    System.out.println("        >> item <" + itm + ">  is add");
                    break;

                case ("add-to-shop"):
                    if (string.length != 3) {
                        break;
                    }
                    String itemFromScanner = string[1];
                    String shopFromScanner = string[2];

                    Bson shopQuer = BsonDocument.parse("{name: \"" + shopFromScanner + "\"}");
                    Bson item = BsonDocument.parse("{name:\"" + itemFromScanner + "\"}");

                    Document thisStore = collectionShop.find(shopQuer).first();
                    Document thisItem = collectionItems.find(item).first();
                    List<String> itList = (List<String>) thisStore.get("items");

                    if ((thisStore.get("name") == null) || (Objects.requireNonNull(thisItem).get("name") == null)) {
                        System.out.println("Проверьте название магазина или товара ");
                        break;
                    }
                    itList.add(thisItem.get("name").toString());
                    collectionShop.updateOne(Filters.eq("name", shopFromScanner), Updates.set("items", itList));

                    System.out.println("- В магазине " + thisStore.get("name") + " есть следующие продукты: " + itList);
                    break;

                case ("info"):
                    Bson lookup = Aggregates.lookup("MyShops", "name", "items", "items_list");
                    Bson unwind = Aggregates.unwind("$items_list");
                    Bson group = Aggregates.group("$" + "items_list",
                            Accumulators.avg("avgprice", "$" + "price"),
                            Accumulators.min("minprice", "$" + "price"),
                            Accumulators.max("maxprice", "$" + "price"));

                    List<Document> results = collectionItems.aggregate(Arrays.asList(lookup, unwind, group)).into(new ArrayList<>());


                    for (Document localDoc : results) {
                        Document shopp = (Document) localDoc.get("_id");
                        List<String> lst = (List<String>) shopp.get("items");

                        List<Document> itemMinHundred = collectionItems
                                .find(BsonDocument.parse("{price: {$lt: 100}}"))
                                .into(new ArrayList<>());

                        Document minItem = collectionItems.find(Filters.eq("price", localDoc.get("minprice"))).first();
                        Document maxItem = collectionItems.find(Filters.eq("price", localDoc.get("maxprice"))).first();

                        System.out.println("\n     <<<    Магазин   *** " + shopp.get("name") + " ***    >>>");
                        System.out.println("Общее количество товаров = " + lst.size());
                        System.out.println("Средняя цена товаров в магазине: " + localDoc.get("avgprice"));
                        System.out.println("Самый дешевый товар: \"" + minItem.get("name") + "\". Цена: " + minItem.get("price") + " руб");
                        System.out.println("Самый дорогой товар: \"" + maxItem.get("name") + "\". Цена: " + maxItem.get("price") + " руб");
                        System.out.println("Количество товаров дешевле 100 руб: " + itemMinHundred
                                .stream()
                                .filter(i -> lst.contains(i.get("name").toString()))
                                .count());
                    }
                    break;

                case ("exit"):
                    start = true;
                    break;

                default:
                    break;

            }
        }
    }
}
