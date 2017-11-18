package mikelaud.domain.tags;

import mikelaud.core.types.StringType;

public class Tag {

	private final int ID;
	private final String VALUE;

	public Tag(int aId, String aValue) {
		ID = aId;
		VALUE = StringType.nvl(aValue);
	}

	public int getId() { return ID; }
	public String getValue() { return VALUE; }

	@Override
	public String toString() {
		return String.format("%s(%d,'%s')", Tag.class.getSimpleName(), getId(), getValue());
	}

}
