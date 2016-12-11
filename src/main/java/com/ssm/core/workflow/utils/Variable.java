package com.ssm.core.workflow.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Variable {

	private List<Entry> entries;
	
	public List<Entry> getEntries() {
		return entries;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}

	public Map<String, Object> getVariableMap() {
		Map<String, Object> variableMap = new HashMap<String, Object>();
		if (entries == null || entries.isEmpty()) {
			return variableMap;
		}
		for(Entry entry : entries) {
			variableMap.put(entry.key, entry.value);
		}
		return variableMap;
	}
	public static class Entry {
		
		private String key;
		
		private Object value;
		
		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			this.value = value;
		}
		
	}
	
}
