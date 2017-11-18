package mikelaud.domain.tags;

import java.util.Objects;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import mikelaud.core.types.StringType;

public class TagModel implements Comparable<TagModel> {

	private final ReadOnlyStringWrapper NAME = new ReadOnlyStringWrapper();
	private final SimpleStringProperty VALUE = new SimpleStringProperty();
	
	public TagModel(String aName, String aValue) {
		NAME.set(StringType.nvl(aName));
		setValue(aValue);
	}
	
	public ReadOnlyStringProperty nameProperty() { return NAME.getReadOnlyProperty(); }
	public SimpleStringProperty valueProperty() { return VALUE; }

	public String getName() { return NAME.get(); }
	public String getValue() { return VALUE.get(); }

	public void setValue(String aValue) { VALUE.set(StringType.nvl(aValue)); }

	public void clear() {
		setValue(StringType.empty());
	}
	
	public void unbind() {
		NAME.unbind();
		VALUE.unbind();
	}
	
	@Override
	public String toString() {
		return String.format("%s('%s','%s')", TagModel.class.getSimpleName(), getName(), getValue());
	}

	@Override
	public int compareTo(TagModel aOther) {
		if (null == aOther) return 1;
		return getName().compareTo(aOther.getName());
	}
	
	@Override
	public boolean equals(Object aOther) {
		if (this == aOther) return true;
		if (!(aOther instanceof TagModel)) return false;
		return Objects.equals(getName(), ((TagModel)aOther).getName());
	}
	
	@Override
    public int hashCode() {
		return getName().hashCode();
	}

}
