package id.jagokoding;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.beans.PropertyEditorSupport;

public class XmlUserCustomEditorExample {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		Customer bean = context.getBean(Customer.class);
		System.out.println(bean);
	}

	public static class Customer {
		private String customerName;
		private Phone phone;

		public String getCustomerName() {
			return customerName;
		}

		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}

		public Phone getPhone() {
			return phone;
		}

		public void setPhone(Phone phone) {
			this.phone = phone;
		}

		@Override
		public String toString() {
			return "Customer{" + "customerName='" + customerName + '\'' + ", phone=" + phone + '}';
		}
	}

	public static class Phone {
		private String phoneNumber;
		private PhoneType phoneType;

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public PhoneType getPhoneType() {
			return phoneType;
		}

		public void setPhoneType(PhoneType phoneType) {
			this.phoneType = phoneType;
		}

		@Override
		public String toString() {
			return "Phone{" + "phoneNumber='" + phoneNumber + '\'' + ", phoneType=" + phoneType + '}';
		}
	}

	public static enum PhoneType {
		LAND, CELL, WORK
	}

	public static class CustomPhoneEditor extends PropertyEditorSupport {

		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			String[] split = text.split("[|]");
			if (split.length != 2) {
				throw new IllegalArgumentException(
						"Telepon tidak ditentukan dengan benar. Format yang benar adalah " + "PhoneType|1111-1111-1111");
			}
			// metode ini sudah menampilkan IllegalArgumentException
			// jadi jangan khawatir tentang pembagian [0] nilai enum yang tidak valid.
			PhoneType phoneType = PhoneType.valueOf(split[0].trim().toUpperCase());
			Phone phone = new Phone();
			phone.setPhoneType(phoneType);
			phone.setPhoneNumber(split[1].trim());
			setValue(phone);

		}
	}
}