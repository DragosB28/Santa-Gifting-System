package jsonparser;

import entities.*;
import enums.Category;
import enums.Cities;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputLoader {
    private final String inputPath;

    public InputLoader(final String inputPath) {
        this.inputPath = inputPath;
    }

    /**
     * Reads data from JSON file
     *
     * @return An input object with all the data from the json
     */
    public Input readData() {
        int numberOfYears = 0;
        double santaBudget = 0;
        InitialData initialData = new InitialData();
        List<AnnualChanges> annualChanges = new ArrayList<>();

        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser
                    .parse(new FileReader(inputPath));

            numberOfYears = Integer.parseInt(jsonObject.get("numberOfYears")
                    .toString());
            santaBudget = Double.parseDouble(jsonObject.get("santaBudget")
                    .toString());
            JSONObject initialDataJson = (JSONObject) jsonObject
                    .get("initialData");
            JSONArray childrenJson = (JSONArray) initialDataJson
                    .get("children");
            JSONArray santaGiftsListJson = (JSONArray) initialDataJson
                    .get("santaGiftsList");
            JSONArray annualChangesJson = (JSONArray) jsonObject
                    .get("annualChanges");

            //adaug in initialData.children
            if (childrenJson != null) {
                for (Object childJson : childrenJson) {
                    JSONArray giftsPreferencesJson = (JSONArray)
                            ((JSONObject) childJson).get("giftsPreferences");
                    List<Category> giftsPreferences = new ArrayList<>();
                    for (Object gift : giftsPreferencesJson) {
                        giftsPreferences.add(Category.valueOf(gift.toString()
                                .toUpperCase().replace(" ", "_")));
                    }
                    initialData.addChild(new Child(
                            Integer.parseInt(((JSONObject) childJson)
                                    .get("id").toString()),
                            (String) ((JSONObject) childJson).get("lastName"),
                            (String) ((JSONObject) childJson).get("firstName"),
                            Integer.parseInt(((JSONObject) childJson)
                                    .get("age").toString()),
                            Cities.valueOf((((JSONObject) childJson)
                                    .get("city")).toString().toUpperCase()
                                    .replace("CLUJ-NAPOCA", "CLUJ")),
                            Double.parseDouble(((JSONObject) childJson)
                                    .get("niceScore").toString()),
                            giftsPreferences
                    ));
                }
            }

            //adaug in initialData.santaGiftsList
            if (santaGiftsListJson != null) {
                for (Object santaGiftJson : santaGiftsListJson) {
                    initialData.addSantaGift(new Gift(
                            (String) ((JSONObject) santaGiftJson)
                                    .get("productName"),
                            Double.parseDouble(((JSONObject) santaGiftJson)
                                    .get("price").toString()),
                            Category.valueOf(((JSONObject) santaGiftJson)
                                    .get("category").toString().toUpperCase()
                                    .replace(" ", "_"))
                    ));
                }
            }

            if (annualChangesJson != null) {
                for (Object annualChangeJson : annualChangesJson) {
                    //Adaug in annualChange lista de noi cadouri
                    JSONArray newGiftsJson = (JSONArray) ((JSONObject)
                            annualChangeJson).get("newGifts");
                    List<Gift> newGifts = new ArrayList<>();
                    for (Object newGiftJson : newGiftsJson) {
                        newGifts.add(new Gift(
                                (String) ((JSONObject) newGiftJson)
                                        .get("productName"),
                                Double.parseDouble(((JSONObject) newGiftJson)
                                        .get("price").toString()),
                                Category.valueOf(((JSONObject) newGiftJson)
                                        .get("category").toString()
                                        .toUpperCase().replace(" ", "_"))
                        ));
                    }

                    //Adaug in annualChange lista de noi copii
                    JSONArray newChildrenJson = (JSONArray) ((JSONObject)
                            annualChangeJson).get("newChildren");
                    List<Child> newChildren = new ArrayList<>();
                    for (Object newChildJson : newChildrenJson) {
                        JSONArray giftsPreferencesJson = (JSONArray)
                                ((JSONObject) newChildJson)
                                        .get("giftsPreferences");
                        List<Category> giftsPreferences = new ArrayList<>();
                        for (Object gift : giftsPreferencesJson) {
                            giftsPreferences.add(Category.valueOf(gift
                                    .toString().toUpperCase()
                                    .replace(" ", "_")));
                        }
                        newChildren.add(new Child(
                                Integer.parseInt(((JSONObject) newChildJson)
                                        .get("id").toString()),
                                (String) ((JSONObject) newChildJson)
                                        .get("lastName"),
                                (String) ((JSONObject) newChildJson)
                                        .get("firstName"),
                                Integer.parseInt(((JSONObject) newChildJson)
                                        .get("age").toString()),
                                Cities.valueOf((((JSONObject) newChildJson)
                                        .get("city")).toString().toUpperCase()
                                        .replace("CLUJ-NAPOCA", "CLUJ")),
                                Double.parseDouble(((JSONObject) newChildJson)
                                        .get("niceScore").toString()),
                                giftsPreferences
                        ));
                    }

                    //Adaug in annualChange lista de childrenUpdates
                    JSONArray childrenUpdatesJson = (JSONArray) ((JSONObject)
                            annualChangeJson).get("childrenUpdates");
                    List<ChildUpdate> childrenUpdates = new ArrayList<>();
                    for (Object childUpdatesJson : childrenUpdatesJson) {
                        JSONArray newGiftsPreferencesJson = (JSONArray)
                                ((JSONObject) childUpdatesJson)
                                        .get("giftsPreferences");
                        List<Category> newGiftsPreferences = new ArrayList<>();
                        for (Object newGiftPreferenceJson : newGiftsPreferencesJson) {
                            newGiftsPreferences.add(Category.
                                    valueOf(newGiftPreferenceJson.toString()
                                            .toUpperCase().replace(" ", "_")));
                        }
                        //Urmatorul if este facut pentru a evita cazul in care .get returneaza null
                        double niceScore;
                        if (((JSONObject) childUpdatesJson).
                                get("niceScore") == null) {
                            niceScore = -1;
                        } else {
                            niceScore = Double.parseDouble(((JSONObject) childUpdatesJson)
                                    .get("niceScore").toString());
                        }

                        childrenUpdates.add(new ChildUpdate(
                                Integer.parseInt(((JSONObject) childUpdatesJson)
                                        .get("id").toString()),
                                niceScore,
                                newGiftsPreferences
                        ));
                    }

                    annualChanges.add(new AnnualChanges(
                            Double.parseDouble(((JSONObject) annualChangeJson)
                                    .get("newSantaBudget").toString()),
                            newGifts,
                            newChildren,
                            childrenUpdates
                    ));
                }
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return new Input(numberOfYears, santaBudget, initialData,
                annualChanges);
    }
}
