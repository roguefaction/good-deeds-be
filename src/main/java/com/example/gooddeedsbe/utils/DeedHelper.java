package com.example.gooddeedsbe.utils;

import com.example.gooddeedsbe.exceptions.InvalidFieldException;
import com.example.gooddeedsbe.model.Deed;

public class DeedHelper {
    public static void validateDeed(Deed deed) throws InvalidFieldException {
        DeedValidator.validateTitle(deed.getTitle());
        DeedValidator.validateCity(deed.getCity());
        DeedValidator.validateContactPerson(deed.getContactPerson());
        DeedValidator.validatePhoneNumber(deed.getPhoneNumber());
        DeedValidator.validateEmail(deed.getEmail());
        DeedValidator.validateOrganization(deed.getOrganization());
        DeedValidator.validateDescription(deed.getDescription());
        DeedValidator.validateTags(deed.getTags());
    }
}
