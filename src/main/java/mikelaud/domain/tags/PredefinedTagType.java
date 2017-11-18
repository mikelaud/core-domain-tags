package mikelaud.domain.tags;

import mikelaud.core.types.StringType;

public enum PredefinedTagType {

	Id(0, "ID"),
	Name(1, "Name"),
	Title(2, "Title"),
	Description(3, "Description");

	private final TagType TAG_TYPE;

	private PredefinedTagType(int aId, String aName) {
		TAG_TYPE = new TagType(aId, aName);
	}

	public TagType get() { return TAG_TYPE; }

	public int getId() { return TAG_TYPE.getId(); }
	public String getName() { return TAG_TYPE.getName(); }
	public TagKind getKind() { return TAG_TYPE.getKind(); }
	
	public Tag create(String aValue) {
		return new Tag(TAG_TYPE.getId(), StringType.nvl(aValue));
	}
	
	@Override
	public String toString() {
		return String.format("%s(%s)", PredefinedTagType.class.getSimpleName(), name());
	}

}
