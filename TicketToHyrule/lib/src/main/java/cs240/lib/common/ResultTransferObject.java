package common;

import com.google.gson.Gson;

/**
 * Created by David on 1/13/2018.
 */

public class ResultTransferObject {
    private static final Gson gson = new Gson();

    private String typeName;
    private String jsonString;

    public ResultTransferObject(String typeName, Object object) {
        this.typeName = typeName;
        this.jsonString = gson.toJson(object);
    }

    public Object getResult() {
        Class<?> klass = null;
        try {
            klass = Class.forName(typeName);
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: could not find the class " + typeName);
            e.printStackTrace();
        }

        Object result = gson.fromJson(jsonString, klass);
        return result;
    }
}
