package com.example.ProyectoFinal.loangrounds.Utilidades;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class OutputStreamHelper {
    public static void writeOutPut (OutputStream stream, JSONObject jsonParam) {
        OutputStreamWriter outputStream = new OutputStreamWriter(stream);
        try {
            outputStream.write(jsonParam.toString());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            CustomLog.logException(e);
        }
    }
}
