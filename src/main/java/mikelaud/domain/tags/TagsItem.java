package mikelaud.domain.tags;

import java.util.Arrays;

import mikelaud.core.types.StringType;

public class TagsItem {

	private final Tag[] TAGS;
	
	private Tag[] createDefaultPredefinedTags() {
		final PredefinedTagType[] predefinedTagTypes = PredefinedTagType.values();
		final Tag[] tags = new Tag[predefinedTagTypes.length];
		Arrays.stream(predefinedTagTypes).forEach(predefinedTag -> {
			int tagIndex = 0;
			tags[tagIndex++] = predefinedTag.create(StringType.empty());
		});
		return tags;
	}

	private String getPredefinedTagValue(PredefinedTagType aPredefinedTagType) {
		return TAGS[aPredefinedTagType.getId()].getValue();
	}

	private void setPredefinedTagValue(PredefinedTagType aPredefinedTagType, String aValue) {
		TAGS[aPredefinedTagType.getId()] = aPredefinedTagType.create(aValue);
	}
	
	public TagsItem(Tag[] aTags, String aRelativePath) {
		TAGS = aTags;
	}

	public TagsItem(String aId, String aName, String aTitle, String aDescription) {
		TAGS = createDefaultPredefinedTags();
		setPredefinedTagValue(PredefinedTagType.Id, aId);
		setPredefinedTagValue(PredefinedTagType.Name, aName);
		setPredefinedTagValue(PredefinedTagType.Title, aTitle);
		setPredefinedTagValue(PredefinedTagType.Description, aDescription);
	}

	public Tag[] getTags() { return TAGS; }
	
	public String getId() { return getPredefinedTagValue(PredefinedTagType.Id); }
	public String getName() { return getPredefinedTagValue(PredefinedTagType.Name); }
	public String getTitle() { return getPredefinedTagValue(PredefinedTagType.Title); }
	public String getDescription() { return getPredefinedTagValue(PredefinedTagType.Description); }

	@Override
	public String toString() {
		return getName();
	}
	
}
