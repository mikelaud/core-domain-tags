package mikelaud.domain.tags;

import java.util.Arrays;
import java.util.Comparator;

import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.property.ReadOnlyMapProperty;
import javafx.beans.property.ReadOnlyMapWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import mikelaud.core.types.StringType;

public class TagsModel {

	private final ReadOnlyMapWrapper<Integer, TagType> TAG_TYPES_PROPERTY;
	private final ReadOnlyMapWrapper<Integer, TagModel> PREDEFINED_TAGS_PROPERTY;
	private final ReadOnlyListWrapper<TagModel> CUSTOM_TAGS_PROPERTY;

	public TagsModel() {
		TAG_TYPES_PROPERTY = new ReadOnlyMapWrapper<>(FXCollections.observableHashMap());
		PREDEFINED_TAGS_PROPERTY = new ReadOnlyMapWrapper<>(FXCollections.observableHashMap());
		CUSTOM_TAGS_PROPERTY = new ReadOnlyListWrapper<>(FXCollections.observableArrayList());
		//
		for (PredefinedTagType predefinedTag : PredefinedTagType.values()) {
			final TagType tagType = predefinedTag.get();
			final int tagId = tagType.getId();
			if (null != TAG_TYPES_PROPERTY.putIfAbsent(tagId, tagType)) {
				System.out.println(String.format("%s already exists (id=%d)", TagType.class.getSimpleName(), tagId));
			}
			if (null != PREDEFINED_TAGS_PROPERTY.put(tagId, new TagModel(tagType.getName(), StringType.empty()))) {
				System.out.println(String.format("%s already exists (id=%d)", PredefinedTagType.class.getSimpleName(), tagId));
			}
		}
	}
	
	public ReadOnlyMapProperty<Integer, TagType> tagTypesProperty() { return TAG_TYPES_PROPERTY.getReadOnlyProperty(); }
	public ReadOnlyMapProperty<Integer, TagModel> predefinedTagsProperty() { return PREDEFINED_TAGS_PROPERTY.getReadOnlyProperty(); }
	public ReadOnlyListProperty<TagModel> customTagsProperty() { return CUSTOM_TAGS_PROPERTY.getReadOnlyProperty(); }
	
	public ObservableMap<Integer, TagType> getTagTypes() { return TAG_TYPES_PROPERTY.get(); }
	public ObservableMap<Integer, TagModel> getPredefinedTags() { return PREDEFINED_TAGS_PROPERTY.get(); }
	public ObservableList<TagModel> getCustomTags() { return CUSTOM_TAGS_PROPERTY.get(); }

	public TagModel getPredefinedTag(int aTagType) { return getPredefinedTags().get(aTagType); }
	public TagModel getPredefinedTag(PredefinedTagType aPredefinedTag) { return getPredefinedTag(aPredefinedTag.get().getId()); }
	
	public TagModel getIdTag() { return getPredefinedTag(PredefinedTagType.Id); }
	public TagModel getNameTag() { return getPredefinedTag(PredefinedTagType.Name); }
	public TagModel getTitleTag() { return getPredefinedTag(PredefinedTagType.Title); }
	public TagModel getDescriptionTag() { return getPredefinedTag(PredefinedTagType.Description); }

	public void set(TagsItem aTagsItem) {
		if (null == aTagsItem) {
			clear();
		}
		else {
			set(aTagsItem.getTags());
		}
	}

	public void set(Tag[] aTags) {
		clear();
		if (null == aTags) return;
		Arrays.stream(aTags).forEach(tag -> set(tag));
		FXCollections.sort(CUSTOM_TAGS_PROPERTY.get(), Comparator.naturalOrder());
	}
	
	public void set(Tag aTag) {
		if (null == aTag) return;
		final int tagId = aTag.getId();
		final TagType tagType = TAG_TYPES_PROPERTY.get(tagId);
		if (null == tagType) {
			System.out.println(String.format("%s not found (id=%d)", TagType.class.getSimpleName(), tagId));
			return;
		}
		final TagKind tagKind = tagType.getKind();
		if (TagKind.Predefined == tagKind) {
			final TagModel predefinedTag = getPredefinedTag(tagId);
			if (null == predefinedTag) {
				System.out.println(String.format("%s not found (id=%d)", PredefinedTagType.class.getSimpleName(), tagId));
				return;
			}
			predefinedTag.setValue(aTag.getValue());
		}
		else {
			CUSTOM_TAGS_PROPERTY.add(new TagModel(tagType.getName(), aTag.getValue()));
		}
	}

	public void clear() {
		clearPredefinedTags();
		clearCustomTags();
	}

	public void clearPredefinedTags() {
		PREDEFINED_TAGS_PROPERTY.values().stream().forEach(tagModel -> tagModel.clear());
	}
	
	public void clearCustomTags() {
		CUSTOM_TAGS_PROPERTY.stream().forEach(tagModel -> tagModel.unbind());
		CUSTOM_TAGS_PROPERTY.clear();		
	}
	
}
