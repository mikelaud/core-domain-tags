package mikelaud.domain.tags;

import java.util.Objects;

import javafx.beans.property.SimpleStringProperty;
import mikelaud.core.types.StringType;

public class MetaTagModel implements Comparable<MetaTagModel> {

	private final SimpleStringProperty NAME = new SimpleStringProperty();
	
	public MetaTagModel(String aName) {
		NAME.set(StringType.nvl(aName));
	}
	
	public SimpleStringProperty nameProperty() { return NAME; }

	public String getName() { return NAME.get(); }

	public void setName(String aName) { NAME.set(StringType.nvl(aName)); }

	public void unbind() {
		NAME.unbind();
	}
	
	@Override
	public String toString() {
		return String.format("%s('%s')", MetaTagModel.class.getSimpleName(), getName());
	}

	@Override
	public int compareTo(MetaTagModel aOther) {
		if (null == aOther) return 1;
		return getName().compareTo(aOther.getName());
	}
	
	@Override
	public boolean equals(Object aOther) {
		if (this == aOther) return true;
		if (!(aOther instanceof MetaTagModel)) return false;
		return Objects.equals(getName(), ((MetaTagModel)aOther).getName());
	}
	
	@Override
    public int hashCode() {
		return getName().hashCode();
	}

}
