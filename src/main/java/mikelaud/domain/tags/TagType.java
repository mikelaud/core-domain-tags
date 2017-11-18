package mikelaud.domain.tags;

import mikelaud.core.types.StringType;

public class TagType {

	private final int ID;
	private final String NAME;

	public TagType(int aId, String aName) {
		ID = aId;
		NAME = StringType.nvl(aName);
	}

	public int getId() { return ID; }
	public String getName() { return NAME; }
	public TagKind getKind() { return TagKind.get(ID); }

	@Override
	public String toString() {
		return String.format("%s(%s, %d,'%s')", TagType.class.getSimpleName(), getKind().toString(), getId(), getName());
	}

}
