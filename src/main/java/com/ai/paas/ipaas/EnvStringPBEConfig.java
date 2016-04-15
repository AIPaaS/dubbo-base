package com.ai.paas.ipaas;

import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;

public class EnvStringPBEConfig extends EnvironmentStringPBEConfig {
	private String passwordEnvName = null;
	private String passwordSysPropertyName = null;
	private String password = null;
	private volatile boolean hasPassword = false;

	public String getPasswordEnvName() {
		return passwordEnvName;
	}

	public void setPasswordEnvName(String passwordEnvName) {
		if (passwordEnvName != null && System.getenv(passwordEnvName) != null
				&& !hasPassword) {
			this.passwordEnvName = passwordEnvName;
			this.passwordSysPropertyName = null;
			super.setPassword(System.getenv(passwordEnvName));
			hasPassword = true;
		}
	}

	public String getPasswordSysPropertyName() {
		return passwordSysPropertyName;
	}

	public void setPasswordSysPropertyName(String passwordSysPropertyName) {
		this.passwordSysPropertyName = passwordSysPropertyName;
		if (passwordSysPropertyName != null
				&& System.getProperty(passwordSysPropertyName) != null
				&& !hasPassword) {
			this.passwordEnvName = null;
			super.setPassword(System.getProperty(passwordSysPropertyName));
			hasPassword = true;
		}
	}

	public void setPassword(String password) {
		if (password != null && !hasPassword) {
			this.password = password;
			super.setPassword(this.password);
		}
	}

}
