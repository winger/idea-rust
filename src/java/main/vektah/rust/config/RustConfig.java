package vektah.rust.config;

public class RustConfig {
	private String cargoBinary;

	public RustConfig() {
		cargoBinary = "cargo";
	}

	public String getCargoBinary() {
		return cargoBinary;
	}

	public void setCargoBinary(String cargoBinary) {
		this.cargoBinary = cargoBinary;
	}
}
