package resus.licenseengine.app.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class License {

	@JsonProperty(access = Access.READ_ONLY)
	private String id;
	@JsonProperty(access = Access.READ_ONLY)
	private String name;
	@JsonProperty(access = Access.READ_ONLY)
	private String notes;
	@JsonProperty(value = "further-information", access = Access.READ_ONLY)
	private ArrayList<String> furtherInformation;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * @return the furtherInformation
	 */
	public ArrayList<String> getFurtherInformation() {
		return furtherInformation;
	}

	/**
	 * @param furtherInformation the furtherInformation to set
	 */
	public void setFurtherInformation(ArrayList<String> furtherInformation) {
		this.furtherInformation = furtherInformation;
	}

}
