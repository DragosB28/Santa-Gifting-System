package io;

import entities.Input;
import entities.InitialData;
import entities.AnnualChanges;
import entities.Child;
import entities.Gift;
import entities.ChildUpdate;
import enums.Category;
import enums.Cities;
import enums.CityStrategyEnum;
import enums.ElvesType;
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

            numberOfYears = Integer.parseInt(jsonObject.get("numberOfYears").toString());
            santaBudget = Double.parseDouble(jsonObject.get("santaBudget").toString());
            JSONObject initialDataJson = (JSONObject) jsonObject.get("initialData");
            JSONArray childrenJson = (JSONArray) initialDataJson.get("children");
            JSONArray santaGiftsListJson = (JSONArray) initialDataJson.get("santaGiftsList");
            JSONArray annualChangesJson = (JSONArray) jsonObject.get("annualChanges");

            //adding to initialData.children
            if (childrenJson != null) {
                for (Object childJson : childrenJson) {
                    JSONArray giftsPreferencesJson = (JSONArray)
                            ((JSONObject) childJson).get("giftsPreferences");
                    List<Category> giftsPreferences = new ArrayList<>();
                    for (Object gift : giftsPreferencesJson) {
                        giftsPreferences.add(Category.valueOf(gift.toString()
                                .toUpperCase().replace(" ", "_")));
                    }
                    initialData.addChild(new Child.Builder(
                            Integer.parseInt(((JSONObject) childJson)
                                    .get("id").toString()),
                            (String) ((JSONObject) childJson).get("lastName"),
                            (String) ((JSONObject) childJson).get("firstName"),
                            Integer.parseInt(((JSONObject) childJson).get("age").toString()),
                            Cities.valueOf((((JSONObject) childJson)
                                    .get("city")).toString().toUpperCase()
                                    .replace("CLUJ-NAPOCA", "CLUJ")),
                            Double.parseDouble(((JSONObject) childJson)
                                    .get("niceScore").toString()),
                            giftsPreferences,

                            ElvesType.valueOf(((JSONObject) childJson)
                                    .get("elf").toString().toUpperCase())
                    ).addNiceScoreBonus(Double.parseDouble(((JSONObject) childJson)
                            .get("niceScoreBonus").toString())).build());
                }
            }

            //adding to initialData.santaGiftsList
            if (santaGiftsListJson != null) {
                for (Object santaGiftJson : santaGiftsListJson) {
                    initialData.addSantaGift(new Gift(
                            (String) ((JSONObject) santaGiftJson).get("productName"),
                            Double.parseDouble(((JSONObject) santaGiftJson)
                                    .get("price").toString()),
                            Category.valueOf(((JSONObject) santaGiftJson)
                                    .get("category").toString().toUpperCase()
                                    .replace(" ", "_")),
                            Integer.parseInt(((JSONObject) santaGiftJson)
                                    .get("quantity").toString())
                    ));
                }
            }

            if (annualChangesJson != null) {
                for (Object annualChangeJson : annualChangesJson) {
                    //adding to annualChange the list of new gifts
                    JSONArray newGiftsJson = (JSONArray) ((JSONObject)
                            annualChangeJson).get("newGifts");
                    List<Gift> newGifts = new ArrayList<>();
                    for (Object newGiftJson : newGiftsJson) {
                        newGifts.add(new Gift(
                                (String) ((JSONObject) newGiftJson).get("productName"),
                                Double.parseDouble(((JSONObject) newGiftJson)
                                        .get("price").toString()),
                                Category.valueOf(((JSONObject) newGiftJson)
                                        .get("category").toString()
                                        .toUpperCase().replace(" ", "_")),
                                Integer.parseInt(((JSONObject) newGiftJson)
                                        .get("quantity").toString())
                        ));
                    }

                    //adding to annualChange the list of new children
                    JSONArray newChildrenJson = (JSONArray) ((JSONObject)
                            annualChangeJson).get("newChildren");
                    List<Child> newChildren = new ArrayList<>();
                    for (Object newChildJson : newChildrenJson) {
                        JSONArray giftsPreferencesJson = (JSONArray)
                                ((JSONObject) newChildJson).get("giftsPreferences");
                        List<Category> giftsPreferences = new ArrayList<>();
                        for (Object gift : giftsPreferencesJson) {
                            giftsPreferences.add(Category.valueOf(gift
                                    .toString().toUpperCase().replace(" ", "_")));
                        }
                        newChildren.add(new Child.Builder(
                                Integer.parseInt(((JSONObject) newChildJson).get("id").toString()),
                                (String) ((JSONObject) newChildJson).get("lastName"),
                                (String) ((JSONObject) newChildJson).get("firstName"),
                                Integer.parseInt(((JSONObject) newChildJson)
                                        .get("age").toString()),
                                Cities.valueOf((((JSONObject) newChildJson)
                                        .get("city")).toString().toUpperCase()
                                        .replace("CLUJ-NAPOCA", "CLUJ")),
                                Double.parseDouble(((JSONObject) newChildJson)
                                        .get("niceScore").toString()),
                                giftsPreferences,
                                ElvesType.valueOf(((JSONObject) newChildJson)
                                        .get("elf").toString().toUpperCase())
                        ).addNiceScoreBonus(Double.parseDouble(((JSONObject) newChildJson)
                                .get("niceScoreBonus").toString())).build());
                    }

                    //adding to annualChange the list of childrenUpdates
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
                        //the following if is used to avoid the case of .get returning null
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
                                newGiftsPreferences,
                                ElvesType.valueOf(((JSONObject) childUpdatesJson)
                                        .get("elf").toString().toUpperCase())
                        ));
                    }

                    annualChanges.add(new AnnualChanges(
                            Double.parseDouble(((JSONObject) annualChangeJson)
                                    .get("newSantaBudget").toString()),
                            newGifts,
                            newChildren,
                            childrenUpdates,
                            CityStrategyEnum.valueOf(((JSONObject) annualChangeJson)
                                    .get("strategy").toString().toUpperCase()
                                    .replace("NICESCORECITY", "NICE_SCORE_CITY")
                                    .replace("NICESCORE", "NICE_SCORE"))
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
