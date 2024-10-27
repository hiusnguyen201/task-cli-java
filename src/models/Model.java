package models;

import com.google.gson.JsonObject;

public abstract class Model {
    abstract JsonObject convertToJsonObject();
}
