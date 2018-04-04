package cs240.lib.common;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by David on 1/18/2018.
 */

public class EncoderDecoder {
    private static final Gson gson = new Gson();

    public void encode(OutputStream outputStream, Object object) {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        gson.toJson(object, outputStreamWriter);
        try {
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Object decode(InputStream inputStream, Class<?> klass) throws Exception
    {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        Object result = gson.fromJson(inputStreamReader, klass);
        inputStreamReader.close();

        return result;
    }
}
