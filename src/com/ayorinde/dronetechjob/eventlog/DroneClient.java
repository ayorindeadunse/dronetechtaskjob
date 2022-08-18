package com.ayorinde.dronetechjob.eventlog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DroneClient {

    public void getEventLog() throws IOException, JSONException {
        List<String> logData = new ArrayList<>();
        URL url = new URL("http://localhost:8080/api/dronetech/geteventlog");
        String readLine = null;
        JSONObject jsonObject = null;
        JSONArray jr = null;
        String responseData = null;
        String output = null;
        StringBuffer response = null;
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            response = new StringBuffer();
            while ((readLine = in.readLine()) != null) {
                jr = new JSONArray(readLine);
            }
            in.close();

            //retrieve Key/Value pairs
            System.out.println("ID SERIAL NUMBER                               DRONE STATE      DATE CREATED                               DATE MODIFIED                         BATTERY LEVEL");
            for (int i = 0; i < jr.length(); i++) {
                JSONObject mJsonObject = jr.getJSONObject(i);

                Long id = mJsonObject.getLong("id");
                String serialNumber = mJsonObject.getString("serialNumber");
                String droneState = mJsonObject.getString("droneState");
                String dateCreated = mJsonObject.getString("dateCreated");
                String dateModified = mJsonObject.getString("dateModified");
                int batteryLevel = mJsonObject.getInt("batteryLevel");

                System.out.println(id +"  "+serialNumber + "        "+droneState+"             "+dateCreated+"              "       +dateModified+"         "+     batteryLevel );

            }


        }
    }
}

