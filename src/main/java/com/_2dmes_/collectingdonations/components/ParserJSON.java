package com._2dmes_.collectingdonations.components;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ParserJSON {
    public static JSONArray getAllTransactionJSON(String dataJSON) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(dataJSON);
        JSONArray allTransactionJSON = (JSONArray) jsonObject.get("data");
        return allTransactionJSON;
    }
}
