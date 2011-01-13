package be.koozi.product;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class OptionBean {
	public static HashMap<String, List<Option>> getOptionMap(List<Option> options) {
		HashMap<String, List<Option>> optionMap = new HashMap<String, List<Option>>();
		for (Iterator<Option> iterator = options.iterator(); iterator.hasNext();) {
			Option option = iterator.next();
			List<Option> optionList = optionMap.get(option.getKey());
			if (optionList == null) {
				optionList = new LinkedList<Option>();
				optionMap.put(option.getKey(), optionList);
			}
			optionList.add(option);
		}
		return optionMap;
	}
}
