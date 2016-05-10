package validation;

import com.ai.paas.ipaas.validator.StringEnum;

public class User {
	private String name;

	@StringEnum(enumClazz = GenderEnum.class, message = "error gender!")
	private String gender;

	public User(String name, String gender) {
		this.name = name;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
