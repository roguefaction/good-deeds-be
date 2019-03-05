package com.example.gooddeedsbe.utils;

import com.example.gooddeedsbe.exceptions.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JobValidator {


    private static Pattern pattern;
    private static Matcher matcher;

    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private static final String NUMBER_REGEX = "[0-9]";
    private static final String PHONE_NUMBER_REGEX = "^\\+370[0-9]{8}";
    private static final String HASHTAG_REGEX = "#[A-Za-z0-9\\-\\.\\_]+";


    static void validateTitle(String title) throws TitleInvalidException {

        if (title.length() < 5 || title.length() > 50) {
            throw new TitleInvalidException("Title must be between 5 and 50 characters long");
        }
    }


    static void validateOrganization(String organization) throws OrganizationInvalidException {

        if (organization.length() < 5 || organization.length() > 50) {
            throw new OrganizationInvalidException("Organization name must be between 5 and 50 characters long");
        }
    }

    static void validateCity(String city) throws CityInvalidException {

        pattern = Pattern.compile(NUMBER_REGEX, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(city);

        if (matcher.matches()) {
            throw new CityInvalidException("City name must not contain numbers");
        }

        if (city.length() < 3 || city.length() > 50) {
            throw new CityInvalidException("City name must be between 3 and 50 characters long");
        }
    }

    static void validateEmail(String email) throws EmailInvalidException {

        pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            throw new EmailInvalidException("Email must be valid");
        }
    }

    static void validateContactPerson(String contactPerson) throws ContactPersonInvalidException {

        pattern = Pattern.compile(NUMBER_REGEX, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(contactPerson);

        if (matcher.matches()) {
            throw new ContactPersonInvalidException("Contact person name must not contain numbers");
        }
    }


    static void validatePhoneNumber(String phoneNumber) throws PhoneNumberInvalidException {

        pattern = Pattern.compile(PHONE_NUMBER_REGEX, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(phoneNumber);

        if (!matcher.matches()) {
            throw new PhoneNumberInvalidException("Phone number bus be valid");
        }
    }

    static void validateDescription(String description) throws DescriptionInvalidException {

        if (description.length() > 500) {
            throw new DescriptionInvalidException("Description cannot exceed 500 characters");
        }
    }

    static void validateTags(String tagString) throws TagInvalidException {

        if (tagString.length() > 500) {
            throw new TagInvalidException("Tags should not exceed 500 character");
        }

        pattern = Pattern.compile(" ", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(tagString);

        if (matcher.matches()) {
            throw new TagInvalidException("Tags cannot contain spaces");
        }

        String[] tags = tagString.split(",");

        pattern = Pattern.compile(HASHTAG_REGEX, Pattern.CASE_INSENSITIVE);

        for (String tag : tags) {
            matcher = pattern.matcher(tag);
            if (!matcher.matches()) {
                throw new TagInvalidException("Tags must be valid");
            }
        }
    }
}
