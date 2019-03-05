package com.example.gooddeedsbe.utils;

import com.example.gooddeedsbe.exceptions.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JobValidator {


    private static Pattern pattern;
    private static Matcher matcher;

    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private static final String PHONE_NUMBER_REGEX = "^\\+370[0-9]{8}";
    private static final String HASHTAG_REGEX_1 = ".*,$";
    private static final String HASHTAG_REGEX_2 = "#[A-Za-z0-9\\-\\.\\_]+";
    private static final String LETTER_AND_SPACES_REGEX = "^[A-Za-z ]+$";


    public static void validateTitle(String title) throws TitleInvalidException {

        if(title == null){
            throw new TitleInvalidException("Title can't be empty");
        }

        if(title.trim().length() == 0){
            throw new TitleInvalidException("Title can't be whitespace");
        }
        if (title.length() < 5 || title.length() > 50) {
            throw new TitleInvalidException("Title must be between 5 and 50 characters long");
        }
    }


    public static void validateOrganization(String organization) throws OrganizationInvalidException {
        if(StringValidator.checkIfNullOrWhitespace(organization))
            return;

        if (organization.length() < 5 || organization.length() > 50) {
            throw new OrganizationInvalidException("Organization name must be between 5 and 50 characters long");
        }
    }

    public static void validateCity(String city) throws CityInvalidException {

        if(city == null){
            throw new CityInvalidException("City can't be empty");
        }

        if(city.trim().length() == 0){
            throw new CityInvalidException("City can't be whitespace");
        }


        pattern = Pattern.compile(LETTER_AND_SPACES_REGEX, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(city);

        if (!matcher.matches()) {
            throw new CityInvalidException("City name must not contain numbers");
        }

        if (city.length() < 3 || city.length() > 50) {
            throw new CityInvalidException("City name must be between 3 and 50 characters long");
        }
    }

    public static void validateEmail(String email) throws EmailInvalidException {

        if(StringValidator.checkIfNullOrWhitespace(email))
            return;

        pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            throw new EmailInvalidException("Email must be valid");
        }
    }

    public static void validateContactPerson(String contactPerson) throws ContactPersonInvalidException {

        if(contactPerson == null){
            throw new ContactPersonInvalidException("Contact person field can't be empty");
        }

        if(contactPerson.trim().length() == 0){
            throw new ContactPersonInvalidException("Contact person field can't be whitespace");
        }

        pattern = Pattern.compile(LETTER_AND_SPACES_REGEX, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(contactPerson);

        if (!matcher.matches()) {
            throw new ContactPersonInvalidException("Contact person name must not contain numbers");
        }
    }


    public static void validatePhoneNumber(String phoneNumber) throws PhoneNumberInvalidException {

        if(StringValidator.checkIfNullOrWhitespace(phoneNumber))
            return;

        pattern = Pattern.compile(PHONE_NUMBER_REGEX, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(phoneNumber);

        if (!matcher.matches()) {
            throw new PhoneNumberInvalidException("Phone number must be valid");
        }
    }

    public static void validateDescription(String description) throws DescriptionInvalidException {

        if(StringValidator.checkIfNullOrWhitespace(description))
            return;

        if (description.length() > 500) {
            throw new DescriptionInvalidException("Description cannot exceed 500 characters");
        }
    }

    public static void validateTags(String tagString) throws TagInvalidException {

        if(StringValidator.checkIfNullOrWhitespace(tagString))
            return;

        if (tagString.length() > 500) {
            throw new TagInvalidException("Tags should not exceed 500 character");
        }

        pattern = Pattern.compile(" ", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(tagString);

        if (matcher.matches()) {
            throw new TagInvalidException("Tags cannot contain spaces");
        }

        pattern = Pattern.compile(HASHTAG_REGEX_1, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(tagString);

        if (matcher.matches()) {
            throw new TagInvalidException("Tags cannot end with ,");
        }

        String[] tags = tagString.split(",");

        pattern = Pattern.compile(HASHTAG_REGEX_2, Pattern.CASE_INSENSITIVE);

        for (String tag : tags) {
            matcher = pattern.matcher(tag);
            if (!matcher.matches()) {
                throw new TagInvalidException("Tags must be valid");
            }
        }
    }
}
