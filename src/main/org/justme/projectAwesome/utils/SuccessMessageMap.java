package justme.projectAwesome.utils;

import justme.projectAwesome.utils.constants.SuccessMessages;

import java.util.HashMap;
import java.util.Map;

public abstract class SuccessMessageMap {
    private static final Map<String, String> successMessageMap = new HashMap<>();

    static {
        successMessageMap.put("user-image-edit",SuccessMessages.SUCCESSFULLY_EDITED_USER_PIC);
        successMessageMap.put("add-comment",SuccessMessages.ADDED_COMMENT);
        successMessageMap.put("remove-comment",SuccessMessages.REMOVED_COMMENT);
        successMessageMap.put("add-review",SuccessMessages.ADDED_REVIEW);
        successMessageMap.put("remove-review",SuccessMessages.REMOVED_REVIEW);
        successMessageMap.put("add-product", SuccessMessages.ADD_PRODUCT);
        successMessageMap.put("remove-product", SuccessMessages.REMOVE_PRODUCT);
        successMessageMap.put("add-category", SuccessMessages.ADD_CATEGORY);
        successMessageMap.put("remove-category", SuccessMessages.REMOVE_CATEGORY);
    }

    public static String get(String mapEntry) {
        return successMessageMap.get(mapEntry);
    }
}
