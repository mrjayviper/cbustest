package com.cbus.aem.core.utils;

import com.cbus.aem.core.data.Owner;
import com.cbus.aem.core.data.Pet;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class PetsUtils {
    private static Logger log = LoggerFactory.getLogger(PetsUtils.class);

    public static List<Owner> parseJsonObject(final String sourceApiResponse) {
        //Intentionally left empty since the source API data is an array

        return new ArrayList<> ();
    }

    public static List<Owner> parseJsonArray(final String sourceApiResponse) {
        //List<Owner> ownersList = new ArrayList<Owner> ();
        try {
            return new ObjectMapper().readValue(sourceApiResponse, new TypeReference<List<Owner>>(){});
        } catch (Exception exception){
            log.error("PetsServlet/parseJsonArray - error in servlet", exception);
        }

        return new ArrayList<> ();
    }

    public static String getAllPetsFilterByType(final List<Owner> ownerList, final String type) {
        List<Pet> petList = new ArrayList<>();

        String jsonResponse = StringUtils.EMPTY;

        ownerList
            .forEach(owner -> {
                for (int i=0; i < owner.numberOfPets(); i++) {
                    if (owner.getPets()[i].getType().toUpperCase().equalsIgnoreCase(type)) {
                        petList.add(
                            new Pet(
                                owner.getName()
                                , owner.getGenderId()
                                , owner.getPets()[i].getName()
                                , owner.getPets()[i].getType().toUpperCase()
                            )
                        );
                    }
                }
            });

        try {
            jsonResponse =new ObjectMapper().writeValueAsString(
                petList
                    .stream()
                    .sorted(Comparator.comparing(Pet::getName))
                    .collect(Collectors.groupingBy(Pet::getOwnerGender))
            );

        } catch (Exception exception) {
            log.error("PetsUtils/getAllPetsFilterByType - error in code | type: " + type);
        }

        return jsonResponse;
    }
}
