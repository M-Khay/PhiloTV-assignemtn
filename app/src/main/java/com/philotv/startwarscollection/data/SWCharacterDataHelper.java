package com.philotv.startwarscollection.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing content for List of Characters
 */
public class SWCharacterDataHelper {

    public static List<SWCharacter> getCharacterList() {
        return characterList;
    }

    public static void setCharacterList(List<SWCharacter> characterList) {
        SWCharacterDataHelper.characterList = characterList;
    }

    private static List<SWCharacter> characterList = new ArrayList<SWCharacter>();


    private static final int COUNT = 25;

    static {
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(SWCharacter item) {
        characterList.add(item);
    }

    private static SWCharacter createDummyItem(int position) {
        return new SWCharacter(String.valueOf(position), "Item " + position,
                "Replace with Data fetched from API");
    }


}
