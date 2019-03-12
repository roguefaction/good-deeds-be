package com.example.gooddeeds.utils;

import com.example.gooddeeds.exceptions.*;
import com.example.gooddeeds.model.Deed;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeedValidator {

    private static Pattern pattern;
    private static Matcher matcher;

    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private static final String PHONE_NUMBER_REGEX = "^\\+370[0-9]{8}";
    private static final String HASHTAG_REGEX_1 = ".*,$";
    private static final String HASHTAG_REGEX_2 = "#[A-Za-z0-9\\-\\.\\_]+";
    private static final String LETTER_AND_SPACES_REGEX = "([^\\x00-\\x7F]*[a-zA-Z\\s]*)*";
    private static final String DATE_FORMAT = "yyyy-MM-dd";


    public static void validateTitle(String title) throws TitleInvalidException {

        if (title == null || title.trim().length() == 0) {
            throw new TitleInvalidException("Title can't be empty");
        }

        if (title.length() < 5 || title.length() > 50) {
            throw new TitleInvalidException("Title must be between 5 and 50 characters long");
        }
    }


    public static void validateOrganization(String organization) throws OrganizationInvalidException {
        if (StringUtils.isBlank(organization)) {
            return;
        }

        if (organization.length() < 5 || organization.length() > 50) {
            throw new OrganizationInvalidException("Organization name must be between 5 and 50 characters long");
        }
    }

    public static void validateCity(String city) throws CityInvalidException {

        if (city == null || city.trim().length() == 0) {
            throw new CityInvalidException("City can't be empty");
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

    public static void validateDate(String date) throws DateInvalidException {

        if (date == null) {
            throw new DateInvalidException("Date must be entered");
        }

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);

        try {
            sdf.parse(date);
        } catch (ParseException e) {
            throw new DateInvalidException("Date must be valid");
        }
    }

    public static void validateEmail(String email) throws EmailInvalidException {

        if (StringUtils.isBlank(email))
            throw new EmailInvalidException("Email must be entered");

        pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            throw new EmailInvalidException("Email must be valid");
        }
    }

    public static void validateContactPerson(String contactPerson) throws ContactPersonInvalidException {

        if (contactPerson == null) {
            throw new ContactPersonInvalidException("Contact person field can't be empty");
        }

        if (contactPerson.trim().length() == 0) {
            throw new ContactPersonInvalidException("Contact person field can't be whitespace");
        }

        pattern = Pattern.compile(LETTER_AND_SPACES_REGEX, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(contactPerson);

        if (!matcher.matches()) {
            throw new ContactPersonInvalidException("Contact person name must not contain numbers");
        }
    }


    public static void validatePhoneNumber(String phoneNumber) throws PhoneNumberInvalidException {

        if (StringUtils.isBlank(phoneNumber))
            throw new PhoneNumberInvalidException("Phone number field must not be empty");


        pattern = Pattern.compile(PHONE_NUMBER_REGEX, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(phoneNumber);

        if (!matcher.matches()) {
            throw new PhoneNumberInvalidException("Phone number must be valid");
        }
    }

    public static void validateDescription(String description) throws DescriptionInvalidException {

        if (StringUtils.isBlank(description))
            return;

        if (description.length() > 500) {
            throw new DescriptionInvalidException("Description cannot exceed 500 characters");
        }
    }

    public static void validateTags(String tagString) throws TagInvalidException {

        if (StringUtils.isBlank(tagString))
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

    public static void validateDeed(Deed deed) throws InvalidFieldException {
        DeedValidator.validateTitle(deed.getTitle());
        DeedValidator.validateCity(deed.getCity());
        DeedValidator.validateDate(deed.getDate());
        DeedValidator.validateContactPerson(deed.getContactPerson());
        DeedValidator.validatePhoneNumber(deed.getPhoneNumber());
        DeedValidator.validateEmail(deed.getEmail());
        DeedValidator.validateOrganization(deed.getOrganization());
        DeedValidator.validateDescription(deed.getDescription());
        DeedValidator.validateTags(deed.getTags());
    }
}
