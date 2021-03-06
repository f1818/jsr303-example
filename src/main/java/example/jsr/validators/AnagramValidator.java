package example.jsr.validators;

import javax.validation.ConstraintValidator;

import org.springframework.util.StringUtils;

import example.jsr.annotations.Anagram;

public abstract class AnagramValidator<K> implements ConstraintValidator<Anagram, K> {

	protected boolean isAnagram(String anagramString) {
		String value = StringUtils.trimAllWhitespace(anagramString);
		if (StringUtils.isEmpty(value))
			return false;
		
		boolean hasMiddleCharacter = value.length() % 2 != 0;
		int firstHalfStopIndex = value.length() / 2;
		int secondHalfStartIndex = hasMiddleCharacter ? value.length() / 2 + 1 : value.length() / 2;
		
		String firstHalf = value.substring(0, firstHalfStopIndex);
		String secondHalf = value.substring(secondHalfStartIndex);
		String reversedSecondHalf = new StringBuilder(secondHalf).reverse().toString();
		
		return firstHalf.equals(reversedSecondHalf);
	}

}
