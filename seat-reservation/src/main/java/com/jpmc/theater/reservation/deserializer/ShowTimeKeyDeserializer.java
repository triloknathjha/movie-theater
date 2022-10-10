package com.jpmc.theater.reservation.deserializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.jpmc.theater.reservation.dto.ShowTimeRange;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.IOException;
import java.time.LocalTime;

public class ShowTimeKeyDeserializer extends KeyDeserializer {

    @Override
    public Object deserializeKey(final String key, final DeserializationContext ctxt ) throws IOException, JsonProcessingException
    {
        ShowTimeRange showTimeRange;
        String startTimeArray[],endTimeArray[];
        try {
            JSONObject requestKey = new JSONObject(key);
            startTimeArray = requestKey.getString("startTime").split(":");
            endTimeArray = requestKey.getString("endTime").split(":");
            showTimeRange = new ShowTimeRange(LocalTime.of(Integer.parseInt(startTimeArray[0]),Integer.parseInt(startTimeArray[1])),LocalTime.of(Integer.parseInt(endTimeArray[0]),Integer.parseInt(endTimeArray[1])));
        } catch (JSONException e) {
            showTimeRange = new ShowTimeRange();
        }
        return showTimeRange;
    }
}
