package mikelaud.domain.tags;

public enum TagKind {

	Predefined,
	Custom;
	
	public static TagKind get(int aTagId) {
		return (aTagId < 0 ? TagKind.Custom : TagKind.Predefined);
	}
	
}
