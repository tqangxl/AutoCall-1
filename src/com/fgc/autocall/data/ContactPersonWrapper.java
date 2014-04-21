package com.fgc.autocall.data;

public class ContactPersonWrapper {
	private static final String DEFAULT_MESSAGE_FORMAT = "%���ã���Ԥ������Ʒ%����2014��%�ϼ����ۣ��۸�Ϊ%Ԫ�������ע��лл��";
	private ContactPerson mContactPerson;
	private String mMessageFormat = DEFAULT_MESSAGE_FORMAT;
	
	public ContactPersonWrapper(ContactPerson contactPerson)
	{
		mContactPerson = contactPerson;
	}
	
	public ContactPerson getContactPerson()
	{
		return mContactPerson;
	}
	
	public String generateShortMessage()
	{
		mMessageFormat = mMessageFormat.replaceFirst("%", mContactPerson.getName());
		mMessageFormat = mMessageFormat.replaceFirst("%", mContactPerson.getNote1());
		mMessageFormat = mMessageFormat.replaceFirst("%", mContactPerson.getNote2());
		mMessageFormat = mMessageFormat.replaceFirst("%", mContactPerson.getNote3());
		
		return mMessageFormat;
	}
	
	public void setMessageFormat(String format)
	{
		mMessageFormat = format;
	}
}
