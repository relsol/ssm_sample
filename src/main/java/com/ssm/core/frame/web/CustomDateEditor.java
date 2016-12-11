package com.ssm.core.frame.web;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class CustomDateEditor extends PropertyEditorSupport {
	
	private final static Logger logger = LoggerFactory.getLogger(CustomDateEditor.class);

	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
	private static final String DATE_ALLTIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	private static final String DATE_FORMAT_CH = "yyyy年MM月dd日";
	private static final String DATE_TIME_FORMAT_CH = "yyyy年MM月dd日 HH:mm";
	private static final String DATE_ALLTIME_FORMAT_CH = "yyyy年MM月dd日 HH:mm:ss";

	private DateFormat dateFormat;
	private boolean allowEmpty = true;

	public CustomDateEditor() {
	}

	public CustomDateEditor(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	public CustomDateEditor(boolean allowEmpty) {
		this.allowEmpty = allowEmpty;
	}

	public CustomDateEditor(DateFormat dateFormat, boolean allowEmpty) {
		this.dateFormat = dateFormat;
		this.allowEmpty = allowEmpty;
	}

	/**
	 * Parse the Date from the given text, using the specified DateFormat.
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (this.allowEmpty && !StringUtils.hasText(text)) {
			setValue(null);
		} else {
			SimpleDateFormat sf = null;
			Date parseDate = null;
			try {
				if (NumberUtils.isNumber(text)) {
					parseDate = new Date(Long.parseLong(text));
				} else if (this.dateFormat != null) {
					parseDate = this.dateFormat.parse(text);
				} else {
					try {
						sf = new SimpleDateFormat(DATE_ALLTIME_FORMAT);
						parseDate = sf.parse(text);
					} catch (ParseException ex) {
						try {
							sf = new SimpleDateFormat(DATE_TIME_FORMAT);
							parseDate = sf.parse(text);
						} catch (Exception e) {
							try {
								sf = new SimpleDateFormat(DATE_FORMAT);
								parseDate = sf.parse(text);
							} catch (Exception e1) {
								try {
									sf = new SimpleDateFormat(DATE_ALLTIME_FORMAT_CH);
									parseDate = sf.parse(text);
								} catch (Exception e2) {
									try {
										sf = new SimpleDateFormat(DATE_TIME_FORMAT_CH);
										parseDate = sf.parse(text);
									} catch (Exception e3) {
										sf = new SimpleDateFormat(DATE_FORMAT_CH);
										parseDate = sf.parse(text);
									}
								}
							}
						}
					}
				}
				setValue(parseDate);
			} catch (ParseException ex) {
				logger.error("setAsText:parse异常2", ex);
				throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
			}
		}
	}

	/**
	 * Format the Date as String, using the specified DateFormat.
	 */
	@Override
	public String getAsText() {
		Date value = (Date) getValue();
		DateFormat dateFormat = this.dateFormat;
		if (dateFormat == null)
			dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
		return (value != null ? dateFormat.format(value) : "");
	}

}
