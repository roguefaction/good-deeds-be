package com.example.gooddeeds;

import com.example.gooddeeds.exceptions.*;
import com.example.gooddeeds.utils.DeedValidator;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeedValidatorTests {

	@Test(expected = TitleInvalidException.class)
	public void check_if_title_valid_should_fail_too_short() throws TitleInvalidException{
		String title = "777";
		DeedValidator.validateTitle(title);

	}

	@Test(expected = TitleInvalidException.class)
	public void check_if_title_valid_should_fail_0() throws TitleInvalidException{
		String title = " ";
		DeedValidator.validateTitle(title);

	}

	@Test
	public void check_if_title_valid_should_pass() throws TitleInvalidException{
		String title = "Test title this";
		DeedValidator.validateTitle(title);

	}

	@Test(expected = TitleInvalidException.class)
	public void check_if_title_valid_should_fail_too_long() throws TitleInvalidException{
		String title = "Test title thisashdbasifhsdafbdkjfbsajdfbkjasvdbkjfbasdjkhaskd pokas opaskdpo kaso pkdoaskdpkpok";
		DeedValidator.validateTitle(title);

	}

@Test(expected = OrganizationInvalidException.class)
	public void check_if_organization_valid_should_fail_too_short() throws OrganizationInvalidException{
		String title = "777";
		DeedValidator.validateOrganization(title);

	}

	@Test
	public void check_if_organization_valid_should_pass_0() throws OrganizationInvalidException{
		String title = "Test title this";
		DeedValidator.validateOrganization(title);

	}

	@Test
	public void check_if_organization_valid_should_pass_1() throws OrganizationInvalidException{
		String title = "   ";
		DeedValidator.validateOrganization(title);

	}

	@Test(expected = OrganizationInvalidException.class)
	public void check_if_organization_valid_should_fail_too_long() throws OrganizationInvalidException{
		String title = "Test title thisashdbasifhsdafbdkjfbsajdfbkjasvdbkjfbasdjkhaskd pokas opaskdpo kaso pkdoaskdpkpok";
		DeedValidator.validateOrganization(title);
	}

	@Test(expected = CityInvalidException.class)
	public void check_if_city_valid_should_fail_0() throws CityInvalidException{
		String city = "1siauliai";
		DeedValidator.validateCity(city);
	}

	@Test(expected = CityInvalidException.class)
	public void check_if_city_valid_should_fail_1() throws CityInvalidException{
		String city = "si";
		DeedValidator.validateCity(city);
	}

	@Test(expected = CityInvalidException.class)
	public void check_if_city_valid_should_fail_2() throws CityInvalidException{
		String city = "sikasldnalksdnlasndlknasdlnaslkdnaslkdnlasndlaskndlknaslndlaksndlasknldnaslkdn";
		DeedValidator.validateCity(city);
	}

	@Test(expected = CityInvalidException.class)
	public void check_if_city_valid_should_fail_3() throws CityInvalidException{
		String city = "_--=-=0=";
		DeedValidator.validateCity(city);
	}

	@Test
	public void check_if_city_valid_should_pass_0() throws CityInvalidException{
		String city = "siauliai";
		DeedValidator.validateCity(city);
	}

	@Test
	public void check_if_city_valid_should_pass_1() throws CityInvalidException {
		String city = "New York";
		DeedValidator.validateCity(city);
	}

	@Test(expected = EmailInvalidException.class)
	public void check_if_email_valid_should_fail_0() throws EmailInvalidException {
		String email = "@asdasd.com";
		DeedValidator.validateEmail(email);
	}

	@Test(expected = EmailInvalidException.class)
	public void check_if_email_valid_should_fail_1() throws EmailInvalidException {
		String email = "@asdasd";
		DeedValidator.validateEmail(email);
	}

	@Test(expected = EmailInvalidException.class)
	public void check_if_email_valid_should_fail_2() throws EmailInvalidException {
		String email = "@";
		DeedValidator.validateEmail(email);
	}

	@Test(expected = EmailInvalidException.class)
	public void check_if_email_valid_should_fail_3() throws EmailInvalidException {
		String email = "asdasd@";
		DeedValidator.validateEmail(email);
	}

	@Test
	public void check_if_email_valid_should_pass_0() throws EmailInvalidException {
		String email = "test@asdasd.com";
		DeedValidator.validateEmail(email);
	}

	@Test
	public void check_if_email_valid_should_pass_1() throws EmailInvalidException {
		String email = "zmoguszmogus@yahoo.net";
		DeedValidator.validateEmail(email);
	}

	@Test(expected = ContactPersonInvalidException.class)
	public void check_if_contact_valid_should_fail_0() throws ContactPersonInvalidException{
		String city = "1siauliai";
		DeedValidator.validateContactPerson(city);
	}

	@Test(expected = ContactPersonInvalidException.class)
	public void check_if_contact_valid_should_fail_3() throws ContactPersonInvalidException{
		String city = "_--=-=0=";
		DeedValidator.validateContactPerson(city);
	}

	@Test
	public void check_if_contact_valid_should_pass_0() throws ContactPersonInvalidException{
		String city = "John Smithian";
		DeedValidator.validateContactPerson(city);
	}

	@Test
	public void check_if_contact_valid_should_pass_1() throws ContactPersonInvalidException{
		String city = "Jane Doe";
		DeedValidator.validateContactPerson(city);
	}

	@Test(expected = PhoneNumberInvalidException.class)
	public void check_if_phone_valid_should_fail_0 () throws PhoneNumberInvalidException {
		String number = "123";
		DeedValidator.validatePhoneNumber(number);
	}

	@Test(expected = PhoneNumberInvalidException.class)
	public void check_if_phone_valid_should_fail_1 () throws PhoneNumberInvalidException {
		String number = "37069984737";
		DeedValidator.validatePhoneNumber(number);
	}

	@Test(expected = PhoneNumberInvalidException.class)
	public void check_if_phone_valid_should_fail_2 () throws PhoneNumberInvalidException {
		String number = "+3706ddjrutk";
		DeedValidator.validatePhoneNumber(number);
	}

	@Test
	public void check_if_phone_valid_should_pass_0 () throws PhoneNumberInvalidException {
		String number = "+37069958473";
		DeedValidator.validatePhoneNumber(number);
	}

	@Test
	public void check_if_phone_valid_should_pass_1 () throws PhoneNumberInvalidException {
		String number = "+37051231233";
		DeedValidator.validatePhoneNumber(number);
	}

	@Test(expected = DescriptionInvalidException.class)
	public void check_if_description_valid_should_fail_0 () throws DescriptionInvalidException{
		String description = "adsfkjhalksdjhflkashjdfkljahsdklfhjskldjfhklashdfkljashdkflhsakdfhjsakldjhfakshdflashdflkashdkfjhadsfkjhalksdjhflkashjdfkljahsdklfhjskldjfhklashdfkljashdkflhsakdfhjsakldjhfakshdflashdflkashdkfjhadsfkjhalksdjhflkashjdfkljahsdklfhjskldjfhklashdfkljashdkflhsakdfhjsakldjhfakshdflashdflkashdkfjhadsfkjhalksdjhflkashjdfkljahsdklfhjskldjfhklashdfkljashdkflhsakdfhjsakldjhfakshdflashdflkashdkfjhadsfkjhalksdjhflkashjdfkljahsdklfhjskldjfhklashdfkljashdkflhsakdfhjsakldjhfakshdflashdflkashdkfjhadsfkjhalksdjhflkashjdfkljahsdklfhjskldjfhklashdfkljashdkflhsakdfhjsakldjhfakshdflashdflkashdkfjh";
		DeedValidator.validateDescription(description);
	}

	@Test
	public void check_if_description_valid_should_pass () throws DescriptionInvalidException{
		String description = "asdasdasdasdasd dasdasd asdasdasd";
		DeedValidator.validateDescription(description);
	}

	@Test(expected = TagInvalidException.class)
	public void check_if_tags_valid_should_fail_0 () throws TagInvalidException {
		String tags = "123456";
		DeedValidator.validateTags(tags);
	}

	@Test(expected = TagInvalidException.class)
	public void check_if_tags_valid_should_fail_1 () throws TagInvalidException {
		String tags = "#";
		DeedValidator.validateTags(tags);
	}

	@Test(expected = TagInvalidException.class)
	public void check_if_tags_valid_should_fail_2 () throws TagInvalidException {
		String tags = "#asdasd ";
		DeedValidator.validateTags(tags);
	}

	@Test(expected = TagInvalidException.class)
	public void check_if_tags_valid_should_fail_3 () throws TagInvalidException {
		String tags = "#asdasd,";
		DeedValidator.validateTags(tags);
	}

	@Test
	public void check_if_tags_valid_should_pass_0 () throws TagInvalidException {
		String tags = "#asdasd";
		DeedValidator.validateTags(tags);
	}

	@Test
	public void check_if_tags_valid_should_pass_1 () throws TagInvalidException {
		String tags = "#asdasd,#dwwdawdwd";
		DeedValidator.validateTags(tags);
	}

	@Test
	public void check_if_tags_valid_should_pass_2 () throws TagInvalidException {
		String tags = "#asdasd,#dwwdawdwd,#asdasd,#dwwdawdwddawdwfeagsrgsge";
		DeedValidator.validateTags(tags);
	}

}
