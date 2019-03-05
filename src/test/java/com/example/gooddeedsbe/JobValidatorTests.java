package com.example.gooddeedsbe;

import com.example.gooddeedsbe.exceptions.*;
import com.example.gooddeedsbe.model.Job;
import com.example.gooddeedsbe.utils.JobValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobValidatorTests {

	@Test
	public void contextLoads() {
	}

	@Test(expected = TitleInvalidException.class)
	public void check_if_title_valid_should_fail_too_short() throws TitleInvalidException{
		String title = "777";
		JobValidator.validateTitle(title);

	}

	@Test(expected = TitleInvalidException.class)
	public void check_if_title_valid_should_fail_0() throws TitleInvalidException{
		String title = " ";
		JobValidator.validateTitle(title);

	}

	@Test
	public void check_if_title_valid_should_pass() throws TitleInvalidException{
		String title = "Test title this";
		JobValidator.validateTitle(title);

	}

	@Test(expected = TitleInvalidException.class)
	public void check_if_title_valid_should_fail_too_long() throws TitleInvalidException{
		String title = "Test title thisashdbasifhsdafbdkjfbsajdfbkjasvdbkjfbasdjkhaskd pokas opaskdpo kaso pkdoaskdpkpok";
		JobValidator.validateTitle(title);

	}

@Test(expected = OrganizationInvalidException.class)
	public void check_if_organization_valid_should_fail_too_short() throws OrganizationInvalidException{
		String title = "777";
		JobValidator.validateOrganization(title);

	}

	@Test
	public void check_if_organization_valid_should_pass_0() throws OrganizationInvalidException{
		String title = "Test title this";
		JobValidator.validateOrganization(title);

	}

	@Test
	public void check_if_organization_valid_should_pass_1() throws OrganizationInvalidException{
		String title = "   ";
		JobValidator.validateOrganization(title);

	}

	@Test(expected = OrganizationInvalidException.class)
	public void check_if_organization_valid_should_fail_too_long() throws OrganizationInvalidException{
		String title = "Test title thisashdbasifhsdafbdkjfbsajdfbkjasvdbkjfbasdjkhaskd pokas opaskdpo kaso pkdoaskdpkpok";
		JobValidator.validateOrganization(title);
	}

	@Test(expected = CityInvalidException.class)
	public void check_if_city_valid_should_fail_0() throws CityInvalidException{
		String city = "1siauliai";
		JobValidator.validateCity(city);
	}

	@Test(expected = CityInvalidException.class)
	public void check_if_city_valid_should_fail_1() throws CityInvalidException{
		String city = "si";
		JobValidator.validateCity(city);
	}

	@Test(expected = CityInvalidException.class)
	public void check_if_city_valid_should_fail_2() throws CityInvalidException{
		String city = "sikasldnalksdnlasndlknasdlnaslkdnaslkdnlasndlaskndlknaslndlaksndlasknldnaslkdn";
		JobValidator.validateCity(city);
	}

	@Test(expected = CityInvalidException.class)
	public void check_if_city_valid_should_fail_3() throws CityInvalidException{
		String city = "_--=-=0=";
		JobValidator.validateCity(city);
	}

	@Test
	public void check_if_city_valid_should_pass_0() throws CityInvalidException{
		String city = "siauliai";
		JobValidator.validateCity(city);
	}

	@Test
	public void check_if_city_valid_should_pass_1() throws CityInvalidException {
		String city = "New York";
		JobValidator.validateCity(city);
	}

	@Test(expected = EmailInvalidException.class)
	public void check_if_email_valid_should_fail_0() throws EmailInvalidException {
		String email = "@asdasd.com";
		JobValidator.validateEmail(email);
	}

	@Test(expected = EmailInvalidException.class)
	public void check_if_email_valid_should_fail_1() throws EmailInvalidException {
		String email = "@asdasd";
		JobValidator.validateEmail(email);
	}

	@Test(expected = EmailInvalidException.class)
	public void check_if_email_valid_should_fail_2() throws EmailInvalidException {
		String email = "@";
		JobValidator.validateEmail(email);
	}

	@Test(expected = EmailInvalidException.class)
	public void check_if_email_valid_should_fail_3() throws EmailInvalidException {
		String email = "asdasd@";
		JobValidator.validateEmail(email);
	}

	@Test
	public void check_if_email_valid_should_pass_0() throws EmailInvalidException {
		String email = "test@asdasd.com";
		JobValidator.validateEmail(email);
	}

	@Test
	public void check_if_email_valid_should_pass_1() throws EmailInvalidException {
		String email = "zmoguszmogus@yahoo.net";
		JobValidator.validateEmail(email);
	}

	@Test(expected = ContactPersonInvalidException.class)
	public void check_if_contact_valid_should_fail_0() throws ContactPersonInvalidException{
		String city = "1siauliai";
		JobValidator.validateContactPerson(city);
	}

	@Test(expected = ContactPersonInvalidException.class)
	public void check_if_contact_valid_should_fail_3() throws ContactPersonInvalidException{
		String city = "_--=-=0=";
		JobValidator.validateContactPerson(city);
	}

	@Test
	public void check_if_contact_valid_should_pass_0() throws ContactPersonInvalidException{
		String city = "John Smithian";
		JobValidator.validateContactPerson(city);
	}

	@Test
	public void check_if_contact_valid_should_pass_1() throws ContactPersonInvalidException{
		String city = "Jane Doe";
		JobValidator.validateContactPerson(city);
	}

	@Test(expected = PhoneNumberInvalidException.class)
	public void check_if_phone_valid_should_fail_0 () throws PhoneNumberInvalidException {
		String number = "123";
		JobValidator.validatePhoneNumber(number);
	}

	@Test(expected = PhoneNumberInvalidException.class)
	public void check_if_phone_valid_should_fail_1 () throws PhoneNumberInvalidException {
		String number = "37069984737";
		JobValidator.validatePhoneNumber(number);
	}

	@Test(expected = PhoneNumberInvalidException.class)
	public void check_if_phone_valid_should_fail_2 () throws PhoneNumberInvalidException {
		String number = "+3706ddjrutk";
		JobValidator.validatePhoneNumber(number);
	}

	@Test
	public void check_if_phone_valid_should_pass_0 () throws PhoneNumberInvalidException {
		String number = "+37069958473";
		JobValidator.validatePhoneNumber(number);
	}

	@Test
	public void check_if_phone_valid_should_pass_1 () throws PhoneNumberInvalidException {
		String number = "+37051231233";
		JobValidator.validatePhoneNumber(number);
	}

	@Test(expected = DescriptionInvalidException.class)
	public void check_if_description_valid_should_fail_0 () throws DescriptionInvalidException{
		String description = "adsfkjhalksdjhflkashjdfkljahsdklfhjskldjfhklashdfkljashdkflhsakdfhjsakldjhfakshdflashdflkashdkfjhadsfkjhalksdjhflkashjdfkljahsdklfhjskldjfhklashdfkljashdkflhsakdfhjsakldjhfakshdflashdflkashdkfjhadsfkjhalksdjhflkashjdfkljahsdklfhjskldjfhklashdfkljashdkflhsakdfhjsakldjhfakshdflashdflkashdkfjhadsfkjhalksdjhflkashjdfkljahsdklfhjskldjfhklashdfkljashdkflhsakdfhjsakldjhfakshdflashdflkashdkfjhadsfkjhalksdjhflkashjdfkljahsdklfhjskldjfhklashdfkljashdkflhsakdfhjsakldjhfakshdflashdflkashdkfjhadsfkjhalksdjhflkashjdfkljahsdklfhjskldjfhklashdfkljashdkflhsakdfhjsakldjhfakshdflashdflkashdkfjh";
		JobValidator.validateDescription(description);
	}

	@Test
	public void check_if_description_valid_should_pass () throws DescriptionInvalidException{
		String description = "asdasdasdasdasd dasdasd asdasdasd";
		JobValidator.validateDescription(description);
	}

	@Test(expected = TagInvalidException.class)
	public void check_if_tags_valid_should_fail_0 () throws TagInvalidException {
		String tags = "123456";
		JobValidator.validateTags(tags);
	}

	@Test(expected = TagInvalidException.class)
	public void check_if_tags_valid_should_fail_1 () throws TagInvalidException {
		String tags = "#";
		JobValidator.validateTags(tags);
	}

	@Test(expected = TagInvalidException.class)
	public void check_if_tags_valid_should_fail_2 () throws TagInvalidException {
		String tags = "#asdasd ";
		JobValidator.validateTags(tags);
	}

	@Test(expected = TagInvalidException.class)
	public void check_if_tags_valid_should_fail_3 () throws TagInvalidException {
		String tags = "#asdasd,";
		JobValidator.validateTags(tags);
	}

	@Test
	public void check_if_tags_valid_should_pass_0 () throws TagInvalidException {
		String tags = "#asdasd";
		JobValidator.validateTags(tags);
	}

	@Test
	public void check_if_tags_valid_should_pass_1 () throws TagInvalidException {
		String tags = "#asdasd,#dwwdawdwd";
		JobValidator.validateTags(tags);
	}

	@Test
	public void check_if_tags_valid_should_pass_2 () throws TagInvalidException {
		String tags = "#asdasd,#dwwdawdwd,#asdasd,#dwwdawdwddawdwfeagsrgsge";
		JobValidator.validateTags(tags);
	}

}
