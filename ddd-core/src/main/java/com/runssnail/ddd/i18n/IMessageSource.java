package com.runssnail.ddd.i18n;


import java.util.Locale;

/**
 * message source
 *
 * copy from spring framework
 *
 * @author zhengwei
 */
public interface IMessageSource {

    /**
     * 是否有这个code存在
     *
     * @param code
     * @param locale
     * @return
     */
    boolean contains(String code, Locale locale);

    /**
     * Try to resolve the message. Return default message if no message was found.
     *
     * @param code           the code to lookup up, such as 'calculator.noRateSet'. Users of this
     *                       class are encouraged to base message names on the relevant fully
     *                       qualified class name, thus avoiding conflict and ensuring maximum
     *                       clarity.
     * @param args           an array of arguments that will be filled in for params within the
     *                       message (params look like "{0}", "{1,date}", "{2,time}" within a
     *                       message), or {@code null} if none.
     * @param defaultMessage a default message to return if the lookup fails
     * @param locale         the locale in which to do the lookup
     * @return the resolved message if the lookup was successful; otherwise the default message
     * passed as a parameter
     * @see java.text.MessageFormat
     */
    String getMessage(String code, Object[] args, String defaultMessage, Locale locale);

    /**
     * Try to resolve the message. Treat as an error if the message can't be found.
     *
     * @param code   the code to lookup up, such as 'calculator.noRateSet'
     * @param args   an array of arguments that will be filled in for params within the message
     *               (params look like "{0}", "{1,date}", "{2,time}" within a message), or {@code
     *               null} if none.
     * @param locale the locale in which to do the lookup
     * @return the resolved message
     * @throws NoSuchMessageException if the message wasn't found
     * @see java.text.MessageFormat
     */
    String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException;

}
