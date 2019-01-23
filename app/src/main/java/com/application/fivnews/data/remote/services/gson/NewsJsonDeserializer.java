package com.application.fivnews.data.remote.services.gson;

import com.application.fivnews.data.model.News;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * track json to a list of track item
 */
public class NewsJsonDeserializer implements JsonDeserializer<List<News>> {
    @Override
    public List<News> deserialize(JsonElement json, Type typeOfT,
                                   JsonDeserializationContext context) throws JsonParseException {
        JsonArray jsonArray = json.getAsJsonObject().get("articles").getAsJsonArray();

        List<News> list = new ArrayList<>();
        for (JsonElement item : jsonArray) {
            list.add(new Gson().fromJson(item, News.class));
        }
        return list;
    }
}